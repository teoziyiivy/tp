package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.FoodBankException;
import seedu.duke.exceptions.meal.EmptyMealListException;
import seedu.duke.exceptions.meal.MealException;
import seedu.duke.exceptions.meal.NoMealDescriptionException;
import seedu.duke.exceptions.meal.NoSuchMealIndexException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author {V }
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

    public void generateMealParameters(String inputArguments) throws FoodBankException, MealException {
        try {
            calories = Parser.getCalories(inputArguments);
            description = Parser.getDescription(inputArguments);
            date = Parser.getDate(inputArguments);
            time = Parser.getTime(inputArguments);
        } catch (DukeException e) {
            throw new NoMealDescriptionException();
        }
    }

    public void addMeal(String inputArguments)
            throws DateTimeParseException, NumberFormatException, MealException, FoodBankException {
        logger.entering(getClass().getName(), "addMeal");
        logger.log(Level.INFO, "generating meal parameters");
        generateMealParameters(inputArguments);
        if (Parser.containsSeparators(description)) {
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
        logger.log(Level.INFO, "meal has been added to meal list");
        logger.exiting(getClass().getName(), "addMeal");
    }

    public void deleteMeal(String inputArguments)
            throws DateTimeParseException, NumberFormatException, FoodBankException, MealException {
        mealNumber = meals.size();
        if (meals.size() == 0) {
            throw new EmptyMealListException();
        }
        //assert mealNumber != 0;
        logger.entering(getClass().getName(), "deleteMeal");
        int mealIndex = Parser.parseStringToInteger(inputArguments) - 1;
        if ((mealIndex < 0) || (mealIndex > (mealNumber - 1))) {
            throw new NoSuchMealIndexException();
        }
        logger.log(Level.INFO, "generating meal parameters");
        generateMealParameters(meals.get(mealIndex));
        logger.log(Level.INFO, "meal parameters generated");
        meals.remove(mealIndex);
        mealNumber -= 1;
        totalCalories -= calories;
        logger.log(Level.INFO, "meal has been deleted from meal list");
        System.out.println(description + " will be removed from your list of meals consumed. You now "
                + "have " + totalCalories + " left!\n");
        logger.exiting(getClass().getName(), "deleteMeal");
    }

    public void listMeals(String date) throws FoodBankException, MealException {
        if (meals.size() == 0) {
            System.out.println("Your meal list is empty!");
        }
        if (date.equals("all")) {
            int i = 1;
            totalCalories = 0;
            mealNumber = 0;
            for (String meal : meals) {
                logger.log(Level.INFO, "generating meal parameters");
                generateMealParameters(meal);
                logger.log(Level.INFO, "meal parameters generated");
                System.out.println(i + ". " + description);
                System.out.println("Calories: " + calories);
                System.out.println("Date: " + date);
                System.out.println("Time: " + time + "\n");
                i += 1;
                totalCalories += calories;
                mealNumber += 1;
            }
        } else {
            assert mealNumber != 0;
            logger.entering(getClass().getName(), "listMeals");
            int i = 1;
            logger.log(Level.INFO, "entering for loop");
            totalCalories = 0;
            mealNumber = 0;
            for (String meal : meals) {
                if (meal.contains(date)) {
                    logger.log(Level.INFO, "generating meal parameters");
                    generateMealParameters(meal);
                    logger.log(Level.INFO, "meal parameters generated");
                    System.out.println(i + ". " + description);
                    System.out.println("Calories: " + calories);
                    System.out.println("Date: " + date);
                    System.out.println("Time: " + time + "\n");
                    i += 1;
                    totalCalories += calories;
                    mealNumber += 1;
                }
            }
        }
        System.out.println("Total number of meals: " + mealNumber);
        System.out.println("Total calories: " + totalCalories);
        logger.log(Level.INFO, "meal list printed");
        logger.exiting(getClass().getName(), "listMeal");
    }

    public int getCalories(String date) throws FoodBankException, MealException {
        int calorieTotal = 0;
        for (String meal : meals) {
            if (meal.contains(date)) {
                generateMealParameters(meal);
                calorieTotal += calories;
            }
        }
        return calorieTotal;
    }
}

