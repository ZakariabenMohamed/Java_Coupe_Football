package coupe;

import java.util.Scanner;
import java.util.Scanner;

public class Match {
	Equipe e1;
	Equipe e2;
	Equipe gagnant;
	String tour;
	boolean fait;

	public Match(Equipe e1, Equipe e2, String tour) {
		this.e1 = e1;
		this.e2 = e2;
		this.tour = tour;
		this.gagnant = null;
		this.fait = false;
	}

	void jouer() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n" + tour + ": " + e1.nom + " vs " + e2.nom);
		System.out.print("Qui gagne? (1=" + e1.nom + ", 2=" + e2.nom + "): ");

		int choix = sc.nextInt();
		if (choix == 1) {
			gagnant = e1;
		} else if (choix == 2) {
			gagnant = e2;
		} else {
			System.out.println("Mauvais choix! Match annulé.");
			return;
		}

		fait = true;
		gagnant.gagne();

		// Trouve le perdant
		Equipe perdant = (gagnant == e1) ? e2 : e1;
		perdant.elimine(tour);

		System.out.println(gagnant.nom + " gagne!");
	}

	void affiche() {
		if (fait) {
			System.out.println(tour + ": " + e1.nom + " " + gagnant.nom + " vs " + e2.nom);
		} else {
			System.out.println(tour + ": " + e1.nom + " vs " + e2.nom + " (à jouer)");
		}
	}
}