import java.util.ArrayList;
import java.util.HashMap;

public class Krankenhaus {

    public static final int MAX_PATIENTEN = 50;

    private String name;
    private String ust_idnr;
    private Adresse adresse;
    private ArrayList<Patient> patienten;
    private HashMap<String, Krankenversicherung> krankenversicherungHashMap;

    /**
     * @return tabNamen(String[ ]) die Tabellennamen der entsprechenden
     * Datenbank werden in einem Array zurückgegeben.
     * @throws Exception Fehlerbehandlung der Methode.
     */

    public Krankenhaus(String name, String ust_idnr, Adresse adresse) throws Exception {
        super();
        this.name = name;
        if (ust_idnr == null) {
            throw new Exception("Die Umsatzstuer-Identifikationsnummer darf nicht leer sein.");
        } else {
            this.ust_idnr = ust_idnr;
        }

        this.adresse = adresse;
        this.patienten = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public ArrayList<Patient> getPatienten() {
        return this.patienten;
    }

    /**
     *
     * @return
     */
    public HashMap<String, Krankenversicherung> getKrankenversicherungHashMap() {
        return krankenversicherungHashMap;
    }

    /**
     * Fuegt einen Patienten in die Liste, wenn freier Platz verfuegbar ist.
     *
     * @param patient
     * @return true, wenn erfolgreich hinzugefuegt, ansonsten false
     */
    public boolean addPatient(Patient patient) {
        if (this.patienten.size() < MAX_PATIENTEN) {
            this.patienten.add(patient);
            return true;
        }
        else {
            System.out.println("Kein Platz mehr für neue Patienten.");
            return false;
        }
    }

    /**
     * pruft ob der patient in der list im krankenhaussteht , wenn der patient nicht gleich ist dann wurd INFO als patient nicht gefunden gegeben
     *
     * @param patient
     * @return true, wenn erfolgreich hinzugefuegt, ansonsten false
     */
    public boolean removePatient(Patient patient) {
        int index = this.patienten.indexOf(patient);
        if (index != -1) {
            this.patienten.remove(-1);
            return true;
        }
        else {
            System.out.println("Patient nicht gefunden.");
            return false;
        }
    }


    /*
     * Getter und Setter
     */

    /**
     * erzeugt den namen von dem krankenhaus ung gibt den zureuck
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Fuegt einen Patienten in die Liste, wenn freier Platz verfuegbar ist.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Fuegt einen Patienten in die Liste, wenn freier Platz verfuegbar ist.
     *
     * @return indifikationsnummer
     */
    public String getUstIdnr() {
        return ust_idnr;
    }

    /**
     * die indifikations nummer von dem krankenhaus
     *
     * @param ust_idnr
     */

    public void setUstIdnr(String ust_idnr) {
        this.ust_idnr = ust_idnr;
    }

    /**
     * adresse des krankenhaUS
     *
     * @return adresse
     */
    public Adresse getAdresse() {
        return adresse;
    }

    /**
     * gibt die adresse von dem krankenhaus.
     *
     * @param adresse
     */
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }


    /**
     * eine ubernehmende override methode erzeugt die adresse und den name von dem krankenhaus und gibt die zureck
     *
     * @return, adresse , name
     */
    @Override
    public String toString() {
        return "{ name: " + name + ", ust_idnr: " + ust_idnr + ", adresse: " + adresse + "}";
    }


}