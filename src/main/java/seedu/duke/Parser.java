package seedu.duke;

import seedu.duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;


public class Parser {

    public static final String DATE_SEPARATOR = " /d ";
    public static final String TIME_SEPARATOR = " /t ";
    public static final String CALORIE_SEPARATOR = " /c ";
    public static final String RECURRING_FLAG = " /r";
    public static final String VOLUME_SEPARATOR = " /v ";
    public static final String SPACE_SEPARATOR = " ";

    public static boolean containsVolumeSeparator(String inputArguments) {
        return inputArguments.contains(VOLUME_SEPARATOR);
    }

    public static boolean containsDateSeparator(String inputArguments) {
        return inputArguments.contains(DATE_SEPARATOR);
    }

    public static boolean containsTimeSeparator(String inputArguments) {
        return inputArguments.contains(TIME_SEPARATOR);
    }

    public static boolean containsCalorieSeparator(String inputArguments) {
        return inputArguments.contains(CALORIE_SEPARATOR);
    }

    public static boolean isRecurringWorkout(String inputArguments) {
        String[] splitResults = inputArguments.split(RECURRING_FLAG, 2);
        if (splitResults.length == 1) {
            return false;
        }
        return splitResults[1].isEmpty(); // true if /r flag is at the end of the string
    }

    public static int parseStringToInteger(String input) throws NumberFormatException {
        return Integer.parseInt(input);
    }

    public static boolean containsSeparators(String inputArguments) {
        if (inputArguments.contains(CALORIE_SEPARATOR.trim())) {
            return true;
        } else if (inputArguments.contains(DATE_SEPARATOR.trim())) {
            return true;
        } else if (inputArguments.contains(TIME_SEPARATOR.trim())) {
            return true;
        } else {
            return false;
        }
    }

    public static int getCalories(String inputArguments) throws DukeException, NumberFormatException {
        String[] userInput = inputArguments.split(SPACE_SEPARATOR);
        int length = userInput.length;
        int calories = 0;
        for (int i = 1; i < length; i++) {
            if (userInput[i].equals(CALORIE_SEPARATOR.trim())) {
                calories = parseStringToInteger(userInput[i + 1]);
                break;
            }
        }
        if (calories < 0) {
            throw new DukeException("Negative calories");
        }
        return calories;
    }

    public static int getVolume(String inputArguments) throws DukeException {
        String[] userInput = inputArguments.split(SPACE_SEPARATOR);
        int length = userInput.length;
        int volume = 0;
        for (int i = 1; i < length; i++) {
            if (userInput[i].equals(VOLUME_SEPARATOR.trim())) {
                volume = parseStringToInteger(userInput[i + 1]);
                break;
            }
        }
        if (volume < 0) {
            throw new DukeException("Negative volume");
        }
        return volume;
    }

    public static String getDescription(String inputArguments) throws DukeException {
        String[] userInput;
        if (containsCalorieSeparator(inputArguments)) {
            userInput = inputArguments.split(CALORIE_SEPARATOR);
        } else if (containsDateSeparator(inputArguments)) {
            userInput = inputArguments.split(DATE_SEPARATOR);
        } else if (containsTimeSeparator(inputArguments)) {
            userInput = inputArguments.split(TIME_SEPARATOR);
        } else {
            return inputArguments;
        }
        String description = userInput[0];
        return description;
    }

    public static String getDate(String inputArguments) throws DukeException, DateTimeParseException {
        String[] userInput = inputArguments.split(SPACE_SEPARATOR);
        int length = userInput.length;
        String date = "";
        for (int i = 1; i < length; i++) {
            if (userInput[i].equals(DATE_SEPARATOR.trim())) {
                date = userInput[i + 1];
                break;
            }
        }
        if (date.equals("")) {
            return getSystemDate();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return formatter.format(localDate);
    }

    public static String getTime(String inputArguments) throws DukeException, DateTimeParseException {
        String[] userInput = inputArguments.split(SPACE_SEPARATOR);
        int length = userInput.length;
        String time = "";
        for (int i = 1; i < length; i++) {
            if (userInput[i].equals(TIME_SEPARATOR.trim())) {
                time = userInput[i + 1];
                break;
            }
        }
        if (time.equals("")) {
            time = getSystemTime();
        }
        LocalTime localTime = LocalTime.parse(time);
        String properTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        return properTime;
    }

    public static int getWeight(String inputArguments) throws DukeException {
        String[] userInput = inputArguments.split(DATE_SEPARATOR);
        int weight = parseStringToInteger(userInput[0]);
        if (weight < 0) {
            throw new DukeException("Negative weight");
        }
        return weight;
    }

    public static String getScheduleDescription(String inputArguments) throws DukeException {
        String[] userInput = inputArguments.split(DATE_SEPARATOR);
        String description = userInput[0];
        if (userInput.length == 1) {
            throw new DukeException("Please enter a valid description!");
        }
        return description;
    }

    public static String getHelpDescription(String inputArguments) {
        String description = inputArguments;
        return description;
    }

    public static String getSystemDate() {
        String systemDate = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault());
        LocalDate now = LocalDate.now();
        systemDate = now.format(dtf);

        return systemDate;
    }

    public static String getSystemTime() {
        String systemTime = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault());
        LocalTime now = LocalTime.now();
        systemTime = now.format(dtf);

        return systemTime;
    }

}
