package seedu.duke.schedule;

import org.junit.jupiter.api.Test;
import seedu.duke.WorkoutTracker;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.FoodBankException;

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
    }

    @Test
    void isCompletedWorkoutNumberWithinRange_inputWithinRange_success() throws DukeException, FoodBankException {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput = "test /c 123 /d 07/07/2021 /t 17:59";
        wt.addWorkout(argumentInput, true);
        assertTrue(wt.isWorkoutNumberWithinRange(1));
    }

    @Test
    void deleteWorkout_outOfRange_exceptionThrow() {
        WorkoutTracker wt = new WorkoutTracker();
        assertThrows(DukeException.class, () -> wt.deleteWorkout("-10"));
        assertThrows(DukeException.class, () -> wt.deleteWorkout("123"));
    }

    @Test
    void deleteWorkout_nonInteger_exceptionThrow() throws DukeException, FoodBankException {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput1 = "test /c 123 /d 07/07/2021 /t 17:59";
        wt.addWorkout(argumentInput1, true);
        String argumentInput2 = "@!$!@$!";
        assertThrows(NumberFormatException.class, () -> wt.deleteWorkout(argumentInput2));
    }

    @Test
    void addWorkout_invalidDateFormat_exceptionThrow() {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput = "test /c 123 /d 07-07-2021 /t 17:59";
        assertThrows(DateTimeParseException.class, () -> wt.addWorkout(argumentInput, true));
    }

    @Test
    void addWorkout_invalidTimeFormat_exceptionThrow() {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput = "test /c 123 /d 07/07/2021 /t 7pm";
        assertThrows(DateTimeParseException.class, () -> wt.addWorkout(argumentInput, true));
    }

    @Test
    void addWorkout_nonIntegerCalorie_exceptionThrow() {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput = "test /c @$! /d 07/07/2021 /t 7:59";
        assertThrows(NumberFormatException.class, () -> wt.addWorkout(argumentInput, true));
    }

    @Test
    void missingDescriptionCheck_missingDescription_exceptionThrow() {
        WorkoutTracker wt = new WorkoutTracker();
        String argumentInput = "  /c 123 /d 07/07/2021 /t 07:59";
        assertThrows(DukeException.class, () -> wt.missingDescriptionCheck(argumentInput));
    }
}