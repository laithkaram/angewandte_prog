package utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger implements ILoggerStrategy {

    SimpleDateFormat loggerFormatter = new SimpleDateFormat("yyyy-mm-dd HH:MM:ss");
    String logFile;

    Writer bw;

    public FileLogger() {
        this("log");
    }

    public FileLogger(String fileName) {
        this.logFile = fileName;
        logFile = FileManager.manipulateFilename(this.logFile, ".txt");
    }

    @Override
    public void log(String message) {
        try {
            bw = new BufferedWriter(new FileWriter(logFile, true));
            bw.append(loggerFormatter.format(new Date()))
                    .append(" - ")
                    .append(message)
                    .append('\n');
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
