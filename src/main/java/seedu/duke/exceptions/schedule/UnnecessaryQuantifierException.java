package seedu.duke.exceptions.schedule;

public class UnnecessaryQuantifierException extends ScheduleException {
    @Override
    public String getMessage() {
        return "Unnecessary activity quantifier splitter \"x\" detected." + System.lineSeparator()
                + "Please enter [activity name]:[distance] for your distance based workout activities if your"
                + " activity name is either running/swimming/cycling.";
    }
}
