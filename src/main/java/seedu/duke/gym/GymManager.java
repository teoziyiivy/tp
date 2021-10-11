package seedu.duke.gym;

import seedu.duke.DukeException;
import seedu.duke.Parser;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class GymManager {
    private ArrayList<ScheduledWorkout> scheduledWorkoutList;
    private ArrayList<CompletedWorkout> completedWorkoutList;
    private static final int LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING = 1;
    private static final int OFFSET_BY_1 = 1; //OFFSET to convert between 0 & 1's Indexing

    public GymManager() {
        scheduledWorkoutList = new ArrayList<ScheduledWorkout>();
        completedWorkoutList = new ArrayList<CompletedWorkout>();
    }

    public void nullArgumentCheck(String inputArguments) throws DukeException {
        if (inputArguments == null) {
            throw new DukeException("Please enter an argument!");
        }
    }

    public void scheduledWorkoutSeparatorCheck(String inputArguments) throws DukeException {
        boolean areSeparatorsCorrect = Parser.containsDateSeparator(inputArguments)
                && Parser.containsTimeSeparator(inputArguments);
        if (!areSeparatorsCorrect) {
            throw new DukeException("Invalid or missing separator... Please try again!");
        }
    }

    public void completedWorkoutSeparatorCheck(String inputArguments) throws DukeException {
        boolean areSeparatorsCorrect = Parser.containsCalorieSeparator(inputArguments)
                && Parser.containsDateSeparator(inputArguments)
                && Parser.containsTimeSeparator(inputArguments);
        if (!areSeparatorsCorrect) {
            throw new DukeException("Invalid or missing separator... Please try again!");
        }
    }

    public void emptyScheduledWorkoutListCheck() throws DukeException {
        if (scheduledWorkoutList.isEmpty()) {
            throw new DukeException("Completed Workout list is empty!");
        }
    }

    public void emptyCompletedWorkoutListCheck() throws DukeException {
        if (completedWorkoutList.isEmpty()) {
            throw new DukeException("Scheduled Workout list is empty!");
        }
    }

    public void addScheduledWorkout(String inputArguments) {
        try {
            nullArgumentCheck(inputArguments);
            scheduledWorkoutSeparatorCheck(inputArguments);
            String workoutDescription = Parser.getScheduleDescription(inputArguments);
            String workoutDate = Parser.getDate(inputArguments);
            String workoutTime = Parser.getTime(inputArguments);
            scheduledWorkoutList.add(
                    new ScheduledWorkout(workoutDescription, workoutDate, workoutTime)
            );
            System.out.println("Noted! CLI.ckFit has scheduled your workout of " + workoutDescription
                    + " at " + workoutDate.toString() + " " + workoutTime.toString() + ".");
        } catch (DukeException ignored) { // message already output to user
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addCompletedWorkout(String inputArguments) {
        try {
            nullArgumentCheck(inputArguments);
            completedWorkoutSeparatorCheck(inputArguments);
            String workoutDescription = Parser.getDescription(inputArguments);
            int caloriesBurned = Parser.getCalories(inputArguments);
            String workoutDate = Parser.getDate(inputArguments);
            String workoutTime = Parser.getTime(inputArguments);
            completedWorkoutList.add(
                    new CompletedWorkout(workoutDescription, workoutDate, workoutTime, caloriesBurned)
            );
            System.out.println("Noted! CLI.ckFit has recorded your workout of " + workoutDescription
                    + " at " + workoutDate.toString() + " " + workoutTime.toString() + ". " + caloriesBurned
                    + " calories have been burned. Keep working!");
        } catch (DukeException ignored) { // message already output to user
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    // assumes whether list is empty has already been checked beforehand
    public boolean isScheduledWorkoutNumberWithinRange(int workoutNumber) {
        int upperBound = scheduledWorkoutList.size();
        int lowerBound = LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING;
        return (workoutNumber >= lowerBound) && (workoutNumber <= upperBound);
    }

    // assumes whether list is empty has already been checked beforehand
    public boolean isCompletedWorkoutNumberWithinRange(int workoutNumber) {
        int upperBound = completedWorkoutList.size();
        int lowerBound = LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING;
        return (workoutNumber >= lowerBound) && (workoutNumber <= upperBound);
    }

    public void deleteScheduledWorkout(String inputArguments) {
        int workoutNumber = 0;
        try {
            nullArgumentCheck(inputArguments);
            emptyScheduledWorkoutListCheck();
            workoutNumber = Parser.parseStringToInteger(inputArguments);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        } catch (DukeException ignored) { //message already output
            return;
        }
        if (isScheduledWorkoutNumberWithinRange(workoutNumber)) {
            ScheduledWorkout workoutToDelete = scheduledWorkoutList.get(workoutNumber - OFFSET_BY_1);
            System.out.println("Noted! CLI.ckFit has successfully deleted your scheduled workout of \""
                    + workoutToDelete.getWorkoutDescription() + "\" on " + workoutToDelete.getWorkoutDate()
                    + " at " + workoutToDelete.getWorkoutTime() + "!");
            scheduledWorkoutList.remove(workoutNumber - OFFSET_BY_1);
        } else {
            System.out.println("Failed to delete that workout! Please enter an Integer within range.");
        }
    }

    public void deleteCompletedWorkout(String inputArguments) {
        int workoutNumber = 0;
        try {
            nullArgumentCheck(inputArguments);
            emptyCompletedWorkoutListCheck();
            workoutNumber = Parser.parseStringToInteger(inputArguments);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        } catch (DukeException ignored) { // message already output to user
            return;
        }
        if (isCompletedWorkoutNumberWithinRange(workoutNumber)) {
            CompletedWorkout workoutToDelete = completedWorkoutList.get(workoutNumber - OFFSET_BY_1);
            System.out.println("Noted! CLI.ckFit has successfully deleted your completed workout of \""
                    + workoutToDelete.getWorkoutDescription() + "\" on " + workoutToDelete.getWorkoutDate()
                    + " at " + workoutToDelete.getWorkoutTime() + " where you burned "
                    + workoutToDelete.getCaloriesBurned() + " calories!");
            completedWorkoutList.remove(workoutNumber - OFFSET_BY_1);
        } else {
            System.out.println("Failed to delete that workout! Please enter an Integer within range.");
        }
    }

    public void listScheduledWorkouts() {
        try {
            emptyScheduledWorkoutListCheck();
        } catch (DukeException ignored) { // message already output to user
            return;
        }
        int currentIndex = 1;
        for (ScheduledWorkout s : scheduledWorkoutList) {
            //placeholder before user interface class implemented
            System.out.println(currentIndex + ". " + s.getWorkoutDescription() + " (at: "
                    + s.getWorkoutDate() + " " + s.getWorkoutTime() + ")");
            currentIndex++;
        }
    }

    public void listCompletedWorkouts() {
        try {
            emptyCompletedWorkoutListCheck();
        } catch (DukeException ignored) { // message already output to user
            return;
        }
        int currentIndex = 1;
        for (CompletedWorkout c : completedWorkoutList) {
            //placeholder before user interface class implemented
            System.out.println(currentIndex + ". " + c.getWorkoutDescription() + " (at: "
                    + c.getWorkoutDate() + " " + c.getWorkoutTime() + " | calories burned: "
                    + c.getCaloriesBurned() + ")");
            currentIndex++;
        }
    }
}
