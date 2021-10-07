package seedu.duke;

import seedu.duke.gym.GymManager;
import java.util.Scanner;

public class CommandManager {
    GymManager gymManager = new GymManager();

    public void commandChecker() {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        String[] result = input.trim().split(" ", 2);
        String command = result[0];


        while (!(command.equals(Keywords.INPUT_BYE))) {

            if (command.equals(Keywords.INPUT_MEAL)) {
                //input = mealInputCommand(input, in);

            } else if (command.equals(Keywords.INPUT_WORKOUT)) {
                //input = workoutInputCommand(input, in);
                gymManager.doneGymWorkout(result[1]);

            } else if (command.equals(Keywords.INPUT_WORKOUT_SCHEDULE)) {
                //input = workoutInputCommand(input, in);
                gymManager.addGymWorkout(result[1]);

            } else if (command.equals(Keywords.INPUT_DRINKS)) {
                //input = drinksInputCommand(input, in);

            } else if (command.equals(Keywords.INPUT_ADD_WEIGHT)) {
                //input = addweightInputCommand(input, in);

            } else if (command.equals(Keywords.INPUT_CHECK_WEIGHT)) {
                //input = checkweightInputCommand(input, in);

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