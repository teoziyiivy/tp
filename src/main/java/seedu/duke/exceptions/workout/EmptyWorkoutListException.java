package seedu.duke.exceptions.workout;

public class EmptyWorkoutListException extends WorkoutException {
    @Override
    public String getMessage() {
        return "Your workout list is empty!";
    }
}
