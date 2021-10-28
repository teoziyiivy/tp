package seedu.duke.exceptions.foodbank;

public class EmptyFoodDescription extends FoodBankException {
    @Override
    public String getMessage() {
        return "Please enter a valid meal/fluid description!";
    }
}
