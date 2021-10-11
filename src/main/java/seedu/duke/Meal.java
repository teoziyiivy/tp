package seedu.duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Meal extends Tracker{
    protected ArrayList<String> meals;
    protected int mealNumber;
    protected int calories;
    protected String description;
    protected String date;
    protected String time;
    protected int totalCalories;

    public Meal() {
        this.meals = new ArrayList<>();
        this.mealNumber = 0;
        this.totalCalories = 0;
    }

    public void generateMealParameters(String inputArguments) {
        try {
            calories = Parser.getCalories(inputArguments);
            description = Parser.getDescription(inputArguments);
            date = Parser.getDate(inputArguments);
            time = Parser.getTime(inputArguments);
        } catch (NumberFormatException e) {

        } catch (DukeException e) {
            System.out.println("run away");
        }
    }

    public void addMeal(String inputArguments) throws DateTimeParseException, NumberFormatException {
        generateMealParameters(inputArguments);
        System.out.println("Noted! CLI.ckFit has recorded your meal of "
                + description + " on " + date + " and at " + time
                + ". " + calories + " calories has been added to the calorie count!\n");
        totalCalories += calories;
        meals.add(inputArguments);
        mealNumber += 1;
    }

    public void deleteMeal(String inputArguments) throws DateTimeParseException, NumberFormatException {
        int mealIndex = Parser.parseStringToInteger(inputArguments) - 1;
        generateMealParameters(meals.get(mealIndex));
        meals.remove(mealIndex);
        mealNumber -= 1;
        totalCalories -= calories;
        System.out.println(description + " will be removed from your list of meals consumed. You now "
                           + "have " + totalCalories + " left!\n");
    }

    public void listMeals() throws DateTimeParseException, NumberFormatException {
        int i = 1;
        for (String meal : meals) {
            generateMealParameters(meal);
            System.out.println(i + ". " + description);
            System.out.println("Calories: " + calories);
            System.out.println("Date: " + date);
            System.out.println("Time: " + time + "\n");
            i += 1;
        }
        System.out.println("Total number of meals: " + mealNumber);
        System.out.println("Total calories: " + totalCalories);
    }
}
