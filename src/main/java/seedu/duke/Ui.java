package seedu.duke;

import java.util.Objects;
import java.util.Scanner;

import static seedu.duke.ClickfitMessages.MESSAGE_A;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_PROMPT;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_Y_INPUT;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_N_INPUT;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_INCORRECT_INPUT;
import static seedu.duke.ClickfitMessages.CALCULATOR_PROMPT;

public class Ui {

    private Scanner uiScanner;
    public static final String HORIZONTAL_BAR = "____________________________________________________________"
        + "____________________________________________________________"; //placeholder
    public static final String USER_PROMPT = "Enter command: "; //placeholder
    public boolean isValidStartup;
    protected String sex;
    protected int weight;
    protected int height;
    protected int age;
    protected int activityLevel;


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

    public void getInfo() {
        System.out.println(CALCULATOR_PROMPT);
        String uiInput = uiScanner.nextLine();

        if (uiInput.trim().equals("Y")) {
            System.out.println("what is your SEX : M / F ?");
            uiInput = uiScanner.nextLine();
            sex = uiInput;
            System.out.println("what is your weight in kg?");
            uiInput = uiScanner.nextLine();
            weight = Integer.parseInt(uiInput);
            System.out.println("what is your height in cm?");
            uiInput = uiScanner.nextLine();
            height = Integer.parseInt(uiInput);
            System.out.println("what is your age in years?");
            uiInput = uiScanner.nextLine();
            age = Integer.parseInt(uiInput);
            System.out.println("what is your activity level from a scale of 1 - 5?");
            uiInput = uiScanner.nextLine();
            activityLevel = Integer.parseInt(uiInput);

            Calculator calculator = new Calculator(sex, weight, height, age, activityLevel);
            calculator.getBmi();
            calculator.getIdealCalories();
        }

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
