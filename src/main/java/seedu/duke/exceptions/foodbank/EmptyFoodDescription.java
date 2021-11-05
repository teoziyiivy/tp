package seedu.duke.exceptions.foodbank;

//@@author pragyan01
public class EmptyFoodDescription extends FoodBankException {
    @Override
    public String getMessage() {
        return "Please enter a valid meal/fluid description!";
    }
}
