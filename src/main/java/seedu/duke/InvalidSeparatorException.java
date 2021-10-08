package seedu.duke;

public class InvalidSeparatorException extends Exception {
    public String getMessage() {
        return "Invalid separator encountered, please enter a valid separator!";
    }
}
