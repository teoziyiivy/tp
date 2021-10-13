package seedu.duke.gym;

public class ScheduledWorkout {
    private String workoutDescription;
    private String workoutDate;
    private String workoutTime;

    public ScheduledWorkout(String workoutDescription, String workoutDate, String workoutTime) {
        this.workoutDescription = workoutDescription;
        this.workoutDate = workoutDate;
        this.workoutTime = workoutTime;
    }

    public String getWorkoutDescription() {
        return workoutDescription;
    }

    public String getWorkoutDate() {
        return workoutDate;
    }

    public String getWorkoutTime() {
        return workoutTime;
    }
}
