import data.*;
import persistence.CSVPersistenceManager;
import persistence.SerializablePersistenceManager;
import ui.MainFrame;
import utils.ConsoleLogger;
import utils.FileLogger;
import utils.Logger;
import utils.ScannerUtils;

import java.util.*;
import java.util.stream.Collectors;

import static utils.ScannerUtils.liesEingabe;

public class Application {


    /**
     * Hauptprogramm.
     *
     * @param args Kommandozeilenparameter
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean isCLI = true;
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("CLI")) {
                System.out.println("Starting the program in CLI mode...");
                isCLI = true;
            }
            else if (args[0].equalsIgnoreCase("GUI")) {
                System.out.println("Starting the program in GUI mode...");
                isCLI = false;
            }
            else {
                System.out.println("Argument konnte nicht eindeutig zugeordnet werden.");
                System.out.println("Mögliche Argumente: CLI oder GUI");
                return;
            }
        }
        else {
            System.out.println("Wollen Sie (0) CLI oder (1) GUI?");
            int selection = liesEingabe(sc, 0, 1);
            isCLI = (selection == 0);
        }

        if (!isCLI) {
            showGui();
        }
        else {
            cliLoop(sc);
        }
    }


    private static void showGui() {
        new MainFrame();
    }

    private static void cliLoop(Scanner sc) {
        Patientenverwaltung pv = new Patientenverwaltung();
        Timer timer = null;

        int auswahl = -1;

        while (auswahl != 0) {
            showMenu();
            auswahl = liesEingabe(sc, 0, 17);

            if (pv.kh == null && auswahl != 11) {
                System.out.println("Krankenhaus nicht definiert --> Importieren Sie zuerst eine Datei.");
                continue;
            }
            switch (auswahl) {
                case 1:
                case 2: {
                    Krankenversicherung kv;
                    if (auswahl == 1) {
                        kv = Privatversicherung.neuAnlegen();
                    } else {
                        kv = GesetzlicheVersicherung.neuAnlegen();
                    }
                    System.out.println("Welchem Patienten soll diese Versicherung zugeordnet werden?");
                    Patient p = pv.searchPatient(sc, false);
                    if (p != null) {
                        boolean erfolgreich = p.getKrankenversicherung().add(kv);
                        if (erfolgreich) {
                            System.out.println("Versicherung dem Patienten erfolgreich zugepordnet.");
                            pv.kh.getKrankenversicherungHashMap().put(kv.getKrankenversichertennummer(), kv);
                        } else {
                            System.out.println("Der Vorgang war nicht erfolgreich. Bitte versuchen Sie es zu einem späteren Zeitpunkt noch einmal.");
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
                case 7:
                case 8: {
                    List<Patient> sortedList = pv.kh.getPatienten().stream()
                            .sorted(Comparator.comparing((auswahl == 7) ? Patient::getPatientennummer : Patient::getNachnameInLowerCase))
                            .collect(Collectors.toList());


                    System.out.println("Es wurden " + sortedList.size() + " Patienten gefunden.");
                    System.out.println("|Patientennummer|Anrede|Vorname        |Nachname       |");
                    System.out.println("+---------------+------+---------------+---------------+");
                    for (Patient p : sortedList) {
                        System.out.printf("|%-15s|%-6s|%-15s|%-15s|\n", p.getPatientennummer(), p.getAnrede(), p.getName(), p.getNachname());
                    }
                    break;
                }
                case 9: {
                    Collection<Krankenversicherung> liste = pv.kh.getKrankenversicherungHashMap().values();
                    System.out.println("Es wurden " + liste.size() + " Krankenversicherungen gefunden.");
                    System.out.println("|Versichertennummer  |Versicherung                            |");
                    System.out.println("+--------------------+----------------------------------------+");
                    for (Krankenversicherung k : liste) {
                        System.out.printf("|%-20s|%-40s|\n", k.getKrankenversichertennummer(), k.getName());
                    }
                    break;
                }
                case 10: {
                    System.out.println("Geben Sie einen Dateinamen an:");
                    String filename = sc.nextLine();
                    boolean success = SerializablePersistenceManager.exportData(pv.kh, filename);

                    if (success) {
                        System.out.println("Daten erfolgreich exportiert.");
                    } else {
                        System.out.println("Daten konnten nicht exportiert werden");
                    }
                    break;
                }
                case 11: {
                    System.out.println("Geben Sie die zu importierende Datei an:");
                    String filename = sc.nextLine();
                    Krankenhaus kh = SerializablePersistenceManager.importData(filename);
                    if (kh != null) {
                        pv.kh = kh;
                        System.out.println("Daten erfolgreich importiert.");
                    } else {
                        System.out.println("Daten konnten nicht importiert werden.");
                    }
                    break;
                }
                case 12:
                    System.out.println("Geben Sie einen Dateinamen an:");
                    String filename = sc.nextLine();
                    String path = CSVPersistenceManager.exportPatientsOrderByName(pv.kh, filename);
                    if (path != null) {
                        int n = pv.kh.getPatienten().size();
                        System.out.println(n + " Datensätze in die Datei \"" + path + "\" exportiert.");
                    }
                    break;
                case 13: {
                    System.out.println("Welcher Patient soll eingecheckt werden? Geben Sie die Patientennummer ein: ");
                    Patient p = pv.searchPatient(sc, false);
                    if (p == null) {
                        continue;
                    }
                    System.out.println("Wann soll der Patient eingecheckt werden? Geben Sie ein Datum ein: (Format: dd.mm.yyyy HH:mm)");
                    Date d = ScannerUtils.liesDatumEin(sc, "dd.MM.yyyy HH:mm");

                    if (!pv.aufenthalte.containsKey(p.getPatientennummer())) {
                        pv.aufenthalte.put(p.getPatientennummer(), new ArrayList<>());
                    }
                    Aufenthalt a = new Aufenthalt(p);
                    a.checkIn(d);
                    pv.aufenthalte.get(p.getPatientennummer()).add(a);
                    System.out.println("Patient ist eingecheckt am " + a.getFrom().toString());
                    break;
                }
                case 14:
                    System.out.println("Welcher Patient soll ausgecheckt werden? Geben Sie die Patientennummer ein: ");
                    Patient p = pv.searchPatient(sc, false);
                    System.out.println("Wann soll der Patient ausgecheckt werden? Geben Sie ein Datum ein: (Format: dd.mm.yyyy HH:mm)");
                    Date d = ScannerUtils.liesDatumEin(sc, "dd.MM.yyyy HH:mm");

                    if (!pv.aufenthalte.containsKey(p.getPatientennummer()) ||
                            pv.aufenthalte.get(p.getPatientennummer()).isEmpty() ||
                            !pv.aufenthalte.get(p.getPatientennummer()).get(pv.aufenthalte.get(p.getPatientennummer()).size() - 1).canCheckOut()) {

                        System.out.println("Patient ist nicht eingescheckt. Kann also nicht ausgecheckt werden.");
                    } else {
                        Aufenthalt a = pv.aufenthalte.get(p.getPatientennummer()).get(pv.aufenthalte.get(p.getPatientennummer()).size() - 1);
                        a.checkOut(d);

                        if (a.getDays() > 2) {
                            pv.qualiStelle.addAufenthalt(a);
                            pv.abrechnungsStelle.addAufenthalt(a);
                            pv.notifyObserver(a);
                        }

                        System.out.println("Patient ist ausgecheckt am " + a.getTo().toString());
                    }
                    break;
                case 15:
                    System.out.println("Wählen Sie eine Protokoll-Strategy aus: ");
                    System.out.println("(0) File-Logger, (1) Konsole-Logger ");
                    int strategyTyp = liesEingabe(sc, 0, 1);
                    if (strategyTyp == 0) {
                        Logger.getInstance().setLoggerStrategy(new FileLogger());
                    } else {
                        Logger.getInstance().setLoggerStrategy(new ConsoleLogger());
                    }

                    break;
                case 16:
                    Logger.getInstance().setLoggerStrategy(new FileLogger("aufenthalte-cli-log"));
                    if (timer != null) {
                        System.out.println("Monitoring bereits gestartet.");
                        continue;
                    }
                    timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            synchronized (pv) {
                                int allAufenthalte = 0;
                                int currentAufenthalte = 0;

                                for(ArrayList<Aufenthalt> aufenthalts: pv.aufenthalte.values()) {
                                    allAufenthalte += aufenthalts.size();
                                    if (aufenthalts.size() > 0 && aufenthalts.get(aufenthalts.size() - 1).canCheckOut()) {
                                        currentAufenthalte++;
                                    }
                                }
                                Logger.getInstance().log("Das Krankenhaus hat gerade "+ currentAufenthalte + " Aufenthalte " +
                                        "von insgesamt " + allAufenthalte + " Aufenthalten.");
                            }
                        }
                    },0 , 30_000);
                    break;
                case 17:
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                    else {
                        System.out.println("Kein Monitoring vorhanden, welches gestoppt werden soll.");
                    }
                    break;
                default: {
                    // kann nicht auftreten, weil fehlerhafte eingabe wird vorher abgefangen
                }
            }
        }
    }

    /**
     * die methode zeigt die hauptmenu .
     */
    private static void showMenu() {
        System.out.println("+--------------------------------------------------------------------------+");
        System.out.println("|                                HAUPTMENU                                 |");
        System.out.println("|                                                                          |");
        System.out.println("| (01) Privatversicherung anlegen und Patientennummer zuordnen             |");
        System.out.println("| (02) Gesetzliche Versicherung anlegen und Patientennummer zuordnen       |");
        System.out.println("| (03) Patient anlegen und Patientennummer zuordnen                        |");
        System.out.println("| (04) Patient mit Versicherungen anzeigen (Auswahl durch Patientennummer) |");
        System.out.println("| (05) Patient mit Versicherungen anzeigen (Auswahl durch Name)            |");
        System.out.println("| (06) Versicherung anzeigen (Auswahl durch KVN)                           |");
        System.out.println("| (07) Alle Patienten sortiert nach aufsteigender Patientennummer anzeigen |");
        System.out.println("| (08) Alle Patienten sortiert nach aufsteigendem Nachnamen                |");
        System.out.println("| (09) Alle Krankenversicherungen unsortiert anzeigen                      |");
        System.out.println("| (10) Daten Export                                                        |");
        System.out.println("| (11) Daten Import                                                        |");
        System.out.println("| (12) Patienten nach Namen sortiert als CSV-Datei exportieren             |");
        System.out.println("| (13) Patient aufnehmen                                                   |");
        System.out.println("| (14) Patient entlassen                                                   |");
        System.out.println("| (15) Protokoll-Strategie wählen                                          |");
        System.out.println("| (16) Monitoring starten                                                  |");
        System.out.println("| (17) Monitoring beenden                                                  |");
        System.out.println("|                                                                          |");
        System.out.println("| (00) Beenden                                                             |");
        System.out.println("+--------------------------------------------------------------------------+");
    }

}
