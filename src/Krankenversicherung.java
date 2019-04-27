

public class Krankenversicherung extends Object {
	private String name;
	private int nummer;


	/**
	 * Fuegt einen Patienten in die Liste, wenn freier Platz verfuegbar ist.
	 * @param name , nummer	 
	 */
	
	public Krankenversicherung(String name, int nummer) {
		this.name = name;
		this.nummer = nummer;
	}

	/**
	 * gibt den name der krankenverischrung zureuck
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Fuegt einen Patienten in die Liste, wenn freier Platz verfuegbar ist.
	 * @param patient
	 * @return true, wenn erfolgreich hinzugefuegt, ansonsten false
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * die krankenversicherungsnummer
	 * @return nummer
	 * 
	 */
	public int getNummer() {
		return nummer;
	}
	/**
	 * gibt die krankenversicherungsnummer zureck
	 * @param nummer
	 */

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	/**
	 *eine ubernehmende Override methode
	 *erzeugt dass die verischerungsnummer gleich sind dann gibt er true 
	 *ansonsten gibt er false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		else if (obj instanceof Krankenversicherung){
			Krankenversicherung k = (Krankenversicherung) obj;
			if (this.name == k.name && this.nummer == k.nummer) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Methode gibt den versicherungsnummer und name zureck und gibt wie es auf der console ausgegeben wird
	 * @return nummer , name ,der krankernversicherung
	 */
	@Override
	public String toString() {
		return "{ name: "+ this.name + ", nummer: " + this.nummer + "}";
	}
	
	
}
