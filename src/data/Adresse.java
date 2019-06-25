package data;

import java.io.Serializable;
import java.util.Scanner;

/**
 * @author laithkaram
 */
public class Adresse implements Serializable {

    private String adresse1;
    private String adresse2;
    private int postleizahl;
    private String ort;

    /**
     * erzeugt die adresse der patienten oder von dem krankenhaus
     *
     * @param adresse1 , adresse2, postleizahl, ort
     */
    public Adresse(String adresse1, String adresse2, int postleizahl, String ort) {
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.postleizahl = postleizahl;
        this.ort = ort;
    }


    /*
     * Getter und Setter
     */


    /**
     * Fuegt die erste adresse von den patienten
     *
     * @return adresse1 , die erste Adresse
     */
    public String getAdresse1() {
        return adresse1;
    }

    /**
     * liegt due erste Adresse an .
     * @param adresse1 die erste Adresse
     */
    public void setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
    }

    /**
     * gibt die Zweite adresse ein
     * @return adresse2 , die zweite Adresse
     */
    public String getAdresse2() {
        return adresse2;
    }

    /**
     * Fuegt einen Patienten in die Liste, wenn freier Platz verfuegbar ist.
     *
     * @param adresse2
     */
    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    /**
     * erzeugt den postleizahl von dem patient und gibt den zureck
     *
     * @return postleizahl , postleizahl
     */
    public int getPostleizahl() {
        return postleizahl;
    }

    /**
     * Fuegt den postleizahl einem patienten
     *
     * @param postleizahl postleizahl
     */
    public void setPostleizahl(int postleizahl) {
        this.postleizahl = postleizahl;
    }

    /**
     * gibt den ort zureck
     *
     * @return ort ,Ort
     */
    public String getOrt() {
        return ort;
    }

    /**
     * Ort vom krankenhaus oder von patienten
     *
     * @param ort
     */
    public void setOrt(String ort) {
        this.ort = ort;
    }

    /**
     * gibt den Stering bei eingabe der nummer
     * @return override methode
     */
    @Override
    public String toString() {
        return "{ adresse1: " + adresse1 + ", adresse2: " + adresse2 + ", postleizahl: " + postleizahl + ", ort: "
                + ort + "}";
    }

    /**
     *liegt eine neue Adresse ein und wird bei postleizahl ein fehler gesucht
     * @return Adresse , new Adress
     */
    public static Adresse neuAnlegen() {
        System.out.println(" ====== ANLEGEN EINER NEUEN ADRESSE ====== ");
        Scanner sc = new Scanner(System.in);
        System.out.println("Adresse 1: ");
        String adresse1 = sc.nextLine();
        System.out.println("Adresse 2: ");
        String adresse2 = sc.nextLine();
        System.out.println("PLZ: ");
        int plz = -1;
        while (true) {
            try {
                String plzString = sc.nextLine();
                plz = Integer.parseInt(plzString);
                if (plz < 0) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Die Eingabe konnte nicht als eine PLZ erkannt werden. Bitte erneut eingeben: ");
            }
        }
        System.out.println("Ort: ");
        String ort = sc.nextLine();

        return new Adresse(adresse1, adresse2, plz, ort);
    }


}
