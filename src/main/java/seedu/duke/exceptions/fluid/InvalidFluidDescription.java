package seedu.duke.exceptions.fluid;

public class InvalidFluidDescription extends FluidExceptions {
    @Override
    public String getMessage() {
        return "Please enter a valid fluid description";
    }
}
