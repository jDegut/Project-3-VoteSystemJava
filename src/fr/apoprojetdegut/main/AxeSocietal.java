package fr.apoprojetdegut.main;

import java.util.ArrayList;

/**
 * Classe AxeSocietal
 * @author jdegu
 *
 */
public class AxeSocietal {
	
	private String name;
	/**
	 * Constructeur de AxeSocietal
	 * @param n
	 */
	public AxeSocietal(String n) {
		name = n;
		Main.list.add(this);
	}
	/**
	 * Méthode Get Name
	 * @return name
	 */

	public String getName() {
		return name;
	}
		
}
