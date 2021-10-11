package seedu.duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    //todo: replace the separators used in methods below with the final Strings to avoid magic literals
    public static final String DATE_SEPARATOR = " /d ";
    public static final String TIME_SEPARATOR = " /t ";
    public static final String CALORIE_SEPARATOR = " /c ";
    public static final String WHITESPACE = " ";

    // the 3 boolean methods below are helper functions to short circuit
    // e.g. if you need /c /d /t separators in the input,
    // if either /c /d /t are missing you can give user an error message and exit the method
    public static boolean containsDateSeparator(String inputArguments) {
        return inputArguments.contains(DATE_SEPARATOR);
    }

    public static boolean containsTimeSeparator(String inputArguments) {
        return inputArguments.contains(TIME_SEPARATOR);
    }

    public static boolean containsCalorieSeparator(String inputArguments) {
        return inputArguments.contains(CALORIE_SEPARATOR);
    }

    public static int parseStringToInteger(String input) throws NumberFormatException {
        return Integer.parseInt(input);
    }

    public static int getCalories(String inputArguments) throws DukeException {
        String[] userInput = inputArguments.split(" ");
        int length = userInput.length;
        int calories = 0;
        for (int i = 1; i < length; i++) {
            if (userInput[i].equals("/c")) {
                calories = parseStringToInteger(userInput[i + 1]);
                break;
            }
        }
        if (calories < 0) {
            throw new DukeException("Negative calories");
        }
        return calories;
    }

    public static String getDescription(String inputArguments) throws DukeException {
        String[] userInput = inputArguments.split(" /c ");
        String description = userInput[0];
        return description;
    }

    public static LocalDate getDate(String inputArguments) throws DukeException, DateTimeParseException {
        String[] userInput = inputArguments.split(" ");
        int length = userInput.length;
        String date = "";
        for (int i = 1; i < length; i++) {
            if (userInput[i].equals("/d")) {
                date = userInput[i + 1];
                break;
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

    public static LocalTime getTime(String inputArguments) throws DukeException, DateTimeParseException {
        String[] userInput = inputArguments.split(" ");
        int length = userInput.length;
        String time = "";
        for (int i = 1; i < length; i++) {
            if (userInput[i].equals("/t")) {
                time = userInput[i + 1];
                break;
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma");
        return LocalTime.parse(time, formatter);
    }

    public static int getWeight(String inputArguments) throws DukeException {
        String[] userInput = inputArguments.split(" /d ");
        int weight = parseStringToInteger(userInput[0]);
        if (weight < 0) {
            throw new DukeException("Negative weight");
        }
        return weight;
    }

    public static String getScheduleDescription(String inputArguments) {
        String[] userInput = inputArguments.split(" /d ");
        String description = userInput[0];
        return description;
    }

    public static String getHelpDescription(String inputArguments) {
        String description = inputArguments;
        return description;
    }
}
