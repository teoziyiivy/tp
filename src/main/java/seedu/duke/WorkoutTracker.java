package seedu.duke;

import seedu.duke.exceptions.workout.DeleteWorkoutException;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.workout.EmptyWorkoutListException;
import seedu.duke.exceptions.workout.MissingWorkoutCalorieSeparatorException;
import seedu.duke.exceptions.workout.MissingWorkoutDescriptionException;
import seedu.duke.exceptions.workout.WorkoutException;
import seedu.duke.exceptions.workout.WorkoutNullArgumentException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//@@author arvejw
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
    }

    public void generateWorkoutParameters(String inputArguments) throws WorkoutException {
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Starting generation of parameters for workout.");
        workoutDescription = Parser.getDescription(inputArguments);
        caloriesBurned = Parser.getCaloriesBurnedForWorkout(inputArguments);
        workoutDate = Parser.getDate(inputArguments);
        workoutTime = Parser.getTime(inputArguments);
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Successfully generated parameters for workout.");
    }

    public void addWorkout(String inputArguments, boolean isSquelchAddMessage) throws WorkoutException {
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
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Successfully added workout.");
    }

    public void deleteWorkout(String inputArguments) throws WorkoutException, DukeException {
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
                    + workoutDescription + "\" on " + workoutDate + " at " + workoutTime + System.lineSeparator()
                    + " where you burned " + caloriesBurned + " calories!");
            workouts.remove(workoutIndex);
            WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Successfully deleted workout.");
        } else {
            WORKOUT_TRACKER_LOGGER.log(Level.WARNING, "Failed to delete workout.");
            throw new DeleteWorkoutException();
        }
    }


    public void listWorkouts(String inputArguments) throws WorkoutException {
        emptyWorkoutListCheck();
        if (inputArguments.equals(INPUT_ALL)) {
            listAllWorkouts();
        } else {
            listWorkoutsOnDate(inputArguments);
        }
    }

    public void listAllWorkouts() throws WorkoutException {
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Starting to try and list workouts.");
        assert workouts.size() > 0 : "List should be non empty at this point";
        System.out.println("All recorded workouts:" + System.lineSeparator() + ClickfitMessages.ENDLINE_PRINT_FORMAT);
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

    public void listWorkoutsOnDate(String inputArguments) throws WorkoutException {
        ArrayList<String> filteredWorkoutList = (ArrayList<String>) workouts.stream()
                .filter((t) -> Parser.getDate(t).equals(inputArguments)).collect(Collectors.toList());
        if (filteredWorkoutList.isEmpty()) {
            System.out.println("No workouts recorded on the date: " + inputArguments + System.lineSeparator()
                    + ClickfitMessages.DATE_ERROR);
        } else {
            if (inputArguments.equals(Parser.getSystemDate())) {
                System.out.println("Workouts recorded today" + System.lineSeparator());
            } else {
                System.out.println("Workouts recorded on " + inputArguments + ":" + System.lineSeparator());
            }
            int currentIndex = 1;
            int totalCaloriesBurned = 0;
            for (String workout : filteredWorkoutList) {
                generateWorkoutParameters(workout);
                System.out.println(currentIndex + ". " + workoutDescription);
                System.out.println("Calories burned: " + caloriesBurned);
                System.out.println("Date: " + workoutDate);
                System.out.println("Time: " + workoutTime + System.lineSeparator());
                totalCaloriesBurned += caloriesBurned;
                currentIndex++;
            }
            System.out.println("Total calories burned: " + totalCaloriesBurned);
        }
    }

    public int getCaloriesBurned(String date) throws WorkoutException {
        ArrayList<String> filteredWorkoutList = (ArrayList<String>) workouts.stream()
                .filter((t) -> Parser.getDateNoDateTracker(t).equals(date)).collect(Collectors.toList());
        int totalCaloriesBurned = 0;
        if (filteredWorkoutList.isEmpty()) {
            return 0;
        } else {
            for (String workout : workouts) {
                totalCaloriesBurned += Parser.getCaloriesBurnedForWorkout(workout);
            }
            return totalCaloriesBurned;
        }
    }

    public void nullArgumentCheck(String inputArguments) throws WorkoutException {
        if (inputArguments == null) {
            WORKOUT_TRACKER_LOGGER.log(Level.WARNING, "User input argument(s) is null.");
            throw new WorkoutNullArgumentException();
        }
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "User input argument(s) is not null.");
    }

    public void emptyWorkoutListCheck() throws WorkoutException {
        if (workouts.isEmpty()) {
            WORKOUT_TRACKER_LOGGER.log(Level.WARNING, "Workout list is empty.");
            throw new EmptyWorkoutListException();
        }
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Workout list is not empty.");
    }

    public boolean isWorkoutNumberWithinRange(int workoutNumber) {
        int upperBound = workouts.size();
        int lowerBound = LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING;
        return (workoutNumber >= lowerBound) && (workoutNumber <= upperBound);
    }

    public void missingDescriptionCheck(String inputArguments) throws WorkoutException {
        int indexOfFirstCalorieSeparator = inputArguments.indexOf(Parser.CALORIE_SEPARATOR.trim());
        String subStringBeforeCalorieSeparator = "";
        if (indexOfFirstCalorieSeparator != -1) { // calorie separator not found
            subStringBeforeCalorieSeparator = inputArguments.substring(0, indexOfFirstCalorieSeparator).trim();
        } else {
            throw new MissingWorkoutCalorieSeparatorException();
        }
        if (subStringBeforeCalorieSeparator.isEmpty()) {
            WORKOUT_TRACKER_LOGGER.log(Level.WARNING, "Description is missing in user input arguments.");
            throw new MissingWorkoutDescriptionException();
        }
        WORKOUT_TRACKER_LOGGER.log(Level.INFO, "Description is present in user input arguments.");
    }
}
