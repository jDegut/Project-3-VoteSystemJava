package fr.apoprojetdegut.main.dynamique;

import java.util.ArrayList;
import java.util.Random;

import fr.apoprojetdegut.main.Main;
import fr.apoprojetdegut.main.dynamique.scrutins.Scrutin;
import fr.apoprojetdegut.main.personne.Candidat;
import fr.apoprojetdegut.main.personne.Electeur;
/**
 * Classe Sondage
 * @author jdegu
 *
 */
public class Sondage {

	private static int id = 0;
	
	private ArrayList<Candidat> cands;
	private ArrayList<Electeur> elects;
	private ArrayList<Double> percentages;
	private Election e;
	/**
	 * Constructeur de Sondage
	 * @param el
	 */
	public Sondage(Election el) {
		
		Sondage.id++;
		this.cands = Main.listCandidat;
		this.elects = new ArrayList<Electeur>();
		this.percentages = new ArrayList<Double>();
		this.e = el;
		
		double n = Election.genererR(0.25*Main.listElecteur.size(),0.75*Main.listElecteur.size());
		int taille = (int) n;
		for(int i=0;i<taille;i++) {
			Random randomizer = new Random();
			int index = randomizer.nextInt(Main.listElecteur.size());
			if(elects.contains(Main.listElecteur.get(index)) && i != 0) {
				i--;
			} else {
				elects.add(Main.listElecteur.get(index));
			}
		}
		dynamique();
	}
	/**
	 * Méthode déclenchant la dynamique du sondage
	 */
	private void dynamique() {
		
		ArrayList<Double> votesCand = new ArrayList<Double>();
		Scrutin s = e.getScrutin();
		
		System.out.println("#############################################################\n");
		System.out.println("Résultat du Sondage n°" + id + "\n");
		Double abstention = 1.0;
		for(Candidat c : cands) {
			Double voies = ((double) s.vote(c)) / Main.listElecteur.size();
			votesCand.add(voies);
			abstention -= voies;
			percentages.add(voies*100);
			System.out.println("Candidat : " + c.getNom() + " - " + voies*100 + "% de votes\n");
		}
		if(abstention < 0) {
			abstention = 0.0;
		}
		percentages.add(abstention*100);
		System.out.println("Asbtentions : " + abstention*100 + "% de votes\n");
		
		for(Electeur e : elects) {

			Candidat memory = cands.get(0);
			for(Candidat c : cands) {
				
				if(e.utility(c,votesCand.get(cands.indexOf(c))) > e.utility(memory,votesCand.get(cands.indexOf(memory)))) {
					memory = c;
				}
			}
			
			ArrayList<Double> newInd = new ArrayList<Double>();
			ArrayList<Double> oldInd = e.getInd();
			ArrayList<Double> cInd = memory.getInd();
			
			// Code de modification des indicateurs proportionnellement à l'utilité mais ne fonctionne pas car utility peut être supérieur à 1 et 1-factor > 1
			/*for(int i=0;i<oldInd.size();i++) {
				Double factor = e.utility(memory,votesCand.get(cands.indexOf(memory)));
				newInd.add((cInd.get(i) * factor) + (oldInd.get(i)) * (1 - factor));
			}*/
			
			// Choix de l'option 2 du sondage => comparer les utilités et rapprocher l'électeur du candidat avec lequel l'utilité est la plus élevée
			for(int i=0;i<oldInd.size();i++) {
				newInd.add((cInd.get(i) * 0.5) + (oldInd.get(i) * 0.5));
			}
			
			e.setInd(newInd);
		}
	}
	/**
	 * Méthode get Percentages
	 * @return percentages
	 */
	public ArrayList<Double> getPercentages() {
		return this.percentages;
	}
}
