package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

class MealTest {

    @Test
    void addMeal() {
        Meal meal = new Meal();
        String input = "ate pasta /c 356 /d 14/09/2021 /t 7pm";
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            meal.addMeal(input);
        });
    }
}