package seedu.duke.gym;

import java.time.LocalDate;
import java.time.LocalTime;

public class CompletedWorkout extends ScheduledWorkout {
    private int caloriesBurned;

    public CompletedWorkout(String workoutDescription, String workoutDate,
                            String workoutTime, int caloriesBurned) {
        super(workoutDescription, workoutDate, workoutTime);
        this.caloriesBurned = caloriesBurned;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
}
