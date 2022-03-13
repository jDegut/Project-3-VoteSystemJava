package fr.apoprojetdegut.main.extensions.SaveExcel;

import java.util.ArrayList;
import java.util.Map;

import fr.apoprojetdegut.main.Main;
import fr.apoprojetdegut.main.personne.Candidat;
import fr.apoprojetdegut.main.personne.Electeur;
/**
 * Classe SaveVotesByTime
 * @author jdegu
 *
 */
public class SaveVotesByTime {
	
	private ArrayList<ArrayList<Candidat>> data;
	/**
	 * Constructeur de SaveVotesByTime
	 */
	public SaveVotesByTime() {
		data = new ArrayList<ArrayList<Candidat>>();
	}
	/**
	 * Méthode qui permet de sauvegarder une ligne
	 */
	public void saveRow() {
		ArrayList<Candidat> voted = new ArrayList<Candidat>();
		
		for(Electeur e : Main.listElecteur) {
			voted.add(e.getWanted());
		}
		data.add(voted);
	}
	/**
	 * Méthode qui permet de retourner les lignes
	 * @return data
	 */
	public ArrayList<ArrayList<Candidat>> getData() {
		return data;
	}

}
