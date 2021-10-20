package seedu.duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DateTracker {
    protected static ArrayList<String> dates; //static

    public DateTracker() {
        dates = new ArrayList<>(); //constructor is not static
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

    public static void sortDateAndTime(ArrayList<String> list) {
        Collections.sort(list, new Comparator<>() {
            public int compare(String o1, String o2) {
                LocalDateTime o1DateTime = LocalDateTime.of(
                        LocalDate.parse(Parser.getDate(o1), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        LocalTime.parse(Parser.getTime(o1), DateTimeFormatter.ofPattern("HH:mm")));
                LocalDateTime o2DateTime = LocalDateTime.of(
                        LocalDate.parse(Parser.getDate(o2), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        LocalTime.parse(Parser.getTime(o2), DateTimeFormatter.ofPattern("HH:mm")));
                return o1DateTime.compareTo(o2DateTime);
            }
        });
    }
}
