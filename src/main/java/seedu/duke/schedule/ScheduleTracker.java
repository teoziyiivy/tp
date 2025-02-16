package seedu.duke.schedule;

import seedu.duke.ClickfitMessages;
import seedu.duke.Storage;
import seedu.duke.Parser;
import seedu.duke.exceptions.schedule.InvalidActivityFormatException;
import seedu.duke.exceptions.schedule.DeleteScheduleException;
import seedu.duke.exceptions.schedule.MissingScheduleDescriptionException;
import seedu.duke.exceptions.schedule.MissingScheduleSeparatorException;
import seedu.duke.exceptions.schedule.NoScheduleIndexException;
import seedu.duke.exceptions.schedule.ScheduleException;
import seedu.duke.exceptions.schedule.ScheduleNullArgumentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//@@author arvejw
public class ScheduleTracker {

    private ArrayList<ScheduledWorkout> scheduledWorkouts;
    private static final int LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING = 1;
    private static final int FIRST_INDEX_IN_LIST = 0;
    private static final int DAYS_IN_A_WEEK = 7;
    private static final String INPUT_ALL = "all";
    public static final Logger SCHEDULE_TRACKER_LOGGER = Logger.getLogger("ScheduleTrackerLogger");

    public ScheduleTracker() {
        scheduledWorkouts = new ArrayList<>();
        SCHEDULE_TRACKER_LOGGER.setLevel(Level.SEVERE);
    }

    public ArrayList<ScheduledWorkout> getScheduledWorkouts() {
        return scheduledWorkouts;
    }

    public void loadScheduleData() {
        File dataFile = new File(Storage.SCHEDULE_FILE_PATH);
        if (dataFile.length() == 0) {
            return;
        }
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            System.out.println(ClickfitMessages.SCHEDULE_DATA_NOT_FOUND);
            return;
        }
        String currentLine = "";
        boolean isDataLoadCorrectly = true;
        while (fileScanner.hasNext()) {
            currentLine = fileScanner.nextLine();
            if (currentLine.isEmpty()) {
                continue;
            }
            try {
                addScheduledWorkout(currentLine, true, false);
            } catch (DateTimeParseException | ScheduleException e) {
                isDataLoadCorrectly = false;
            }
        }
        cleanUpScheduleList();
        if (!isDataLoadCorrectly) {
            System.out.println(ClickfitMessages.INCORRECT_LOADING_SCHEDULE_DATA);
        }
    }

    public String[] generateScheduledWorkoutParameters(String inputArguments) throws ScheduleException {
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Starting generation of parameters for scheduled workout.");
        String workoutDescription = Parser.getScheduleDescription(inputArguments);
        String workoutDate = Parser.getDateNoDateTracker(inputArguments);
        String workoutTime = Parser.getTime(inputArguments);
        String[] generatedParameters = {workoutDescription, workoutDate, workoutTime};
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Successfully generated parameters for scheduled workout.");
        return generatedParameters;
    }

    public void addScheduledWorkout(String inputArguments, boolean isSquelchAddMessage, boolean isCleanUp)
            throws ScheduleException {
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Starting to try and add scheduled workout.");
        nullArgumentCheck(inputArguments);
        assert inputArguments != null : "Exception should already been thrown if argument is null";
        missingDescriptionCheck(inputArguments);
        scheduledWorkoutSeparatorCheck(inputArguments);
        // index 0: description | index 1: date | index 2: time
        String[] generatedParameters = generateScheduledWorkoutParameters(inputArguments);
        assert generatedParameters.length == 3 : "Exactly 3 parameters should be generated";
        String workoutDescription = generatedParameters[0];
        String workoutDate = generatedParameters[1];
        String workoutTime = generatedParameters[2];
        Map<String, ArrayList<Integer>> activityMap;
        try {
            activityMap = Parser.getActivities(inputArguments);
        } catch (NumberFormatException nfe) {
            throw new InvalidActivityFormatException();
        }
        boolean isRecurringWorkout = Parser.isRecurringWorkout(inputArguments);
        scheduledWorkouts.add(
                new ScheduledWorkout(workoutDescription, workoutDate, workoutTime, activityMap, isRecurringWorkout)
        );
        ScheduledWorkout workout = scheduledWorkouts.get(scheduledWorkouts.size() - 1);
        if (!isSquelchAddMessage) {
            System.out.println("Noted! CLI.ckFit has scheduled your " + workout.isRecurringStatusAsText()
                    + "workout of description \"" + workoutDescription + "\" on " + workoutDate + " at "
                    + workoutTime + ".");
        }
        if (isCleanUp) {
            cleanUpScheduleList();
        }
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Successfully added workout to schedule.");
    }

    public boolean isScheduledWorkoutNumberWithinRange(int workoutNumber) {
        int upperBound = scheduledWorkouts.size();
        int lowerBound = LOWER_BOUND_INDEX_NON_EMPTY_LIST_ONES_INDEXING;
        return (workoutNumber >= lowerBound) && (workoutNumber <= upperBound);
    }

    public void deleteScheduledWorkout(String inputArguments) throws ScheduleException {
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Starting to try and delete scheduled workout.");
        if (inputArguments == null) {
            throw new NoScheduleIndexException();
        }
        assert inputArguments != null : "Exception should already been thrown if argument is null";
        if (isScheduledWorkoutListEmpty()) {
            System.out.println(ClickfitMessages.EMPTY_SCHEDULE_LIST_MESSAGE);
            return;
        }
        assert scheduledWorkouts.size() > 0 : "List should be non empty at this point";
        int workoutNumber = Parser.parseStringToInteger(inputArguments);
        int workoutIndex = workoutNumber - 1; // 0-indexing
        if (isScheduledWorkoutNumberWithinRange(workoutNumber)) {
            ScheduledWorkout workoutToDelete = scheduledWorkouts.get(workoutIndex);
            System.out.println("Noted! CLI.ckFit has successfully deleted your "
                    + workoutToDelete.isRecurringStatusAsText() + "scheduled workout of description \""
                    + workoutToDelete.getWorkoutDescription() + "\" on " + workoutToDelete.getWorkoutDate()
                    + " at " + workoutToDelete.getWorkoutTime() + "!");
            scheduledWorkouts.remove(workoutIndex);
            SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Successfully deleted scheduled workout.");
        } else {
            SCHEDULE_TRACKER_LOGGER.log(Level.WARNING, "Failed to delete scheduled workout.");
            throw new DeleteScheduleException();
        }
    }

    public void listScheduledWorkouts(String inputArguments) throws ScheduleException {
        if (isScheduledWorkoutListEmpty()) {
            System.out.println(ClickfitMessages.EMPTY_SCHEDULE_LIST_MESSAGE);
            return;
        }
        cleanUpScheduleList();
        if (inputArguments.equals(INPUT_ALL)) {
            listAllScheduledWorkouts();
        } else {
            listScheduledWorkoutsOnDate(inputArguments);
        }
    }

    public void listAllScheduledWorkouts() {
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Starting to try and list scheduled workouts.");
        System.out.println("Full Workout Schedule:" + System.lineSeparator() + ClickfitMessages.ENDLINE_PRINT_FORMAT);
        int currentIndex = 1;
        for (ScheduledWorkout workout : scheduledWorkouts) {
            System.out.println(currentIndex + ". " + workout.getWorkoutDescription() + workout.isRecurringStatus());
            System.out.println("Date: " + workout.getWorkoutDate());
            System.out.println("Time: " + workout.getWorkoutTime());
            System.out.println(workout.getActivitiesAsStringToPrint());
            currentIndex++;
        }
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Successfully listed workouts.");
    }

    public void listScheduledWorkoutsOnDate(String inputArguments) {
        ArrayList<ScheduledWorkout> filteredScheduleList = (ArrayList<ScheduledWorkout>) scheduledWorkouts.stream()
                .filter((t) -> t.getWorkoutDate().equals(inputArguments)).collect(Collectors.toList());
        if (filteredScheduleList.isEmpty()) {
            System.out.println("Workout schedule is empty on the date: " + inputArguments);
        } else {
            if (inputArguments.equals(Parser.getSystemDate())) {
                System.out.println("Today's workout schedule:" + System.lineSeparator()
                        + ClickfitMessages.ENDLINE_PRINT_FORMAT);
            } else {
                System.out.println("Workout schedule on " + inputArguments + ":" + System.lineSeparator()
                        + ClickfitMessages.ENDLINE_PRINT_FORMAT);
            }
            int currentIndex = 1;
            int workoutCount = 0;
            for (ScheduledWorkout workout : filteredScheduleList) {
                System.out.println(currentIndex + ". "
                        + workout.getWorkoutDescription() + workout.isRecurringStatus());
                System.out.println("Date: " + workout.getWorkoutDate());
                System.out.println("Time: " + workout.getWorkoutTime());
                System.out.println(workout.getActivitiesAsStringToPrint());
                currentIndex++;
                workoutCount++;
            }
            System.out.println("You have " + workoutCount + " scheduled workouts on that day!");
        }
    }

    public void cleanUpScheduleList() {
        if (isScheduledWorkoutListEmpty()) {
            return;
        }
        sortScheduleList();
        LocalDate currentDate = LocalDateTime.now().toLocalDate();
        boolean isAnyWorkoutUpdatedOrDeleted = false;
        boolean isAnyWorkoutOverdue = true;
        ScheduledWorkout firstWorkoutEntry;
        while (isAnyWorkoutOverdue) {
            firstWorkoutEntry = scheduledWorkouts.get(FIRST_INDEX_IN_LIST);
            if (firstWorkoutEntry.getWorkoutDateAsLocalDate().isBefore(currentDate)) {
                updateOrDeleteScheduledWorkout(firstWorkoutEntry, currentDate);
                isAnyWorkoutUpdatedOrDeleted = true;
            } else {
                isAnyWorkoutOverdue = false;
            }
        }
        if (isAnyWorkoutUpdatedOrDeleted) {
            System.out.println(ClickfitMessages.DELETE_OR_UPDATE_SCHEDULE_MESSAGE);
        }
    }

    public void updateOrDeleteScheduledWorkout(ScheduledWorkout scheduledWorkout, LocalDate currentDate) {
        if (scheduledWorkout.isRecurring()) {
            rescheduleRecurringWorkout(scheduledWorkout, currentDate);
        } else {
            scheduledWorkouts.remove(scheduledWorkout);
        }
        sortScheduleList();
    }

    public void rescheduleRecurringWorkout(ScheduledWorkout scheduledWorkout, LocalDate currentDate) {
        long daysUntilCurrentDate = ChronoUnit.DAYS.between(
                scheduledWorkout.getWorkoutDateAsLocalDate(), currentDate);
        long daysToAdd = (long) (Math.ceil((double) daysUntilCurrentDate / DAYS_IN_A_WEEK) * DAYS_IN_A_WEEK);
        scheduledWorkout.incrementWorkoutDate(daysToAdd);
    }

    public void sortScheduleList() {
        scheduledWorkouts.sort(Comparator.comparing(ScheduledWorkout::getWorkoutDateTime));
    }

    public void nullArgumentCheck(String inputArguments) throws ScheduleException {
        if (inputArguments == null) {
            SCHEDULE_TRACKER_LOGGER.log(Level.WARNING, "User input argument is null.");
            throw new ScheduleNullArgumentException();
        }
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "User input argument(s) is not null.");
    }

    public void scheduledWorkoutSeparatorCheck(String inputArguments) throws ScheduleException {
        boolean areSeparatorsCorrect = Parser.containsDateSeparator(inputArguments)
                && Parser.containsTimeSeparator(inputArguments);
        if (!areSeparatorsCorrect) {
            SCHEDULE_TRACKER_LOGGER.log(Level.WARNING, "Separators in user input are missing or invalid.");
            throw new MissingScheduleSeparatorException();
        }
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Separators in user input are correct.");
    }

    public boolean isScheduledWorkoutListEmpty() {
        return scheduledWorkouts.isEmpty();
    }

    public void missingDescriptionCheck(String inputArguments) throws ScheduleException {
        int indexOfFirstDateSeparator = inputArguments.indexOf(Parser.DATE_SEPARATOR.trim());
        String subStringBeforeDateSeparator = "";
        if (indexOfFirstDateSeparator != -1) { // date separator not found
            subStringBeforeDateSeparator = inputArguments.substring(0, indexOfFirstDateSeparator).trim();
        } else {
            scheduledWorkoutSeparatorCheck(inputArguments);
        }
        if (subStringBeforeDateSeparator.isEmpty()) {
            SCHEDULE_TRACKER_LOGGER.log(Level.WARNING, "Description is missing in user input arguments.");
            throw new MissingScheduleDescriptionException();
        }
        SCHEDULE_TRACKER_LOGGER.log(Level.INFO, "Description is present in user input arguments.");
    }
}
