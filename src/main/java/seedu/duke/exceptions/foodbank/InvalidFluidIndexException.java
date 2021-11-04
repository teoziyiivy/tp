package seedu.duke.exceptions.foodbank;

public class InvalidFluidIndexException extends FoodBankException {
    @Override
    public String getMessage() {
        return "Please enter a valid fluid index! Use \"library listfluids\" to see the fluid indexes";
    }
}
