package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.weight.WeightException;
import seedu.duke.exceptions.weight.DeleteWeightException;
import seedu.duke.exceptions.weight.DeleteWeightIndexException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

//@@author teoziyiivy
public class WeightTrackerTest {
    @Test
    void addWeight_validFormat1_noExceptionThrow() {
        WeightTracker weights = new WeightTracker();
        String input = "50 /d 03/11/2021";
        assertDoesNotThrow(() -> weights.addWeight(input));
    }

    @Test
    void addWeight_validFormat2_noExceptionThrow() {
        WeightTracker weights = new WeightTracker();
        String input = "50";
        assertDoesNotThrow(() -> weights.addWeight(input));
    }

    @Test
    void addWeight_invalidFormat1_exceptionThrow() {
        WeightTracker weights = new WeightTracker();
        String input = "";
        Assertions.assertThrows(WeightException.class, () -> weights.addWeight(input));
    }

    @Test
    void addWeight_invalidFormat2_exceptionThrow() {
        WeightTracker weights = new WeightTracker();
        String input = "50 03/11/2021";
        Assertions.assertThrows(WeightException.class, () -> weights.addWeight(input));
    }

    @Test
    void deleteWeight_validFormat_noExceptionThrow() throws WeightException {
        WeightTracker weights = new WeightTracker();
        String entry = "50 /d 03/11/2021";
        String input = "1";
        weights.addWeight(entry);
        assertDoesNotThrow(() -> weights.deleteWeight(input));
    }

    @Test
    void deleteWeight_invalidFormat_exceptionThrow() {
        WeightTracker weights = new WeightTracker();
        String input = "delete weight";
        Assertions.assertThrows(DeleteWeightException.class, () -> weights.deleteWeight(input));
    }

    @Test
    void deleteWeightIndex_invalidIndex1_exceptionThrow() {
        WeightTracker weights = new WeightTracker();
        String input = "1";
        Assertions.assertThrows(DeleteWeightIndexException.class, () -> weights.deleteWeight(input));
    }

    @Test
    void deleteWeightIndex_invalidIndex2_exceptionThrow() {
        WeightTracker weights = new WeightTracker();
        String input = "-1";
        Assertions.assertThrows(DeleteWeightIndexException.class, () -> weights.deleteWeight(input));
    }

    @Test
    void listWeight_validFormat1_noExceptionThrow() throws WeightException {
        WeightTracker weights = new WeightTracker();
        String entry = "50 /d 03/11/2021";
        weights.addWeight(entry);
        String input = "03/11/2021";
        assertDoesNotThrow(() -> weights.listWeights(input));
    }

    @Test
    void listWeight_validFormat2_noExceptionThrow() throws WeightException {
        WeightTracker weights = new WeightTracker();
        String entry1 = "50 /d 02/11/2021";
        String entry2 = "45 /d 03/11/2021";
        weights.addWeight(entry1);
        weights.addWeight(entry2);
        String input = "03/11/2021";
        assertDoesNotThrow(() -> weights.listWeights(input));
    }

    @Test
    void listWeight_validFormat3_noExceptionThrow() throws WeightException {
        WeightTracker weights = new WeightTracker();
        String entry1 = "50 /d 02/11/2021";
        String entry2 = "45 /d 03/11/2021";
        weights.addWeight(entry1);
        weights.addWeight(entry2);
        String input = "all";
        assertDoesNotThrow(() -> weights.listWeights(input));
    }

    @Test
    void listWeight_validFormat4_noExceptionThrow() {
        WeightTracker weights = new WeightTracker();
        String input = "";
        assertDoesNotThrow(() -> weights.listWeights(input));
    }
}
