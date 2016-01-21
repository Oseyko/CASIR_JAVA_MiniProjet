package App;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ListEtuHandler extends DefaultHandler{
	
	private static String HTMLToReturn = ""; 
	private static int id = -1; 
	
	public ListEtuHandler() {
	}

	public static String getCodeHtml(){
		return HTMLToReturn ; 
	}
	
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		super.characters(arg0, arg1, arg2);
	}

	public void endDocument() throws SAXException {
		super.endDocument();
	}
	
	public void endElement(String uri, String local, String qName)
			throws SAXException {
		
		if(qName.toUpperCase().equals("TITRE")){
			HTMLToReturn+="</title></head>" ;
		}
		else if(qName.toUpperCase().equals("LISTE")){
			HTMLToReturn+="</table>";
		}
		else if(qName.toUpperCase().equals("FORMULAIRE")){
			HTMLToReturn+="</form>";
		}
		else if(qName.toUpperCase().equals("INPUT")){
			HTMLToReturn += "</input></br>";
		}
	}


	public void startDocument() throws SAXException {
		super.startDocument();
	}


	public void startElement(String uri, String local, String qName,
			Attributes attr) throws SAXException {
		if(qName.toUpperCase().equals("content")){
			HTMLToReturn = "" ;
		}
		if(qName.toUpperCase().equals("titre")){
			HTMLToReturn+="<head><title>"+attr.getValue("value") ;
		}
		else if(qName.toUpperCase().equals("tableauEtudiant")){
			HTMLToReturn+="<table>";
			HTMLToReturn+="<tr><th>Nom</th><th>Prenom</th><th>Groupe</th></tr>";
			for (Etudiant etu : Application.createList()) {
				HTMLToReturn+="<tr onclick=\"++\">" ;
				HTMLToReturn+="<td><a href=\"detail.html?"+etu.getId()+"\">"+etu.getNom()+"</a></td>";
				HTMLToReturn+="<td>"+etu.getPrenom()+"</td>";
				HTMLToReturn+="<td>"+etu.getGroupe()+"</td>";
				HTMLToReturn+="<tr>";
			}
		}
		else if(qName.toUpperCase().equals("FORMULAIRE")){
			HTMLToReturn+="<form method=\"GET\">";
		}
		System.out.println(HTMLToReturn);
		/*else if(qName.toUpperCase().equals("INPUT")){
			String value = ""; 
			switch (attr.getValue("name")){
			case "id" :{
				value = JeuxEtudiant.getEtudiantbyId(idEtudiant).getId()+"";
				break;
			}
			case "prenom" :{
				value = JeuxEtudiant.getEtudiantbyId(idEtudiant).getPrenom();
				break;
			}
			case "nom" :{
				value = JeuxEtudiant.getEtudiantbyId(idEtudiant).getNom();
				break;
			}
			case "groupe" :{
				value = JeuxEtudiant.getEtudiantbyId(idEtudiant).getGroupe();
				break;
			}
				
				
			}
			HTMLToReturn+="<input name=\""+attr.getValue("name")+"\" value=\""+value+"\">";
		}*/
		
		
	}
}
