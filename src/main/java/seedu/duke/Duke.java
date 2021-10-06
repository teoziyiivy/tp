package seedu.duke;

import java.time.format.DateTimeParseException;

public class Duke {

    private Meal meal;
    private Ui ui;

    public Duke() {
        meal = new Meal();
        ui = new Ui();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Enter the name of a meal, its calories, and today's date!");
    }

    public void run() {
        try {
            String line = ui.sendInput();
            meal.addMeal(line);
        } catch (DateTimeParseException e) {
            System.out.println("Please type in the correct date and time format!");
        } catch (NumberFormatException e) {
            System.out.println("Please enter the number of calories of the meal properly!");
        } catch (DukeException e) {
            System.out.println("My apologies!");
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
