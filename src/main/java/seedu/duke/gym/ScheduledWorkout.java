package seedu.duke.gym;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ScheduledWorkout {
    private String workoutDescription;
    private String workoutDate;
    private String workoutTime;
    private LocalDateTime workoutDateTime;
    private boolean isRecurring;

    public ScheduledWorkout(String workoutDescription, String workoutDate, String workoutTime) {

        this.workoutDescription = workoutDescription;
        this.workoutDate = workoutDate;
        this.workoutTime = workoutTime;
        workoutDateTime = LocalDateTime.of(
                LocalDate.parse(workoutDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalTime.parse(workoutTime, DateTimeFormatter.ofPattern("h:mma")));
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

    public LocalDateTime getWorkoutDateTime() {
        return workoutDateTime;
    }
}
