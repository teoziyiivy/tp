package seedu.duke;

import seedu.duke.exceptions.LoadException;
import java.util.Scanner;
import static seedu.duke.ClickfitMessages.MESSAGE_A;
import static seedu.duke.ClickfitMessages.CALCULATOR_PROMPT;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_Y_INPUT;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_N_INPUT;
import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_PROMPT;

//@@author { E/V}
public class Ui {

    private Scanner uiScanner;
    public static final String HORIZONTAL_BAR = "____________________________________________________________"
            + "____________________________________________________________"; //placeholder
    public static final String USER_PROMPT = "Enter command: "; //placeholder
    protected String sex;
    protected int weight;
    protected int height;
    protected int age;
    protected int activityLevel;


    public Ui() {
        uiScanner = new Scanner(System.in);
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
        String uiInput;
        boolean answerIsCorrect = false;
        boolean flag = false;
        while (!flag) {
            uiInput = uiScanner.nextLine();
            if (uiInput.isEmpty()) {
                System.out.println(System.lineSeparator());
                return;
            } else if (uiInput.trim().equals("y")) {
                flag = true;
            } else {
                System.out.println(ClickfitMessages.INCORRECT_INPUT);
            }
        }

        while (!answerIsCorrect) {
            System.out.println("what is your SEX : M / F ?");
            uiInput = uiScanner.nextLine();
            sex = uiInput;
            if (sex.equals("M") || sex.equals("F")) {
                answerIsCorrect = true;
            }
        }
        answerIsCorrect = false;
        while (!answerIsCorrect) {
            System.out.println("what is your weight in kg?");
            uiInput = uiScanner.nextLine();
            weight = Integer.parseInt(uiInput);
            answerIsCorrect = true;
        }
        answerIsCorrect = false;
        while (!answerIsCorrect) {
            System.out.println("what is your height in cm?");
            uiInput = uiScanner.nextLine();
            height = Integer.parseInt(uiInput);
            answerIsCorrect = true;
        }
        answerIsCorrect = false;
        while (!answerIsCorrect) {
            System.out.println("what is your age in years?");
            uiInput = uiScanner.nextLine();
            age = Integer.parseInt(uiInput);
            answerIsCorrect = true;
        }
        answerIsCorrect = false;
        while (!answerIsCorrect) {
            System.out.println("what is your activity level from a scale of 1 - 5?");
            uiInput = uiScanner.nextLine();
            activityLevel = Integer.parseInt(uiInput);
            if ((activityLevel > 0 ) && (activityLevel < 6)) {
                answerIsCorrect = true;
            }
        }
        Calculator calculator = new Calculator(sex, weight, height, age, activityLevel);
        calculator.getBmi();
        calculator.getIdealCalories();
    }

    public boolean memoryStartup() throws LoadException {
        System.out.println(MEMORY_STARTUP_PROMPT);
        String uiInput = uiScanner.nextLine();
        if (uiInput.equals("y")) {
            System.out.println(MEMORY_STARTUP_Y_INPUT);
            return true;
        } else if (uiInput.isEmpty()) {
            System.out.println(MEMORY_STARTUP_N_INPUT);
            System.out.println(System.lineSeparator() + "What would you like to do?");
            return false;
        } else {
            throw new LoadException();
        }
    }
}
