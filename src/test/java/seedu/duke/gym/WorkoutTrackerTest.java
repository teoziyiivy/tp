package seedu.duke.gym;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkoutTrackerTest {

    @Test
    void nullArgumentCheck_NullInput_exceptionThrow() {
        ScheduleTracker wt = new ScheduleTracker();
        assertThrows(DukeException.class, () -> wt.nullArgumentCheck(null));
    }

    @Test
    void isCompletedWorkoutNumberWithinRange_inputOutOfRange_failure() {
        WorkoutTracker wt = new WorkoutTracker();
        assertFalse(wt.isWorkoutNumberWithinRange(-256));
        assertFalse(wt.isWorkoutNumberWithinRange(0));
        assertFalse(wt.isWorkoutNumberWithinRange(2));
        assertFalse(wt.isWorkoutNumberWithinRange(2048));
    }

    @Test
    void isCompletedWorkoutNumberWithinRange_inputWithinRange_success() throws DukeException {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput = "test /c 123 /d 07/07/2021 /t 17:59";
        wt.addWorkout(argumentInput);
        assertTrue(wt.isWorkoutNumberWithinRange(1));
    }

    @Test
    void completedWorkoutSeparatorCheck_MissingSeparator_exceptionThrow() {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput = "test 123 07/07/2021 17:59";
        assertThrows(DukeException.class, () -> wt.workoutSeparatorCheck(argumentInput));
    }

    @Test
    void emptyCompletedWorkoutListCheck_emptyList_exceptionThrow() {
        WorkoutTracker wt = new WorkoutTracker();
        assertThrows(DukeException.class, wt::emptyWorkoutListCheck);
    }

    @Test
    void deleteWorkout_outOfRange_exceptionThrow() {
        WorkoutTracker wt = new WorkoutTracker();
        assertThrows(DukeException.class, () -> wt.deleteWorkout("-10"));
        assertThrows(DukeException.class, () -> wt.deleteWorkout("123"));
    }

    @Test
    void deleteWorkout_nonInteger_exceptionThrow() {
        WorkoutTracker wt = new WorkoutTracker();
        String inputArguments = "@!$!@$!";
        assertThrows(DukeException.class, () -> wt.deleteWorkout(inputArguments));
    }

    @Test
    void addWorkout_missingSeparator_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /c 123 07/07/2021 17:59";
        assertThrows(DukeException.class, () -> st.addScheduledWorkout(argumentInput));
    }

    @Test
    void addWorkout_invalidDateFormat_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /c 123 /d 07-07-2021 /t 17:59";
        assertThrows(DateTimeParseException.class, () -> st.addScheduledWorkout(argumentInput));
    }

    @Test
    void addWorkout_invalidTimeFormat_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test /c 123 /d 07/07/2021 /t 7pm";
        assertThrows(DateTimeParseException.class, () -> st.addScheduledWorkout(argumentInput));
    }

    @Test
    void addWorkout_nonIntegerCalorie_exceptionThrow() {
        ScheduleTracker st = new ScheduleTracker();
        String argumentInput = "test c/ @$! /d 07/07/2021 /t 7pm";
        assertThrows(DateTimeParseException.class, () -> st.addScheduledWorkout(argumentInput));
    }
}