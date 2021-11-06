package seedu.duke.exceptions.fluid;

//@@author pragyan01
/**
 * Custom exception for when fluidList is empty.
 *
 * @author pragyan01
 */
public class EmptyFluidList extends FluidExceptions {
    @Override
    public String getMessage() {
        return "An unknown error has occurred in FluidTracker";
    }
}
