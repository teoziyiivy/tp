package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.foodbank.FoodBankException;
import seedu.duke.exceptions.schedule.GetActivityException;
import seedu.duke.exceptions.schedule.InvalidActivityFormatException;
import seedu.duke.exceptions.schedule.InvalidScheduleDescriptionException;
import seedu.duke.exceptions.schedule.MissingActivityQuantifierException;
import seedu.duke.exceptions.schedule.MissingActivitySplitterException;
import seedu.duke.exceptions.workout.NegativeWorkoutCalorieException;
import seedu.duke.exceptions.schedule.ScheduleException;
import seedu.duke.exceptions.workout.WorkoutException;
import seedu.duke.schedule.WorkoutActivity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Parser {

    public static final String DATE_SEPARATOR = " /d ";
    public static final String TIME_SEPARATOR = " /t ";
    public static final String CALORIE_SEPARATOR = " /c ";
    public static final String RECURRING_FLAG = " /r";
    public static final String VOLUME_SEPARATOR = " /v ";
    public static final String ACTIVITY_SEPARATOR = " /a ";
    public static final String MULTIPLE_ACTIVITY_MARKER = ",";
    public static final String ACTIVITY_SPLITTER = ":";
    public static final String QUANTIFIER_SPLITTER = "x";
    public static final String SPACE_SEPARATOR = " ";

    //@@author { I}
    public static boolean containsDateSeparator(String inputArguments) {
        return inputArguments.contains(DATE_SEPARATOR);
    }

    //@@author { I}
    public static boolean containsTimeSeparator(String inputArguments) {
        return inputArguments.contains(TIME_SEPARATOR);
    }

    //@@author { I}
    public static boolean containsCalorieSeparator(String inputArguments) {
        return inputArguments.contains(CALORIE_SEPARATOR);
    }

    //@@author { J}
    public static boolean isRecurringWorkout(String inputArguments) {
        String[] splitResults = inputArguments.split(RECURRING_FLAG, 2);
        if (splitResults.length == 1) {
            return false;
        }
        return splitResults[1].isEmpty(); // true if /r flag is at the end of the string
    }

    //@@author { I}
    public static int parseStringToInteger(String input) throws NumberFormatException {
        return Integer.parseInt(input);
    }

    //@@author { I}
    public static double parseStringToDouble(String input) throws NumberFormatException {
        return Double.parseDouble(input);
    }

    //@@author {V }
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

    //@@author { V}
    public static int getCalories(String inputArguments)
            throws DukeException, NumberFormatException, FoodBankException {
        int calories = 0;
        if (!containsCalorieSeparator(inputArguments)) {
            String description = getDescription(inputArguments);
            if (Parser.containsSeparators(description)) {
                throw new DukeException("");
            }
            calories = FoodBank.findCalories(description);
            return calories;
        } else {
            String[] userInput = inputArguments.split(SPACE_SEPARATOR);
            int length = userInput.length;
            for (int i = 1; i < length; i++) {
                if (userInput[i].equals(CALORIE_SEPARATOR.trim())) {
                    calories = parseStringToInteger(userInput[i + 1]);
                }
            }
            if (calories < 0) {
                throw new DukeException("Negative calories");
            } else {
                return calories;
            }
        }
    }

    //@@author { J}
    public static int getCaloriesBurnedForWorkout(String inputArguments) throws WorkoutException {
        int calories = 0;
        String[] userInput = inputArguments.split(SPACE_SEPARATOR);
        int length = userInput.length;
        for (int i = 0; i < length; i++) {
            if (userInput[i].equals(CALORIE_SEPARATOR.trim())) {
                try {
                    calories = parseStringToInteger(userInput[i + 1]);
                    break;
                } catch (IndexOutOfBoundsException e) {
                    throw new NumberFormatException();
                }
            }
        }
        if (calories < 0) {
            throw new NegativeWorkoutCalorieException();
        } else {
            return calories;
        }
    }

    //@@author {P}
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

    //@@author { I}
    public static String getDescription(String inputArguments) {
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

    //@@author { I}
    public static String getDate(String inputArguments) throws DateTimeParseException {
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
            String newDate = getSystemDate();
            DateTracker.checkIfDateExists(newDate);
            return newDate;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        DateTracker.checkIfDateExists(formatter.format(localDate));
        return formatter.format(localDate);
    }

    //@@author { I}
    public static String getDateNoDateTracker(String inputArguments) throws DateTimeParseException {
        String[] userInput = inputArguments.split(SPACE_SEPARATOR);
        int length = userInput.length;
        String date = "";
        for (int i = 1; i < length; i++) {
            if (userInput[i].equals(DATE_SEPARATOR.trim())) {
                date = userInput[i + 1];
                break;
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return formatter.format(localDate);
    }

    //@@author { I}
    public static String getTime(String inputArguments) throws DateTimeParseException {
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

    // implement double
    //@@author { I}
    public static double getWeight(String inputArguments) throws DukeException {
        String[] userInput = inputArguments.split(DATE_SEPARATOR);
        if (!userInput[0].matches("^\\d+(\\.\\d+)?")) {
            throw new DukeException("Invalid weight!");
        }
        double weight = parseStringToDouble(userInput[0]);
        if (weight < 0) {
            throw new DukeException("Negative weight");
        }
        return weight;
    }

    //@@author arvejw
    public static String getScheduleDescription(String inputArguments) throws ScheduleException {
        String[] userInput = inputArguments.split(DATE_SEPARATOR);
        String description = userInput[0];
        if (userInput.length == 1) {
            throw new InvalidScheduleDescriptionException();
        }
        return description;
    }

    //@@author arvejw
    public static Map<String, ArrayList<Integer>> getActivities(String inputArguments) throws ScheduleException {
        int indexOfActivitySeparator = inputArguments.indexOf(Parser.ACTIVITY_SEPARATOR);
        String subSubstringAfterActivitySeparator = "";
        if (indexOfActivitySeparator != -1) {
            subSubstringAfterActivitySeparator = inputArguments.substring(
                    indexOfActivitySeparator).trim();
            if (isRecurringWorkout(inputArguments)) {
                subSubstringAfterActivitySeparator = subSubstringAfterActivitySeparator
                        .replace(RECURRING_FLAG, "").replace(ACTIVITY_SEPARATOR.trim(), "").trim();
            } else {
                subSubstringAfterActivitySeparator = subSubstringAfterActivitySeparator
                        .replace(ACTIVITY_SEPARATOR.trim(), "").trim();
            }
        }
        if (subSubstringAfterActivitySeparator.isEmpty()) {
            return new HashMap<>();
        } else {
            return getActivityArguments(subSubstringAfterActivitySeparator.split(MULTIPLE_ACTIVITY_MARKER));
        }
    }

    //@@author arvejw
    public static Map<String, ArrayList<Integer>> getActivityArguments(String[] nonParsedActivities)
            throws ScheduleException {
        Map<String, ArrayList<Integer>> outputMap = new HashMap<>();
        for (String activity : nonParsedActivities) {
            String[] splitResults = activity.split(ACTIVITY_SPLITTER, 2);
            if (splitResults.length == 1) {
                throw new MissingActivitySplitterException();
            }
            String[] quantifierSplitResults = splitResults[1].split(QUANTIFIER_SPLITTER, 2);
            if (quantifierSplitResults.length == 1 && !WorkoutActivity.isDistanceActivity(splitResults[0])) {
                throw new MissingActivityQuantifierException();
            }
            ArrayList<Integer> activityQuantifiers = new ArrayList<Integer>();
            if (WorkoutActivity.isDistanceActivity(splitResults[0])) {
                try {
                    activityQuantifiers.add(parseStringToInteger(quantifierSplitResults[0].trim()));
                } catch (NumberFormatException e) {
                    throw new InvalidActivityFormatException();
                }
            } else if (quantifierSplitResults.length == 2) {
                try {
                    activityQuantifiers.add(parseStringToInteger(quantifierSplitResults[0].trim()));
                    activityQuantifiers.add(parseStringToInteger(quantifierSplitResults[1].trim()));
                } catch (NumberFormatException e) {
                    throw new InvalidActivityFormatException();
                }
            } else {
                throw new GetActivityException();
            }
            outputMap.put(splitResults[0].trim(), activityQuantifiers);
        }
        return outputMap;
    }

    //@@author { P}
    public static String getSystemDate() {
        String systemDate = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault());
        LocalDate now = LocalDate.now();
        systemDate = now.format(dtf);

        return systemDate;
    }

    //@@author { P}
    public static String getSystemTime() {
        String systemTime = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault());
        LocalTime now = LocalTime.now();
        systemTime = now.format(dtf);
        return systemTime;
    }
}
