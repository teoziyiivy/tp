package seedu.duke.gym;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GymManagerTest {

    @Test
    // test for exception thrown in event of non-integer input for calories
    void doneGymWorkout_non_integer_input(){
        GymManager gm = new GymManager();
        String input = "chest day /at 23:59 /c $@$!";
        assertThrows(NumberFormatException.class, () -> gm.doneGymWorkout(input));
    }
}
