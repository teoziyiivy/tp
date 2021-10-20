package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.gym.ScheduleTracker;
import seedu.duke.gym.WorkoutTracker;
import java.util.*;

public class DateTracker {
    protected static ArrayList<String> dates;

    public DateTracker() {
        dates = new ArrayList<>();
    }

    public static void checkIfDateExists(String newDate) {
        for (String date : dates) {
            if (date.equals(newDate)) {
                return;
            }
        }
        dates.add(newDate);
        sortDate();
    }

    public static void sortDate() {
        Collections.sort(dates);
    }

    public static void sortTime(ArrayList<String> list) {
        Collections.sort(list, new Comparator<String>() {
            public int compare(String o1, String o2) {
                String[] left = o1.split("\\s+");
                String[] right = o2.split("\\s+");
                return left[left.length - 1].compareTo(right[right.length - 1]);
            }
        });
    }

    public static void deleteDateFromList(String inputArguments, Fluid fluid, Meal meal, ScheduleTracker scheduleTracker, WorkoutTracker workoutTracker,
                                          WeightTracker weightTracker) throws DukeException {
        String date = Parser.getDate(inputArguments);
        for (String m : meal.meals) {
            if (m.contains(date)) {
                return;
            }
        }
        for (String f : fluid.fluidArray) {
            if (f.contains(date)) {
                return;
            }
        }
        dates.remove(dates.indexOf(date));
    }
}
