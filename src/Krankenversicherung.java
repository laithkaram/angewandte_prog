

public abstract class Krankenversicherung {
	private static int ID_COUNTER = 10000;

	private String name;
	private String versicherungsNummer;


	/**
	 * Fuegt einen Patienten in die Liste, wenn freier Platz verfuegbar ist.
	 * @param name , nummer	 
	 */
	
	public Krankenversicherung(String name) {
		this.name = name;
		this.versicherungsNummer = "V-" + ID_COUNTER++;
	}


	abstract double calculateCoverage(double cost, int quarter, int previousQuarter);

	
	/**
	 * gibt den name der krankenverischrung zureuck
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Fuegt einen Patienten in die Liste, wenn freier Platz verfuegbar ist.
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
	public String getVersicherungsNummer() {
		return versicherungsNummer;
	}

	/**
	 *
	 * @param versicherungsNummer
	 */
	public void setVersicherungsNummer(String versicherungsNummer) {
		this.versicherungsNummer = versicherungsNummer;
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
			if (this.name == k.name && this.versicherungsNummer == k.versicherungsNummer) {
				return true;
			}
		}
		return false;
	}


	@Override
	public String toString() {
		return "{ Name: "+ this.name + ", Versicherungsnummer: " + this.versicherungsNummer + "}";
	}
	
	
}
