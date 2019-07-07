package data;

import persistence.CSVPersistenceManager;
import persistence.SerializablePersistenceManager;
import utils.FileManager;

import java.text.SimpleDateFormat;
import java.util.*;

import static utils.ScannerUtils.PLACEHOLDER;
import static utils.ScannerUtils.liesEingabe;

/**
 * @author laithkaram
 * @version 1.8
 */

public class Patientenverwaltung extends Observable {

    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
    public Krankenhaus kh;
    public Qualitaetsmanagementstelle qualiStelle;
    public Abrechnungsstelle abrechnungsStelle;

    public HashMap<String, ArrayList<Aufenthalt>> aufenthalte;

    public Patientenverwaltung() {
        try {
            this.kh = new Krankenhaus("Krankenhaus 1", "UST_IDNR",
                    new Adresse("Müllerweg 1", "", 12345, "Berlin"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        aufenthalte = new HashMap<>();

        qualiStelle = new Qualitaetsmanagementstelle();
        abrechnungsStelle = new Abrechnungsstelle();
        this.addObserver(qualiStelle);
        this.addObserver(abrechnungsStelle);
    }

    public void loadSerializable() {
        this.kh = SerializablePersistenceManager.importData("krankenhaus-export.ser");
    }

    public void importCSV(String filename) {
        List<Patient> patientenListe = CSVPersistenceManager.importPatientsCSV(filename);
        for(Patient p: patientenListe) {
           this.kh.addPatient(p);
        }
    }

    /**
     * create dummy data
     */
    @Deprecated()
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
            p1.getKrankenversicherung().add(k1_p1);
            p1.getKrankenversicherung().add(k2_p1);
            p1.getKrankenversicherung().add(k3_p1);

            Patient p2 = new Patient(Anrede.HERR, "Kross", "Lucas",
                    new Adresse("Stromstrasse", "binjastrasse 23", 12412, "Berlin"),
                    new Date(1994 - 1900, 10, 23),
                    "max.mustermann@gmail.com",
                    "+491534234234");
            Krankenversicherung k1_p2 = new GesetzlicheVersicherung("Allianz", false);
            p2.getKrankenversicherung().add(k1_p2);

            Patient p3 = new Patient(Anrede.FRAU, "Julia", "Kilman",
                    new Adresse("Schokolade Ecke", "Abgebissen 23", 12412, "Bremen"),
                    new Date(1994 - 1900, 10, 23),
                    "max.mustermann@gmail.com",
                    "+491534234234");
            Krankenversicherung k1_p3 = new Privatversicherung("Allianz", 1250);
            p3.getKrankenversicherung().add(k1_p3);

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
     * @param sc     scanner
     * @param byName name
     * @return foundPatients.get(0) , die gefundene patienten
     */
    public Patient searchPatient(Scanner sc, boolean byName) {
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

    /**
     * Sucht die krankenversicherung und gibt die zureck un prueft ob die id numer mit der krankenversichrungen gefgunden wird
     *
     * @param sc Scanner
     * @return krankenversicherungen.get(0) krankenversicherung
     */
    public Krankenversicherung searchKrankenversicherung(Scanner sc) {
        String versicherungsId = sc.nextLine().toLowerCase();

        ArrayList<Krankenversicherung> krankenversicherungen = new ArrayList<>();

        for (Patient p : this.kh.getPatienten()) {
            for (Krankenversicherung k : p.getKrankenversicherung()) {
                if (k.getKrankenversichertennummer().toLowerCase().matches(PLACEHOLDER + versicherungsId + PLACEHOLDER)) {
                    krankenversicherungen.add(k);
                }
            }
        }

        if (krankenversicherungen.isEmpty()) {
            System.out.println("Keine Versicherung mit dem Id '" + versicherungsId + "' gefunden.");
            return null;
        } else if (krankenversicherungen.size() > 1) {
            System.out.println("Es wurden mehrere Krankenversicherungen zu dem Suchbegriff gefunden. Wählen Sie einen aus: ");
            for (int i = 0; i < krankenversicherungen.size(); i++) {
                System.out.printf("(%2d) - %-15s %-30s\n", i, krankenversicherungen.get(i).getKrankenversichertennummer(), krankenversicherungen.get(i).getName());
            }
            int index = liesEingabe(sc, 0, krankenversicherungen.size() - 1);

            return krankenversicherungen.get(index);
        } else {
            return krankenversicherungen.get(0);
        }
    }

    /**
     * Ausgabe der Patienten
     *
     * @param p Patient
     */
    public void gibPatientAus(Patient p) {
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
        sb.append(String.format(tabellenPattern, "Geburtsdatum", new SimpleDateFormat("dd.MM.yyyy, EEEE", Locale.GERMAN).format(p.getGeburtsdatum())));
        sb.append(String.format(tabellenPattern, "Telefonnummer", p.getTelefonnummer()));
        sb.append(String.format(tabellenPattern, "Email", p.getEmailadresse()));
        if (p.getKrankenversicherung().isEmpty()) {
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

    /**
     * gibt die krankenversicherung raus
     *
     * @param kv Krankenbersicherung
     */
    public void gibVersicherungAus(Krankenversicherung kv) {
        if (kv == null) {
            return;
        }

        String tabellenPattern = "%-20s : %-30s\n";

        System.out.println("======== KRANKENVERSICHERUNG ========");
        if (kv instanceof Privatversicherung) {
            System.out.printf(tabellenPattern, "Typ", "Privatversicherung");
            System.out.printf(tabellenPattern, "Nummer", kv.getKrankenversichertennummer());
            System.out.printf(tabellenPattern, "Name", kv.getName());
            System.out.printf("%-20s : %-30.2fs\n", "Deckungslimit", ((Privatversicherung) kv).getDeckungslimit());
        } else if (kv instanceof GesetzlicheVersicherung) {
            System.out.printf(tabellenPattern, "Typ", "Gesetzliche Versicherung");
            System.out.printf(tabellenPattern, "Nummer", kv.getKrankenversichertennummer());
            System.out.printf(tabellenPattern, "Name", kv.getName());
            System.out.printf(tabellenPattern, "Familienvers.", ((GesetzlicheVersicherung) kv).isIstFamilienVersicherung());
        }
    }

    public void notifyObserver(Object arg) {
        this.setChanged();
        this.notifyObservers(arg);
    }

    public void patientAufnehmen(Patient p, Date date) {
        ArrayList<Aufenthalt> aufenthalts = this.aufenthalte.get(p.getPatientennummer());
        if ( aufenthalts != null && aufenthalts.size() > 0 && aufenthalts.get(aufenthalts.size() - 1).canCheckOut()) {
            System.out.println("Patient bereits eingecheckt.");
            return;
        }
        if ( aufenthalts == null) {
            this.aufenthalte.put(p.getPatientennummer(), new ArrayList<>());
        }
        Aufenthalt a = new Aufenthalt(p);
        a.checkIn(date);
        this.aufenthalte.get(p.getPatientennummer()).add(a);
    }

    public void patientAuschecken(Patient p, Date date) {
        Aufenthalt a = this.aufenthalte.get(p.getPatientennummer()).get(aufenthalte.get(p.getPatientennummer()).size() - 1);
        a.checkOut(date);
    }
}
