package persistence;

import data.Adresse;
import data.Anrede;
import data.Krankenhaus;
import data.Patient;
import utils.FileManager;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CSVPersistenceManager {

    private static String NEWLINE = "\r\n";

    public static List<Patient> importPatientsCSV(String filename) {
        filename = FileManager.manipulateFilename(filename, ".csv");
        File file = new File(filename);

        ArrayList<Patient> result = new ArrayList<>();

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int index = 0;
            while((line = br.readLine()) != null) {
                if (index++ == 0) continue;
                try {
                    String[] patientData = line.split(";");
                    String id = patientData[0];
                    String anrede = patientData[1];
                    String name = patientData[2];
                    String geb = patientData[3];
                    String telefon = patientData[4];
                    String email = patientData[5];
                    String adresse1 = patientData[6];
                    String adresse2 = patientData[7];
                    String plz = patientData[8];
                    String ort = patientData[9];

                    Patient p = new Patient((anrede.equals("Herr")) ? Anrede.HERR:Anrede.FRAU,
                            name.split(" ")[0],
                            name.split(" ")[1],
                            new Adresse(adresse1, adresse2, Integer.parseInt(plz), ort),
                            (new SimpleDateFormat("dd.mm.yyyy")).parse(geb),
                            telefon,
                            email);
                    result.add(p);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

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
