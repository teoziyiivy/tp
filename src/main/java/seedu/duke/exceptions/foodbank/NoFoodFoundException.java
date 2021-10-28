package seedu.duke.exceptions.foodbank;

import seedu.duke.ClickfitMessages;

public class NoFoodFoundException extends FoodBankException {
    @Override
    public String getMessage() {
        return ClickfitMessages.FOOD_BANK_EXCEPTION_MESSAGE;
    }
}
