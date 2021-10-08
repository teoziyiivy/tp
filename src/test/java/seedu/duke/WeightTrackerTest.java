package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeightTrackerTest {

    @Test
    void addWeight() {
        WeightTracker weights = new WeightTracker();
        String input = "addweight 50 08/10/2021";
        Assertions.assertThrows(NumberFormatException.class, () -> {
            weights.addWeight(input);
        });
    }
}
