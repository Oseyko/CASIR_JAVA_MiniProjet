package App;

import java.util.ArrayList;

public class Etudiant {
	private static int ID = 0;
	private int id;
	private String nom;
	private String prenom;
	private String groupe;

	public Etudiant() {
		Etudiant.ID++;
		this.id = Etudiant.ID;
		this.nom = "";
		this.prenom = "";
		this.groupe = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}
	
	public Etudiant getById(int id){
		ArrayList<Etudiant> lesEtu = Application.createList();
		for(Etudiant etudiant : lesEtu){
			if(etudiant.getId() == id){
				return etudiant;
			}
		}
		return null;
	}
}
