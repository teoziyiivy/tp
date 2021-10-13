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

    @Test
    void deleteWeight() {
        WeightTracker weights = new WeightTracker();
        String input = "deleteweight";
        Assertions.assertThrows(DeleteWeightException.class, () -> weights.deleteWeight(input));
    }

    @Test
    void deleteWeightIndex() {
        WeightTracker weights = new WeightTracker();
        String input = "1";
        Assertions.assertThrows(DeleteWeightIndexException.class, () -> weights.deleteWeight(input));
    }

    @Test
    void checkWeightIndex() {
        WeightTracker weights = new WeightTracker();
        Assertions.assertThrows(NoWeightsException.class, weights::printWeight);
    }
}
