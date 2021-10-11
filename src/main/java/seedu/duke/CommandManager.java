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
        String[] inputArguments = input.trim().split(" ", 2);
        String command = inputArguments[0];
        // naming/way is implemented can be changed,
        // to handle input= oneword e.g "addschedule" which does not split so inputArguments.length will be 1
        // basically this is to avoid accessing inputArguments[1] which is out of bounds
        // inputArguments is null if user input < 2 words
        String checkedInputArguments = (inputArguments.length == 2) ? inputArguments[1] : null;
      
        while (!(command.equals(Keywords.INPUT_BYE))) {

            if (command.equals(Keywords.INPUT_MEAL)) {
                meal.addMeal(inputArguments[1]);
            } else if (command.equals(Keywords.DELETE_MEAL)) {
                meal.deleteMeal(inputArguments[1]);
            } else if (command.equals(Keywords.LIST_MEAL)) {
                meal.listMeals();
            } else if (command.equals(Keywords.INPUT_WORKOUT)) {
                gymManager.addCompletedWorkout(checkedInputArguments);

            } else if (command.equals(Keywords.INPUT_WORKOUT_SCHEDULE)) {
                gymManager.addScheduledWorkout(checkedInputArguments);

            } else if (command.equals(Keywords.INPUT_DRINKS)) {
                fluid.sayDrank(input);

            } else if (command.equals(Keywords.INPUT_ADD_WEIGHT)) {
                weightTracker.readInput(input);

            } else if (command.equals(Keywords.INPUT_CHECK_WEIGHT)) {
                weightTracker.readInput(input);

            } else if (command.equals(Keywords.INPUT_HELP)) {
                userHelp.generateUserHelpParameters(checkedInputArguments);

            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");//overall error check
            }
            input = in.nextLine();
            inputArguments = input.trim().split(" ", 2);
            command = inputArguments[0];
            checkedInputArguments = (inputArguments.length == 2) ? inputArguments[1] : null;
        }
        System.out.println(CREDITS);

    }

}