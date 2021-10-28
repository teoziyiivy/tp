package seedu.duke.exceptions.meal;

public class NoMealDetailsException extends MealException {
    @Override
    public String getMessage() {
        return "Please enter the details of the meal you wish to add!";
    }
}
