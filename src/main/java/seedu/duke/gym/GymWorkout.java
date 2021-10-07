package seedu.duke.gym;

public class GymWorkout {
    private String workoutDescription;
    private String workoutAt; //todo: store as DateTime instead of String

    public GymWorkout(String workoutDescription, String workoutAt) {
        this.workoutDescription = workoutDescription;
        this.workoutAt = workoutAt;
    }

    public String getWorkoutDescription() {
        return workoutDescription;
    }

    public void setWorkoutDescription(String workoutDescription) {
        this.workoutDescription = workoutDescription;
    }

    public String getWorkoutAt() {
        return workoutAt;
    }

    public void setWorkoutAt(String workoutAt) {
        this.workoutAt = workoutAt;
    }
}
