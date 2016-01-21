package A;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{	
	public static void main(String[] args)
	{
		// args 0 : l'adresse locale en notation point�e
		// args 1 : le port local
		String ip = "127.0.0.1";
		String port = "80";
		
		InetSocketAddress sa = new InetSocketAddress(ip, Integer.parseInt(port));
		ServerSocket ss = null;
		
		try
		{
			ss = new ServerSocket();
			ss.bind(sa);
		} catch (IOException e) 
		{
			System.out.println(e);
		}
		while (true)
		{
			try
			{
				Socket client = ss.accept();
				HTTP thread = new HTTP(client);
				thread.start();
			} catch (IOException e) 
			{
				System.out.println(e);
			}
		}
	}
}
