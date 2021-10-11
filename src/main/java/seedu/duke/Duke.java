package seedu.duke;

import seedu.duke.gym.GymManager;

import java.time.format.DateTimeParseException;

@SuppressWarnings("ALL")
public class Duke {

    private Meal meal;
    private Ui ui;
    private Fluid fluid;
    private GymManager gymManager;
    private WeightTracker weightTracker;
    private CommandManager commandManager;
    private UserHelp userHelp;


    public static void main(String[] args) {
        new Duke().uiRun();
        new Duke().run();
    }


    public Duke() {
        meal = new Meal();
        ui = new Ui();
        fluid = new Fluid();
        gymManager = new GymManager();
        weightTracker = new WeightTracker();
        userHelp = new UserHelp();
        commandManager = new CommandManager(fluid, meal, gymManager, weightTracker, userHelp);
    }

    public void run() {
        try {
            while (!commandManager.isExit) {
                commandManager.commandChecker();
            }
        } catch (DateTimeParseException | DukeException e) {
            System.out.println("date problem");
        }
    }

    public void uiRun() {
        ui.welcomeMessage();
        ui.memoryStartup();
    }
}
