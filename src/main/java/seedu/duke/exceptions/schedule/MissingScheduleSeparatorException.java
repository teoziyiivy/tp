package seedu.duke.exceptions.schedule;

public class MissingScheduleSeparatorException extends ScheduleException {
    @Override
    public String getMessage() {
        return "Invalid or missing separator... " + System.lineSeparator()
                + "Please enter in the format: schedule [workout_description] /d [dd/mm/yyyy] /t [hh:mm]";
    }
}
