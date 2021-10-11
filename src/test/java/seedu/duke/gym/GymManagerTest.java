package seedu.duke.gym;

import org.junit.jupiter.api.Test;

import seedu.duke.DukeException;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GymManagerTest {

    @Test
    void nullArgumentCheck_NullInput() {
        GymManager gm = new GymManager();
        String argumentInput = null;
        assertThrows(DukeException.class, () -> gm.nullArgumentCheck(argumentInput));
    }

    @Test
    void isScheduledWorkoutNumberWithinRange_inputOutOfRangeNegative_failure() {
        GymManager gm = new GymManager();
        assertFalse(gm.isScheduledWorkoutNumberWithinRange(-256));
        assertFalse(gm.isScheduledWorkoutNumberWithinRange(0));
        assertFalse(gm.isScheduledWorkoutNumberWithinRange(2));
        assertFalse(gm.isScheduledWorkoutNumberWithinRange(2048));
    }


    @Test
    void isScheduledWorkoutNumberWithinRange_inputWithinRange_success() {
        GymManager gm = new GymManager();
        String argumentInput = "test /d 07/07/2021 /t 7:59PM";
        gm.addScheduledWorkout(argumentInput);
        assertTrue(gm.isScheduledWorkoutNumberWithinRange(1));
    }


    @Test
    void isCompletedWorkoutNumberWithinRange_inputOutOfRange_failure() {
        GymManager gm = new GymManager();
        assertFalse(gm.isCompletedWorkoutNumberWithinRange(-256));
        assertFalse(gm.isCompletedWorkoutNumberWithinRange(0));
        assertFalse(gm.isCompletedWorkoutNumberWithinRange(2));
        assertFalse(gm.isCompletedWorkoutNumberWithinRange(2048));
    }

    @Test
    void isCompletedWorkoutNumberWithinRange_inputWithinRange_success() {
        GymManager gm = new GymManager();
        String argumentInput = "test /c 123 /d 07/07/2021 /t 7:59PM";
        gm.addCompletedWorkout(argumentInput);
        assertTrue(gm.isCompletedWorkoutNumberWithinRange(1));
    }

    @Test
    void scheduledWorkoutSeparatorCheck_MissingSeparator_exceptionThrow() {
        GymManager gm = new GymManager();
        String argumentInput = "test /d 07/07/2021 7:59PM";
        assertThrows(DukeException.class, () -> gm.scheduledWorkoutSeparatorCheck(argumentInput));
    }

    @Test
    void completedWorkoutSeparatorCheck_MissingSeparator_exceptionThrow() {
        GymManager gm = new GymManager();
        String argumentInput = "test 123 07/07/2021 7:59PM";
        assertThrows(DukeException.class, () -> gm.scheduledWorkoutSeparatorCheck(argumentInput));
    }

    @Test
    void emptyScheduledWorkoutListCheck_emptyList_exceptionThrow() {
        GymManager gm = new GymManager();
        assertThrows(DukeException.class, gm::emptyScheduledWorkoutListCheck);
    }

    @Test
    void emptyCompletedWorkoutListCheck_emptyList_exceptionThrow() {
        GymManager gm = new GymManager();
        assertThrows(DukeException.class, gm::emptyCompletedWorkoutListCheck);
    }
}
