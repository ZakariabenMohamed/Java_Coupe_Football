package coupe;

import java.util.Scanner;

public class Equipe {
	int id;
	String nom;
	String couleur;
	int victoires;
	boolean elimine;
	String quandElimine;
	int place;

	public Equipe(int id, String nom, String couleur) {
		this.id = id;
		this.nom = nom;
		this.couleur = couleur;
		this.victoires = 0;
		this.elimine = false;
		this.quandElimine = "";
		this.place = 0;
	}

	void gagne() {
		victoires++;
	}

	void elimine(String tour) {
		this.elimine = true;
		this.quandElimine = tour;
	}

	void affiche() {
		System.out.println(id + ". " + nom + " (" + couleur + ")");
		System.out.println("   Gagné: " + victoires + " matchs");
		if (elimine) {
			System.out.println("   Éliminé en: " + quandElimine);
		}
		if (place > 0) {
			System.out.println("   Place: " + place + "ème");
		}
	}
}