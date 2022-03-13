package fr.apoprojetdegut.main.personne;

import java.util.ArrayList;
import java.util.Random;

import fr.apoprojetdegut.main.AxeSocietal;
import fr.apoprojetdegut.main.Main;
import fr.apoprojetdegut.main.dynamique.Election;
/**
 * Classe Electeur
 * @author jdegu
 *
 */
public class Electeur extends Personne{
		
	private Candidat wanted;
	private ArrayList<Candidat> wanteds;
	/**
	 * Constructeur de Electeur
	 */
	public Electeur() {
		
		super(null);
		
		wanted = null;
		wanteds = null;
		
		for(AxeSocietal a : Main.list) {
			ind.add((new Random()).nextDouble());
		}
		
		Main.listElecteur.add(this);

	}
	/**
	 * Méthode qui retourne le candidat voulu par l'électeur
	 * @return wanted
	 */
	public Candidat getWanted() {
		wanted = Main.listCandidat.get(0);
		for(Candidat c : Main.listCandidat) {
			if(apetance(c) >= apetance(wanted)) {
				wanted = c;
			}
		}
		if(apetance(wanted) > 0.6 * Math.sqrt(Main.list.size())) {
			wanted = null;
		}
		return wanted;
	}
	/**
	 * Méthode qui retourne la liste des candidats votés par les électeurs (pour le scrutin par Approbation)
	 * @return wanteds (liste de wanted)
	 */
	
	public ArrayList<Candidat> getWantedAppro() {
		wanteds = new ArrayList<Candidat>();
		// choix du nombre de candidats votés aléatoires
		int nb_Cand = (int) Election.genererR(0,Main.listCandidat.size());
		ArrayList<Candidat> listCand = (ArrayList<Candidat>) Main.listCandidat.clone();
		for(int i=0;i<nb_Cand;i++) {
			Candidat memory = listCand.get(0);
			for(Candidat c : listCand) {
				if(apetance(c) > apetance(memory)) {
					memory = c;
				}
			}
			wanteds.add(memory);
		}
		
		return wanteds;
	}
	/**
	 * Méthode qui permet d'avoir le classement de candidat de chaque électeur
	 * @param enLice
	 * @return wanteds (liste de wanted)
	 */
	public ArrayList<Candidat> getWantedClassement(ArrayList<Candidat> enLice) {
		ArrayList<Candidat> boucle = (ArrayList<Candidat>) enLice.clone();
		wanteds = new ArrayList<Candidat>();
		int i=boucle.size();
		while(i > 0) {
			Candidat memory = boucle.get(0);
			for(Candidat c : boucle) {
				if(apetance(c) > apetance(memory)) {
					memory = c;
				}
			}
			wanteds.add(memory);
			boucle.remove(memory);
			i--;
		}
		return wanteds;
	}
	/**
	 * Méthode set Wanted
	 * @param c
	 */
	
	public void setWanted(Candidat c) {
		wanted = c;
	}
}
