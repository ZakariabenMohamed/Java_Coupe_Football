package coupe;

import java.util.*;

public class Tournoi {
	Equipe[] equipes = new Equipe[16];
	List<Equipe> enJeu = new ArrayList<>();
	List<Equipe> elimines = new ArrayList<>();
	List<Match> matchs = new ArrayList<>();

	public Tournoi() {
		creerEquipes();
	}

	void creerEquipes() {
		String[] noms = { "EST", "ESS", "CA", "CSS", "USM", "ST", "CAB", "ESM", "ASM", "JSK", "OB", "USBG", "ASG",
				"ESZ", "EOSB", "CSC" };

		String[] couleurs = { "Rouge", "Bleu", "Vert", "Jaune", "Orange", "Violet", "Rose", "Marron", "Gris", "Noir",
				"Blanc", "Turquoise", "Magenta", "Cyan", "Beige", "Or" };

		for (int i = 0; i < 16; i++) {
			equipes[i] = new Equipe(i + 1, noms[i], couleurs[i]);
			enJeu.add(equipes[i]);
		}
	}

	void afficheEquipes() {
		System.out.println("\n--- Équipes (16) ---");
		for (Equipe e : equipes) {
			System.out.println(e.id + ". " + e.nom + " (" + e.couleur + ")");
		}
	}

	void creerHuitiemes() {
		matchs.clear();
		Collections.shuffle(enJeu);

		for (int i = 0; i < 8; i++) {
			Equipe e1 = enJeu.get(i * 2);
			Equipe e2 = enJeu.get(i * 2 + 1);
			matchs.add(new Match(e1, e2, "1/8"));
		}
		System.out.println("8 matchs créés (1/8)");
	}

	void creerQuarts() {
		Collections.shuffle(enJeu);

		for (int i = 0; i < 4; i++) {
			Equipe e1 = enJeu.get(i * 2);
			Equipe e2 = enJeu.get(i * 2 + 1);
			matchs.add(new Match(e1, e2, "1/4"));
		}
		System.out.println("4 matchs créés (1/4)");
	}

	void creerDemis() {
		Collections.shuffle(enJeu);

		for (int i = 0; i < 2; i++) {
			Equipe e1 = enJeu.get(i * 2);
			Equipe e2 = enJeu.get(i * 2 + 1);
			matchs.add(new Match(e1, e2, "1/2"));
		}
		System.out.println("2 matchs créés (1/2)");
	}

	void creerFinales() {
		if (enJeu.size() >= 2) {
			// Finale
			Match finale = new Match(enJeu.get(0), enJeu.get(1), "Finale");
			matchs.add(finale);

			// Match 3e place (avec les derniers éliminés)
			if (elimines.size() >= 2) {
				Match troisieme = new Match(elimines.get(elimines.size() - 2), elimines.get(elimines.size() - 1),
						"3e place");
				matchs.add(troisieme);
			}
		}
	}

	void jouerTour(String tour) {
		System.out.println("\n--- Jouer " + tour + " ---");
		int joues = 0;

		for (Match m : matchs) {
			if (m.tour.equals(tour) && !m.fait) {
				m.jouer();
				joues++;
			}
		}

		if (joues > 0) {
			majListes();

			// Si on finit un tour, créer le suivant
			if (tour.equals("1/8") && enJeu.size() >= 8) {
				creerQuarts();
			} else if (tour.equals("1/4") && enJeu.size() >= 4) {
				creerDemis();
			} else if (tour.equals("1/2") && enJeu.size() >= 2) {
				creerFinales();
			}
		} else {
			System.out.println("Aucun match à jouer");
		}
	}

	void majListes() {
		List<Equipe> qualif = new ArrayList<>();

		for (Match m : matchs) {
			if (m.fait && m.gagnant != null) {
				if (!qualif.contains(m.gagnant)) {
					qualif.add(m.gagnant);
				}
				elimines.remove(m.gagnant);

				// Trouve le perdant
				Equipe perdant = (m.gagnant == m.e1) ? m.e2 : m.e1;
				if (!elimines.contains(perdant)) {
					elimines.add(perdant);
				}
			}
		}

		enJeu = qualif;
	}

	void afficheCalendrier() {
		System.out.println("\n--- Calendrier ---");
		if (matchs.isEmpty()) {
			System.out.println("Pas de matchs");
			return;
		}

		for (Match m : matchs) {
			m.affiche();
		}
	}

	void afficheEnJeu() {
		System.out.println("\n--- En jeu (" + enJeu.size() + ") ---");
		for (Equipe e : enJeu) {
			System.out.println("- " + e.nom);
		}
	}

	void afficheElimines() {
		System.out.println("\n--- Éliminés (" + elimines.size() + ") ---");
		for (Equipe e : elimines) {
			System.out.println("- " + e.nom + " (en " + e.quandElimine + ")");
		}
	}

	void afficheClassement() {
		System.out.println("\n--- Classement ---");

		if (!enJeu.isEmpty()) {
			System.out.println("Tournoi pas fini!");
			return;
		}

		// Trouver les 4 premières places
		Equipe champion = null, deuxieme = null, troisieme = null, quatrieme = null;

		for (Match m : matchs) {
			if (m.tour.equals("Finale") && m.fait) {
				champion = m.gagnant;
				deuxieme = (champion == m.e1) ? m.e2 : m.e1;
			}
			if (m.tour.equals("3e place") && m.fait) {
				troisieme = m.gagnant;
				quatrieme = (troisieme == m.e1) ? m.e2 : m.e1;
			}
		}

		if (champion != null) {
			champion.place = 1;
			System.out.println("1. " + champion.nom + " 🏆");
		}
		if (deuxieme != null) {
			deuxieme.place = 2;
			System.out.println("2. " + deuxieme.nom);
		}
		if (troisieme != null) {
			troisieme.place = 3;
			System.out.println("3. " + troisieme.nom);
		}
		if (quatrieme != null) {
			quatrieme.place = 4;
			System.out.println("4. " + quatrieme.nom);
		}

		System.out.println("\nÉliminés en 1/2:");
		System.out.println("Éliminés en 1/4:");
		System.out.println("Éliminés en 1/8:");
	}
}
