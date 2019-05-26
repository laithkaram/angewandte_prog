package utils;

import java.io.File;

public class FileManager {

    public static String manipulateFilename(String filename, String defaultPostfix) {
        // erstelle Ordner falls nicht existiert
        File directory = new File("./storage");
        if(!directory.exists()){
            directory.mkdir();
        }

        filename = filename.trim();
        filename += ((filename.endsWith(defaultPostfix)) ? "": defaultPostfix);

        // Aendere Dateinamen falls nicht mit Storage beginnent.
        if (!filename.startsWith("./storage/")) {
            filename = "./storage/" + filename;
        }

        return filename;
    }

    public static String manipulateFilename(String filename) {
        return manipulateFilename(filename, ".ser");
    }
}
