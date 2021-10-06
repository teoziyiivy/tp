package seedu.duke;

import java.util.Scanner;

public class Ui {

    protected Scanner in;

    /**
     * Instantiates the Ui object and allows the user to send their commands.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String sendInput() {
        return in.nextLine();
    }
}
