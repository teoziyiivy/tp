package seedu.duke.schedule;

import org.junit.jupiter.api.Test;

import seedu.duke.Parser;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.schedule.ScheduleException;


import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author arvejw
class ScheduleTrackerTest {

    @Test
    void nullArgumentCheck_NullInput_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        assertThrows(ScheduleException.class, () -> st.nullArgumentCheck(null));
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
        assertThrows(ScheduleException.class, () -> st.scheduledWorkoutSeparatorCheck(argumentInput));
    }

    @Test
    void deleteScheduledWorkout_outOfRange_exceptionThrow() throws ScheduleException {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput1 = "test /d 07/07/2022 /t 17:59";
        st.addScheduledWorkout(argumentInput1, false, true); //must be non empty list
        assertThrows(ScheduleException.class, () -> st.deleteScheduledWorkout("-10"));
    }

    @Test
    void deleteScheduledWorkout_nonInteger_exceptionThrow() throws ScheduleException {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput1 = "test /d 07/07/2022 /t 17:59";
        st.addScheduledWorkout(argumentInput1, false, true);
        String argumentInput2 = "@!$!@$!";
        assertThrows(NumberFormatException.class, () -> st.deleteScheduledWorkout(argumentInput2));
    }

    @Test
    void deleteScheduledWorkout_nullInput_exceptionThrow() throws ScheduleException {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput1 = "test /d 07/07/2022 /t 17:59";
        st.addScheduledWorkout(argumentInput1, false, true);
        assertThrows(ScheduleException.class, () -> st.deleteScheduledWorkout(null));
    }

    @Test
    void addScheduledWorkout_missingSeparator_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /d 07/07/2022 17:59";
        assertThrows(ScheduleException.class, () -> st.addScheduledWorkout(argumentInput, false, true));
    }

    @Test
    void addScheduledWorkout_invalidDateFormat_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /d 07-07-2022 /t 17:59";
        assertThrows(DateTimeParseException.class, () -> st.addScheduledWorkout(argumentInput, false, true));
    }

    @Test
    void addScheduledWorkout_invalidTimeFormat_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /d 07/07/2022 /t 7pm";
        assertThrows(DateTimeParseException.class, () -> st.addScheduledWorkout(argumentInput, false, true));
    }

    @Test
    void missingDescriptionCheck_missingDescription_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = " /d 07/07/2022 /t 07:59";
        assertThrows(ScheduleException.class, () -> st.missingDescriptionCheck(argumentInput));
    }

    @Test
    void addScheduledWorkout_noActivityQuantifier_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String input = "test123 /d 28/12/2021 /t 18:59 /a squats:310";
        assertThrows(ScheduleException.class, () -> st.addScheduledWorkout(input, false, true));
    }

    @Test
    void addScheduledWorkout_noActivitySeparator_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String input = "test123 /d 28/12/2021 /t 18:59 /a swimming  800";
        assertThrows(ScheduleException.class, () -> st.addScheduledWorkout(input, false, true));
    }

    @Test
    void addScheduledWorkout_noActivityMarker_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String input = "test123 /d 28/12/2021 /t 18:59 /a squats:3x10 swimming:600";
        assertThrows(ScheduleException.class, () -> st.addScheduledWorkout(input, false, true));
    }

    @Test
    void addScheduledWorkout_validActivityFormat_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String input1 = "test123 /d 28/12/2021 /t 18:59 /a swimming:310 /r";
        String input2 = "test123 /d 28/12/2021 /t 18:59 /a squats:3x10 /r";
        String input3 = "test123 /d 28/12/2021 /t 18:59 /a squats: 3x10, swimming:1000, running:1000";
        String[] inputs = {input1, input2, input3};
        for (String i : inputs) {
            assertDoesNotThrow(() -> st.addScheduledWorkout(i, false, true));
        }
    }

    @Test
    void addScheduledWorkout_unnecessaryActivityQuantifier_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String input = "test123 /d 28/12/2021 /t 18:59 /a squats: 3x10, swimming:1000x123, running:1000";
        assertThrows(ScheduleException.class, () -> st.addScheduledWorkout(input, false, true));
    }
}
