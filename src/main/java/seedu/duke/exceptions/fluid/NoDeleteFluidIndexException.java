package seedu.duke.exceptions.fluid;

public class NoDeleteFluidIndexException extends FluidExceptions {
    @Override
    public String getMessage() {
        return "Please enter a valid fluid index. You may wish to list to check the index numbers.";
    }
}
