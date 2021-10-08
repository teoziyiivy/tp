package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeightTrackerTest {

    @Test
    void addWeight() {
        WeightTracker weights = new WeightTracker();
        String input = "addweight";
        Assertions.assertThrows(AddWeightException.class, () -> weights.addWeight(input));
    }
}
