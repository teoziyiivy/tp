package seedu.duke.exceptions;

public class ScheduleException extends Exception {
    @Override
    public String getMessage() {
        return "An unusual error has occurred in ScheduleTracker";
    }
}
