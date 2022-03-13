package fr.apoprojetdegut.main.dynamique;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import fr.apoprojetdegut.main.Main;
import fr.apoprojetdegut.main.dynamique.scrutins.Alternatif;
import fr.apoprojetdegut.main.dynamique.scrutins.Appro;
import fr.apoprojetdegut.main.dynamique.scrutins.Borda;
import fr.apoprojetdegut.main.dynamique.scrutins.Majo;
import fr.apoprojetdegut.main.dynamique.scrutins.Scrutin;
import fr.apoprojetdegut.main.extensions.SaveExcel.SaveFile;
import fr.apoprojetdegut.main.extensions.SaveExcel.SaveSondageByTime;
import fr.apoprojetdegut.main.extensions.SaveExcel.SaveVotesByTime;
import fr.apoprojetdegut.main.personne.Candidat;
import fr.apoprojetdegut.main.personne.Electeur;
import fr.apoprojetdegut.main.personne.Personne;
/**
 * Classe Election
 * @author jdegu
 *
 */
public class Election {
	
	private static int id = 0;
	
	private ArrayList<Candidat> candidats;
	private ArrayList<Electeur> electeurs;
	private String mode;
	private Double seuil;
	private int nb_sondage;
	
	private Scrutin s;
	private Candidat fin;
	private SaveVotesByTime saveV;
	private SaveSondageByTime saveS;
	
	/**
	 * Constructeur de Election
	 * @param cands
	 * @param elects
	 * @param modeScrutin
	 * @param seuilMajo
	 * @param nbx
	 */
	
	public Election(ArrayList<Candidat> cands, ArrayList<Electeur> elects, String modeScrutin, Double seuilMajo, int nbx) {
		
		id++;
		
		candidats = cands;
		electeurs = elects;
		mode = modeScrutin;
		seuil = seuilMajo;
		nb_sondage = nbx;
		saveV = new SaveVotesByTime();
		saveS = new SaveSondageByTime();
		
		if(mode == "majo") {
			s = new Majo(cands,elects);
		} else if(mode == "appro") {
			s = new Appro(cands,elects);
		} else if(mode == "alternatif") {
			s = new Alternatif(cands,elects);
		} else if(mode == "borda") {
			s = new Borda(cands,elects);
		}
		
		fin = declencher();
		Main.listElection.add(this);
	}
	
	/**
	 * Méthode qui déclenche l'élection
	 * @return fin (candidat élu)
	 */
	public Candidat declencher() {
		ArrayList<Integer> ind_sondages = new ArrayList<Integer>();
		
		for(int i=1;i<=nb_sondage;i++) {
			ind_sondages.add((int) (Main.listElecteur.size() / nb_sondage) * i);
		}
		
		for(int i=0;i<electeurs.size();i++) {
			int indice = (int) genererR(0,electeurs.size()-1);
			Electeur e = electeurs.get(indice);
			
			ArrayList<Personne> listTotal = new ArrayList<Personne>();
			listTotal.addAll(electeurs); listTotal.addAll(candidats); Collections.shuffle(listTotal);
			indice = (int) genererR(0,listTotal.size());
			Personne p = listTotal.get(indice);
			while(p == e) {
				indice = (int) genererR(0,listTotal.size());
				p = listTotal.get(indice);
			}
			
			if(p.apetance(e) < 0.5 * Math.sqrt(Main.list.size())) {
				ArrayList<Double> newInd = new ArrayList<Double>();
				ArrayList<Double> oldInd = p.getInd();
				ArrayList<Double> eInd = e.getInd();
				
				for(int j=0;j<oldInd.size();j++) {
					// Facteurs de 0.25 et 0.75 choisis arbitrairement
					newInd.add((eInd.get(j) * 0.25) + (oldInd.get(j) * 0.75));
				}
				p.setInd(newInd);
			} else {
				ArrayList<Double> newInd = new ArrayList<Double>();
				ArrayList<Double> oldInd = p.getInd();
				ArrayList<Double> eInd = e.getInd();

				for(int j=0;j<oldInd.size();j++) {
					
					// Facteur de 0.25 choisi arbitrairement
					if(oldInd.get(j) <= eInd.get(j)) {
						newInd.add(oldInd.get(j) - (eInd.get(j) * 0.25 *  oldInd.get(j)));
					} else {
						newInd.add(oldInd.get(j) + (eInd.get(j) * 0.25 * (1 - oldInd.get(j))));
					}
				}
				p.setInd(newInd);
			}
			
			if(nb_sondage > 0 && ind_sondages.contains(i)) {
				Sondage s = new Sondage(this);
				saveS.saveRow(s);
				nb_sondage--;
			}
			
			saveV.saveRow();
		}
		elect(s);
		try {
			SaveFile.save(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fin;
	}
	
	/**
	 * Méthode qui éli le candidat
	 * @param s
	 */
	public void elect(Scrutin s) {
		if(s instanceof Majo) {
			fin = s.appliquer(seuil);
			if(seuil != 0.0) {
				seuil = 0.0;
				fin = declencher();
			} 
		} else if(s instanceof Alternatif || s instanceof Borda || s instanceof Appro) {
			fin = s.appliquer();
		}
	}
	/**
	 * Méthode de classe permettant de générer un entier aléatoire entre deux bornes
	 * @param borneInf
	 * @param borneSup
	 * @return nb
	 */
	public static double genererR(double borneInf, double borneSup){
	   Random random = new Random();
	   double nb;
	   nb = random.nextDouble() * (borneSup-borneInf) + borneInf;
	   return nb;
	}
	/**
	 * Méthode get Id
	 * @return id
	 */
	
	public int getId() {
		return id;
	}
	/**
	 * Méthode get Fin
	 * @return fin
	 */
	
	public Candidat getFin() {
		return fin;
	}
	/**
	 * Méthode get Mode
	 * @return mode
	 */
	
	public String getMode() {
		return mode;
	}
	/**
	 * Méthode get Scrutin
	 * @return s
	 */
	
	public Scrutin getScrutin() {
		return s;
	}
	/**
	 * Méthode get SaveV
	 * @return saveV
	 */
	
	public SaveVotesByTime getSaveV() {
		return saveV;
	}
	/**
	 * Méthode get SaveS
	 * @return saveS
	 */
	
	public SaveSondageByTime getSaveS() {
		return saveS;
	}
	/**
	 * Méthode get NbSondage
	 * @return nb_sondage
	 */
	
	public int getNbSondage() {
		return nb_sondage;
	}
	
}
