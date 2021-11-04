package seedu.duke.exceptions.foodbank;

public class DuplicateFood extends FoodBankException {
    @Override
    public String getMessage() {
        return "This food entry already exists.";
    }
}
