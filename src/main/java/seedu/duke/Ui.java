package seedu.duke;

import java.util.Objects;
import java.util.Scanner;
import static seedu.duke.ClickfitMessages.MESSAGE_B;
import static seedu.duke.ClickfitMessages.MESSAGE_A;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_PROMPT;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_Y_INPUT;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_N_INPUT;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_INCORRECT_INPUT;


public class Ui {
    public static void welcomeMessage() {
        String logo = "   ______  _____     _____            __       ________  _   _\n"
                + " .' ___  ||_   _|   |_   _|          [  |  _  |_   __  |(_) / |_\n"
                + "/ .'   \\_|  | |       | |      .---.  | | / ]   | |_ \\_|__ `| |-'\n"
                + "| |         | |   _   | |     / /'`\\] | '' <    |  _|  [  | | |\n"
                + "\\ `.___.'\\ _| |__/ | _| |_  _ | \\__.  | |`\\ \\  _| |_    | | | |,\n"
                + " `.____ .'|________||_____|(_)'.___.'[__|  \\_]|_____|  [___]\\__/";

        System.out.println("Greetings from\n" + logo + "\n");
        //future implementation: if list is empty, sout message A, else sout mesage B
        System.out.println(MESSAGE_A);
        //System.out.println(MESSAGE_B);
    }

    public static void memoryStartup() {
        System.out.println(MEMORY_STARTUP_PROMPT);

        String uiInput;
        Scanner in = new Scanner(System.in);
        uiInput = in.nextLine();
        assert !Objects.equals(uiInput, "");

        if (uiInput.trim().equals("Y")) {
            System.out.println(MEMORY_STARTUP_Y_INPUT);
            //future: call list from command manager class

        } else if (uiInput.trim().equals("N")) {
            System.out.println(MEMORY_STARTUP_N_INPUT);

        } else {
            System.out.println(MEMORY_STARTUP_INCORRECT_INPUT);
            uiInput = in.nextLine();
        }
        System.out.println("What would you like to start with?");
    }

}
