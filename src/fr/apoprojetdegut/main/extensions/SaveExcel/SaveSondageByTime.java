package fr.apoprojetdegut.main.extensions.SaveExcel;

import java.util.ArrayList;

import fr.apoprojetdegut.main.dynamique.Sondage;
/**
 * Classe SaveSondageByTime
 * @author jdegu
 *
 */
public class SaveSondageByTime {
	
private ArrayList<ArrayList<Double>> percentages;
	/**
	 * Constructeur de SaveSondageByTime
	 */
	public SaveSondageByTime() {
		percentages = new ArrayList<ArrayList<Double>>();
	}
	/**
	 * Méthode qui sauvegarde une ligne
	 * @param s
	 */
	public void saveRow(Sondage s) {
		percentages.add(s.getPercentages());
	}
	/**
	 * Méthode qui retourne les lignes
	 * @return percentages
	 */
	
	public ArrayList<ArrayList<Double>> getPercentages() {
		return percentages;
	}

}
