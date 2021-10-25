package seedu.duke.exceptions.schedule;

public class EmptyScheduleListException extends ScheduleException {
    @Override
    public String getMessage() {
        return "Your workout schedule is empty!";
    }
}
