package seedu.duke;

import seedu.duke.gym.GymManager;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import static seedu.duke.ClickfitMessages.CREDITS;

public class CommandManager {
    protected GymManager gymManager;
    protected Meal meal;
    protected Fluid fluid;
    protected WeightTracker weightTracker;
    protected UserHelp userHelp;
    protected Scanner scanner;
    protected boolean isExit;
    protected String command;
    protected String inputArguments;

    public CommandManager(Fluid fluid, Meal meal, GymManager gymManager, WeightTracker weightTracker,
                          UserHelp userHelp) {
        this.fluid = fluid;
        this.meal = meal;
        this.gymManager = gymManager;
        this.scanner = new Scanner(System.in);
        this.weightTracker = weightTracker;
        this.userHelp = userHelp;
        this.isExit = false;
    }

    public void commandChecker() throws DukeException {
        String input = scanner.nextLine();
        String[] splitResults = input.trim().split(" ", 2);
        command = splitResults[0];
        inputArguments = (splitResults.length == 2) ? splitResults[1] : null;
        switch (command) {
        case Keywords.INPUT_MEAL:
            meal.addMeal(inputArguments);
            break;
        case Keywords.DELETE_MEAL:
            meal.deleteMeal(inputArguments);
            break;
        case Keywords.LIST_MEAL:
            meal.listMeals();
            break;
        case Keywords.INPUT_WORKOUT:
            gymManager.addCompletedWorkout(inputArguments);

            break;
        case Keywords.INPUT_DELETE_WORKOUT:
            gymManager.deleteCompletedWorkout(inputArguments);
            break;
        case Keywords.INPUT_LIST_WORKOUT:
            gymManager.listCompletedWorkouts();
            break;
        case Keywords.INPUT_WORKOUT_SCHEDULE:
            gymManager.addScheduledWorkout(inputArguments);
            break;
        case Keywords.INPUT_DELETE_SCHEDULE:
            gymManager.deleteScheduledWorkout(inputArguments);
            break;
        case Keywords.INPUT_LIST_SCHEDULE:
            gymManager.listScheduledWorkouts();
            break;
        case Keywords.INPUT_DRINKS:
            if (inputArguments != null) {
                try {
                    fluid.addFluid(inputArguments);
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter in the format: [fluid_name] /c [calorie_intake] "
                            + "/v [volume] /d [dd/mm/yyyy] /t [hh:mm]");
                }
            } else {
                System.out.println("Please enter in the format: [fluid_name] /c [calorie_intake] "
                        + "/v [volume] /d [dd/mm/yyyy] /t [hh:mm]");
            }
            break;
        case Keywords.DELETE_DRINKS:
            if (inputArguments != null) {
                if (fluid.fluidArray.size() == 0) {
                    System.out.println("You have no existing fluid entries to delete.");
                } else {
                    fluid.deleteFluid(inputArguments);
                }
            } else {
                System.out.println("Please enter in the format: deletefluid [entry_number]");
            }
            break;
        case Keywords.LIST_DRINKS:
            fluid.listFluid();
            break;
        case Keywords.INPUT_ADD_WEIGHT:
            try {
                weightTracker.readInput(input);
            } catch (DukeException e) {
                return;
            } catch (DateTimeParseException e) {
                weightTracker.printAddWeightException();
            }
            break;
        case Keywords.INPUT_DELETE_WEIGHT:
            try {
                weightTracker.readInput(input);
            } catch (DukeException e) {
                return;
            }
            break;
        case Keywords.INPUT_CHECK_WEIGHT:
            try {
                weightTracker.readInput(input);
            } catch (DukeException e) {
                return;
            }
            weightTracker.readInput(input);
            break;
        case Keywords.INPUT_HELP:
            UserHelp.generateUserHelpParameters(inputArguments);
            break;
        case Keywords.INPUT_BYE:
            isExit = true;
            System.out.println(CREDITS);
            break;
        default:
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
    }
}