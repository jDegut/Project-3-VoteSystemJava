package fr.apoprojetdegut.main.dynamique.scrutins;

import java.util.ArrayList;

import fr.apoprojetdegut.main.personne.Candidat;
import fr.apoprojetdegut.main.personne.Electeur;

public abstract class Scrutin {
	
	protected ArrayList<Candidat> cands;
	protected ArrayList<Electeur> elects;
	
	public Scrutin() {}
	/**
	 * M�thode qui applique le scrutin sans seuil
	 * @return candidat
	 */
	public abstract Candidat appliquer();
	/**
	 * M�thode qui applique le scrutin avec seuil
	 * @param seuil
	 * @return
	 */
	public abstract Candidat appliquer(Double seuil);
	
	/**
	 * M�thode qui d�termine le nombre de vote d'un candidat
	 * @param c
	 * @return res
	 */
	public int vote(Candidat c) {
		int res = 0;
		for(Electeur e : elects) {
			if(e.getWanted() == c) {
				res++;
			}
		}
		return res;
	}
	
	/**
	 * M�thode qui retourne la liste du nombre de vote de chaque candidat
	 * @return res
	 */
	public ArrayList<Integer> vote() {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for(Candidat c : cands) {
			int nb_vote = 0;
			for(Electeur e : elects) {
				if(e.getWantedAppro().contains(c)) {
					nb_vote++;
				}
			}
			res.add(nb_vote);
		}
		return res;
	}

}
