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

	public String getPatiennummer() {
		return patiennummer;
	}
	
	public Anrede getAnrede() {
		return anrede;
	}

	public void setAnrede(Anrede anrede) {
		this.anrede = anrede;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Krankenversicherung[] getKrankenversicherung() {
		return krankenversicherung;
	}

	public boolean addKrankenversicherung(Krankenversicherung krankenversicherung) {
		for(int i = 0; i < this.krankenversicherung.length; i++) {
			if (this.krankenversicherung[i] == null) {
				this.krankenversicherung[i] = krankenversicherung;
				return true;
			}
		}
		System.out.println("INFO: Der Patient hat bereits 5 Krankenversicherungen. Bitte entfernen Sie eins bevor Sie ein neues hinzufügen können.");
		return false;
	}

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

enum Anrede {
	HERR,
	FRAU;
};