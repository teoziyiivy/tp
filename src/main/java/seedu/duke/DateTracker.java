package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.schedule.ScheduleTracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DateTracker {
    protected static ArrayList<String> dates = new ArrayList<>();

    //@@author VishalJeyaram
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

    //@@author pragyan01
    public static void sortDate(ArrayList<String> list) {
        Collections.sort(list, new Comparator<String>() {
            public int compare(String o1, String o2) {
                LocalDate o1Date = LocalDate.parse(Parser.getDateNoDateTracker(o1),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalDate o2Date = LocalDate.parse(Parser.getDateNoDateTracker(o2),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                return o1Date.compareTo(o2Date);
            }
        });
    }

    //@@author VishalJeyaram
    public static void deleteDateFromList(String inputArguments, Fluid fluid, Meal meal,
                                          WorkoutTracker workoutTracker,
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
        for (String w : workoutTracker.workouts) {
            if (w.contains(date)) {
                return;
            }
        }
        for (String w : weightTracker.weightsArray) {
            if (w.contains(date)) {
                return;
            }
        }
        dates.remove(date);
    }

    ////@@author arvejw
    public static void sortDateAndTime(ArrayList<String> list) {
        Collections.sort(list, new Comparator<String>() {
            public int compare(String o1, String o2) {
                LocalDateTime o1DateTime = LocalDateTime.of(
                        LocalDate.parse(Parser.getDateNoDateTracker(o1),
                                DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        LocalTime.parse(Parser.getTime(o1),
                                DateTimeFormatter.ofPattern("HH:mm")));
                LocalDateTime o2DateTime = LocalDateTime.of(
                        LocalDate.parse(Parser.getDateNoDateTracker(o2),
                                DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        LocalTime.parse(Parser.getTime(o2),
                                DateTimeFormatter.ofPattern("HH:mm")));
                return o1DateTime.compareTo(o2DateTime);
            }
        });
    }
}

