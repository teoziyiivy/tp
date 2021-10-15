package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.FluidExceptions;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FluidTest {

    @Test
    void addFluid() {
        Fluid fluid = new Fluid();
        String input = "drank coke /c 40 /v 100 /d 2021-3-9 /t 10:30";
        assertThrows(DateTimeParseException.class, () -> fluid.addFluid(input));
    }

    @Test
    void addFluid2() {
        Fluid fluid = new Fluid();
        String input = "drank coke /c 40 /v 100 /d 12/12/2021 /t 2359pm";
        assertThrows(DateTimeParseException.class, () -> fluid.addFluid(input));
    }

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
    void deleteFluid() throws DukeException, FluidExceptions {
        Fluid fluid = new Fluid();
        fluid.addFluid("coke /c 40 /v 100 /d 12/12/2021 /t 10:30");
        String input = "deletefluid one";
        assertThrows(NumberFormatException.class, () -> fluid.deleteFluid(input));
    }
}
