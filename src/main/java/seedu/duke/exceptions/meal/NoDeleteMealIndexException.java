package seedu.duke.exceptions.meal;

public class NoDeleteMealIndexException extends MealException {
    @Override
    public String getMessage() {
        return "Please enter the index of the meal you wish to delete!";
    }
}
