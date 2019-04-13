import java.util.Arrays;

public class Krankenhaus {

	public static final int MAX_PATIENTEN = 50;
	
	private String name;
	private String ust_idnr;
	private Adresse adresse;
	private Patient[] patienten;

	public Krankenhaus(String name, String ust_idnr, Adresse adresse) throws Exception {
		super();
		this.name = name;
		if (ust_idnr == null) {
			throw new Exception("Die Umsatzstuer-Identifikationsnummer darf nicht leer sein.");
		}
		else {
			this.ust_idnr = ust_idnr;
		}
	
		this.adresse = adresse;
		this.patienten = new Patient[MAX_PATIENTEN];
	}

	/**
	 * Fuegt einen Patienten in die Liste, wenn freier Platz verfuegbar ist.
	 * @param patient
	 * @return true, wenn erfolgreich hinzugefuegt, ansonsten false
	 */
	public boolean addPatient(Patient patient) {
		for(int i = 0; i < this.patienten.length; i++) {
			if (this.patienten[i] == null) {
				this.patienten[i] = patient;
				return true;
			}
		}
		System.out.println("Kein Platz mehr für neue Patienten.");
		return false;
	}
	
	public boolean removePatient(Patient patient) {
		for(int i = 0; i < this.patienten.length; i++) {
			if (this.patienten[i].equals(patient)) {
				this.patienten[i] = null;
				return true;
			}
		}
		System.out.println("Patient nicht gefunden.");
		return false;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUstIdnr() {
		return ust_idnr;
	}

	public void setUstIdnr(String ust_idnr) {
		this.ust_idnr = ust_idnr;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	
	
	@Override
	public String toString() {
		return "{ name: " + name + ", ust_idnr: " + ust_idnr + ", adresse: " + adresse + "}";
	}
	
	
}