package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeightTrackerTest {
    
    @Test
    void addWeight() {
        String input = "addweight 50 08/10/2021";
        Assertions.assertThrows(NumberFormatException.class, () -> {
            Duke.addWeight(input);
        });
    }
}
