package seedu.duke;

public class InvalidArgumentException extends Exception {
    public String getMessage() {
        return "Invalid arguments encountered, please enter valid arguments!";
    }
}
