package seedu.duke.exceptions.meal;

public class NoMealDescriptionException extends MealException {
    @Override
    public String getMessage() {
        return "Please enter the description/name of the meal you wish to add!";
    }
}
