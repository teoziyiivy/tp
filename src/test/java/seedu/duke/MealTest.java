package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.FoodBankException;
import seedu.duke.exceptions.MealException;

import java.time.format.DateTimeParseException;

class MealTest {

    /*
    @Test
    void addMeal1() {
        Meal meal = new Meal();
        String input = "pasta /c 356 /d 14/09/2021 /t 7pm";
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            meal.addMeal(input);
        });
    }
     */

    @Test
    void addMeal2() {
        Meal meal = new Meal();
        String input = "pasta /c 356 /d 14-09-2021 /t 17:45";
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            meal.addMeal(input);
        });
    }

    @Test
    void addMeal3() {
        Meal meal = new Meal();
        String input = "pasta /c sauce /d 14/09/2021 /t 17:45";
        Assertions.assertThrows(NumberFormatException.class, () -> {
            meal.addMeal(input);
        });
    }

    /*
    @Test
    void deleteMeal1() throws MealException, FoodBankException {
        Meal meal = new Meal();
        String input = "pasta /c 300 /d 14/09/2021 /t 17:45";
        meal.addMeal(input);
        String deleteInput = "two";
        Assertions.assertThrows(NumberFormatException.class, () -> {
            meal.deleteMeal(deleteInput);
        });
    }
     */
}