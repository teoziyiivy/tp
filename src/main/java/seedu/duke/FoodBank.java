package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.foodbank.DuplicateFood;
import seedu.duke.exceptions.foodbank.EmptyFluidBankException;
import seedu.duke.exceptions.foodbank.EmptyFoodDescription;
import seedu.duke.exceptions.foodbank.EmptyMealBankException;
import seedu.duke.exceptions.foodbank.FoodBankException;
import seedu.duke.exceptions.foodbank.InvalidFluidIndexException;
import seedu.duke.exceptions.foodbank.InvalidMealIndexException;
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

    //@@author VishalJeyaram
    public FoodBank() {
        meals = new ArrayList<>();
        fluids = new ArrayList<>();
        totalMeals = 0;
        totalFluids = 0;
    }

    /**
     * This method splits user input to extract parameters such as description and calories.
     *
     * @param inputArguments user input provided
     * @throws FoodBankException if calories are not provided
     *
     * @author pragyan01
     */
    public static void generateParameters(String inputArguments) throws FoodBankException {
        try {
            calories = Parser.getCalories(inputArguments);
            description = Parser.getDescription(inputArguments);
        } catch (DukeException e) {
            System.out.println("run away");
        }
    }

    /**
     * This method adds a fluid entry to the fluid library.
     *
     * @param inputArguments user input provided
     * @throws FoodBankException if user input is null
     *
     * @author pragyan01
     */
    public static void addCustomFluid(String inputArguments) throws FoodBankException {
        totalFluids = fluids.size();
        if (inputArguments == null) {
            throw new EmptyFoodDescription();
        }
        generateParameters(inputArguments);
        inputArguments = description + " /c " + calories;
        if (Parser.containsSeparators(description)) {
            throw new FoodBankException();
        }
        for (String f : fluids) {
            if (f.contains(description)) {
                throw new DuplicateFood();
            }
        }
        fluids.add(inputArguments);
        totalFluids += 1;
        System.out.println(description + ", which has " + calories
                + " calories, will be added to your library of fluids."
                + " You now have " + totalFluids + " fluids!\n");
    }

    /**
     * This method deletes a fluid entry from the fluid library.
     *
     * @param inputArguments user input provided
     * @throws FoodBankException if the fluid entry index is invalid or not provided
     *
     * @author pragyan01
     */
    public static void deleteCustomFluids(String inputArguments) throws FoodBankException {
        totalFluids = fluids.size();
        if (inputArguments == null) {
            throw new NoFoodIndexException();
        }
        if (fluids.size() == 0) {
            throw new EmptyFluidBankException();
        }
        int fluidIndex = Parser.parseStringToInteger(inputArguments) - 1;
        if ((fluidIndex < 0) || (fluidIndex > (fluids.size() - 1))) {
            throw new InvalidFluidIndexException();
        }
        generateParameters(fluids.get(fluidIndex));
        fluids.remove(fluidIndex);
        totalFluids -= 1;
        System.out.println(description + " will be removed from your list of fluids consumed."
                + " You now have " + totalFluids + " fluids left!\n");
    }

    /**
     * This method lists out all fluid entries stored in the food library.
     *
     *@throws FoodBankException if calories for a fluid entry is not found
     *
     * @author pragyan01
     */
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

    //@@author VishalJeyaram
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

    //@@author VishalJeyaram
    public static void addCustomMeal(String inputArguments) throws FoodBankException {
        totalMeals = meals.size();
        if (inputArguments == null) {
            throw new EmptyFoodDescription();
        }
        for (String m : meals) {
            if (m.contains(inputArguments)) {
                throw new DuplicateFood();
            }
        }
        generateParameters(inputArguments);
        inputArguments = description + " /c " + calories;
        meals.add(inputArguments);
        totalMeals += 1;
        System.out.println(description + ", which has " + calories
                + " calories, will be added to your library of meals. You now have " + totalMeals + " meals!\n");
    }

    //@@author VishalJeyaram
    public static void deleteCustomMeal(String inputArguments) throws NumberFormatException, FoodBankException {
        totalMeals = meals.size();
        if (inputArguments == null) {
            throw new NoFoodIndexException();
        }
        if (meals.size() == 0) {
            throw new EmptyMealBankException();
        }
        int mealIndex = Parser.parseStringToInteger(inputArguments) - 1;
        if ((mealIndex < 0) || (mealIndex > (meals.size() - 1))) {
            throw new InvalidMealIndexException();
        }
        generateParameters(meals.get(mealIndex));
        meals.remove(mealIndex);
        totalMeals -= 1;
        System.out.println(description + " will be removed from your list of meals consumed. You now have "
                + totalMeals + " meals left!\n");
    }

    /**
     * This method finds calories associated with a specific food entry.
     *
     * @throws FoodBankException if specified food entry
     * @return calories associated with the specific food entry
     *
     * @author pragyan01
     */
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
