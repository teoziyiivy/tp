package seedu.duke.gym;

import seedu.duke.DukeException;
import seedu.duke.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class GymManager {
    private ArrayList<ScheduledGymWorkout> scheduledWorkoutList;
    private ArrayList<CompletedGymWorkout> completedWorkoutList;
    private static final int LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING = 1;
    private static final int OFFSET_BY_1 = 1; //OFFSET to convert between 0 & 1's Indexing

    public GymManager() {
        scheduledWorkoutList = new ArrayList<ScheduledGymWorkout>();
        completedWorkoutList = new ArrayList<CompletedGymWorkout>();
    }

    public void addScheduledWorkout(String inputArguments) {
        try {
            String workoutDescription = Parser.getScheduleDescription(inputArguments);
            LocalDate workoutDate = Parser.getDate(inputArguments);
            LocalTime workoutTime = Parser.getTime(inputArguments);
            scheduledWorkoutList.add(
                    new ScheduledGymWorkout(workoutDescription, workoutDate, workoutTime)
            );
        } catch (DukeException e) {
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addCompletedWorkout(String inputArguments) {
        try {
            String workoutDescription = Parser.getDescription(inputArguments);
            int caloriesBurned = Parser.getCalories(inputArguments);
            LocalDate workoutDate = Parser.getDate(inputArguments);
            LocalTime workoutTime = Parser.getTime(inputArguments);
            completedWorkoutList.add(
                    new CompletedGymWorkout(workoutDescription, workoutDate, workoutTime, caloriesBurned)
            );
        } catch (DukeException e) {
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isWorkoutNumberWithinRange(int workoutNumber) {
        //short circuit if empty
        if (scheduledWorkoutList.isEmpty()) {
            return false;
        }
        int upperBound = scheduledWorkoutList.size();
        int lowerBound = LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING;
        return (workoutNumber >= lowerBound) && (workoutNumber <= upperBound) ? true : false;
    }

    public void deleteScheduledWorkout(String inputArguments) {
        // short circuit if empty
        if (scheduledWorkoutList.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        int workoutNumber;
        try {
            workoutNumber = Parser.parseStringToInteger(inputArguments);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }
        if (isWorkoutNumberWithinRange(workoutNumber)) {
            scheduledWorkoutList.remove(workoutNumber - OFFSET_BY_1);
        } else {
            System.out.println("Failed to delete that workout! Please enter an Integer within range.");
        }
    }

    public void deleteCompletedGymWorkout(String inputArguments) {
        // short circuit if empty
        if (completedWorkoutList.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        int workoutNumber;
        try {
            workoutNumber = Parser.parseStringToInteger(inputArguments);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }
        if (isWorkoutNumberWithinRange(workoutNumber)) {
            completedWorkoutList.remove(workoutNumber - OFFSET_BY_1);
        } else {
            System.out.println("Failed to delete that workout! Please enter an Integer within range.");
        }
    }

    public void listScheduledWorkouts() {
        // short circuit if empty
        if (scheduledWorkoutList.isEmpty()) {
            System.out.println("Sorry but your scheduled workout list is empty!");
            return;
        }
        int currentIndex = 1;
        for (ScheduledGymWorkout s : scheduledWorkoutList) {
            //placeholder before user interface class implemented
            System.out.println(currentIndex + ". " + s.getWorkoutDescription() + "(at: "
                    + s.getWorkoutDate().toString() + " " + s.getWorkoutTime().toString() + ")");
            currentIndex++;
        }
    }

    public void printCompletedGymWorkouts() {
        // short circuit if empty
        if (completedWorkoutList.isEmpty()) {
            System.out.println("Sorry but your completed workout list is empty!");
            return;
        }
        int currentIndex = 1;
        for (CompletedGymWorkout c : completedWorkoutList) {
            //placeholder before user interface class implemented
            System.out.println(currentIndex + ". " + c.getWorkoutDescription() + "(at: "
                    + c.getWorkoutDate().toString() + " " + c.getWorkoutTime().toString() + ")(calories burned: "
                    + c.getCaloriesBurned() + ")");
            currentIndex++;
        }
    }
}
