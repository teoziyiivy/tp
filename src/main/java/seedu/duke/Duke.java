package seedu.duke;


import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<WeightTracker> weights = new ArrayList<>();

    public static void printAddWeightResponse() {
        System.out.println("Noted! CLI.ckFit has recorded your weight as " + weights.get(weights.size() - 1).getWeight() 
                           + ". Check back for your progress!");
    }

    public static void printAddWeightException() {
        System.out.println("There was a problem adding your weight.");
    }

    public static void addWeight(String line) throws AddWeightException {
        if (!line.matches("(.*) (.*)")) {
            throw new AddWeightException();
        } else {
            //extracting the weight and date
            String weight = line.replaceAll(" .+", "");
            String date = line.replaceAll(".+ ", "");
            weights.add(new WeightTracker(weight, date));
            printAddWeightResponse();
        }
    }

    public static void printWeight() {
        System.out.println("Here are your recorded weights:");
        for (int i = 0; i < weights.size(); i++) {
            weights.get(i).checkWeight();
        }

    }

    public static void readInput(String line) {
        String[] splitLine = line.split(" ", 2);
        String command = splitLine[0];
        line = line.replaceAll("^" + command + " ", "");
        if (command.equals("checkweight")) {
            printWeight();
        } else if (command.equals("saveweight")) {
            try {
                addWeight(line);
            } catch (AddWeightException e) {
                printAddWeightException();
            }
        }
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        //continue to run program unless types "bye" to exit program
        while (!line.equals("bye")) {
            readInput(line);
            line = in.nextLine();
        }
=======
import seedu.duke.gym.GymManager;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Duke {

    private Meal meal;
    private Ui ui;
    private Fluid fluid;
    private GymManager gymManager;
    private CommandManager commandManager;


    public Duke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo + "\n");
        System.out.println("What is your command?");
        meal = new Meal();
        ui = new Ui();
        fluid = new Fluid();
        gymManager = new GymManager();
        commandManager = new CommandManager(fluid,meal,gymManager);
    }

    public void run() {
        commandManager.commandChecker();
    }


    public static void main(String[] args) {
        new Duke().run();
    }
}
