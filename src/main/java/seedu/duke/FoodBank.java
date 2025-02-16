package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.foodbank.FoodBankException;
import seedu.duke.exceptions.foodbank.EmptyFluidBankException;
import seedu.duke.exceptions.foodbank.EmptyMealBankException;
import seedu.duke.exceptions.foodbank.EmptyFoodDescription;
import seedu.duke.exceptions.foodbank.NoFoodFoundException;
import seedu.duke.exceptions.foodbank.NoFoodIndexException;
import java.util.ArrayList;

public class FoodBank {

    protected static ArrayList<String> meals;
    protected static ArrayList<String> fluids;
    protected static int calories;
    protected static String description;
    protected static int totalMeals;
    protected static int totalFluids;

    //@@author {V }
    public FoodBank() {
        meals = new ArrayList<>();
        fluids = new ArrayList<>();
        totalMeals = 0;
        totalFluids = 0;
    }

    //@@author {P }
    public static void generateParameters(String inputArguments) throws FoodBankException {
        try {
            calories = Parser.getCalories(inputArguments);
            description = Parser.getDescription(inputArguments);
        } catch (DukeException e) {
            System.out.println("run away");
        }
    }

    //@@author {P }
    public static void addCustomFluid(String inputArguments) throws FoodBankException {
        if (inputArguments == null) {
            throw new EmptyFoodDescription();
        }
        generateParameters(inputArguments);
        inputArguments = description + " /c " + calories;
        if (Parser.containsSeparators(description)) {
            throw new FoodBankException();
        }
        fluids.add(inputArguments);
        totalFluids += 1;
        System.out.println(description + ", which has " + calories
                + " calories, will be added to your library of fluids."
                + " You now have " + totalFluids + " fluids!\n");
    }

    //@@author { P}
    public static void deleteCustomFluids(String inputArguments) throws FoodBankException {
        if (inputArguments == null) {
            throw new NoFoodIndexException();
        }
        if (fluids.size() == 0) {
            throw new EmptyFluidBankException();
        }
        int taskNumber = Parser.parseStringToInteger(inputArguments) - 1;
        generateParameters(fluids.get(taskNumber));
        fluids.remove(taskNumber);
        totalFluids -= 1;
        System.out.println(description + " will be removed from your list of fluids consumed."
                + " You now have " + totalFluids + " fluids left!\n");
    }

    //@@author {P }
    public static void listCustomFluids() throws FoodBankException {
        if (fluids.size() == 0) {
            System.out.println("Your fluids library is empty!");
        }
        int i = 1;
        for (String fluid : fluids) {
            generateParameters(fluid);
            System.out.println(i + ". " + description);
            System.out.println("Calories: " + calories + "\n");
            i += 1;
        }
        System.out.println("Total number of fluids in library: " + totalFluids);
    }

    //@@author { V}
    public static void listCustomMeal() throws FoodBankException {
        if (meals.size() == 0) {
            System.out.println("Your meals library is empty!");
        }
        int i = 1;
        for (String meal : meals) {
            generateParameters(meal);
            System.out.println(i + ". " + description);
            System.out.println("Calories: " + calories + "\n");
            i += 1;
        }
        System.out.println("Total number of meals in library: " + totalMeals);
    }

    //@@author {V }
    public static void addCustomMeal(String inputArguments) throws FoodBankException {
        if (inputArguments == null) {
            throw new EmptyFoodDescription();
        }
        generateParameters(inputArguments);
        inputArguments = description + " /c " + calories;
        meals.add(inputArguments);
        totalMeals += 1;
        System.out.println(description + ", which has " + calories
                + " calories, will be added to your library of meals. You now have " + totalMeals + " meals!\n");
    }

    //@@author { V}
    public static void deleteCustomMeal(String inputArguments) throws NumberFormatException, FoodBankException {
        if (inputArguments == null) {
            throw new NoFoodIndexException();
        }
        if (meals.size() == 0) {
            throw new EmptyMealBankException();
        }
        int mealIndex = Parser.parseStringToInteger(inputArguments) - 1;
        generateParameters(meals.get(mealIndex));
        meals.remove(mealIndex);
        totalMeals -= 1;
        System.out.println(description + " will be removed from your list of meals consumed. You now have "
                + totalMeals + " meals left!\n");
    }

    //@@author { P}
    public static int findCalories(String name) throws FoodBankException {
        for (String meal : meals) {
            generateParameters(meal);
            if (description.equals(name)) {
                return calories;
            }
        }
        for (String fluid : fluids) {
            generateParameters(fluid);
            if (description.equals(name)) {
                return calories;
            }
        }
        throw new NoFoodFoundException();
    }
}
