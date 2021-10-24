package seedu.duke.exceptions.workout;

public class MissingWorkoutCalorieSeparatorException extends WorkoutException {
    @Override
    public String getMessage() {
        return "Your workout is missing the calorie separator /c" + System.lineSeparator()
                + "Please minimally have the format: add workout [workout_description] /c [calories]";
    }
}
