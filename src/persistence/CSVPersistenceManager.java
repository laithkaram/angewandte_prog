package persistence;

import data.Krankenhaus;
import data.Patient;
import utils.FileManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CSVPersistenceManager {

    private static String NEWLINE = "\r\n";

    /**
     *
     * @param kh Krankenhaus
     * @param filename filename der patientn
     * @return file.getAbsolutePath(), File
     */
    public static String exportPatientsOrderByName(Krankenhaus kh, String filename) {
        List<Patient> sortedList = kh.getPatienten().stream()
                .sorted(Comparator.comparing(Patient::getNachnameInLowerCase))
                .collect(Collectors.toList());

        filename = FileManager.manipulateFilename(filename, ".csv");

        File file = new File(filename);

        try {
            FileWriter fw = new FileWriter(file);
            fw.write("Patientennummer;Name;AnzahlKV;HauptKV" + NEWLINE);

            for(Patient p: sortedList) {
                StringBuilder sb = new StringBuilder();
                sb.append(p.getPatientennummer())
                        .append(";")
                        .append(p.getName()).append(" ").append(p.getNachname())
                        .append(";")
                        .append(p.getKrankenversicherung().size())
                        .append(";");
                if (!p.getKrankenversicherung().isEmpty()) {
                    sb.append(p.getKrankenversicherung().get(0).getName());
                }
                sb.append(NEWLINE);

                fw.write(sb.toString());
            }

            fw.close();
        } catch (IOException ex) {
            System.out.println("Datei konnte nicht gefunden bzw. erstellt werden.");
            return null;
        }

        return file.getAbsolutePath();
    }
}
