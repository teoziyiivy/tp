package seedu.duke.gym;

public class CompletedGymWorkout extends GymWorkout {
    private int caloriesBurned;

    public CompletedGymWorkout(String activityDescription, String activityAt, int caloriesBurned) {
        super(activityDescription, activityAt);
        this.caloriesBurned = caloriesBurned;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
}
