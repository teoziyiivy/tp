package seedu.duke.exceptions.fluid;

/**
 * Custom exception for when unknown error related to fluid tracker occurs.
 *
 * @author pragyan01
 */
public class FluidExceptions extends Exception {
    @Override
    public String getMessage() {
        return "An unknown error has occurred in FluidTracker";
    }
}
