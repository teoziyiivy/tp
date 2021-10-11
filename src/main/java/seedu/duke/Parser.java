package seedu.duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Parser {

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

    public static String getWeight(String inputArguments) throws DukeException {
        String[] userInput = inputArguments.split(" /d ");
        String weightString = userInput[0];
        int weightInt = parseStringToInteger(userInput[0]);
        if (weightInt < 0) {
            throw new DukeException("Negative weight");
        }
        return weightString;
    }

    public static String getScheduleDescription(String inputArguments) throws DukeException {
        String[] userInput = inputArguments.split(" /d ");
        String description = userInput[0];
        return description;
    }




}
