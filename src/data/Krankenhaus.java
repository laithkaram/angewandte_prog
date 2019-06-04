package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Krankenhaus implements Serializable {

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
        this.krankenversicherungHashMap = new HashMap<>();
    }

    /**
     *Liste der Patienten
     * @return this.patienten , Die Patienten
     */
    public ArrayList<Patient> getPatienten() {
        return this.patienten;
    }

    /**
     *
     * @return krankenversicherungHashMap die krankenversicherung
     */
    public HashMap<String, Krankenversicherung> getKrankenversicherungHashMap() {
        return krankenversicherungHashMap;
    }

    /**
     * Fuegt einen Patienten in die Liste, wenn freier Platz verfuegbar ist.
     *
     * @param patient patient
     * @return true, wenn erfolgreich hinzugefuegt, ansonsten false
     */
    public boolean addPatient(Patient patient) {
        if (this.patienten.size() < MAX_PATIENTEN) {
            this.patienten.add(patient);
            for(Krankenversicherung k: patient.getKrankenversicherung()) {
                this.getKrankenversicherungHashMap().put(k.getKrankenversichertennummer(), k);
            }
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
     * @param patient patient
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
     * liegt den name der patientn ein
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Fuegt die Inifikationsnummer der patient.
     *
     * @return ust_idnr ,indifikationsnummer
     */
    public String getUstIdnr() {
        return ust_idnr;
    }

    /**
     *
     *
     * @param ust_idnr indifikationsnummer
     */

    public void setUstIdnr(String ust_idnr) {
        this.ust_idnr = ust_idnr;
    }

    /**
     * liegt die Adresse an
     *
     * @return adresse , adresse
     */
    public Adresse getAdresse() {
        return adresse;
    }

    /**
     *
     *
     * @param adresse adresse
     */
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }


    /**
     *
     * @return name , name
     * @return ust_idnr , indifikationsnummer
     * @return adresse , die adresse
     */
    @Override
    public String toString() {
        return "{ name: " + name + ", ust_idnr: " + ust_idnr + ", adresse: " + adresse + "}";
    }


}