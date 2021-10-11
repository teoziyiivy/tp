package seedu.duke;

import seedu.duke.gym.GymManager;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CommandManager {
    protected GymManager gymManager;
    protected Meal meal;
    protected Fluid fluid;
    protected WeightTracker weightTracker;

    public CommandManager(Fluid fluid, Meal meal, GymManager gymManager, WeightTracker weightTracker) {
        this.fluid = fluid;
        this.meal = meal;
        this.gymManager = gymManager;
        this.weightTracker = weightTracker;
    }

    public void commandChecker() throws DukeException {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        String[] inputArguments = input.trim().split(" ", 2);
        String command = inputArguments[0];

        while (!(command.equals(Keywords.INPUT_BYE))) {

            switch (command) {
            case Keywords.INPUT_MEAL:
                meal.addMeal(input);
                break;
            case Keywords.INPUT_WORKOUT:
                gymManager.doneGymWorkout(inputArguments[1]);
                break;
            case Keywords.INPUT_WORKOUT_SCHEDULE:
                gymManager.addGymWorkout(inputArguments[1]);
                break;
            case Keywords.INPUT_DRINKS:
                try {
                    fluid.addFluid(inputArguments[1]);
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter in the format: deadline (desc) /d dd/mm/yyyy");
                }
                break;
            case Keywords.DELETE_DRINKS:
                fluid.deleteFluid(inputArguments[1]);
                break;
            case Keywords.LIST_DRINKS:
                fluid.listFluid();
                break;
            case Keywords.INPUT_ADD_WEIGHT:
            case Keywords.INPUT_CHECK_WEIGHT:
                weightTracker.readInput(input);
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");//overall error check
                break;
            }
            input = in.nextLine();
            inputArguments = input.trim().split(" ", 2);
            command = inputArguments[0];

        }
        System.out.println("    Bye! Hope to see you again soon!");

    }

}