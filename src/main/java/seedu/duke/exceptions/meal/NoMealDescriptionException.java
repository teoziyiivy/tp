package seedu.duke.exceptions.meal;

//@@author VishalJeyaram
/**
 * Custom exception for when user does not key in the description of the meal he or she wishes to add.
 */
public class NoMealDescriptionException extends MealException {
    @Override
    public String getMessage() {
        return "Please enter the description/name of the meal you wish to add!";
    }
}
