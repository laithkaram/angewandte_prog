import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * 
 * @author laithkaram
 *
 */

public class Patient {

	private static int COUNTER_ID = 20_000;

	private final String patientennummer;
	private Anrede anrede;
	private String name;
	private String nachname;
	
	private Adresse adresse;
	private ArrayList<Krankenversicherung> krankenversicherung;

	private Date geburtsdatum;
	private String telefonnummer;
	private String emailadresse;


	/**
	 * Fuegt einen Patienten in die Liste, wenn freier Platz verfuegbar ist.
	 * @param anrede {@link Anrede}
	 * @param name the given surname
	 * @param nachname the given name
	 * @param adresse
	 * @param geburtsdatum
	 * @param telefonnummer
	 * @param emailadresse
	 */
	public Patient(Anrede anrede, String name, String nachname, Adresse adresse, Date geburtsdatum,
			String telefonnummer, String emailadresse) {
		super();
		this.patientennummer = "P-" + COUNTER_ID++;
		this.anrede = anrede;
		this.name = name;
		this.nachname = nachname;
		this.adresse = adresse;
		this.geburtsdatum = geburtsdatum;
		this.telefonnummer = telefonnummer;
		this.emailadresse = emailadresse;
		this.krankenversicherung = new ArrayList<>();
	}

	/**
	 * Fuegt die patiente nummer und gibt die zureck
	 * @return patientnummer
	 */
	public String getPatientennummer() {
		return patientennummer;
	}

	/**
	 * Fuegt eine anrede fue die patienten ung gibt die zureck
	 * @return anrede
	 */
	public Anrede getAnrede() {
		return anrede;
	}
	
	/**
	 *
	 * @param anrede
	 */
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
	 * @return true, wenn erfolgreich hinzugefuegt, ansonsten false
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	/**
	 *
	 * @return
	 */
	public ArrayList<Krankenversicherung> getKrankenversicherung() {
		return krankenversicherung;
	}

	/**
	 *
	 * @return
	 */
	public Date getGeburtsdatum() {
		return geburtsdatum;
	}

	/**
	 *
	 * @param geburtsdatum
	 */
	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	/**
	 *
	 * @return
	 */
	public String getTelefonnummer() {
		return telefonnummer;
	}

	/**
	 *
	 * @param telefonnummer
	 */
	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}

	/**
	 *
	 * @return
	 */
	public String getEmailadresse() {
		return emailadresse;
	}

	/**
	 *
	 * @param emailadresse
	 */
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
		for(int i = 0; i < this.krankenversicherung.size(); i++) {
			sb.append("\t\t" + this.krankenversicherung.get(i) + ",\n");
		}
		sb.append("\t],\n");	
		sb.append("\tgeburtsdatum: " + this.geburtsdatum.toLocaleString() + ",\n");
		sb.append("\temailadresse: " + this.emailadresse + ",\n");
		sb.append("\ttelefonnummer: " + this.telefonnummer + "\n");
		sb.append("}");
		
		return sb.toString();
	}

	/**
	 *
	 * @return
	 */
	public static Patient neuAnlegen() {
		System.out.println(" ======== ANLEGEN EINES NEUEN PATIENTEN ======== ");
		Scanner sc = new Scanner(System.in);
		Anrede anrede = liesAnredeEin(sc);
		if (anrede == Anrede.HERR) {
			System.out.println("Wie heißt der Patient? ");
		}
		else {
			System.out.println("Wie heißt die Patientin?");
		}
		System.out.println("Vorname: ");
		String name = sc.nextLine();
		System.out.println("Nachname: ");
		String nachname = sc.nextLine();

		Adresse adresse = Adresse.neuAnlegen();

		Date gebDatum = liesGebDatumEin(sc);
		System.out.println("Telefonnummer: ");
		String telefonnummer = sc.nextLine();
		System.out.println("Email-Adresse: ");
		String emailadresse = sc.nextLine();
		Patient p = new Patient(anrede, name, nachname, adresse, gebDatum, telefonnummer, emailadresse);
		return p;
	}


	/**
	 *
	 * @param sc
	 * @return
	 */
	private static Anrede liesAnredeEin(Scanner sc) {
		System.out.println("(0) - Herr oder (1) - Frau?");
		Anrede anrede;
		while(true) {
			String eingabe = sc.nextLine();
			try {
				int eingabeInt = Integer.parseInt(eingabe);
				if (eingabeInt == 0) {
					anrede = Anrede.HERR;
					break;
				}
				else if (eingabeInt == 1) {
					anrede = Anrede.FRAU;
					break;
				}
				else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Bitte (0) für einen Mann oder (1) für eine Frau eingeben: ");
			}
		}
		return anrede;
	}

	/**
	 *
	 * @param sc
	 * @return
	 */
	private static Date liesGebDatumEin(Scanner sc) {
		System.out.println("Geburtsdatum (dd.mm.yyyy):");
		while(true) {
			String gebDatumString = sc.nextLine();
			try {
				Date date = new SimpleDateFormat("dd.MM.yyyy").parse(gebDatumString);
				return date;
			} catch (Exception e) {
				System.out.println("Datum bitte in dem Format dd.MM.yyyy eingeben: ");
			}
		}
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