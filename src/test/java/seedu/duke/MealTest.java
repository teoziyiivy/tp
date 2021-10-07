package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MealTest {

    @Test
    void addMeal() {
        Meal meal = new Meal();
        String input = "ate pasta 14/09/2021";
        Assertions.assertThrows(NumberFormatException.class, () -> {
            meal.addMeal(input);
        });
    }
}