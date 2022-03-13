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
	 * M�thode qui retourne le candidat voulu par l'�lecteur
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
	 * M�thode qui retourne la liste des candidats vot�s par les �lecteurs (pour le scrutin par Approbation)
	 * @return wanteds (liste de wanted)
	 */
	
	public ArrayList<Candidat> getWantedAppro() {
		wanteds = new ArrayList<Candidat>();
		// choix du nombre de candidats vot�s al�atoires
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
	 * M�thode qui permet d'avoir le classement de candidat de chaque �lecteur
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
	 * M�thode set Wanted
	 * @param c
	 */
	
	public void setWanted(Candidat c) {
		wanted = c;
	}
}
