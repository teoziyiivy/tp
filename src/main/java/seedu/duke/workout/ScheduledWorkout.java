package seedu.duke.workout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class ScheduledWorkout {
    private String workoutDescription;
    private ArrayList<WorkoutActivity> activities;
    private String workoutDate;
    private String workoutTime;
    private LocalDateTime workoutDateTime;

    private boolean isRecurring;

    public ScheduledWorkout(String workoutDescription, String workoutDate, String workoutTime,
                            Map<String, int[]> activityMap, boolean isRecurring) {
        this.workoutDescription = workoutDescription;
        this.workoutDate = workoutDate;
        this.workoutTime = workoutTime;
        workoutDateTime = LocalDateTime.of(
                LocalDate.parse(workoutDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalTime.parse(workoutTime, DateTimeFormatter.ofPattern("HH:mm")));
        this.isRecurring = isRecurring;
        activities = new ArrayList<>();
        if (!activityMap.isEmpty()) {
            for (var entry : activityMap.entrySet()) {
                activities.add(
                        new WorkoutActivity(
                                entry.getKey(), entry.getValue(),
                                WorkoutActivity.isDistanceActivity(entry.getKey())
                        )
                );
            }
        }
    }

    public ArrayList<WorkoutActivity> getActivities() {
        return activities;
    }

    public String getWorkoutDescription() {
        return workoutDescription;
    }

    public String getWorkoutDate() {
        return workoutDate;
    }

    public void incrementWorkoutDate(long days) {
        workoutDateTime = workoutDateTime.plusDays(days);
        workoutDate = workoutDateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public LocalDate getWorkoutDateAsLocalDate() {
        return workoutDateTime.toLocalDate();
    }

    public String getWorkoutTime() {
        return workoutTime;
    }

    public LocalDateTime getWorkoutDateTime() {
        return workoutDateTime;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public String isRecurringStatus() {
        return isRecurring ? " [R]" : "";
    }

    public String isRecurringStatusAsText() {
        return isRecurring ? "recurring " : "";
    }

    public String getActivitiesAsString() {
        String output = System.lineSeparator() + "Activities Breakdown: " + System.lineSeparator();
        if (activities.isEmpty()) {
            return output + "nil" + System.lineSeparator();
        }
        for (WorkoutActivity a : activities) {
            if (a.isDistanceActivity()) {
                output += a.getActivityDescription() + ": " + a.getActivityDistance()
                        + "metres" + System.lineSeparator();
            } else {
                output += a.getActivityDescription() + ": " + a.getActivitySets() + "sets x "
                        + a.getActivitySets() + "reps" + System.lineSeparator();
            }
        }
        return output;
    }
}
