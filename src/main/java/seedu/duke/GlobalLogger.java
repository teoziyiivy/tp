package seedu.duke;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GlobalLogger {

    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final int MAXIMUM_LOG_FILE_SIZE = 1024 * 1024; // 10MB for now

    public GlobalLogger() {
        LogManager.getLogManager().reset(); // clear all settings
        setUpLogger();
    }

    public static void setUpLogger() {
        LOGGER.setLevel(Level.ALL);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE); // change this to test console logs of different levels
        LOGGER.addHandler(ch);
        try {
            FileHandler fh = new FileHandler("Logger.log", MAXIMUM_LOG_FILE_SIZE, 1, false);
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.INFO); // change this to test file logs of different levels
            LOGGER.addHandler(fh);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "File for Logger is not working.", e);
        }
    }
}
