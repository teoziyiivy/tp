package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.FluidExceptions;
import seedu.duke.exceptions.FoodBankException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FluidTest {

    @Test
    void addFluid() {
        Fluid fluid = new Fluid();
        String input = "drank coke /c 40 /v 100 /d 2021-3-9 /t 10:30";
        assertThrows(DateTimeParseException.class, () -> fluid.addFluid(input));
    }

    /*
    @Test
    void addFluid2() {
        Fluid fluid = new Fluid();
        String input = "drank coke /c 40 /v 100 /d 12/12/2021 /t 2359pm";
        assertThrows(DateTimeParseException.class, () -> fluid.addFluid(input));
    }
     */

    @Test
    void addFluid3() {
        Fluid fluid = new Fluid();
        String input = "drank coke /c hello /v 100 /d 12/12/2021 /t 10:30";
        assertThrows(NumberFormatException.class, () -> fluid.addFluid(input));
    }

    @Test
    void addFluid4() {
        Fluid fluid = new Fluid();
        String input = "drank coke /c 40 /v 100ml /d 12/12/2021 /t 10:30";
        assertThrows(NumberFormatException.class, () -> fluid.addFluid(input));
    }

    @Test
    void addFluid5() throws DukeException {
        Fluid fluid = new Fluid();
        String inputArguments = "/c 40 /v 100 /d 12/12/2021 /t 10:30";
        String description = Parser.getDescription(inputArguments);
        try {
            if ((description.equals("") || Parser.containsSeparators(description))) {
                throw new FluidExceptions();
            }
            assertThrows(FluidExceptions.class, () -> fluid.addFluid(inputArguments));
        } catch (FluidExceptions e) {
            System.out.println(ClickfitMessages.FLUID_ADD_FORMAT_ERROR);
        }
    }

    @Test
    void addFluid6() {
        Fluid fluid = new Fluid();
        String inputArguments = "coke /c 40 /v 100 /d 12/12/2021";
        // time is auto generated from current time when user does not put /t
        //assertThrows(DateTimeParseException.class, () -> fluid.addFluid(inputArguments));
    }

    @Test
    void addFluid7() {
        Fluid fluid = new Fluid();
        String inputArguments = "coke /c 40 /v 100 /t 10:30";
        // date is auto generated from current date when user does not put /d
        //assertThrows(DateTimeParseException.class, () -> fluid.addFluid(inputArguments));
    }

    /*
    @Test
    void deleteFluid() throws DukeException, FluidExceptions, FoodBankException {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        String input = "deletefluid one";
        assertThrows(NumberFormatException.class, () -> fluid.deleteFluid(input));
    }
     */

    /*
    @Test
    void deleteFluid2() throws DukeException, FluidExceptions, FoodBankException {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        String input = "deletefluid";
        assertThrows(NumberFormatException.class, () -> fluid.deleteFluid(input));
    }
     */

    /*
    @Test
    void listFluid() {
        Fluid fluid = new Fluid();
        assert ((fluid.fluidArray).size() == 0);
    }

    @Test
    void listFluid2() {
        Fluid fluid = new Fluid();
        fluid.fluidArray.add("drank coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        fluid.fluidArray.add("drank water /c 0 /v 200 /d 13/12/2021 /t 12:30");
        assert (fluid.fluidArray.size() != 0);
    }

     */
}
