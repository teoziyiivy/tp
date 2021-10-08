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
        String logo = "   ______  _____     _____            __       ________  _   _\n"
                + " .' ___  ||_   _|   |_   _|          [  |  _  |_   __  |(_) / |_\n"
                + "/ .'   \\_|  | |       | |      .---.  | | / ]   | |_ \\_|__ `| |-'\n"
                + "| |         | |   _   | |     / /'`\\] | '' <    |  _|  [  | | |\n"
                + "\\ `.___.'\\ _| |__/ | _| |_  _ | \\__.  | |`\\ \\  _| |_    | | | |,\n"
                + " `.____ .'|________||_____|(_)'.___.'[__|  \\_]|_____|  [___]\\__/";

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
