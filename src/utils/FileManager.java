package utils;

import java.io.File;

public class FileManager {
    /**
     *erzeugt Ein file name und fragt nach erstellung eines ornders falls nicht existiert
     * @param filename der name von dem file
     * @param defaultPostfix  der Orndner
     * @return file name ,der name von dem file
     */
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

    /**
     *
     * @param filename name von dem file
     * @return manipulateFilename , der name von dem File
     */
    public static String manipulateFilename(String filename) {
        return manipulateFilename(filename, ".ser");
    }
}
