package seedu.duke;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FluidTest {

    @Test
    void sayDrank() {
        Fluid fluid = new Fluid();
        String input = "drank cola 300 12-12-2021";
        assertThrows(DateTimeParseException.class, () -> fluid.sayDrank(input));
    }
}
