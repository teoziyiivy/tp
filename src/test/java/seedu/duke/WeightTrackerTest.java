package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.weight.WeightException;
import seedu.duke.exceptions.weight.DeleteWeightException;
import seedu.duke.exceptions.weight.DeleteWeightIndexException;

public class WeightTrackerTest {

    @Test
    void addWeight() {
        WeightTracker weights = new WeightTracker();
        String input = "";
        Assertions.assertThrows(WeightException.class, () -> weights.addWeight(input));
    }

    @Test
    void deleteWeight() {
        WeightTracker weights = new WeightTracker();
        String input = "delete weight";
        Assertions.assertThrows(DeleteWeightException.class, () -> weights.deleteWeight(input));
    }

    @Test
    void deleteWeightIndex() {
        WeightTracker weights = new WeightTracker();
        String input = "1";
        Assertions.assertThrows(DeleteWeightIndexException.class, () -> weights.deleteWeight(input));
    }
}
