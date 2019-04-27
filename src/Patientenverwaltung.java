import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author laithkaram
 * @version 1.8
 */

public class Patientenverwaltung {

    public static final String PLACEHOLDER = ".*";

    private Krankenhaus kh;

    private void loadDumb() {
        try {
            kh = new Krankenhaus("Helios Krankenhaus", "DE812524991", new Adresse("Schwanebecker Chaussee 50", "", 13125, "Berlin"));

            Patient p1 = new Patient(Anrede.HERR, "Max", "Mustermann",
                    new Adresse("afrikanischestrasse 23", "kottiplatz 23", 12412, "Berlin"),
                    new Date(1994 - 1900, 10, 23),
                    "+491534234234",
                    "max.mustermann@gmail.com");

            Krankenversicherung k1_p1 = new Privatversicherung("TK", 300);
            Krankenversicherung k2_p1 = new GesetzlicheVersicherung("Allianz - Unfallversicherung", true);
            Krankenversicherung k3_p1 = new Privatversicherung("DBK - Arbeitsunfähigkeitsversicherung", 450);
            p1.addKrankenversicherung(k1_p1);
            p1.addKrankenversicherung(k2_p1);
            p1.addKrankenversicherung(k3_p1);

            Patient p2 = new Patient(Anrede.HERR, "Kross", "Lucas",
                    new Adresse("Stromstrasse", "binjastrasse 23", 12412, "Berlin"),
                    new Date(1994 - 1900, 10, 23),
                    "max.mustermann@gmail.com",
                    "+491534234234");
            Krankenversicherung k1_p2 = new GesetzlicheVersicherung("Allianz", false);
            p2.addKrankenversicherung(k1_p2);

            Patient p3 = new Patient(Anrede.FRAU, "Julia", "Kilman",
                    new Adresse("Schokolade Ecke", "Abgebissen 23", 12412, "Bremen"),
                    new Date(1994 - 1900, 10, 23),
                    "max.mustermann@gmail.com",
                    "+491534234234");
            Krankenversicherung k1_p3 = new Privatversicherung("Allianz", 1250);
            p3.addKrankenversicherung(k1_p3);

            Patient p4 = new Patient(Anrede.HERR, "Jork", "Klein",
                    new Adresse("reickinstrasse", "residenzplatz 23", 12433, "Hamburg"),
                    new Date(1994 - 1900, 10, 23),
                    "max.mustermann@gmail.com",
                    "+491534234234");

            Patient p5 = new Patient(Anrede.FRAU, "Heike", "Freter",
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
            auswahl = liesEingabe(sc, 0, 6);

            switch (auswahl) {
                case 1: {
                    // Privatversicherung anlegen und Patientennummer zuordnen
                    Privatversicherung privatversicherung = Privatversicherung.neuAnlegen();
                    System.out.println("Welchem Patienten soll diese Versicherung zugeordnet werden?");
                    Patient p = pv.searchPatient(sc, false);
                    if (p != null) {
                        boolean erfolgreich = p.addKrankenversicherung(privatversicherung);
                        if (erfolgreich) {
                            System.out.println("Versicherung dem Patienten erfolgreich zugepordnet.");
                        } else {
                            System.out.println("Der Patient hat bereits 5 Krankenversicherungen. Bitte entfernen Sie eins bevor Sie ein neues hinzufügen können.");
                        }
                    }
                    break;
                }
                case 2: {
                    // Gesetzliche Versicherung anlegen und Patientennummer zuordnen
                    GesetzlicheVersicherung gesetzlicheVersicherung = GesetzlicheVersicherung.neuAnlegen();
                    System.out.println("Welchem Patienten soll diese Versicherung zugeordnet werden? ");
                    Patient p = pv.searchPatient(sc, false);
                    if (p != null) {
                        boolean erfolgreich = p.addKrankenversicherung(gesetzlicheVersicherung);
                        if (erfolgreich) {
                            System.out.println("Versicherung dem Patienten erfolgreich zugepordnet.");
                        } else {
                            System.out.println("Der Patient hat bereits 5 Krankenversicherungen. Bitte entfernen Sie eins bevor Sie ein neues hinzufügen können.");
                        }
                    }
                    break;
                }
                case 3: {
                    // Patient anlegen und Patientennummer zuordnen
                    Patient p = Patient.neuAnlegen();
                    if (p != null) {
                        pv.kh.addPatient(p);
                        System.out.println("Patient '" + p.getName() + " " + p.getNachname() + "' wurde erfolgreich ins System aufgenommen.\n" +
                                "Patientennummer: " + p.getPatientennummer());
                    }
                    break;
                }
                case 4: {
                    // Patient mit Versicherungen anzeigen (Auswahl durch Patientennummer)
                    System.out.println("Nach welchem Patienten suchen Sie? Geben Sie die Patientennummer ein: ");
                    Patient p = pv.searchPatient(sc, false);
                    pv.gibPatientAus(p);
                    break;
                }
                case 5: {
                    // Patient mit Versicherungen anzeigen (Auswahl durch Name)
                    System.out.println("Nach welchem Patienten suchen Sie? Geben Sie den Namen ein: ");
                    Patient p = pv.searchPatient(sc, true);
                    pv.gibPatientAus(p);
                    break;
                }
                case 6: {
                    // Versicherung anzeigen (Auswahl durch Versicherungsnummer)
                    System.out.println("Nach welcher Versicherung suchen Sie? Geben Sie die Versicherungsnummer ein: ");
                    Krankenversicherung kv = pv.searchKrankenversicherung(sc);
                    pv.gibVersicherungAus(kv);
                    break;
                }
                default: {
                    // kann nicht auftreten, weil fehlerhafte eingabe wird vorher abgefangen
                }
            }

        }
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

    private static int liesEingabe(Scanner sc, int min, int max) {
        int eingabe = -1;

        while (eingabe == -1) {
            System.out.println("Eingabe: ");
            try {
                String eingabeString = sc.nextLine();
                eingabe = Integer.parseInt(eingabeString);
                if (eingabe < min || eingabe > max) {
                    eingabe = -1;
                    throw new Exception("");
                }
            } catch (Exception e) {
                System.out.println("Ihre Eingabe konnte nicht als eine gültige Eingabe verifiziert werden. Versuchen Sie es erneut!");
            }
        }
        return eingabe;
    }

    private Patient searchPatient(Scanner sc, boolean byName) {
        String nameOrId = sc.nextLine().toLowerCase();

        ArrayList<Patient> foundPatients = new ArrayList<>();
        if (byName) {
            for (Patient p : this.kh.getPatienten()) {
                if (p.getName().toLowerCase().matches(PLACEHOLDER + nameOrId + PLACEHOLDER) || p.getNachname().toLowerCase().matches(PLACEHOLDER + nameOrId + PLACEHOLDER)) {
                    foundPatients.add(p);
                }
            }
        } else {
            for (Patient p : this.kh.getPatienten()) {
                if (p.getPatientennummer().toLowerCase().matches(PLACEHOLDER + nameOrId + PLACEHOLDER)) {
                    foundPatients.add(p);
                }
            }
        }

        if (foundPatients.isEmpty()) {
            System.out.println("Keine Person mit dem Suchbegriff '" + nameOrId + "' gefunden.");
            return null;
        } else if (foundPatients.size() > 1) {
            System.out.println("Es wurden mehrere Personen zu dem Suchbegriff gefunden. Wählen Sie einen aus: ");
            for (int i = 0; i < foundPatients.size(); i++) {
                System.out.printf("(%2d) - %-15s %-30s\n", i, foundPatients.get(i).getPatientennummer(), foundPatients.get(i).getName() + " " + foundPatients.get(i).getNachname());
            }
            int index = liesEingabe(sc, 0, foundPatients.size() - 1);

            return foundPatients.get(index);
        } else {
            return foundPatients.get(0);
        }
    }

    private Krankenversicherung searchKrankenversicherung(Scanner sc) {
        String versicherungsId = sc.nextLine().toLowerCase();

        ArrayList<Krankenversicherung> krankenversicherungen = new ArrayList<>();

        for(Patient p: this.kh.getPatienten()) {
            // TODO gehe durch alle krankenversicherungen durch und suche nach der Versicherungsnummer

            // TODO wenn du was findest füge in die Liste krankenversicherungen hinzu
            // krankenversicherungen.add( --- );
        }
        return null;
    }

    private void gibPatientAus(Patient p) {
        if (p == null) {
            return;
        }

        String tabellenPattern = "%-20s : %-30s\n";
        StringBuilder sb = new StringBuilder();

        System.out.println("======== PATIENTENAUSGABE ========");
        sb.append(String.format(tabellenPattern, "Patientennummer", p.getPatientennummer()));
        sb.append(String.format(tabellenPattern, "Anrede", p.getAnrede()));
        sb.append(String.format(tabellenPattern, "Name", p.getName()));
        sb.append(String.format(tabellenPattern, "Nachname", p.getNachname()));
        sb.append(String.format(tabellenPattern, "Adresse", p.getAdresse()));
        sb.append(String.format(tabellenPattern, "Geburtsdatum",  new SimpleDateFormat("dd.MM.yyyy, EEEE", Locale.GERMAN).format(p.getGeburtsdatum())));
        sb.append(String.format(tabellenPattern, "Telefonnummer", p.getTelefonnummer()));
        sb.append(String.format(tabellenPattern, "Email", p.getEmailadresse()));
        if (p.getKrankenversicherungenAnzahl() == 0) {
            sb.append(String.format(tabellenPattern, "Versicherungen", "-- keine --"));
        } else {
            sb.append(String.format("%-20s :\n", "Versicherungen"));
            for (Krankenversicherung k : p.getKrankenversicherung()) {
                if (k != null) {
                    sb.append(String.format("%-20s - %-30s\n", "", k));
                }
            }
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }

    private void gibVersicherungAus(Krankenversicherung kv) {
        String tabellenPattern = "%-20s : %-30s\n";

        System.out.println("======== KRANKENVERSICHERUNG ========");
        if (kv instanceof Privatversicherung) {
            System.out.printf(tabellenPattern, "Typ", "Privatversicherung");
            System.out.printf(tabellenPattern, "Nummer", kv.getVersicherungsNummer());
            System.out.printf(tabellenPattern, "Name", kv.getName());
            System.out.printf("%-20s : %-30.2fs\n", "Name", ((Privatversicherung) kv).getDeckungslimit());
        }
        else if (kv instanceof GesetzlicheVersicherung) {
            System.out.printf(tabellenPattern, "Typ", "Gesetzliche Versicherung");
            System.out.printf(tabellenPattern, "Nummer", kv.getVersicherungsNummer());
            System.out.printf(tabellenPattern, "Name", kv.getName());
            System.out.printf(tabellenPattern, "Familienvers.", ((GesetzlicheVersicherung) kv).isIstFamilienVersicherung());
        }
    }
}
