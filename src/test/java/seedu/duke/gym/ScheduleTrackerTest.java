package seedu.duke.gym;

import org.junit.jupiter.api.Test;

import seedu.duke.DukeException;


import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScheduleTrackerTest {

    @Test
    void nullArgumentCheck_NullInput_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        assertThrows(DukeException.class, () -> st.nullArgumentCheck(null));
    }

    @Test
    void isScheduledWorkoutNumberWithinRange_inputOutOfRangeNegative_failure() {
        ScheduleTracker st = new ScheduleTracker();
        assertFalse(st.isScheduledWorkoutNumberWithinRange(-256));
        assertFalse(st.isScheduledWorkoutNumberWithinRange(0));
        assertFalse(st.isScheduledWorkoutNumberWithinRange(2));
        assertFalse(st.isScheduledWorkoutNumberWithinRange(2048));
    }

    @Test
    void isScheduledWorkoutNumberWithinRange_inputWithinRange_success() throws DukeException {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /d 07/07/2021 /t 17:59";
        st.addScheduledWorkout(argumentInput);
        assertTrue(st.isScheduledWorkoutNumberWithinRange(1));
    }

    @Test
    void scheduledWorkoutSeparatorCheck_MissingSeparator_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /d 07/07/2021 17:59";
        assertThrows(DukeException.class, () -> st.scheduledWorkoutSeparatorCheck(argumentInput));
    }

    @Test
    void emptyScheduledWorkoutListCheck_emptyList_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        assertThrows(DukeException.class, st::emptyScheduledWorkoutListCheck);
    }

    @Test
    void deleteScheduledWorkout_outOfRange_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        assertThrows(DukeException.class, () -> st.deleteScheduledWorkout("-10"));
        assertThrows(DukeException.class, () -> st.deleteScheduledWorkout("123"));
    }



    @Test
    void deleteScheduledWorkout_nonInteger_exceptionThrow() throws DukeException {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput1 = "test /d 07/07/2021 /t 17:59";
        st.addScheduledWorkout(argumentInput1); //must be non empty list
        String argumentInput2 = "@!$!@$!";
        assertThrows(NumberFormatException.class, () -> st.deleteScheduledWorkout(argumentInput2));
    }

    @Test
    void addScheduledWorkout_missingSeparator_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /d 07/07/2021 17:59";
        assertThrows(DukeException.class, () -> st.addScheduledWorkout(argumentInput));
    }

    @Test
    void addScheduledWorkout_invalidDateFormat_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /d 07-07-2021 /t 17:59";
        assertThrows(DateTimeParseException.class, () -> st.addScheduledWorkout(argumentInput));
    }

    @Test
    void addScheduledWorkout_invalidTimeFormat_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /d 07/07/2021 /t 7pm";
        assertThrows(DateTimeParseException.class, () -> st.addScheduledWorkout(argumentInput));
    }

    @Test
    void missingDescriptionCheck_missingDescription_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = " /d 07/07/2021 /t 07:59";
        assertThrows(DukeException.class, () -> st.missingDescriptionCheck(argumentInput));
    }
}
