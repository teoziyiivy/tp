package seedu.duke.exceptions.foodbank;

public class NoFoodIndexException extends FoodBankException {
    @Override
    public String getMessage() {
        return "Please enter a valid meal/fluid index! "
                + "Use \"library listmeals\" to view each meal's index, and "
                + "use \"library listfluids\" to view each fluid's index!";
    }
}
