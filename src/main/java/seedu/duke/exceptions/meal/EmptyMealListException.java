package seedu.duke.exceptions.meal;

public class EmptyMealListException extends MealException{
    @Override
    public String getMessage() {
        return "Your meal list is empty!";
    }
}
