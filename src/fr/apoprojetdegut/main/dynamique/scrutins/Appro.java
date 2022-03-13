package fr.apoprojetdegut.main.dynamique.scrutins;

import java.util.ArrayList;

import fr.apoprojetdegut.main.personne.Candidat;
import fr.apoprojetdegut.main.personne.Electeur;
/**
 * Classe Appro
 * @author jdegu
 *
 */
public class Appro extends Scrutin{
	/**
	 * Constructeur de Appro
	 * @param candListe
	 * @param electListe
	 */
	public Appro(ArrayList<Candidat> candListe, ArrayList<Electeur> electListe) {
		cands = candListe;
		elects = electListe;
	}
	/**
	 * Méthode qui applique le scrutin sans seuil
	 * @return memory
	 */
	@Override
	public Candidat appliquer() {
		ArrayList<Integer> votes = vote();
		Candidat res = cands.get(0);
		for(Candidat c : cands) {
			if(votes.get(cands.indexOf(c)) > votes.get(cands.indexOf(res)) || (votes.get(cands.indexOf(c)) == votes.get(cands.indexOf(res)) && c.getAge() > res.getAge())) {
				res = c;
			}
		}
		return res;
	}
	/**
	 * Méthode qui applique le scrutin avec seuil (retourne vers appliquer())
	 * @return memory
	 */
	@Override
	public Candidat appliquer(Double seuil) {
		return null;
	}
}
