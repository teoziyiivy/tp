package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Meal {
    protected ArrayList<String> meals;
    protected int mealNumber;

    public Meal() {
        this.meals = new ArrayList<>();
        this.mealNumber = 0;
    }

    public void addMeal(String meal) throws DateTimeParseException,NumberFormatException {
        String[] userInput = meal.split(" ");
        int calorieIndex = userInput.length - 2;
        /*
        if (calorieIndex <= 1) {
            throw new DukeException("Please specify the name of the meal!");
        }
        if (!(userInput[0].equals("ate"))) {
            throw new DukeException("Please type in \"ate\" at the beginning!");
        }
        */
        int dateIndex = userInput.length - 1;
        int calories = Integer.parseInt(userInput[calorieIndex]);
        String mealDescription = userInput[1];
        for (int i = 2 ; i < calorieIndex ; i ++) {
            mealDescription = mealDescription.concat(" " + userInput[i]);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = userInput[dateIndex];
        LocalDate localDate = LocalDate.parse(date, formatter);
        System.out.println("Noted! CLI.ckFit has recorded your meal of " + mealDescription + " on "
                           + formatter.format(localDate) + ". " + calories +
                           " calories has been added to the calorie count!\n");
        meals.add(meal);
        mealNumber += 1;
    }

}
