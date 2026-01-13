package coupe;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Tournoi tournoi = new Tournoi();
		boolean run = true;

		System.out.println("=== COUPE DE FOOT ===");

		while (run) {
			System.out.println("\n=== MENU ===");
			System.out.println("1. Voir équipes");
			System.out.println("2. Créer matchs (1/8)");
			System.out.println("3. Voir calendrier");
			System.out.println("4. Jouer matchs");
			System.out.println("5. Équipes en jeu");
			System.out.println("6. Équipes éliminées");
			System.out.println("7. Classement");
			System.out.println("8. Quitter");
			System.out.print("Choix: ");

			int choix = sc.nextInt();

			switch (choix) {
			case 1:
				tournoi.afficheEquipes();
				break;

			case 2:
				tournoi.creerHuitiemes();
				break;

			case 3:
				tournoi.afficheCalendrier();
				break;

			case 4:
				System.out.println("Jouer quel tour?");
				System.out.println("1. 1/8  2. 1/4  3. 1/2  4. Finale  5. 3e place");
				int tour = sc.nextInt();

				if (tour == 1)
					tournoi.jouerTour("1/8");
				else if (tour == 2)
					tournoi.jouerTour("1/4");
				else if (tour == 3)
					tournoi.jouerTour("1/2");
				else if (tour == 4)
					tournoi.jouerTour("Finale");
				else if (tour == 5)
					tournoi.jouerTour("3e place");
				break;

			case 5:
				tournoi.afficheEnJeu();
				break;

			case 6:
				tournoi.afficheElimines();
				break;

			case 7:
				tournoi.afficheClassement();
				break;

			case 8:
				run = false;
				System.out.println("Bye!");
				break;

			default:
				System.out.println("Mauvais choix!");
			}

			if (run) {
				System.out.print("\nEntrée pour continuer...");
				sc.nextLine();
				sc.nextLine();
			}
		}

		sc.close();
	}
}