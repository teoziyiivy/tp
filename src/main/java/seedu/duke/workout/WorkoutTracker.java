package seedu.duke.workout;

import seedu.duke.DateTracker;
import seedu.duke.Storage;
import seedu.duke.exceptions.DukeException;
import seedu.duke.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class WorkoutTracker {
    protected ArrayList<String> workouts;
    protected String workoutDescription;
    protected int caloriesBurned;
    protected String workoutDate;
    protected String workoutTime;
    private static final String INPUT_ALL = "all";
    private static final int LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING = 1;
    public static final Logger WORKOUT_TRACKER_LOGGER = Logger.getLogger("WorkoutTrackerLogger");

    public WorkoutTracker() {
        this.workouts = new ArrayList<>();
        WORKOUT_TRACKER_LOGGER.setLevel(Level.SEVERE);
        try {
            loadWorkoutData(); //auto-load for now, wait till integration then just call .loadWorkoutData elsewhere
        } catch (FileNotFoundException e) {
            System.out.println("Unable to locate ScheduleTracker data file.");
        }
    }

    public void loadWorkoutData() throws FileNotFoundException {
        File dataFile = new File(Storage.WORKOUT_DATA_FILE_PATH);
        // short circuit preload if file is empty
        if (dataFile.length() == 0) {
            return;
        }
        Scanner fileScanner = new Scanner(dataFile);
        String currentLine = "";
        boolean isDataLoadCorrectly = true;
        while (fileScanner.hasNext()) {
            currentLine = fileScanner.nextLine();
            // if any empty lines, skip to next iteration of the while loop
            if (currentLine.isEmpty()) {
                continue;
            }
            try {
                addWorkout(currentLine, true);
            } catch (DukeException | DateTimeParseException | NumberFormatException e) {
                isDataLoadCorrectly = false;
            }
        }
        if (!isDataLoadCorrectly) {
            System.out.println("There were some errors during loading of WorkoutTracker data, "
                    + System.lineSeparator() + "some data may have been lost or loaded incorrectly.");
        }
    }

    public void generateWorkoutParameters(String inputArguments)
            throws DukeException, DateTimeParseException, NumberFormatException {
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Starting generation of parameters for workout.");
        workoutDescription = Parser.getDescription(inputArguments);
        caloriesBurned = Parser.getCaloriesBurnedForWorkout(inputArguments);
        workoutDate = Parser.getDateNoDateTracker(inputArguments);
        workoutTime = Parser.getTime(inputArguments);
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Successfully generated parameters for workout.");
    }

    public void addWorkout(String inputArguments, boolean isSquelchAddMessage)
            throws DukeException, DateTimeParseException, NumberFormatException {
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Starting to try and add workout.");
        nullArgumentCheck(inputArguments);
        assert inputArguments != null : "Exception should already been thrown if argument is null";
        missingDescriptionCheck(inputArguments);
        generateWorkoutParameters(inputArguments);
        if (!isSquelchAddMessage) {
            System.out.println("Noted! CLI.ckFit has recorded your workout of description \"" + workoutDescription
                    + "\" on " + workoutDate + " at " + workoutTime + " where you burned "
                    + caloriesBurned + " calories!");
        }
        String updatedArguments = workoutDescription + Parser.CALORIE_SEPARATOR + caloriesBurned
                + Parser.DATE_SEPARATOR + workoutDate + Parser.TIME_SEPARATOR + workoutTime;
        workouts.add(updatedArguments);
        sortWorkoutList();
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Successfully added workout.");
    }

    public void deleteWorkout(String inputArguments) throws DukeException, NumberFormatException {
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Starting to try and delete workout.");
        nullArgumentCheck(inputArguments);
        assert inputArguments != null : "Exception should already been thrown if argument is null";
        emptyWorkoutListCheck();
        assert workouts.size() > 0 : "List should be non empty at this point";
        int workoutNumber = Parser.parseStringToInteger(inputArguments);
        int workoutIndex = workoutNumber - 1; // 0-indexing
        if (isWorkoutNumberWithinRange(workoutNumber)) {
            generateWorkoutParameters(workouts.get(workoutIndex));
            System.out.println("Noted! CLI.ckFit has successfully deleted your recorded workout of description \""
                    + workoutDescription + "\" on " + workoutDate + " at " + workoutTime + " where you burned "
                    + caloriesBurned + " calories!");
            workouts.remove(workoutIndex);
            WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Successfully deleted workout.");
        } else {
            WORKOUT_TRACKER_LOGGER.log(Level.WARNING, "Failed to delete workout.");
            throw new DukeException("Failed to delete that workout! Please enter an Integer within range.");
        }
    }

    public void listWorkouts(String inputArguments) throws DukeException {
        sortWorkoutList();
        if (inputArguments == null) {
            listWorkoutsOnDate(Parser.getSystemDate());
        } else if (inputArguments.equals(INPUT_ALL)) {
            listAllWorkouts();
        } else {
            listWorkoutsOnDate(inputArguments);
        }
    }

    public void listAllWorkouts()
            throws DukeException, DateTimeParseException, NumberFormatException {
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Starting to try and list workouts.");
        emptyWorkoutListCheck();
        assert workouts.size() > 0 : "List should be non empty at this point";
        System.out.println("ALL WORKOUTS:" + System.lineSeparator());
        int currentIndex = 1;
        for (String workout : workouts) {
            generateWorkoutParameters(workout);
            System.out.println(currentIndex + ". " + workoutDescription);
            System.out.println("Calories burned: " + caloriesBurned);
            System.out.println("Date: " + workoutDate);
            System.out.println("Time: " + workoutTime + "\n");
            currentIndex++;
        }
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Successfully listed workouts.");
    }

    public void listWorkoutsOnDate(String inputArguments) throws DukeException {
        ArrayList<String> filteredWorkoutList = (ArrayList<String>) workouts.stream()
                .filter((t) -> Parser.getDate(t).equals(inputArguments)).collect(Collectors.toList());
        if (filteredWorkoutList.isEmpty()) {
            System.out.println("No workouts recorded on that the date: " + inputArguments);
        } else {
            if (inputArguments.equals(Parser.getSystemDate())) {
                System.out.println("WORKOUTS TODAY:" + System.lineSeparator());
            } else {
                System.out.println("WORKOUTS ON " + inputArguments + ":" + System.lineSeparator());
            }
            int currentIndex = 1;
            for (String workout : filteredWorkoutList) {
                generateWorkoutParameters(workout);
                System.out.println(currentIndex + ". " + workoutDescription);
                System.out.println("Calories burned: " + caloriesBurned);
                System.out.println("Date: " + workoutDate);
                System.out.println("Time: " + workoutTime + "\n");
                currentIndex++;
            }
        }
    }

    public void sortWorkoutList() {
        DateTracker.sortDateAndTime(workouts);
    }

    public String getWorkoutListAsString() {
        String workoutListAsString = "";
        for (String workout : workouts) {
            workoutListAsString += workout + System.lineSeparator();
        }
        return workoutListAsString;
    }

    public void nullArgumentCheck(String inputArguments) throws DukeException {
        if (inputArguments == null) {
            WORKOUT_TRACKER_LOGGER.log(Level.WARNING, "User input argument(s) is null.");
            throw new DukeException("Please enter an argument!");
        }
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "User input argument(s) is not null.");
    }

    public void emptyWorkoutListCheck() throws DukeException {
        if (workouts.isEmpty()) {
            WORKOUT_TRACKER_LOGGER.log(Level.WARNING, "Workout list is empty.");
            throw new DukeException("Workout list is empty!");
        }
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Workout list is not empty.");
    }

    public boolean isWorkoutNumberWithinRange(int workoutNumber) {
        int upperBound = workouts.size();
        int lowerBound = LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING;
        return (workoutNumber >= lowerBound) && (workoutNumber <= upperBound);
    }

    public void missingDescriptionCheck(String inputArguments) throws DukeException {
        int indexOfFirstCalorieSeparator = inputArguments.indexOf(Parser.CALORIE_SEPARATOR.trim());
        String subStringBeforeCalorieSeparator = "";
        if (indexOfFirstCalorieSeparator != -1) { // calorie separator not found
            subStringBeforeCalorieSeparator = inputArguments.substring(0, indexOfFirstCalorieSeparator).trim();
        } else {
            throw new DukeException("Missing calorie separator /c");
        }
        if (subStringBeforeCalorieSeparator.isEmpty()) {
            WORKOUT_TRACKER_LOGGER.log(Level.WARNING, "Description is missing in user input arguments.");
            throw new DukeException("I am sorry... it appears the description is missing." + System.lineSeparator()
                    + "Please enter a workout description!");
        }
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Description is present in user input arguments.");
    }
}
