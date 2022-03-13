package fr.apoprojetdegut.main.personne;

import java.util.ArrayList;
/**
 * Classe Personne
 * @author jdegu
 *
 */
public abstract class Personne {

	protected String nom;
	protected ArrayList<Double> ind;
	/**
	 * Constructeur abstrait de Personne
	 * @param n
	 */
	public Personne(String n) {
		nom = n;
		ind = new ArrayList<Double>();
	}
	/**
	 * Méthode qui donne l'apétance entre la personne et une autre
	 * @param p
	 * @return apetance
	 */
	public Double apetance(Personne p) {
		if(p instanceof Candidat) {
			p = (Candidat) p;
		}else if(p instanceof Electeur) {
			p = (Electeur) p;
		}
		ArrayList<Double> indc = p.getInd();
		Double vector = 0.0;
		for(int i=0; i<indc.size();i++) {
			Double d = indc.get(i) - ind.get(i);
			vector = vector + (d * d);
		}
		
		return Math.sqrt(vector);
	}
	/**
	 * Méthode qui retourne l'utilité entre la personne et une autre personne
	 * @param p
	 * @param percent
	 * @return l'utilité
	 */
	public Double utility(Personne p, Double percent) {
		if(p instanceof Candidat) {
			p = (Candidat) p;
		}else if(p instanceof Electeur) {
			p = (Electeur) p;
		}
		return (1 / apetance(p)) * percent;
	}
	/**
	 * Méthode get nom
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Méthode set Nom
	 * @param n
	 */
	public void setNom(String n) {
		nom = n;
	}
	/**
	 * Méthode get Ind
	 * @return ind
	 */
	public ArrayList<Double> getInd() {
		return ind;
	}
	/**
	 * Méthode set Ind
	 * @param inds
	 */
	public void setInd(ArrayList<Double> inds) {
		ind = inds;
	}
}
