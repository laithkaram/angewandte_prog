package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ScannerUtils {
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
