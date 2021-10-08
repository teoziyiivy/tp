package seedu.duke;

import seedu.duke.gym.GymManager;

@SuppressWarnings("ALL")
public class Duke {

    private Meal meal;
    private Ui ui;
    private Fluid fluid;
    private GymManager gymManager;
    private WeightTracker weightTracker;
    private CommandManager commandManager;


    public Duke() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo + "\n");
        System.out.println("What is your command?");
        meal = new Meal();
        ui = new Ui();
        fluid = new Fluid();
        gymManager = new GymManager();
        weightTracker = new WeightTracker();
        commandManager = new CommandManager(fluid, meal, gymManager, weightTracker);
    }

    public void run() {
        commandManager.commandChecker();
    }


    public static void main(String[] args) {
        new Duke().run();
    }
}
