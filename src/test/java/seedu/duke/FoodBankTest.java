package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.foodbank.DuplicateFood;
import seedu.duke.exceptions.foodbank.EmptyFluidBankException;
import seedu.duke.exceptions.foodbank.EmptyFoodDescription;
import seedu.duke.exceptions.foodbank.FoodBankException;
import seedu.duke.exceptions.foodbank.InvalidFluidIndexException;
import seedu.duke.exceptions.foodbank.NoFoodFoundException;
import seedu.duke.exceptions.foodbank.NoFoodIndexException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("ALL")
public class FoodBankTest {

    //@@author pragyan01
    @Test
    void generateParameters() {
        FoodBank foodbank = new FoodBank();
        String input = "water /c 0";
        assertDoesNotThrow(() -> foodbank.generateParameters(input));
    }

    //@@author pragyan01
    @Test
    void generateParameters2() {
        FoodBank foodbank = new FoodBank();
        String input = "water";
        assertThrows(NoFoodFoundException.class, () -> foodbank.generateParameters(input));
    }

    //@@author pragyan01
    @Test
    void generateParameters3() {
        FoodBank foodbank = new FoodBank();
        String input = "";
        assertThrows(NoFoodFoundException.class, () -> foodbank.generateParameters(input));
    }

    //@@author pragyan01
    @Test
    void generateParameters4() {
        FoodBank foodbank = new FoodBank();
        String input = " ";
        assertThrows(NoFoodFoundException.class, () -> foodbank.generateParameters(input));
    }

    //@@author pragyan01
    @Test
    void generateParameters5() {
        FoodBank foodbank = new FoodBank();
        String input = null;
        assertThrows(NullPointerException.class, () -> foodbank.generateParameters(input));
    }

    //@@author pragyan01
    @Test
    void generateParameters6() {
        FoodBank foodbank = new FoodBank();
        String input = "-5";
        assertThrows(NoFoodFoundException.class, () -> foodbank.generateParameters(input));
    }

    //@@author pragyan01
    @Test
    void generateParameters7() {
        FoodBank foodbank = new FoodBank();
        String input = "/d";
        assertThrows(EmptyFoodDescription.class, () -> foodbank.generateParameters(input));
    }

    //@@author pragyan01
    @Test
    void generateParameters8() {
        FoodBank foodbank = new FoodBank();
        String input = "juice /c 60 /v 100 /d 12/12/2021 /t 10:30";
        assertDoesNotThrow(() -> foodbank.generateParameters(input));
    }

    //@@author pragyan01
    @Test
    void addCustomFluid() {
        FoodBank foodbank = new FoodBank();
        String input = "coke /c 40";
        assertDoesNotThrow(() -> foodbank.addCustomFluid(input));
    }

    //@@author pragyan01
    @Test
    void addCustomFluid1() {
        FoodBank foodbank = new FoodBank();
        String input = "";
        assertThrows(NoFoodFoundException.class, () -> foodbank.addCustomFluid(input));
    }

    //@@author pragyan01
    @Test
    void addCustomFluid2() {
        FoodBank foodbank = new FoodBank();
        String input = " ";
        assertThrows(NoFoodFoundException.class, () -> foodbank.addCustomFluid(input));
    }

    //@@author pragyan01
    @Test
    void addCustomFluid3() {
        FoodBank foodbank = new FoodBank();
        String input = null;
        assertThrows(EmptyFoodDescription.class, () -> foodbank.addCustomFluid(input));
    }

    //@@author pragyan01
    @Test
    void addCustomFluid4() {
        FoodBank foodbank = new FoodBank();
        String input = "/c";
        assertThrows(FoodBankException.class, () -> foodbank.addCustomFluid(input));
    }

    //@@author pragyan01
    @Test
    void addCustomFluid5() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomFluid("cola /c 60");
        String input = "cola /c 60";
        assertThrows(DuplicateFood.class, () -> foodbank.addCustomFluid(input));
    }

    //@@author pragyan01
    @Test
    void addCustomFluid6() {
        FoodBank foodbank = new FoodBank();
        String input = "21";
        assertThrows(NoFoodFoundException.class, () -> foodbank.addCustomFluid(input));
    }

    //@@author pragyan01
    @Test
    void addCustomFluid7() {
        FoodBank foodbank = new FoodBank();
        String input = "add fluid";
        assertThrows(NoFoodFoundException.class, () -> foodbank.addCustomFluid(input));
    }

    //@@author pragyan01
    @Test
    void deleteCustomFluid() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomFluid("coke /c 40");
        String input = "1";
        assertDoesNotThrow(() -> foodbank.deleteCustomFluids(input));
    }

    //@@author pragyan01
    @Test
    void deleteCustomFluid2() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomFluid("coke /c 40");
        String input = "one";
        assertThrows(NumberFormatException.class, () -> foodbank.deleteCustomFluids(input));
    }

    //@@author pragyan01
    @Test
    void deleteCustomFluid3() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomFluid("coke /c 40");
        String input = null;
        assertThrows(NoFoodIndexException.class, () -> foodbank.deleteCustomFluids(input));
    }

    //@@author pragyan01
    @Test
    void deleteCustomFluid4() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomFluid("coke /c 40");
        String input = "";
        assertThrows(NumberFormatException.class, () -> foodbank.deleteCustomFluids(input));
    }

    //@@author pragyan01
    @Test
    void deleteCustomFluid5() {
        FoodBank foodbank = new FoodBank();
        String input = "1";
        assertThrows(EmptyFluidBankException.class, () -> foodbank.deleteCustomFluids(input));
    }

    //@@author pragyan01
    @Test
    void deleteCustomFluid6() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomFluid("coke /c 40");
        String input = "20";
        assertThrows(InvalidFluidIndexException.class, () -> foodbank.deleteCustomFluids(input));
    }

    //@@author pragyan01
    @Test
    void deleteCustomFluid7() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomFluid("coke /c 40");
        String input = "-3";
        assertThrows(InvalidFluidIndexException.class, () -> foodbank.deleteCustomFluids(input));
    }

    //@@author pragyan01
    @Test
    void listCustomFluid() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        assertDoesNotThrow(() -> foodbank.listCustomFluids());
    }

    //@@author pragyan01
    @Test
    void listFluid2() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        foodbank.addCustomFluid("water /c 0 /v 300 /d 11/11/2021 /t 11:59");
        assertDoesNotThrow(() -> foodbank.listCustomFluids());
    }

    //@@author pragyan01
    @Test
    void listFluid3() {
        FoodBank foodbank = new FoodBank();
        assertDoesNotThrow(() -> foodbank.listCustomFluids());
    }

    //@@author pragyan01
    @Test
    void findCalories() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomFluid("coke /c 40");
        String input = "coke";
        assertDoesNotThrow(() -> foodbank.findCalories(input));
    }

    //@@author pragyan01
    @Test
    void findCalories2() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomFluid("coke /c 40");
        String input = "water";
        assertThrows(NoFoodFoundException.class, () -> foodbank.findCalories(input));
    }

    //@@author pragyan01
    @Test
    void findCalories3() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomFluid("coke /c 40");
        String input = "1";
        assertThrows(NoFoodFoundException.class, () -> foodbank.findCalories(input));
    }

    //@@author pragyan01
    @Test
    void findCalories4() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomFluid("coke /c 40");
        String input = "";
        assertThrows(NoFoodFoundException.class, () -> foodbank.findCalories(input));
    }

    //@@author pragyan01
    @Test
    void findCalories5() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomFluid("coke /c 40");
        String input = " ";
        assertThrows(NoFoodFoundException.class, () -> foodbank.findCalories(input));
    }

    //@@author pragyan01
    @Test
    void findCalories6() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomFluid("coke /c 40");
        String input = null;
        assertThrows(NoFoodFoundException.class, () -> foodbank.findCalories(input));
    }

    //@@author pragyan01
    @Test
    void findCalories7() {
        FoodBank foodbank = new FoodBank();
        String input = "cola";
        assertThrows(NoFoodFoundException.class, () -> foodbank.findCalories(input));
    }

    //@@author pragyan01
    @Test
    void findCalories8() {
        FoodBank foodbank = new FoodBank();
        String input = "-5";
        assertThrows(NoFoodFoundException.class, () -> foodbank.findCalories(input));
    }

    //@@author pragyan01
    @Test
    void isFoodFound() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomFluid("coke /c 40");
        String input = "coke";
        assertDoesNotThrow(() -> foodbank.isFoodFound(input));
    }

    //@@author pragyan01
    @Test
    void isFoodFound2() {
        FoodBank foodbank = new FoodBank();
        String input = "coke";
        assertDoesNotThrow(() -> foodbank.isFoodFound(input));
    }

    //@@author pragyan01
    @Test
    void isFoodFound3() {
        FoodBank foodbank = new FoodBank();
        String input = "-5";
        assertDoesNotThrow(() -> foodbank.isFoodFound(input));
    }

    //@@author pragyan01
    @Test
    void isFoodFound4() {
        FoodBank foodbank = new FoodBank();
        String input = null;
        assertDoesNotThrow(() -> foodbank.isFoodFound(input));
    }

    //@@author pragyan01
    @Test
    void isFoodFound5() {
        FoodBank foodbank = new FoodBank();
        String input = "";
        assertDoesNotThrow(() -> foodbank.isFoodFound(input));
    }

    //@@author pragyan01
    @Test
    void isFoodFound6() {
        FoodBank foodbank = new FoodBank();
        String input = " ";
        assertDoesNotThrow(() -> foodbank.isFoodFound(input));
    }

    //@@author pragyan01
    @Test
    void isFoodFound7() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomMeal("pasta /c 200");
        String input = "pasta";
        assertDoesNotThrow(() -> foodbank.isFoodFound(input));
    }

    //@@author pragyan01
    @Test
    void isFoodFound8() throws FoodBankException {
        FoodBank foodbank = new FoodBank();
        foodbank.addCustomMeal("pasta /c 200");
        String input = "water";
        assertDoesNotThrow(() -> foodbank.isFoodFound(input));
    }
}
