package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.foodbank.DuplicateFood;
import seedu.duke.exceptions.foodbank.EmptyFoodDescription;
import seedu.duke.exceptions.foodbank.EmptyMealBankException;
import seedu.duke.exceptions.foodbank.FoodBankException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import seedu.duke.exceptions.foodbank.InvalidMealIndexException;
import seedu.duke.exceptions.foodbank.NegativeCaloriesException;
import seedu.duke.exceptions.foodbank.NoFoodFoundException;
import seedu.duke.exceptions.foodbank.NoFoodIndexException;

public class FoodBankTest {

    //@@author VishalJeyaram
    @Test
    void addCustomMeal1() {
        FoodBank foodBank = new FoodBank();
        String input = "pasta /c 122";
        assertDoesNotThrow(() -> FoodBank.addCustomMeal(input));
    }

    //@@author VishalJeyaram
    @Test
    void addCustomMeal2() {
        FoodBank foodBank = new FoodBank();
        String input = null;
        assertThrows(EmptyFoodDescription.class, () -> FoodBank.addCustomMeal(input));
    }

    //@@author VishalJeyaram
    @Test
    void addCustomMeal3() throws FoodBankException {
        FoodBank foodBank = new FoodBank();
        String input = "pasta /c 122";
        FoodBank.addCustomMeal(input);
        String secondInput = "pasta /c 124";
        assertThrows(DuplicateFood.class, () -> FoodBank.addCustomMeal(secondInput));
    }

    //@@author VishalJeyaram
    @Test
    void addCustomMeal4() {
        FoodBank foodBank = new FoodBank();
        String input = " ";
        assertThrows(NoFoodFoundException.class, () -> FoodBank.addCustomMeal(input));
    }

    //@@author VishalJeyaram
    @Test
    void addCustomMeal5() {
        FoodBank foodBank = new FoodBank();
        String input = "/c";
        assertThrows(FoodBankException.class, () -> FoodBank.addCustomMeal(input));
    }

    //@@author VishalJeyaram
    @Test
    void addCustomMeal6() {
        FoodBank foodBank = new FoodBank();
        String input = "21";
        assertThrows(NoFoodFoundException.class, () -> FoodBank.addCustomMeal(input));
    }

    //@@author VishalJeyaram
    @Test
    void addCustomMeal7() {
        FoodBank foodBank = new FoodBank();
        String input = "pasta /c -122";
        assertThrows(NegativeCaloriesException.class, () -> FoodBank.addCustomMeal(input));
    }

    //@@author VishalJeyaram
    @Test
    void addCustomMeal8() {
        FoodBank foodBank = new FoodBank();
        String input = "pasta";
        assertThrows(NoFoodFoundException.class, () -> FoodBank.addCustomMeal(input));
    }

    //@@author VishalJeyaram
    @Test
    void addCustomMeal9() throws FoodBankException {
        FoodBank foodBank = new FoodBank();
        String input = "pasta /c122";
        assertThrows(EmptyFoodDescription.class, () -> FoodBank.addCustomMeal(input));
    }

    //@@author VishalJeyaram
    @Test
    void deleteCustomMeal1() throws FoodBankException {
        FoodBank foodBank = new FoodBank();
        String input = "pasta /c 122";
        FoodBank.addCustomMeal(input);
        assertDoesNotThrow(() -> FoodBank.deleteCustomMeal("1"));
    }

    //@@author VishalJeyaram
    @Test
    void deleteCustomMeal2() throws FoodBankException {
        FoodBank foodBank = new FoodBank();
        String input = "pasta /c 122";
        FoodBank.addCustomMeal(input);
        String secondInput = null;
        assertThrows(NoFoodIndexException.class, () -> FoodBank.deleteCustomMeal(secondInput));
    }

    //@@author VishalJeyaram
    @Test
    void deleteCustomMeal3() throws FoodBankException {
        FoodBank foodBank = new FoodBank();
        String input = "1";
        assertThrows(EmptyMealBankException.class, () -> FoodBank.deleteCustomMeal(input));
    }

    //@@author VishalJeyaram
    @Test
    void deleteCustomMeal4() throws FoodBankException {
        FoodBank foodBank = new FoodBank();
        String input = "pasta /c 122";
        FoodBank.addCustomMeal(input);
        String secondInput = "2";
        assertThrows(InvalidMealIndexException.class, () -> FoodBank.deleteCustomMeal(secondInput));
    }

    //@@author VishalJeyaram
    @Test
    void deleteCustomMeal5() throws FoodBankException {
        FoodBank foodBank = new FoodBank();
        String input = "pasta /c 122";
        FoodBank.addCustomMeal(input);
        String secondInput = "#";
        assertThrows(NumberFormatException.class, () -> FoodBank.deleteCustomMeal(secondInput));
    }

    //@@author VishalJeyaram
    @Test
    void deleteCustomMeal6() throws FoodBankException {
        FoodBank foodBank = new FoodBank();
        String input = "pasta /c 122";
        FoodBank.addCustomMeal(input);
        String secondInput = "";
        assertThrows(NumberFormatException.class, () -> FoodBank.deleteCustomMeal(secondInput));
    }

    //@@author VishalJeyaram
    @Test
    void deleteCustomMeal7() throws FoodBankException {
        FoodBank foodBank = new FoodBank();
        String input = "pasta /c 122";
        FoodBank.addCustomMeal(input);
        String secondInput = " ";
        assertThrows(NumberFormatException.class, () -> FoodBank.deleteCustomMeal(secondInput));
    }

    //@@author VishalJeyaram
    @Test
    void deleteCustomMeal8() throws FoodBankException {
        FoodBank foodBank = new FoodBank();
        String input = "pasta /c 122";
        FoodBank.addCustomMeal(input);
        String secondInput = "delete meal";
        assertThrows(NumberFormatException.class, () -> FoodBank.deleteCustomMeal(secondInput));
    }

    //@@author VishalJeyaram
    @Test
    void deleteCustomMeal9() throws FoodBankException {
        FoodBank foodBank = new FoodBank();
        String input = "pasta /c 122";
        FoodBank.addCustomMeal(input);
        String secondInput = "";
        assertThrows(NumberFormatException.class, () -> FoodBank.deleteCustomMeal(secondInput));
    }

    //@@author VishalJeyaram
    @Test
    void listCustomMeal1() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        FoodBank.addCustomMeal("pasta /c 213 /d 12/09/2021 /t 10:35");
        assertDoesNotThrow(() -> FoodBank.listCustomMeal());
    }
}
