import java.util.Date;

/**
 * 
 * @author laithkaram
 *
 */

public class Patient extends Object {
	
	private final String patiennummer;
	private Anrede anrede;
	private String name;
	private String nachname;
	
	private Adresse adresse;
	private Krankenversicherung[] krankenversicherung;
	
	private Date geburtsdatum;
	private String telefonnummer;
	private String emailadresse;

	/**
	 * Fuegt einen Patienten in die Liste, wenn freier Platz verfuegbar ist.
	 * @param patieninnummer,anrede,name,nachname,adresse,geburtsagdatum
	 */
	public Patient(String patiennummer, Anrede anrede, String name, String nachname, Adresse adresse, Date geburtsdatum,
			String telefonnummer, String emailadresse) {
		super();
		this.patiennummer = patiennummer;
		this.anrede = anrede;
		this.name = name;
		this.nachname = nachname;
		this.adresse = adresse;
		this.geburtsdatum = geburtsdatum;
		this.telefonnummer = telefonnummer;
		this.emailadresse = emailadresse;
		this.krankenversicherung = new Krankenversicherung[5];
	}

	/**
	 * Fuegt die patiente nummer und gibt die zureck
	 * @return patientnummer
	 */
	public String getPatiennummer() {
		return patiennummer;
	}

	/**
	 * Fuegt eine anrede fue die patienten ung gibt die zureck
	 * @return anrede
	 */
	public Anrede getAnrede() {
		return anrede;
	}


	public void setAnrede(Anrede anrede) {
		this.anrede = anrede;
	}

	/**
	 * erzeugt den namen von den Patientenund gibt es zureuck
	 * @return Name, der name von dem Patient
	 */
	public String getName() {
		return name;
	}

	/**
	 * name von dem patient
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * gibt den patienten nachname
	 * @param patient
	 * @return Nachname der patient 
	 */
	public String getNachname() {
		return nachname;
	}


	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	/**
	 * erzeugt die adresse von den patienten
	 * @return adresse
	 */
	public Adresse getAdresse() {
		return adresse;
	}

	/**
	 * Fuegt einen Patienten in die Liste, wenn freier Platz verfuegbar ist.
	 * @param patient
	 * @return true, wenn erfolgreich hinzugefuegt, ansonsten false
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	/**
	 * Fuegt und pruft wie viele krankenversicherungen der patient hat .
	 * @param krankernversicherung
	 * @return true, wenn erfolgreich hinzugefuegt, ansonsten false
	 */
	public Krankenversicherung[] getKrankenversicherung() {
		return krankenversicherung;
	}

	public boolean addKrankenversicherung(Krankenversicherung krankenversicherung) {
		for(int i = 0; i < this.krankenversicherung.length; i++) {
			if (this.krankenversicherung[i] == null) {
				this.krankenversicherung[i] = krankenversicherung;
				return true;
			}
			/**
			* false,gibt die folgende INFO raus wenn der patient mehr als 5 krankenversicherungen
			*/
		}
		System.out.println("INFO: Der Patient hat bereits 5 Krankenversicherungen. Bitte entfernen Sie eins bevor Sie ein neues hinzufügen können.");
		return false;
	}

	/**
	 * Die moethode boolean erzeugt ob der patient eine krankenbversicherung hat oder nicht 
	 * @param krankenversicherung
	 * @return true, Der patient hat die ubergebene krankenversicherung
	 */
	public boolean removeKrankenversicherung(Krankenversicherung krankenversicherung) {
		for(int i = 0; i < this.krankenversicherung.length; i++) {
			if (this.krankenversicherung[i] == krankenversicherung) {
				this.krankenversicherung[i] = null;
				return true;
			}
		}
		
		System.out.println("INFO: Der Patient hat die übergebene Krankenversicherungen nicht.");
		return false;
	}
	
	
	public Date getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public String getTelefonnummer() {
		return telefonnummer;
	}

	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}

	public String getEmailadresse() {
		return emailadresse;
	}

	public void setEmailadresse(String emailadresse) {
		this.emailadresse = emailadresse;
	}
	/**
	* eine ubernehmende Override methode
	* falls zwei personen den gleiche vor und nachname haben und den gleichen geburtsagdatum 
	*
	* @param obj
	* @return ,true 
	*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Patient) {
			Patient p = (Patient) obj;
			if (this.name == p.name && this.nachname == p.nachname && this.geburtsdatum.equals(p.geburtsdatum)){
				return true;
			}
		}
		return false;
	}

	/**
	 * eine uberhnehmende Ovveride methode
	 * Beschreibung: Die Ausgabe aus der Console von Patienten mit ihren daten in dem krankenhaus
	 * @return sb.toString(),
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("{\n");
		sb.append("\tAnrede: " + this.anrede + ",\n");
		sb.append("\tname: " + this.name + ",\n");
		sb.append("\tnachname: " + this.nachname + ",\n");
		sb.append("\tadresse: "+ this.adresse + ",\n");
		sb.append("\tkrankenversicherung:");
		sb.append("[\n");
		for(int i = 0; i < this.krankenversicherung.length; i++) {
			if (this.krankenversicherung[i] != null) {
				sb.append("\t\t" + this.krankenversicherung[i] + ",\n");
			}
		}
		sb.append("\t],\n");	
		sb.append("\tgeburtsdatum: " + this.geburtsdatum.toLocaleString() + ",\n");
		sb.append("\temailadresse: " + this.emailadresse + ",\n");
		sb.append("\ttelefonnummer: " + this.telefonnummer + "\n");
		sb.append("}");
		
		return sb.toString();
	}
	
}
/**
* enum class 
* unterscheidet zwischen dem gender von patienten
*/
enum Anrede {
	HERR,
	FRAU;
};