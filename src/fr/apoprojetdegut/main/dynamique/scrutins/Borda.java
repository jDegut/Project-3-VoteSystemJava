package fr.apoprojetdegut.main.dynamique.scrutins;

import java.util.ArrayList;
import java.util.Collections;

import fr.apoprojetdegut.main.personne.Candidat;
import fr.apoprojetdegut.main.personne.Electeur;

public class Borda extends Scrutin{
	/**
	 * Constructeur de Borda
	 * @param candListe
	 * @param electListe
	 */
	public Borda(ArrayList<Candidat> candListe, ArrayList<Electeur> electListe) {
		cands = candListe;
		elects = electListe;
	}
	/**
	 * Méthode qui applique le scrutin sans seuil
	 * @return memory
	 */
	@Override
	public Candidat appliquer() {
		for(Electeur e : elects) {
			ArrayList<Candidat> classement = e.getWantedClassement(cands);
			Collections.reverse(classement);
			for(int i = 0;i<classement.size();i++) {
				classement.get(i).addPoints(i + 1);
			}
		}
		Candidat memory = cands.get(0);
		for(Candidat c : cands) {
			if(c.getPoints() > memory.getPoints() || (c.getPoints() == memory.getPoints() && c.getAge() > memory.getAge())) {
				memory = c;
			}
		}
		return memory;
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
