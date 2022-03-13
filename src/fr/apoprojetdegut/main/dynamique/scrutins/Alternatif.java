package fr.apoprojetdegut.main.dynamique.scrutins;

import java.util.ArrayList;

import fr.apoprojetdegut.main.personne.Candidat;
import fr.apoprojetdegut.main.personne.Electeur;
/**
 * Classe Alternatif
 * @author jdegu
 *
 */
public class Alternatif extends Scrutin{
	/**
	 * Constructeur de Alternatif
	 * @param candListe
	 * @param electListe
	 */
	public Alternatif(ArrayList<Candidat> candListe, ArrayList<Electeur> electListe) {
		cands = candListe;
		elects = electListe;
	}
	/**
	 * Méthode qui applique le scrutin sans seuil
	 * @return memory
	 */
	@Override
	public Candidat appliquer() {
		ArrayList<Candidat> enLice = (ArrayList<Candidat>) cands.clone();
		boolean end = false;
		while(end == false) {
			for(Electeur e : elects) {
				ArrayList<Candidat> classement = e.getWantedClassement(enLice);
				e.setWanted(classement.get(0));
			}
			Candidat min = enLice.get(0);
			for(Candidat c : enLice) {
				if(vote(c) < vote(min) || (vote(c) == vote(min) && c.getAge() < min.getAge())) {
					min = c;
				}
			}
			enLice.remove(min);
			if(enLice.size() == 1) {
				end = true;
			}
		}
		return enLice.get(0);
	}
	/**
	 * Méthode qui applique le scrutin avec seuil (retourne vers appliquer())
	 * @return memory
	 */
	@Override
	public Candidat appliquer(Double seuil) {
		return appliquer();
	}

}
