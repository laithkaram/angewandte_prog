package utils;

import java.util.Date;

public class ConsoleLogger implements ILoggerStrategy {


    @Override
    public void log(String message) {
        System.out.println(new Date().toString() +": " + message);
    }
}
