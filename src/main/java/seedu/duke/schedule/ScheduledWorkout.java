package seedu.duke.schedule;

import seedu.duke.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

//@@author arvejw
public class ScheduledWorkout {
    private String workoutDescription;
    private ArrayList<WorkoutActivity> activities;
    private String workoutDate;
    private String workoutTime;
    private LocalDateTime workoutDateTime;

    private boolean isRecurring;

    public ScheduledWorkout(String workoutDescription, String workoutDate, String workoutTime,
                            Map<String, ArrayList<Integer>> activityMap, boolean isRecurring) {
        this.workoutDescription = workoutDescription;
        this.workoutDate = workoutDate;
        this.workoutTime = workoutTime;
        workoutDateTime = LocalDateTime.of(
                LocalDate.parse(workoutDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalTime.parse(workoutTime, DateTimeFormatter.ofPattern("HH:mm")));
        this.isRecurring = isRecurring;
        activities = new ArrayList<>();
//        if (!activityMap.isEmpty()) {
//            for (var entry : activityMap.entrySet()) {
//                activities.add(
//                        new WorkoutActivity(
//                                entry.getKey(), entry.getValue(),
//                                WorkoutActivity.isDistanceActivity(entry.getKey())
//                        )
//                );
//            }
//        }
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

    public String getActivitiesAsStringToPrint() {
        String output = System.lineSeparator() + "Activities Breakdown: " + System.lineSeparator();
        if (activities.isEmpty()) {
            return output + "nil" + System.lineSeparator() + "____________________________";
        }
        int currentIndex = 1;
        for (WorkoutActivity a : activities) {
            if (a.isDistanceActivity()) {
                output += currentIndex + ". " + a.getActivityDescription() + ": " + a.getActivityDistance()
                        + "metres" + System.lineSeparator();
            } else {
                output += currentIndex + ". " + a.getActivityDescription() + ": " + a.getActivitySets() + "sets x "
                        + a.getActivityReps() + "reps" + System.lineSeparator();
            }
            currentIndex++;
        }
        return output + System.lineSeparator() + "____________________________";
    }

    public String getScheduledWorkoutAsString() {
        String output = workoutDescription + Parser.DATE_SEPARATOR
                + workoutDate + Parser.TIME_SEPARATOR + workoutTime;
        output += getActivitiesAsString();
        if (isRecurring) {
            output += Parser.RECURRING_FLAG;
        }
        return output;
    }

    private String getActivitiesAsString() {
        StringBuilder activityString = new StringBuilder();
        if (!activities.isEmpty()) {
            activityString.append(Parser.ACTIVITY_SEPARATOR);
            int currentIndex = 0;
            for (WorkoutActivity activity : activities) {
                if (activity.isDistanceActivity()) {
                    activityString.append(activity.getActivityDescription())
                            .append(Parser.ACTIVITY_SPLITTER)
                            .append(activity.getActivityDistance());
                } else {
                    activityString.append(activity.getActivityDescription())
                            .append(Parser.ACTIVITY_SPLITTER)
                            .append(activity.getActivitySets())
                            .append(Parser.QUANTIFIER_SPLITTER)
                            .append(activity.getActivityReps());
                }
                currentIndex++;
                activityString.append(
                        (currentIndex < activities.size()) ? Parser.MULTIPLE_ACTIVITY_MARKER : "");
            }
        }
        return activityString.toString();
    }
}
