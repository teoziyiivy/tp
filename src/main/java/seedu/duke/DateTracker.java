package seedu.duke;

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
}
