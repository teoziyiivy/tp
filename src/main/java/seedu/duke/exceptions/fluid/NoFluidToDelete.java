package seedu.duke.exceptions.fluid;

public class NoFluidToDelete extends FluidExceptions {
    @Override
    public String getMessage() {
        return "This fluid entry does not exist. You may try again or wish to add a fluid entry first.";
    }
}
