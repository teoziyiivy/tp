package seedu.duke.exceptions.foodbank;

public class FoodBankException extends Exception {
    @Override
    public String getMessage() {
        return "Unknown Error occurred";
    }
}
