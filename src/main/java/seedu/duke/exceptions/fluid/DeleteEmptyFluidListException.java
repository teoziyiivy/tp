package seedu.duke.exceptions.fluid;

public class DeleteEmptyFluidListException extends FluidExceptions{
    @Override
    public String getMessage() {
        return "You don't have any fluid entries to delete. You may wish to add a fluid entry first.";
    }
}
