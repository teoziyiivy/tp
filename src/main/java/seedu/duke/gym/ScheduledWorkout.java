package seedu.duke.gym;

import seedu.duke.Tracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ScheduledWorkout extends Tracker {
    private String workoutDescription;
    private LocalDate workoutDate;
    private LocalTime workoutTime;
    private LocalDateTime workoutDateTime;

    public ScheduledWorkout(String workoutDescription, LocalDate workoutDate, LocalTime workoutTime) {
        this.workoutDescription = workoutDescription;
        this.workoutDate = workoutDate;
        this.workoutTime = workoutTime;
        this.workoutDateTime = LocalDateTime.of(workoutDate, workoutTime);
    }

    public String getWorkoutDescription() {
        return workoutDescription;
    }

    public void setWorkoutDescription(String workoutDescription) {
        this.workoutDescription = workoutDescription;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(LocalDate workoutDate) {
        this.workoutDate = workoutDate;
    }

    public LocalTime getWorkoutTime() {
        return workoutTime;
    }

    public void setWorkoutTime(LocalTime workoutTime) {
        this.workoutTime = workoutTime;
    }

    public LocalDateTime getWorkoutDateTime() {
        return workoutDateTime;
    }

    public void setWorkoutDateTime(LocalDateTime workoutDateTime) {
        this.workoutDateTime = workoutDateTime;
    }
}
