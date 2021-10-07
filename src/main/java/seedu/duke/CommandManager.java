package seedu.duke;

import seedu.duke.gym.GymManager;

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

    public void commandChecker() {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        String[] result = input.trim().split(" ", 2);
        String command = result[0];


        while (!(command.equals(Keywords.INPUT_BYE))) {

            if (command.equals(Keywords.INPUT_MEAL)) {
                meal.addMeal(input);

            } else if (command.equals(Keywords.INPUT_WORKOUT)) {
                gymManager.doneGymWorkout(result[1]);

            } else if (command.equals(Keywords.INPUT_WORKOUT_SCHEDULE)) {
                gymManager.addGymWorkout(result[1]);

            } else if (command.equals(Keywords.INPUT_DRINKS)) {
                fluid.sayDrank(input);

            } else if (command.equals(Keywords.INPUT_ADD_WEIGHT)) {
                weightTracker.readInput(input);

            } else if (command.equals(Keywords.INPUT_CHECK_WEIGHT)) {
                weightTracker.readInput(input);

            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");//overall error check
            }
            input = in.nextLine();
            result = input.trim().split(" ", 2);
            command = result[0];

        }
        System.out.println("    Bye! Hope to see you again soon!");

    }

}