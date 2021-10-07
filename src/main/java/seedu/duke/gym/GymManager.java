package seedu.duke.gym;

import java.util.ArrayList;

public class GymManager {
    private ArrayList<GymWorkout> gymScheduleList;
    private ArrayList<CompletedGymWorkout> completedGymWorkoutList;
    private static final int LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING = 1;
    private static final int OFFSET_BY_1 = 1; //OFFSET to convert between 0 & 1's Indexing

    public GymManager() {
        gymScheduleList = new ArrayList<GymWorkout>();
        completedGymWorkoutList = new ArrayList<CompletedGymWorkout>();
    }

    // e.g. workoutschedule chest day /at 12pm
    public void addGymWorkout(String input) {
        String[] arguments = input.trim().split("/at", 2);
        gymScheduleList.add(new GymWorkout(arguments[0].trim(), arguments[1].trim()));
        System.out.println("Noted! CLI.ckFit has scheduled your workout of " + arguments[0].trim()
                + " at " + arguments[1].trim() + ".");
    }

    public void addGymWorkout(String activityDescription, String activityAt) {
        gymScheduleList.add(new GymWorkout(activityDescription, activityAt));
    }

    // e.g. workout chest day /at 12pm /c 356
    public void doneGymWorkout(String input) {
        String[] arguments = input.trim().split("/at", 2);
        String[] arguments2 = arguments[1].trim().split("/c", 2);
        int calories = Integer.parseInt(arguments2[1].trim());
        completedGymWorkoutList.add(
                new CompletedGymWorkout(arguments[0].trim(), arguments2[0].trim(), calories)
        );
        System.out.println("Noted! CLI.ckFit has recorded your workout of " + arguments[0].trim()
                + " at " + arguments2[0].trim() + ". " + calories + " calories have been burned. Keep working!");
    }

    // if activity exists in schedule list can use activity number
    // once date/time properly implemented only workouts on the current date can be "done"
    public void doneGymWorkout(int workoutNumber, int caloriesBurned) {
        //short circuit if empty
        if (gymScheduleList.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        if (isWorkoutNumberWithinRange(workoutNumber)) {
            completedGymWorkoutList.add(
                    new CompletedGymWorkout(gymScheduleList.get(workoutNumber - OFFSET_BY_1).getWorkoutDescription(),
                            gymScheduleList.get(workoutNumber - OFFSET_BY_1).getWorkoutAt(), caloriesBurned)
            );
        } else {
            System.out.println("Invalid Activity number");
        }
    }

    // completing activity that is not in gym schedule
    public void doneGymWorkout(String workoutDescription, String workoutAt, int caloriesBurned) {
        completedGymWorkoutList.add(
                new CompletedGymWorkout(workoutDescription, workoutAt, caloriesBurned)
        );
    }

    public void deleteGymWorkout(int workoutNumber) {
        // short circuit if empty
        if (gymScheduleList.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        if (isWorkoutNumberWithinRange(workoutNumber)) {
            gymScheduleList.remove(workoutNumber - OFFSET_BY_1);
        } else {
            System.out.println("Invalid Activity number");
        }
    }

    public boolean isWorkoutNumberWithinRange(int workoutNumber) {
        //short circuit if empty
        if (gymScheduleList.isEmpty()) {
            return false;
        }
        int upperBound = gymScheduleList.size();
        int lowerBound = LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING; // equals 1
        return (workoutNumber >= lowerBound) && (workoutNumber <= upperBound) ? true : false;
    }

    public void deleteCompletedGymWorkout(int activityWorkout) {
        // short circuit if empty
        if (completedGymWorkoutList.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        if (isWorkoutNumberWithinRange(activityWorkout)) {
            completedGymWorkoutList.remove(activityWorkout - OFFSET_BY_1);
        } else {
            System.out.println("Invalid Activity number");
        }
    }

    public void printGymSchedule() {
        // short circuit if empty
        if (gymScheduleList.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        int currentIndex = 1;
        for (GymWorkout a : gymScheduleList) {
            //placeholder before user interface class implemented
            System.out.println(currentIndex + ". " + a.getWorkoutDescription() + "(at: " + a.getWorkoutAt() + ")");
            currentIndex++;
        }
    }

    public void printCompletedGymWorkouts() {
        // short circuit if empty
        if (completedGymWorkoutList.isEmpty()) {
            System.out.println("Completed list is empty");
            return;
        }
        int currentIndex = 1;
        for (CompletedGymWorkout a : completedGymWorkoutList) {
            //placeholder before user interface class implemented
            System.out.println(currentIndex + ". " + a.getWorkoutDescription() + "(at: " + a.getWorkoutAt()
                    + ")(calories burned: " + a.getCaloriesBurned() + ")");
            currentIndex++;
        }
    }
}
