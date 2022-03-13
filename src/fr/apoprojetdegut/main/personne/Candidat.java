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
	 * M�thode get Nom
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * M�thode get Ind
	 * @return ind
	 */
	public ArrayList<Double> getInd() {
		return ind;
	}
	/**
	 * M�thode set Ind
	 * @param inds
	 */
	public void setInd(ArrayList<Double> inds) {
		ind = inds;
	}
	/**
	 * M�thode get Points
	 * @return
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * M�thode add Points
	 * @param p
	 */
	public void addPoints(int p) {
		points = points + p;
	}
	/**
	 * M�thode get Age
	 * @return
	 */
	public int getAge() {
		return age;
	}
}
