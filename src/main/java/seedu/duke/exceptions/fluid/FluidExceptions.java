package seedu.duke.exceptions.fluid;

public class FluidExceptions extends Exception {
    @Override
    public String getMessage() {
        return "An unknown error has occurred in FluidTracker";
    }
}
