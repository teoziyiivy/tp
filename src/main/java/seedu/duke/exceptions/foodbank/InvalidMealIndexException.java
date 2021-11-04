package seedu.duke.exceptions.foodbank;

public class InvalidMealIndexException extends FoodBankException {
    @Override
    public String getMessage() {
        return "Please enter a valid meal index! Use \"library listmeals\" to see the meal indexes";
    }
}
