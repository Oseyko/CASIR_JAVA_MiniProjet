package App;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Application {

	private static ArrayList<Etudiant> listEtudiants;

	public static ArrayList<Etudiant> createList() {

		final DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();
		listEtudiants = new ArrayList<Etudiant>();

		try {

			final DocumentBuilder builder = factory.newDocumentBuilder();

			final Document document = builder.parse(new File("etudiants.xml"));

			final Element racine = document.getDocumentElement();

			System.out.println("\n*************RACINE************");

			System.out.println(racine.getNodeName());

			final NodeList racineNoeuds = racine.getChildNodes();

			final int nbRacineNoeuds = racineNoeuds.getLength();

			for (int i = 0; i < nbRacineNoeuds; i++) {

				if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {

					final Element etudiant = (Element) racineNoeuds.item(i);
					
					// Récupération des valeurs

					final Element nom = (Element) etudiant
							.getElementsByTagName("nom").item(0);

					final Element prenom = (Element) etudiant
							.getElementsByTagName("prenom").item(0);

					final Element groupe = (Element) etudiant
							.getElementsByTagName("groupe").item(0);
					// Creation de l'objet Etudiant
					Etudiant etu = new Etudiant();
					etu.setNom(nom.getTextContent());
					etu.setPrenom(prenom.getTextContent());
					etu.setGroupe(groupe.getTextContent());
					listEtudiants.add(etu);

				}

			}
		}

		catch (final ParserConfigurationException e) {
			e.printStackTrace();
		}

		catch (final SAXException e) {
			e.printStackTrace();
		}

		catch (final IOException e) {
			e.printStackTrace();
		}
		//System.out.println(listEtudiants);
		return listEtudiants;

	}
	

	public static boolean updateXML(String textOut){
		File out = new File(textOut);
		ArrayList<Etudiant> listEtu = createList(); 
		boolean result= true;
		 DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();
		
		try {
			DocumentBuilder builderWrite = factory.newDocumentBuilder();

			Document document = builderWrite.newDocument();

			 Element etudiants = document.createElement("etudiants");
			
			
			 // Use a Transformer for output
		    TransformerFactory tFactory =  TransformerFactory.newInstance();
		    @SuppressWarnings("unused")
			Transformer transformer =  tFactory.newTransformer();
		    @SuppressWarnings("unused")
		    DOMSource source = new DOMSource(document);
		   
			for (int i=0;i<listEtu.size();i++)
			{
				// Remplir cet arbre avec la liste des Etudiants
				Etudiant e = (Etudiant)(listEtu.get(i));
				Element etudiant = document.createElement("etudiant");
				
						
				Element id = document.createElement("id");
				Text t_id = document.createTextNode((String.valueOf(e.getId())));
				id.appendChild(t_id);
				etudiant.appendChild(id);
				
				
				Element nom = document.createElement("nom");
				Text t_nom = document.createTextNode(e.getNom());
				nom.appendChild(t_nom);
				etudiant.appendChild(nom);
				
				Element prenom = document.createElement("prenom");
				Text t_prenom = document.createTextNode(e.getPrenom());
				prenom.appendChild(t_prenom);
				etudiant.appendChild(prenom);
				
				Element groupe = document.createElement("groupe");
				Text t_groupe = document.createTextNode(e.getGroupe());
				groupe.appendChild(t_groupe);
				etudiant.appendChild(groupe);
				
				etudiants.appendChild(etudiant);
			}
			document.appendChild(etudiants);

			 Transformer xformer = TransformerFactory.newInstance().newTransformer();
			    xformer.setOutputProperty(OutputKeys.METHOD, "xml");
			    xformer.setOutputProperty(OutputKeys.INDENT, "yes");
			    xformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			    Source masource = new DOMSource(document);
			    Result monresult = new StreamResult(out);
			    xformer.transform(masource, monresult);
			
//			StreamResult s_result = new StreamResult(out);
	//		    transformer.transform(source, s_result);
				
		} catch (Exception e) {return false;}

		return result;
	}
}