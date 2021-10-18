package seedu.duke.gym;

import seedu.duke.exceptions.DukeException;
import seedu.duke.Parser;
import seedu.duke.Tracker;
import seedu.duke.exceptions.FoodBankException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkoutTracker extends Tracker {
    protected ArrayList<String> workoutList;
    protected String workoutDescription;
    protected int caloriesBurned;
    protected String workoutDate;
    protected String workoutTime;
    private static final int LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING = 1;
    public static final Logger WORKOUT_TRACKER_LOGGER = Logger.getLogger("WorkoutTrackerLogger");

    public WorkoutTracker() {
        this.workoutList = new ArrayList<>();
        WORKOUT_TRACKER_LOGGER.setLevel(Level.SEVERE);
    }

    public void generateWorkoutParameters(String inputArguments)
            throws DukeException, DateTimeParseException, NumberFormatException, FoodBankException {
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Starting generation of parameters for workout.");
        workoutDescription = Parser.getDescription(inputArguments);
        caloriesBurned = Parser.getCalories(inputArguments);
        workoutDate = Parser.getDate(inputArguments);
        workoutTime = Parser.getTime(inputArguments);
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Successfully generated parameters for workout.");
    }

    public void addWorkout(String inputArguments)
            throws DukeException, DateTimeParseException, NumberFormatException, FoodBankException {
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Starting to try and add workout.");
        nullArgumentCheck(inputArguments);
        assert inputArguments != null : "Exception should already been thrown if argument is null";
        missingDescriptionCheck(inputArguments);
        //workoutSeparatorCheck(inputArguments);
        generateWorkoutParameters(inputArguments);
        System.out.println("Noted! CLI.ckFit has recorded your workout of description \"" + workoutDescription
                + "\" on " + workoutDate + " at " + workoutTime + " where you burned "
                + caloriesBurned + " calories!" + System.lineSeparator());
        String updatedArguments = workoutDescription + Parser.CALORIE_SEPARATOR + caloriesBurned
                + Parser.DATE_SEPARATOR + workoutDate + Parser.TIME_SEPARATOR + workoutTime;
        workoutList.add(updatedArguments);
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Successfully added workout.");
    }

    public void deleteWorkout(String inputArguments) throws DukeException, NumberFormatException, FoodBankException {
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Starting to try and delete workout.");
        nullArgumentCheck(inputArguments);
        assert inputArguments != null : "Exception should already been thrown if argument is null";
        emptyWorkoutListCheck();
        assert workoutList.size() > 0 : "List should be non empty at this point";
        int workoutNumber = Parser.parseStringToInteger(inputArguments);
        int workoutIndex = workoutNumber - 1; // 0-indexing
        if (isWorkoutNumberWithinRange(workoutNumber)) {
            generateWorkoutParameters(workoutList.get(workoutIndex));
            System.out.println("Noted! CLI.ckFit has successfully deleted your recorded workout of description \""
                    + workoutDescription + "\" on " + workoutDate + " at " + workoutTime + " where you burned "
                    + caloriesBurned + " calories!" + System.lineSeparator());
            workoutList.remove(workoutIndex);
            WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Successfully deleted workout.");
        } else {
            WORKOUT_TRACKER_LOGGER.log(Level.WARNING, "Failed to delete workout.");
            throw new DukeException("Failed to delete that workout! Please enter an Integer within range.");
        }
    }

    public void listWorkouts()
            throws DukeException, DateTimeParseException, NumberFormatException, FoodBankException {
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Starting to try and list workouts.");
        emptyWorkoutListCheck();
        assert workoutList.size() > 0 : "List should be non empty at this point";
        int currentIndex = 1;
        for (String workout : workoutList) {
            generateWorkoutParameters(workout);
            System.out.println(currentIndex + ". " + workoutDescription);
            System.out.println("Calories burned: " + caloriesBurned);
            System.out.println("Date: " + workoutDate);
            System.out.println("Time: " + workoutTime + "\n");
            currentIndex++;
        }
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Successfully listed workouts.");
    }

    public void nullArgumentCheck(String inputArguments) throws DukeException {
        if (inputArguments == null) {
            WORKOUT_TRACKER_LOGGER.log(Level.WARNING, "User input argument(s) is null.");
            throw new DukeException("Please enter an argument!" + System.lineSeparator());
        }
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "User input argument(s) is not null.");
    }

    public void emptyWorkoutListCheck() throws DukeException {
        if (workoutList.isEmpty()) {
            WORKOUT_TRACKER_LOGGER.log(Level.WARNING, "Workout list is empty.");
            throw new DukeException("Workout list is empty!");
        }
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Workout list is not empty.");
    }

    public boolean isWorkoutNumberWithinRange(int workoutNumber) {
        int upperBound = workoutList.size();
        int lowerBound = LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING;
        return (workoutNumber >= lowerBound) && (workoutNumber <= upperBound);
    }

    public void missingDescriptionCheck(String inputArguments) throws DukeException {
        int indexOfFirstCalorieSeparator = inputArguments.indexOf(Parser.CALORIE_SEPARATOR.trim());
        String subStringBeforeCalorieSeparator = "";
        if (indexOfFirstCalorieSeparator != -1) { // calorie separator not found
            subStringBeforeCalorieSeparator = inputArguments.substring(0, indexOfFirstCalorieSeparator).trim();
        }
        if (subStringBeforeCalorieSeparator.isEmpty()) {
            WORKOUT_TRACKER_LOGGER.log(Level.WARNING, "Description is missing in user input arguments.");
            throw new DukeException("I am sorry... it appears the description is missing." + System.lineSeparator()
                    + "Please enter a workout description!");
        }
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Description is present in user input arguments.");
    }
}
