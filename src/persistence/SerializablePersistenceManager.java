package persistence;

import data.Krankenhaus;
import utils.FileManager;

import java.io.*;

public class SerializablePersistenceManager {

    /**
     *
     * @param kh krankenhaus
     * @param filename der name von dem file
     * @return true ,  wenn der datei gefunden wird
     * @return false , wenn der datei nicht gefunden wird
     */
    public static boolean exportData(Krankenhaus kh, String filename) {

        // Serialization
        try {
            filename = FileManager.manipulateFilename(filename);

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

    /**
     *
     * @param filename der name von dem file
     * @return kh , krankenhaus
     */
    public static Krankenhaus importData(String filename) {
        Krankenhaus kh = null;

        // Deserialization
        try {
            filename = FileManager.manipulateFilename(filename);

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

}
