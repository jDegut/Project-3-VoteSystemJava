package fr.apoprojetdegut.main;

import java.util.ArrayList;

import fr.apoprojetdegut.main.dynamique.Election;
import fr.apoprojetdegut.main.personne.Candidat;
import fr.apoprojetdegut.main.personne.Electeur;
/**
 * Classe Main
 * @author jdegu
 *
 */
public class Main {
	
	public static ArrayList<AxeSocietal> list = new ArrayList<AxeSocietal>();
	public static ArrayList<Candidat> listCandidat = new ArrayList<Candidat>();
	public static ArrayList<Electeur> listElecteur = new ArrayList<Electeur>();
	public static ArrayList<Election> listElection = new ArrayList<Election>();

	/**
	 * Méthode main
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		java.util.Scanner entree =   new java.util.Scanner(System.in);
		
		System.out.println("#############################################################\n");
		System.out.println("Programme de simulation électorale\n");
		System.out.println("#############################################################\n");
		
		System.out.println("Pour commencer, veuillez indiquer le nombre d'axe(s) sociétal(aux) voulu(s) :");
		int totalAxe = entree.nextInt();
		while(totalAxe > 0) {
			System.out.println("Nom de l'axe sociétal à ajouter :");
			String name = entree.next();
			AxeSocietal a = new AxeSocietal(name);
			totalAxe--;
		}
		
		System.out.println("Ensuite, veuillez indiquer la taille de votre population (attention, plus le nombre est élevé, plus l'élection sera longue) :");
		int totalElect = entree.nextInt();
		
		while(totalElect != 0) {
			Electeur e = new Electeur();
			totalElect--;
		}
		
		System.out.println("Pour continuer, veuillez indiquer le nombre de candidat(e)(s) voulu :");
		int totalCand = entree.nextInt();
		while(totalCand > 0) {
			System.out.println("Nom du candidat à ajouter :");
			String name = entree.next();
			Candidat c = new Candidat(name);
			totalCand--;
		}
		
		System.out.println("Puis veuillez indiquer le nombre de sondage voulu pendant les élections :");
		int nb_sondage = entree.nextInt();
		
		System.out.println("Pour finir, veuillez indiquer le type de scrutin voulu (0 : majo/1 : appro/2 : alternatif/3 : borda) :");
		int modeScrutin = entree.nextInt();
		String mode = "";
		if(modeScrutin == 0) {
			mode = "majo";
		} else if(modeScrutin == 1) {
			mode = "appro"; 
		} else if(modeScrutin == 2) {
			mode = "alternatif";
		} else if(modeScrutin == 3) {
			mode = "borda";
		}
		
		System.out.println("Seuil pour candidats (seulement pour majo 2 tours, mettre 0 pour le reste) :");
		Double majoSeuil = entree.nextDouble();
		
		Election e = new Election(listCandidat,listElecteur,mode,majoSeuil,nb_sondage);
		
		System.out.println("#############################################################\n");
		System.out.println("Résultat de la simulation électorale : " + e.getFin().getNom() + " est élu(e) après application du scrutin " + e.getMode() + " avec " + listElecteur.size() + " votes effectués.");
		
	}

}
