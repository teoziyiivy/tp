package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.FoodBankException;

import java.util.ArrayList;

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

    public static void generateParameters(String inputArguments) {
        try {
            calories = Parser.getCalories(inputArguments);
            description = Parser.getDescription(inputArguments);
        } catch (DukeException | FoodBankException e) {
            System.out.println("run away");
        }
    }

    public static void addCustomMeal(String inputArguments) {
        generateParameters(inputArguments);
        inputArguments = description + " /c " + calories;
        meals.add(inputArguments);
    }

    public static void addCustomFluid(String inputArguments) {
        generateParameters(inputArguments);
        inputArguments = description + " /c " + calories;
        fluids.add(inputArguments);
        totalFluids += 1;
        System.out.println(description + ", which has " + calories + " calories, will be added to your library of fluids." +
                " You now have " + totalFluids + " fluids!\n");
    }

    public static void deleteCustomFluids(String inputArguments) {
        int taskNumber = Parser.parseStringToInteger(inputArguments) - 1;
        generateParameters(fluids.get(taskNumber));
        fluids.remove(taskNumber);
        totalFluids -= 1;
        System.out.println(description + " will be removed from your list of fluids consumed." +
                " You now have " + totalFluids + " fluids left!\n");
    }

    public static void listCustomFluids() {
        int i = 1;
        for (String fluid : fluids) {
            generateParameters(fluid);
            System.out.println(i + ". " + description + " " + calories + "\n");
            i++;
        }
        System.out.println("Total number of fluids in library: " + totalFluids + "\n");
    }

    public static int findCalories(String name) throws FoodBankException {
        int i = 0;
        for (String fluid: fluids) {
            generateParameters(fluid);
            if (description.equals(name)) {
                return calories;
            }
            i += 1;
        }
        throw new FoodBankException();
    }
}
