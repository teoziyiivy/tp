package seedu.duke.workout;

public class WorkoutActivity {
    private String activityDescription;
    private boolean isDistanceActivity = false;
    private int activityDistance;
    private int activitySets;
    private int activityReps;
    public static final String ACTIVITY_SWIMMING = "swimming";
    public static final String ACTIVITY_RUNNING = "running";
    public static final String ACTIVITY_CYCLING = "cycling";


    public WorkoutActivity(String activityDescription, int[] activityQuantifier, boolean isDistanceActivity) {
        this.activityDescription = activityDescription;
        if (isDistanceActivity) {
            activityDistance = activityQuantifier[0];
            this.isDistanceActivity = true;
        } else {
            activitySets = activityQuantifier[0];
            activityReps = activityQuantifier[1];
        }
    }

    public static boolean isDistanceActivity(String activityDescription) {
        if (activityDescription.equals(ACTIVITY_SWIMMING)
                || activityDescription.equals(ACTIVITY_RUNNING) || activityDescription.equals(ACTIVITY_CYCLING)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDistanceActivity() {
        return isDistanceActivity;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public int getActivityDistance() {
        return activityDistance;
    }

    public int getActivitySets() {
        return activitySets;
    }

    public int getActivityReps() {
        return activityReps;
    }
}
