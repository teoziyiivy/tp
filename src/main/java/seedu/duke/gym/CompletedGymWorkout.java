package seedu.duke.gym;

import java.time.LocalDate;
import java.time.LocalTime;

public class CompletedGymWorkout extends ScheduledGymWorkout {
    private int caloriesBurned;

    public CompletedGymWorkout(String workoutDescription, LocalDate workoutDate,
                               LocalTime workoutTime, int caloriesBurned) {
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
