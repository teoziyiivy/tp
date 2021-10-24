package seedu.duke.exceptions.schedule;

public class InvalidActivityFormatException extends ScheduleException {
    @Override
    public String getMessage() {
        return "Please enter a single integer [distance in metres] for distance based "
                + "activities(swimming/running/cycling)." + System.lineSeparator() + "E.g. running:8000" + ""
                + System.lineSeparator() + "Enter two integers in the format [set]x[reps] for everything else."
                + System.lineSeparator() + "E.g. bench press:3x12";
    }
}
