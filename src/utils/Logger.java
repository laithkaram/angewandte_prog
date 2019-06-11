package utils;

public class Logger {
    private static Logger logger;
    private ILoggerStrategy loggerStrategy;

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    private Logger() {
        loggerStrategy = new ConsoleLogger();
    }

    public ILoggerStrategy getLoggerStrategy() {
        return loggerStrategy;
    }

    public void setLoggerStrategy(ILoggerStrategy loggerStrategy) {
        this.loggerStrategy = loggerStrategy;
    }

    public void log(String message) {
        this.loggerStrategy.log(message);
    }
}
