package utils;

import java.io.*;
import java.util.Date;

public class FileLogger implements ILoggerStrategy {

    String logFile = "log";

    Writer bw;

    public FileLogger() {
        logFile = FileManager.manipulateFilename("log", ".txt");
    }

    @Override
    public void log(String message) {
        try {
            bw = new BufferedWriter(new FileWriter(logFile, true));
            bw.append(new Date().toString())
                    .append(": ")
                    .append(message)
                    .append('\n');
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
