package seedu.duke.exceptions.fluid;

public class NoVolumeEntered extends FluidExceptions {
    @Override
    public String getMessage() {
        return "Please enter volume.";
    }
}
