package seedu.duke;

import java.util.Objects;
import java.util.Scanner;

import static seedu.duke.ClickfitMessages.MESSAGE_A;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_PROMPT;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_Y_INPUT;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_N_INPUT;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_INCORRECT_INPUT;


public class Ui {

    private Scanner uiScanner;
    public boolean isValidStartup;

    public Ui() {
        uiScanner = new Scanner(System.in);
        isValidStartup = false;
    }

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

    public void memoryStartup() {
        System.out.println(MEMORY_STARTUP_PROMPT);
        String uiInput = uiScanner.nextLine();
        assert !Objects.equals(uiInput, "");
        if (uiInput.trim().equals("Y")) {
            System.out.println(MEMORY_STARTUP_Y_INPUT);
            System.out.println("What would you like to start with?");
            isValidStartup = true;
            //future: call list from command manager class
        } else if (uiInput.trim().equals("N")) {
            System.out.println(MEMORY_STARTUP_N_INPUT);
            System.out.println("What would you like to start with?");
            isValidStartup = true;
        } else {
            System.out.println(MEMORY_STARTUP_INCORRECT_INPUT);
        }
    }
}
