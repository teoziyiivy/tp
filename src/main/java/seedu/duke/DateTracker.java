package seedu.duke;

import java.util.ArrayList;

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
    }
}
