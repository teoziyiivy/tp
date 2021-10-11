package seedu.duke;

import seedu.duke.gym.GymManager;
import java.util.Scanner;
import static seedu.duke.ClickfitMessages.CREDITS;

public class CommandManager {
    protected GymManager gymManager;
    protected Meal meal;
    protected Fluid fluid;
    protected WeightTracker weightTracker;
    protected UserHelp userHelp;

    public CommandManager(Fluid fluid, Meal meal, GymManager gymManager, WeightTracker weightTracker,
                          UserHelp userHelp) {
        this.fluid = fluid;
        this.meal = meal;
        this.gymManager = gymManager;
        this.weightTracker = weightTracker;
        this.userHelp = userHelp;
    }

    public void commandChecker() {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        String[] splitResult = input.trim().split(" ", 2); //todo: maybe also add this to parser class?
        String command = splitResult[0];
        // naming/way is implemented can be changed,
        // to handle input= oneword e.g "addschedule" which does not split so inputArguments.length will be 1
        // basically this is to avoid accessing inputArguments[1] which is out of bounds
        // inputArguments is null if user input < 2 words
        String inputArguments = (splitResult.length == 2) ? splitResult[1] : null;

        while (!(command.equals(Keywords.INPUT_BYE))) {

            if (command.equals(Keywords.INPUT_MEAL)) {
                meal.addMeal(input);

            } else if (command.equals(Keywords.INPUT_WORKOUT)) {
                gymManager.addCompletedWorkout(inputArguments);

            } else if (command.equals(Keywords.INPUT_WORKOUT_SCHEDULE)) {
                gymManager.addScheduledWorkout(inputArguments);

            } else if (command.equals(Keywords.INPUT_DRINKS)) {
                fluid.sayDrank(input);

            } else if (command.equals(Keywords.INPUT_ADD_WEIGHT)) {
                weightTracker.readInput(input);

            } else if (command.equals(Keywords.INPUT_CHECK_WEIGHT)) {
                weightTracker.readInput(input);

            } else if (command.equals(Keywords.INPUT_HELP)) {
                userHelp.generateUserHelpParameters(inputArguments);

            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");//overall error check
            }
            input = in.nextLine();
            splitResult = input.trim().split(" ", 2);
            command = splitResult[0];
            inputArguments = (splitResult.length == 2) ? splitResult[1] : null;
        }
        System.out.println(CREDITS);

    }

}