package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ScannerUtils {

    /**
     * Liesst die Eingabe des Benutzers von der Koncole und verifiziert, dass die Eingabe
     * zwischen 'min'(eingedschlossen) und 'max'(eingeschlossen) liegt.
     * <p>
     * also min <= x <= max
     * <p>
     * Es wird solange wiederholt gefragt, bis die Eingabe korrekt ist.
     *
     * @param sc  scanner eingabe
     * @param min minimalwert
     * @param max maximalwert
     * @return eingabe , der eingegebene wert
     */
    public static int liesEingabe(Scanner sc, int min, int max) {
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

    /**
     *liest die eingabe von dem Datum in bestimmterreinfolge dd.mm.yyyy
     * @param sc scanner
     * @return date, Datum
     */
    public static Date liesDatumEin(Scanner sc) {
        return liesDatumEin(sc, "dd.MM.yyyy");
    }

    public static Date liesDatumEin(Scanner sc, String datePattern) {
        System.out.println("Tipp: Leerlassen für jetzt");
        while(true) {
            String gebDatumString = sc.nextLine();
            try {
                Date date;
                if (gebDatumString.isEmpty()) {
                    date = new Date();
                }
                else {
                    date = new SimpleDateFormat(datePattern).parse(gebDatumString);
                }
                return date;
            } catch (Exception e) {
                System.out.println("Datum bitte in dem Format \"" + datePattern + "\" eingeben: ");
            }
        }
    }
}
