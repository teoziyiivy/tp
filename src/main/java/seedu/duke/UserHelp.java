package seedu.duke;

import static seedu.duke.ClickfitMessages.HELP_COMMANDS;
import static seedu.duke.ClickfitMessages.HELP_UG;

public class UserHelp {

    public static void generateUserHelpParameters(String input) {
        if (input.trim().equals("commands")) {
            getCommands();

        } else {
            getUG();
        }
    }

    public static void getCommands() {
        System.out.println(HELP_COMMANDS);
    }

    public static void getUG() {
        System.out.println(HELP_UG);
    }

}
