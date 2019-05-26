package persistence;

import data.Krankenhaus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializablePersistenceManager {


    public static boolean exportData(Krankenhaus kh, String filename) {

        // Serialization
        try {
            // erstelle Ordner falls nicht existiert
            File directory = new File("./storage");
            if(!directory.exists()){
                directory.mkdir();
            }

            // Aendere Dateinamen falls nicht mit Storage beginnent.
            if (!filename.startsWith("storage/")) {
                filename = "storage/" + filename;
            }

            // Saving of object in a file
            FileOutputStream fileStream = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileStream);

            // Method for serialization of object
            out.writeObject(kh);

            out.close();
            fileStream.close();

            return true;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
