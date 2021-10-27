package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.fluid.NoDeleteFluidIndexException;
import seedu.duke.exceptions.fluid.FluidExceptions;
import seedu.duke.exceptions.FoodBankException;
import seedu.duke.exceptions.MealException;
import seedu.duke.exceptions.fluid.InvalidFluidDescription;
import seedu.duke.exceptions.schedule.ScheduleException;
import seedu.duke.exceptions.weight.AddWeightException;
import seedu.duke.exceptions.weight.DeleteWeightException;
import seedu.duke.exceptions.weight.DeleteWeightIndexException;
import seedu.duke.exceptions.weight.NoWeightsException;
import seedu.duke.exceptions.workout.WorkoutException;
import seedu.duke.schedule.ScheduleTracker;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

//@@author { E}
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

    public void commandChecker() throws
            DukeException, NullPointerException,
            MealException, FluidExceptions,
            FoodBankException, IOException,
            ScheduleException, AddWeightException,
            DeleteWeightException, DeleteWeightIndexException,
            WorkoutException, NoWeightsException {
        String input = scanner.nextLine();
        System.out.println(Ui.HORIZONTAL_BAR + System.lineSeparator());
        String[] splitResults = input.trim().split(" ", 2);
        command = splitResults[0];
        inputArguments = (splitResults.length == 2) ? splitResults[1] : null;
        assert !Objects.equals(inputArguments, "");
        switch (command) {
        case Keywords.LIST:
            if (inputArguments == null) {
                listEverything(Parser.getSystemDate());
            } else {
                listParser(inputArguments);
            }
            break;
        case Keywords.LIBRARY:
            assert inputArguments != null;
            foodBankParser(inputArguments);
            break;
        case Keywords.ADD:
            assert inputArguments != null;
            addParser(inputArguments);
            break;
        case Keywords.DELETE:
            assert inputArguments != null;
            deleteParser(inputArguments);
            break;
        case Keywords.INPUT_HELP:
            UserHelp.generateUserHelpParameters(inputArguments);
            break;
        case Keywords.INPUT_BYE:
            isExit = true;
            System.out.println(ClickfitMessages.CREDITS);
            break;
        default:
            System.out.println(ClickfitMessages.DONT_UNDERSTAND);
            break;
        }
        saveEverything();
    }

    public void saveEverything() throws IOException {
        storage.saveFood(fluid, meal);
        storage.saveLibrary();
        storage.saveWeight(weightTracker);
        storage.saveSchedule(scheduleTracker);
        storage.saveWorkout(workoutTracker);
    }

    public void foodBankParser(String inputArguments) throws
            NullPointerException, FoodBankException, InvalidFluidDescription {
        String[] splitResults = inputArguments.trim().split(" ", 2);
        command = splitResults[0];
        inputArguments = (splitResults.length == 2) ? splitResults[1] : null;
        switch (command) {
        case Keywords.ADD_FLUID:
        if (splitResults.length == 1) {
            throw new InvalidFluidDescription();
        }
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
            System.out.println(ClickfitMessages.DONT_UNDERSTAND);
            break;
        }
    }

    public void listParser(String inputArguments) throws
            NullPointerException, FoodBankException,
            ScheduleException, WorkoutException, NoWeightsException, FluidExceptions {
        String[] splitResults = inputArguments.trim().split(" ", 2);
        command = splitResults[0];
        String date;
        if (splitResults.length == 1) {
            if (command.contains("/")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(command, formatter);
                date = formatter.format(localDate);
                listEverything(date);
                return;
            } else {
                date = Parser.getSystemDate();
            }
        } else {
            date = splitResults[1];
            if (!date.equals("all")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(date, formatter);
                date = formatter.format(localDate);
            }
        }
        switch (command) {
        case Keywords.MEALS:
            meal.listMeals(date);
            break;
        case Keywords.FLUIDS:
            fluid.listFluids(date);
            break;
        case Keywords.CALORIES:
            listCalories(date);
            break;
        case Keywords.VOLUME:
            int volCount = fluid.getVolume(date);
            System.out.println("\n" + "Your total volume consumption for " + date + " is: " + volCount + " ml.");
            break;
        case Keywords.WORKOUTS:
            workoutTracker.listWorkouts(date);
            break;
        case Keywords.SCHEDULE:
            scheduleTracker.listScheduledWorkouts(date);
            break;
        case Keywords.WEIGHTS:
            weightTracker.listWeights(date);
            break;
        default:
            System.out.println(ClickfitMessages.DONT_UNDERSTAND);
            break;
        }
    }

    private void listCalories(String date) throws
            FoodBankException, WorkoutException, FluidExceptions {
        int calCount = fluid.getCalories(date) + meal.getCalories(date);
        int caloriesBurned = workoutTracker.getCaloriesBurned(date);
        int netCalories = calCount - caloriesBurned;
        System.out.println(System.lineSeparator() + "Your total calorie consumption for "
                + date + " is: " + calCount + " calories.");
        System.out.println("Your total calories burned for " + date + " is: " + caloriesBurned + " calories.");
        System.out.println("Your NET calories for " + date + " is: " + netCalories + " calories.");
    }

    public void addParser(String input) throws
            NullPointerException, FoodBankException,
            DukeException, MealException,
            FluidExceptions, ScheduleException,
            WorkoutException, AddWeightException {
        String[] splitResults = input.trim().split(" ", 2);
        command = splitResults[0];
        inputArguments = (splitResults.length == 2) ? splitResults[1] : null;
        switch (command) {
        case Keywords.MEAL:
            meal.addMeal(inputArguments);
            DateTracker.sortDateAndTime(meal.meals);
            break;
        case Keywords.FLUID:
            fluid.addFluid(inputArguments);
            DateTracker.sortDateAndTime(fluid.fluidArray);
            break;
        case Keywords.WORKOUT:
            workoutTracker.addWorkout(inputArguments, false);
            DateTracker.sortDateAndTime(workoutTracker.workouts);
            break;
        case Keywords.SCHEDULE:
            scheduleTracker.addScheduledWorkout(inputArguments, false, true);
            break;
        case Keywords.WEIGHT:
            weightTracker.addWeight(inputArguments);
            DateTracker.sortDate(weightTracker.weightsArray);
            break;
        default:
            System.out.println(ClickfitMessages.DONT_UNDERSTAND);
            break;
        }
    }

    public void deleteParser(String input) throws NullPointerException,
            FoodBankException, DukeException,
            ScheduleException,
            WorkoutException,
            DeleteWeightException,
            DeleteWeightIndexException, FluidExceptions {
        String[] splitResults = input.trim().split(" ", 2);
        command = splitResults[0];
        inputArguments = (splitResults.length == 2) ? splitResults[1] : null;
        switch (command) {
        case Keywords.MEAL:
            meal.deleteMeal(inputArguments);
            break;
        case Keywords.FLUID:
            fluid.deleteFluid(inputArguments);
            break;
        case Keywords.WORKOUT:
            workoutTracker.deleteWorkout(inputArguments);
            break;
        case Keywords.SCHEDULE:
            scheduleTracker.deleteScheduledWorkout(inputArguments);
            break;
        case Keywords.WEIGHT:
            if (inputArguments == null) {
                throw new DeleteWeightException();
            }
            weightTracker.deleteWeight(inputArguments);
            break;
        default:
            System.out.println(ClickfitMessages.DONT_UNDERSTAND);
            break;
        }
        DateTracker.deleteDateFromList(inputArguments, fluid, meal, workoutTracker, weightTracker);
    }

    public void listEverything(String date) throws
            NullPointerException, FoodBankException,
            ScheduleException, WorkoutException,
            NoWeightsException, FluidExceptions {
        meal.listMeals(date);
        System.out.println();
        fluid.listFluids(date);
        System.out.println();
        weightTracker.listWeights(date);
        System.out.println();
        workoutTracker.listWorkouts(date);
        System.out.println();
        scheduleTracker.listScheduledWorkouts(date);
    }
}