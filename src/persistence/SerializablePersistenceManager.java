package persistence;

import data.Krankenhaus;

import java.io.*;

public class SerializablePersistenceManager {


    public static boolean exportData(Krankenhaus kh, String filename) {

        // Serialization
        try {
            filename = manipulateFilename(filename);

            // Saving of object in a file
            FileOutputStream fileStream = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileStream);

            // Method for serialization of object
            out.writeObject(kh);

            out.close();
            fileStream.close();

            return true;
        } catch (IOException ex) {
            System.out.println("Dateiname konnte nicht gefunden bzw. erstellt werden.");
        }
        return false;
    }

    public static Krankenhaus importData(String filename) {
        Krankenhaus kh = null;

        // Deserialization
        try {
            filename = manipulateFilename(filename);

            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            kh = (Krankenhaus) in.readObject();

            in.close();
            file.close();
        } catch (IOException ex) {
            System.out.println("Dateiname konnte nicht gefunden bzw. erstellt werden.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Die angegebene Klasse konnte nicht gefunden bzw. erstellt werden.");
        }
        return kh;
    }

    private static String manipulateFilename(String filename) {
        // erstelle Ordner falls nicht existiert
        File directory = new File("./storage");
        if(!directory.exists()){
            directory.mkdir();
        }

        filename = filename.trim();
        filename += ((filename.endsWith(".ser")) ? "": ".ser");

        // Aendere Dateinamen falls nicht mit Storage beginnent.
        if (!filename.startsWith("./storage/")) {
            filename = "./storage/" + filename;
        }

        return filename;
    }
}
