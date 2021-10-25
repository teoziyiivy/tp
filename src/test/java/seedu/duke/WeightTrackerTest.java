package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.AddWeightException;
import seedu.duke.exceptions.DeleteWeightException;
import seedu.duke.exceptions.DeleteWeightIndexException;
import seedu.duke.exceptions.NoWeightsException;

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
        String input = "01/01/1999";
        Assertions.assertThrows(NoWeightsException.class, () -> weights.listWeights(input));
    }

}
