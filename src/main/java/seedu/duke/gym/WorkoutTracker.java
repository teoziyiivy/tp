package seedu.duke.gym;

import seedu.duke.DukeException;
import seedu.duke.Parser;
import seedu.duke.Tracker;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class WorkoutTracker extends Tracker {
    protected ArrayList<String> workoutList;
    protected String workoutDescription;
    protected int caloriesBurned;
    protected String workoutDate;
    protected String workoutTime;
    private static final int LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING = 1;

    public WorkoutTracker() {
        this.workoutList = new ArrayList<>();
    }

    public void generateWorkoutParameters(String inputArguments)
            throws DukeException, DateTimeParseException, NumberFormatException {
        workoutDescription = Parser.getDescription(inputArguments);
        caloriesBurned = Parser.getCalories(inputArguments);
        workoutDate = Parser.getDate(inputArguments);
        workoutTime = Parser.getTime(inputArguments);
    }

    public void addWorkout(String inputArguments)
            throws DukeException, DateTimeParseException, NumberFormatException {
        nullArgumentCheck(inputArguments);
        missingDescriptionCheck(inputArguments);
        assert inputArguments != null : "Exception should already been thrown if argument is null";
        workoutSeparatorCheck(inputArguments);
        generateWorkoutParameters(inputArguments);
        System.out.println("Noted! CLI.ckFit has scheduled your workout of description \"" + workoutDescription
                + "\" on " + workoutDate + " at " + workoutTime + ".");
        workoutList.add(inputArguments);
    }

    public void deleteWorkout(String inputArguments) throws DukeException, NumberFormatException {
        nullArgumentCheck(inputArguments);
        assert inputArguments != null : "Exception should already been thrown if argument is null";
        emptyWorkoutListCheck();
        assert workoutList.size() > 0 : "List should be non empty at this point";
        int workoutNumber = Parser.parseStringToInteger(inputArguments);
        int workoutIndex = workoutNumber - 1; // 0-indexing
        if (isWorkoutNumberWithinRange(workoutNumber)) {
            generateWorkoutParameters(workoutList.get(workoutIndex));
            System.out.println("Noted! CLI.ckFit has successfully deleted your completed workout of description \""
                    + workoutDescription + "\" on " + workoutDate + " at " + workoutTime + " where you burned "
                    + caloriesBurned + " calories!");
            workoutList.remove(workoutIndex);
        } else {
            throw new DukeException("Failed to delete that workout! Please enter an Integer within range.");
        }
    }

    public void listWorkouts()
            throws DukeException, DateTimeParseException, NumberFormatException {
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
    }

    public void workoutSeparatorCheck(String inputArguments) throws DukeException {
        boolean areSeparatorsCorrect = Parser.containsCalorieSeparator(inputArguments)
                && Parser.containsDateSeparator(inputArguments)
                && Parser.containsTimeSeparator(inputArguments);
        if (!areSeparatorsCorrect) {
            throw new DukeException("Invalid or missing separator... Please try again!");
        }
    }

    public void nullArgumentCheck(String inputArguments) throws DukeException {
        if (inputArguments == null) {
            throw new DukeException("Please enter an argument!");
        }
    }

    public void emptyWorkoutListCheck() throws DukeException {
        if (workoutList.isEmpty()) {
            throw new DukeException("Workout list is empty!");
        }
    }

    public boolean isWorkoutNumberWithinRange(int workoutNumber) {
        int upperBound = workoutList.size();
        int lowerBound = LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING;
        return (workoutNumber >= lowerBound) && (workoutNumber <= upperBound);
    }

    public void missingDescriptionCheck(String inputArguments) throws DukeException {
        int indexOfFirstCalorieSeparator = inputArguments.indexOf(Parser.CALORIE_SEPARATOR.trim());
        String subStringBeforeCalorieSeparator;
        if (indexOfFirstCalorieSeparator != -1) { // date separator not found
            subStringBeforeCalorieSeparator = inputArguments.substring(0, indexOfFirstCalorieSeparator).trim();
        } else {
            throw new DukeException("Invalid or missing separator... Please try again!");
        }
        if (subStringBeforeCalorieSeparator.isEmpty()) {
            throw new DukeException("I am sorry... it appears the description is missing." + System.lineSeparator()
                    + "Please enter a description!");
        }
    }
}
