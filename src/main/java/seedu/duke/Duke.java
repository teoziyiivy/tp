package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.FluidExceptions;
import seedu.duke.exceptions.FoodBankException;
import seedu.duke.exceptions.MealException;
import seedu.duke.workout.ScheduleTracker;
import seedu.duke.workout.WorkoutTracker;

import java.io.IOException;
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
    private FoodBank foodbank;
    private DateTracker dateTracker;
    private Storage storage;

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
        storage = new Storage("Food.txt");
        commandManager = new CommandManager(storage, fluid,
                meal, scheduleTracker, workoutTracker,
                weightTracker, userHelp);
        foodbank = new FoodBank();
        dateTracker = new DateTracker();
    }

    public void run() {
        while (!commandManager.isExit) {
            try {
                System.out.println(Ui.HORIZONTAL_BAR);
                System.out.print(Ui.USER_PROMPT);
                commandManager.commandChecker();
            } catch (DateTimeParseException e) {
                System.out.println(ClickfitMessages.DATE_ERROR);
            } catch (NumberFormatException e) {
                System.out.println(ClickfitMessages.NUMBER_ERROR);
            } catch (NullPointerException e) {
                System.out.println(ClickfitMessages.INCORRECT_INPUT);
            } catch (DukeException ignored) {
                continue;
            }  catch (MealException e) {
                System.out.println(ClickfitMessages.MEAL_NAME_ERROR);
            } catch (FluidExceptions e) {
                System.out.println(ClickfitMessages.FLUID_ADD_FORMAT_ERROR);
            } catch (FoodBankException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("ccc");
            }
        }
        LogManager.getLogManager().reset();
    }

    public void uiRun() {
        ui.welcomeMessage();
        ui.getInfo();
        while (!ui.isValidStartup) {
            ui.memoryStartup();
        }
    }
}
