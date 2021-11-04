package seedu.duke.exceptions.fluid;

public class noVolumeEntered extends FluidExceptions {
    @Override
    public String getMessage() {
        return "Please enter volume.";
    }
}
