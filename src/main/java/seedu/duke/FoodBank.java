package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.FoodBankException;
import seedu.duke.exceptions.MealException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;

public class FoodBank {
    protected static ArrayList<String> meals;
    protected static ArrayList<String> fluids;
    protected static int calories;
    protected static String description;
    protected static int totalMeals;
    protected static int totalFluids;


    public FoodBank() {
        meals = new ArrayList<>();
        fluids = new ArrayList<>();
        totalMeals = 0;
        totalFluids = 0;
    }

    public static void generateParameters(String inputArguments) throws FoodBankException {
        try {
            calories = Parser.getCalories(inputArguments);
            description = Parser.getDescription(inputArguments);
        } catch (DukeException e) {
            System.out.println("run away");
        }
    }

    public static void addCustomMeal(String inputArguments) throws FoodBankException{
        generateParameters(inputArguments);
        inputArguments = description + " /c " + calories;
        meals.add(inputArguments);
        totalMeals += 1;
        System.out.println(description + ", which has " + calories + " calories, will be added to your library of meals. You now have "
                + totalMeals + " meals!\n");
    }

    public static void deleteCustomMeal(String inputArguments) throws NumberFormatException , FoodBankException {
        int mealIndex = Parser.parseStringToInteger(inputArguments) - 1;
        generateParameters(meals.get(mealIndex));
        meals.remove(mealIndex);
        totalMeals -= 1;
        System.out.println(description + " will be removed from your list of meals consumed. You now have "
                + totalMeals + " meals left!\n");
    }

    public static void listCustomMeal() throws FoodBankException {
        int i = 1;
        for (String meal : meals) {
            generateParameters(meal);
            System.out.println(i + ". " + description);
            System.out.println("Calories: " + calories + "\n");
            i += 1;
        }
        System.out.println("Total number of meals in library: " + totalMeals);
    }

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
        throw new FoodBankException();
    }
}
