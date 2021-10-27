package seedu.duke.exceptions.meal;

public class MealException extends Exception {
    @Override
    public String getMessage() {
        return "An unknown error has occurred";
    }
}
