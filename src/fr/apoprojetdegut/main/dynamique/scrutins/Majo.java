package fr.apoprojetdegut.main.dynamique.scrutins;

import java.util.ArrayList;

import fr.apoprojetdegut.main.personne.Candidat;
import fr.apoprojetdegut.main.personne.Electeur;
/**
 * Classe Majo
 * @author jdegu
 *
 */
public class Majo extends Scrutin{
	/**
	 * Constructeur de Majo
	 * @param candListe
	 * @param electListe
	 */
	public Majo(ArrayList<Candidat> candListe, ArrayList<Electeur> electListe) {
		cands = candListe;
		elects = electListe;
	}
	/**
	 * Méthode qui applique le scrutin sans seuil
	 * @return memory
	 */
	@Override
	public Candidat appliquer() {
		Candidat memory = cands.get(0);
		for(Candidat c : cands) {
			int votes = vote(c);
			if(votes > vote(memory) || (votes == vote(memory) && c.getAge() > memory.getAge())) {
				memory = c;
			}
		}
		return memory;
	}
	/**
	 * Méthode qui applique le scrutin avec seuil
	 * @return memory
	 */
	@Override
	public Candidat appliquer(Double percentage) {
		Candidat memory = null;
		if(percentage == 0.0) {
			memory = appliquer();
		} else {
			ArrayList<Candidat> res = new ArrayList<Candidat>();
			for(Candidat c : cands) {
				int votes = vote(c);
				if(votes >= elects.size() * percentage) {
					res.add(c);
				}
			}
			if(res.size() == 0) {
				return null;
			}
			this.cands = res;
			for(Candidat c : res) {
			}
		}
		return memory;
	}
}
