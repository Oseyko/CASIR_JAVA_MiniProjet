package classes;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class HTTP extends Thread
{
	private Socket s;
	public HTTP(Socket sock) 
	{
		super();
		this.s = sock;
	}
	public void run()
	{
		
		try
		{
			InputStream is = this.s.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "US-ASCII"));
			String rq = br.readLine();
			System.out.println(rq);
			if ((rq != null) && (rq.startsWith("GET")))
			{
				System.out.println("Connexion recue");
				boolean hasParams = rq.indexOf("?")!=-1;
				String page ; 
				if(hasParams){
					page = rq.substring(rq.indexOf(' ')+2).split("\\?")[0];
					String params = rq.substring(rq.indexOf("?")).split(" ")[1];
					System.out.println(params);
					if(page == "detail.html"){
						ListEtuHandler.setIdEtudiant(Integer.parseInt(params));
					}
				}
				else {
					page = rq.substring(rq.indexOf(' ')+2).split(" ")[0];
				}
				
				System.out.println(page);
				//				File f = new File(rq.substring(rq.indexOf(' ')+2).split(" ")[0]);

				String output = "";

				output = AnalyseSax.getAnalyzedSax("src/"+page);
				this.s.getOutputStream().write(output.getBytes());
				this.s.getOutputStream().flush();

			}
			br.close();
		} catch (IOException e) {}
		try {this.s.close();} catch (IOException e) {}
	}
}