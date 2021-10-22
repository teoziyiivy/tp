package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.FluidExceptions;
import seedu.duke.exceptions.FoodBankException;
import seedu.duke.exceptions.LoadException;
import seedu.duke.exceptions.MealException;
import seedu.duke.exceptions.ScheduleException;
import seedu.duke.schedule.ScheduleTracker;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.logging.LogManager;

import static seedu.duke.ClickfitMessages.MEMORY_STARTUP_INCORRECT_INPUT;

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

    public static void main(String[] args) throws DukeException, FoodBankException, FluidExceptions {
        new Duke().run();
    }

    public Duke() throws DukeException, FoodBankException, FluidExceptions {
        try {
            meal = new Meal();
            ui = new Ui();
            fluid = new Fluid();
            scheduleTracker = new ScheduleTracker();
            workoutTracker = new WorkoutTracker();
            weightTracker = new WeightTracker();
            userHelp = new UserHelp();
            storage = new Storage("Food.txt", "Foodbank.txt", "Weight.txt");
            commandManager = new CommandManager(storage, fluid,
                    meal, scheduleTracker, workoutTracker,
                    weightTracker, userHelp);
            foodbank = new FoodBank();
            dateTracker = new DateTracker();
            ui.welcomeMessage();
            ui.getInfo();
            if (ui.memoryStartup()) {
                meal.meals = storage.loadMeals();
                fluid.fluidArray = storage.loadFluids();
                weightTracker.weightsArray = storage.loadWeights();
                FoodBank.meals = storage.loadMealLibrary();
                FoodBank.fluids = storage.loadFluidLibrary();
                workoutTracker.workouts = storage.loadWorkouts();
                scheduleTracker.loadScheduleData();
                storage.printLoadedLists();
                System.out.println("What would you like to start with?");
            }
        } catch (LoadException e) {
            System.out.println(MEMORY_STARTUP_INCORRECT_INPUT);
        } catch (IOException e) {
            System.out.println("ccc");
        }
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
            } catch (ScheduleException e) {
                System.out.println(e.getMessage());
            } catch (MealException e) {
                System.out.println(ClickfitMessages.MEAL_NAME_ERROR);
            } catch (FluidExceptions e) {
                System.out.println(ClickfitMessages.FLUID_ADD_FORMAT_ERROR);
            } catch (FoodBankException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("No saved data found. CLI.ckFit shall create one for you!");
            }
        }
        LogManager.getLogManager().reset();
    }
}
