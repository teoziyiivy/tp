package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.MealException;
import seedu.duke.gym.ScheduleTracker;
import seedu.duke.gym.WorkoutTracker;
import java.time.format.DateTimeParseException;
import java.util.logging.LogManager;

@SuppressWarnings("ALL")
public class Duke {

    private Meal meal;
    private Ui ui;
    private Fluid fluid;
    private ScheduleTracker scheduleTracker;
    private WorkoutTracker workoutTracker;
    private WeightTracker weightTracker;
    private CommandManager commandManager;
    private UserHelp userHelp;

    public static void main(String[] args) throws DukeException {
        new Duke().uiRun();
        new Duke().run();
    }

    public Duke() {
        meal = new Meal();
        ui = new Ui();
        fluid = new Fluid();
        scheduleTracker = new ScheduleTracker();
        workoutTracker = new WorkoutTracker();
        weightTracker = new WeightTracker();
        userHelp = new UserHelp();
        commandManager = new CommandManager(fluid, meal, scheduleTracker, workoutTracker, weightTracker, userHelp);
    }

    public void run() {
        while (!commandManager.isExit) {
            try {
                commandManager.commandChecker();
            } catch (DateTimeParseException e) {
                System.out.println(ClickfitMessages.DATE_ERROR);
            } catch (DukeException ignored) {
                continue;
            } catch (NumberFormatException e) {
                System.out.println(ClickfitMessages.NUMBER_ERROR);
            } catch (MealException e) {
                System.out.println(ClickfitMessages.MEAL_NAME_ERROR);
            } catch (NullPointerException e) {
                System.out.println(ClickfitMessages.INCORRECT_INPUT);
            }
            LogManager.getLogManager().reset();
        }
    }

    public void uiRun() {
        ui.welcomeMessage();
        ui.memoryStartup();
    }
}
