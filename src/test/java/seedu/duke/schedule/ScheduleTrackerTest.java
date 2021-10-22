package seedu.duke.schedule;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.ScheduleException;


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
    }

    @Test
    void scheduledWorkoutSeparatorCheck_MissingSeparator_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /d 07/07/2022 17:59";
        assertThrows(DukeException.class, () -> st.scheduledWorkoutSeparatorCheck(argumentInput));
    }

    @Test
    void deleteScheduledWorkout_outOfRange_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        assertThrows(DukeException.class, () -> st.deleteScheduledWorkout("-10"));
        assertThrows(DukeException.class, () -> st.deleteScheduledWorkout("123"));
    }



    @Test
    void deleteScheduledWorkout_nonInteger_exceptionThrow() throws DukeException, ScheduleException {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput1 = "test /d 07/07/2022 /t 17:59";
        st.addScheduledWorkout(argumentInput1, false); //must be non empty list
        String argumentInput2 = "@!$!@$!";
        assertThrows(NumberFormatException.class, () -> st.deleteScheduledWorkout(argumentInput2));
    }

    @Test
    void addScheduledWorkout_missingSeparator_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /d 07/07/2022 17:59";
        assertThrows(DukeException.class, () -> st.addScheduledWorkout(argumentInput, false));
    }

    @Test
    void addScheduledWorkout_invalidDateFormat_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /d 07-07-2022 /t 17:59";
        assertThrows(DateTimeParseException.class, () -> st.addScheduledWorkout(argumentInput, false));
    }

    @Test
    void addScheduledWorkout_invalidTimeFormat_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /d 07/07/2022 /t 7pm";
        assertThrows(DateTimeParseException.class, () -> st.addScheduledWorkout(argumentInput, false));
    }

    @Test
    void missingDescriptionCheck_missingDescription_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = " /d 07/07/2022 /t 07:59";
        assertThrows(DukeException.class, () -> st.missingDescriptionCheck(argumentInput));
    }
}
