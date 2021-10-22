package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.FluidExceptions;
import seedu.duke.exceptions.FoodBankException;
import seedu.duke.exceptions.InvalidActivityFormatException;
import seedu.duke.exceptions.MealException;
import seedu.duke.exceptions.ScheduleException;
import seedu.duke.workout.ScheduleTracker;
import seedu.duke.workout.WorkoutTracker;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class CommandManager {
    protected ScheduleTracker scheduleTracker;
    protected WorkoutTracker workoutTracker;
    protected Meal meal;
    protected Fluid fluid;
    protected WeightTracker weightTracker;
    protected UserHelp userHelp;
    protected Scanner scanner;
    protected boolean isExit;
    protected String command;
    protected String inputArguments;
    protected Storage storage;

    public CommandManager(Storage storage, Fluid fluid, Meal meal,
                          ScheduleTracker scheduleTracker, WorkoutTracker workoutTracker,
                          WeightTracker weightTracker, UserHelp userHelp) {
        this.fluid = fluid;
        this.meal = meal;
        this.scheduleTracker = scheduleTracker;
        this.workoutTracker = workoutTracker;
        this.scanner = new Scanner(System.in);
        this.weightTracker = weightTracker;
        this.userHelp = userHelp;
        this.isExit = false;
        this.storage = storage;
    }

    public void commandChecker() throws DukeException, NullPointerException,
            MealException, FluidExceptions,
            FoodBankException, IOException, ScheduleException {
        String input = scanner.nextLine();
        System.out.println(Ui.HORIZONTAL_BAR + System.lineSeparator());
        String[] splitResults = input.trim().split(" ", 2);
        command = splitResults[0];
        inputArguments = (splitResults.length == 2) ? splitResults[1] : null;
        assert !Objects.equals(inputArguments, "");
        switch (command) {
        case Keywords.LIST:
            assert inputArguments != null;
            if (splitResults.length == 1) {
                listEverything(Parser.getSystemDate());
                // list everything for that day- send today's date as a parameter
            } else {
                listParser(inputArguments);
            }
            break;
        case Keywords.LIBRARY:
            assert inputArguments != null;
            foodBankParser(inputArguments);
            break;
        case Keywords.INPUT_MEAL:
            if (splitResults.length == 1) {
                throw new MealException();
            }
            meal.addMeal(inputArguments);
            DateTracker.sortTime(meal.meals);
            break;
        case Keywords.DELETE_MEAL:
            meal.deleteMeal(inputArguments);
            DateTracker.deleteDateFromList(inputArguments, fluid, meal, scheduleTracker, workoutTracker, weightTracker);
            break;

        case Keywords.INPUT_ADD_WORKOUT:
        case Keywords.INPUT_DELETE_WORKOUT:
            executeWorkoutCommand(command, inputArguments);
            break;
        case Keywords.INPUT_ADD_SCHEDULE:
        case Keywords.INPUT_DELETE_SCHEDULE:
            executeScheduleCommand(command, inputArguments);
            break;
        case Keywords.INPUT_DRINKS:
            if (inputArguments != null) {
                try {
                    fluid.addFluid(inputArguments);
                    DateTracker.sortTime(fluid.fluidArray);
                } catch (FluidExceptions | FoodBankException e) {
                    System.out.println(ClickfitMessages.FLUID_ADD_FORMAT_ERROR);
                }
            } else {
                throw new FluidExceptions();
            }
            break;
        case Keywords.DELETE_DRINKS:
            if (inputArguments != null) {
                if (fluid.fluidArray.size() == 0) {
                    System.out.println(ClickfitMessages.FLUID_DELETE_ERROR);
                } else {
                    fluid.deleteFluid(inputArguments);
                    DateTracker.deleteDateFromList(inputArguments, fluid, meal,
                            scheduleTracker, workoutTracker, weightTracker);
                }
            } else {
                System.out.println(ClickfitMessages.FLUID_DELETE_FORMAT_ERROR);
            }
            break;
        case Keywords.INPUT_ADD_WEIGHT:
            try {
                weightTracker.readInput(input);
            } catch (DukeException e) {
                return;
            } catch (DateTimeParseException e) {
                WeightTrackerMessages.printAddWeightException();
            }
            break;
        case Keywords.INPUT_DELETE_WEIGHT:
        case Keywords.INPUT_LIST_WEIGHT:
            try {
                weightTracker.readInput(input);
            } catch (DukeException e) {
                return;
            }
            break;
        case Keywords.INPUT_HELP:
            UserHelp.generateUserHelpParameters(inputArguments);
            break;
        case Keywords.INPUT_BYE:
            isExit = true;
            System.out.println(ClickfitMessages.CREDITS);
            break;
        default:
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
        storage.saveFood(fluid, meal);
        storage.saveLibrary();
        storage.saveWeight(weightTracker);
        storage.saveScheduleData(scheduleTracker);
        storage.saveWorkoutData(workoutTracker);
    }

    public void foodBankParser(String inputArguments) throws NullPointerException, FoodBankException {
        String[] splitResults = inputArguments.trim().split(" ", 2);
        command = splitResults[0];
        inputArguments = (splitResults.length == 2) ? splitResults[1] : null;
        switch (command) {
        case Keywords.ADD_FLUID:
            FoodBank.addCustomFluid(inputArguments);
            break;
        case Keywords.DELETE_DRINKS:
            FoodBank.deleteCustomFluids(inputArguments);
            break;
        case Keywords.LIST_DRINKS:
            FoodBank.listCustomFluids();
            break;
        case Keywords.ADD_MEAL:
            FoodBank.addCustomMeal(inputArguments);
            break;
        case Keywords.DELETE_MEAL:
            FoodBank.deleteCustomMeal(inputArguments);
            break;
        case Keywords.LIST_MEAL:
            FoodBank.listCustomMeal();
            break;
        default:
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
    }

    public void listParser(String inputArguments) throws NullPointerException, FoodBankException, DukeException {
        String[] splitResults = inputArguments.trim().split(" ", 2);
        command = splitResults[0];
        String date;
        if (splitResults.length == 1) {
            if (command.contains("/")) {
                listEverything(command);
                return;
            } else {
                date = Parser.getSystemDate();
            }
        } else {
            date = splitResults[1];
        }
        switch (command) {
        case Keywords.MEAL:
            meal.listMeals(date);
            break;
        case Keywords.FLUID:
            fluid.listFluid(date);
            break;
        case Keywords.CALORIES:
            int calCount = fluid.getCalories(date) + meal.getCalories(date);
            int caloriesBurned = workoutTracker.getCaloriesBurned(date);
            int netCalories = calCount - caloriesBurned;
            System.out.println("\nYour total calorie consumption for " + date + " is: " + calCount + " calories.");
            System.out.println("Your total calories burned for " + date + " is: " + caloriesBurned + " calories.");
            System.out.println("Your NET calories for " + date + " is: " + netCalories + " calories.");
            break;
        case Keywords.VOLUME:
            int volCount = fluid.getVolume(date);
            System.out.println("\n" + "Your total volume consumption for " + date + " is: " + volCount + " ml.");
            break;
        case Keywords.WORKOUT:
            workoutTracker.listWorkouts(date);
            break;
        case Keywords.SCHEDULE:
            scheduleTracker.listScheduledWorkouts(date);
            break;
            /*
        case Keywords.WEIGHT:
            FoodBank.deleteCustomMeal(inputArguments);
            break;
             */
        default:
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
    }

    public void executeScheduleCommand(String command, String inputArguments)
            throws DukeException, ScheduleException {
        switch (command) {
        case Keywords.INPUT_ADD_SCHEDULE:
            scheduleTracker.addScheduledWorkout(inputArguments, false);
            break;
        case Keywords.INPUT_DELETE_SCHEDULE:
            scheduleTracker.deleteScheduledWorkout(inputArguments);
            break;
        default:
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
    }

    public void executeWorkoutCommand(String command, String inputArguments) throws DukeException {
        switch (command) {
        case Keywords.INPUT_ADD_WORKOUT:
            workoutTracker.addWorkout(inputArguments, false);
            break;
        case Keywords.INPUT_DELETE_WORKOUT:
            workoutTracker.deleteWorkout(inputArguments);
            break;
        default:
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
        Storage.saveWorkoutData(workoutTracker);
    }

    public void listEverything(String date) throws NullPointerException, FoodBankException, DukeException {
        meal.listMeals(date);
        System.out.println();
        fluid.listFluid(date);
        System.out.println();
        workoutTracker.listWorkouts(date);
        System.out.println();
        scheduleTracker.listScheduledWorkouts(date);
        System.out.println();
    }
}