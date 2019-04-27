import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author laithkaram
 * @version 1.8
 */

public class Patientenverwaltung {

    private Krankenhaus kh;

    private void loadDumb() {
        try {
            kh = new Krankenhaus("Helios Krankenhaus", "DE812524991", new Adresse("Schwanebecker Chaussee 50", "", 13125, "Berlin"));

            Patient p1 = new Patient("P-234213", Anrede.HERR, "Max", "Mustermann",
                    new Adresse("afrikanischestrasse 23", "kottiplatz 23", 12412, "Berlin"),
                    new Date(1994 - 1900, 10, 23),
                    "+491534234234",
                    "max.mustermann@gmail.com");

            Krankenversicherung k1_p1 = new Privatversicherung("TK", 2139861234, 300);
            Krankenversicherung k2_p1 = new GesetzlicheVersicherung("Allianz - Unfallversicherung", 124124, true);
            Krankenversicherung k3_p1 = new Privatversicherung("DBK - Arbeitsunfähigkeitsversicherung", 687283423, 450);
            p1.addKrankenversicherung(k1_p1);
            p1.addKrankenversicherung(k2_p1);
            p1.addKrankenversicherung(k3_p1);

            Patient p2 = new Patient("P-234244", Anrede.HERR, "Kross", "lucas",
                    new Adresse("Stromstrasse", "binjastrasse 23", 12412, "Berlin"),
                    new Date(1994 - 1900, 10, 23),
                    "max.mustermann@gmail.com",
                    "+491534234234");
            Krankenversicherung k1_p2 = new GesetzlicheVersicherung("Allianz", 3434, false);
            p2.addKrankenversicherung(k1_p2);

            Patient p3 = new Patient("P-234213", Anrede.FRAU, "Julia", "kilman",
                    new Adresse("Schokolade Ecke", "Abgebissen 23", 12412, "Bremen"),
                    new Date(1994 - 1900, 10, 23),
                    "max.mustermann@gmail.com",
                    "+491534234234");
            Krankenversicherung k1_p3 = new Privatversicherung("Allianz", 3434, 1250);
            p3.addKrankenversicherung(k1_p3);

            Patient p4 = new Patient("P-234213", Anrede.HERR, "jork", "klein",
                    new Adresse("reickinstrasse", "residenzplatz 23", 12433, "Hamburg"),
                    new Date(1994 - 1900, 10, 23),
                    "max.mustermann@gmail.com",
                    "+491534234234");

            Patient p5 = new Patient("P-234213", Anrede.FRAU, "Heike", "Freter",
                    new Adresse("lipschitzalle23", "Johanstallerchausse", 12488, "Berlin"),
                    new Date(1994 - 1900, 10, 23),
                    "max.mustermann@gmail.com",
                    "+491534234234");

            kh.addPatient(p1);
            kh.addPatient(p2);
            kh.addPatient(p3);
            kh.addPatient(p4);
            kh.addPatient(p5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Hauptprogramm.
     *
     * @param args Kommandozeilenparameter
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Patientenverwaltung pv = new Patientenverwaltung();
        pv.loadDumb();

        int auswahl = -1;

        while (auswahl != 0) {
            showMenu();
            auswahl = liesEingabe(sc);

            switch(auswahl) {
                case 1: {
                    // Privatversicherung anlegen und Patientennummer zuordnen
                    break;
                }
                case 2: {
                    // Gesetzliche Versicherung anlegen und Patientennummer zuordnen

                    break;
                }
                case 3: {
                    // Patient anlegen und Patientennummer zuordnen

                    break;
                }
                case 4: {
                    // Patient mit Versicherungen anzeigen (Auswahl durch Patientennummer)

                    break;
                }
                case 5: {
                    // Patient mit Versicherungen anzeigen (Auswahl durch Name)

                    break;
                }
                case 6: {
                    // Versicherung anzeigen (Auswahl durch Versicherungsnummer)

                    break;
                }
                default: {
                    // kann nicht auftreten, weil fehlerhafte eingabe wird vorher abgefangen
                }
            }

        }
    }

    private static int liesEingabe(Scanner sc) {
        int eingabe = -1;

        while (eingabe == -1){
            System.out.println("Eingabe: ");
            try {
                String eingabeString = sc.nextLine();
                eingabe = Integer.parseInt(eingabeString);
                if (eingabe < 0 || eingabe > 6) {
                    eingabe = -1;
                    throw new Exception("");
                }
            } catch (Exception e) {
                System.out.println("Ihre Eingabe konnte nicht als eine gültige Eingabe verifiziert werden. Versuchen Sie es erneut!");
            }
        }
        return eingabe;
    }

    private static void showMenu() {
        System.out.println("+--------------------------------------------------------------------------+");
        System.out.println("|                                HAUPTMENU                                 |");
        System.out.println("|                                                                          |");
        System.out.println("| (01) Privatversicherung anlegen und Patientennummer zuordnen             |");
        System.out.println("| (02) Gesetzliche Versicherung anlegen und Patientennummer zuordnen       |");
        System.out.println("| (03) Patient anlegen und Patientennummer zuordnen                        |");
        System.out.println("| (04) Patient mit Versicherungen anzeigen (Auswahl durch Patientennummer) |");
        System.out.println("| (05) Patient mit Versicherungen anzeigen (Auswahl durch Name)            |");
        System.out.println("| (06) Versicherung anzeigen (Auswahl durch Versicherungsnummer)           |");
        System.out.println("|                                                                          |");
        System.out.println("| (00) Beenden                                                             |");
        System.out.println("+--------------------------------------------------------------------------+");
    }
}
