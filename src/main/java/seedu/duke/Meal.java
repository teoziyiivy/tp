package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.FoodBankException;
import seedu.duke.exceptions.MealException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Meal extends Tracker {
    protected ArrayList<String> meals;
    protected int mealNumber;
    protected int calories;
    protected String description;
    protected String date;
    protected String time;
    protected int totalCalories;
    private static Logger logger = Logger.getLogger("MealTrackerLogger");


    public Meal() {
        this.meals = new ArrayList<>();
        this.mealNumber = 0;
        this.totalCalories = 0;
        logger.setLevel(Level.SEVERE);
    }

    public void generateMealParameters(String inputArguments) {
        try {
            calories = Parser.getCalories(inputArguments);
            description = Parser.getDescription(inputArguments);
            date = Parser.getDate(inputArguments);
            time = Parser.getTime(inputArguments);
        } catch (DukeException | FoodBankException e) {
            System.out.println("run away");
        }
    }

    public void addMeal(String inputArguments) throws DateTimeParseException, NumberFormatException, MealException {
        logger.entering(getClass().getName(),"addMeal");
        logger.log(Level.INFO, "generating meal parameters");

        generateMealParameters(inputArguments);

        if ((description.equals("") || Parser.containsSeparators(description))) {
            throw new MealException();
        }
        logger.log(Level.INFO, "meal parameters generated");
        System.out.println("Noted! CLI.ckFit has recorded your meal of "
                + description + " on " + date + " and at " + time
                + ". " + calories + " calories has been added to the calorie count!\n");
        totalCalories += calories;
        inputArguments = description + " /c " + calories + " /d " + date + " /t " + time;
        meals.add(inputArguments);
        mealNumber += 1;
        logger.log(Level.INFO,"meal has been added to meal list");
        logger.exiting(getClass().getName(),"addMeal");
    }

    public void deleteMeal(String inputArguments) throws DateTimeParseException, NumberFormatException {
        assert mealNumber != 0;
        logger.entering(getClass().getName(),"deleteMeal");
        int mealIndex = Parser.parseStringToInteger(inputArguments) - 1;
        logger.log(Level.INFO, "generating meal parameters");
        generateMealParameters(meals.get(mealIndex));
        logger.log(Level.INFO, "meal parameters generated");
        meals.remove(mealIndex);
        mealNumber -= 1;
        totalCalories -= calories;
        logger.log(Level.INFO,"meal has been deleted from meal list");
        System.out.println(description + " will be removed from your list of meals consumed. You now "
                + "have " + totalCalories + " left!\n");
        logger.exiting(getClass().getName(),"deleteMeal");
    }

    public void listMeals() {
        assert mealNumber != 0;
        logger.entering(getClass().getName(),"listMeals");
        int i = 1;
        logger.log(Level.INFO, "entering for loop");
        for (String meal : meals) {
            logger.log(Level.INFO, "generating meal parameters");
            generateMealParameters(meal);
            logger.log(Level.INFO, "meal parameters generated");
            System.out.println(i + ". " + description);
            System.out.println("Calories: " + calories);
            System.out.println("Date: " + date);
            System.out.println("Time: " + time + "\n");
            i += 1;
        }
        System.out.println("Total number of meals: " + mealNumber);
        System.out.println("Total calories: " + totalCalories);
        logger.log(Level.INFO,"meal list printed");
        logger.exiting(getClass().getName(),"listMeal");
    }
}
