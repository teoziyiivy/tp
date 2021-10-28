package seedu.duke.exceptions.meal;

public class NoSuchMealIndexException extends MealException {
    @Override
    public String getMessage() {
        return "Please enter a proper meal index. Use \"list meals all\" to view each meal's index";
    }
}
