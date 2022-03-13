package fr.apoprojetdegut.main.personne;

import java.util.ArrayList;
import java.util.Random;

import fr.apoprojetdegut.main.AxeSocietal;
import fr.apoprojetdegut.main.Main;
import fr.apoprojetdegut.main.dynamique.Election;
/**
 * Classe Candidat
 * @author jdegu
 *
 */
public class Candidat extends Personne{
	
	private int points;
	private int age;
	/**
	 * Constructeur de Candidat
	 * @param n
	 */
	public Candidat(String n) {
		
		super(n);
		points = 0;
		age = (int) Election.genererR(18,75);
		
		for(AxeSocietal a : Main.list) {
			ind.add((new Random()).nextDouble());
		}
		
		Main.listCandidat.add(this);
		
	}
	/**
	 * Méthode get Nom
	 * @return nom
	 */
	public String getNom() {
		return nom;
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
	/**
	 * Méthode get Points
	 * @return
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * Méthode add Points
	 * @param p
	 */
	public void addPoints(int p) {
		points = points + p;
	}
	/**
	 * Méthode get Age
	 * @return
	 */
	public int getAge() {
		return age;
	}
}
