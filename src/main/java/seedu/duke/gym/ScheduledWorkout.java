package seedu.duke.gym;

import seedu.duke.Tracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ScheduledWorkout extends Tracker {
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

    public void setWorkoutDescription(String workoutDescription) {
        this.workoutDescription = workoutDescription;
    }

    public String getWorkoutDate() {
        return workoutDate;
    }

    public String getWorkoutTime() {
        return workoutTime;
    }

}
