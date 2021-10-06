package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {

    @SuppressWarnings("FieldCanBeLocal")
    private static Fluid fluid;

    public static void sayDrank(String input) {               //drank coca cola 300 17/10/2021
        String[] arrayString = input.split(" ");
        int lastIndex = arrayString.length - 1;              //date
        int secondLastIndex = arrayString.length - 2;        //volume
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateTime = arrayString[lastIndex];
        LocalDate localDate = LocalDate.parse(dateTime, formatter);
        System.out.println("Date Entered: " + formatter.format(localDate) + "\n");
        String fluidDescription = arrayString[1];
        for (int i = 2; i < secondLastIndex; i++) {
            fluidDescription = fluidDescription.concat(" " + arrayString[i]);
        }
        int volume = Integer.parseInt(arrayString[secondLastIndex]);
        String date = arrayString[lastIndex];
        fluid = new Fluid(fluidDescription, volume, date);
        fluid.toPrint();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");
        System.out.println("Enter your wish: " + "\n");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String actionWord = input.split(" ")[0];

        switch (actionWord) {
        case "bye":
            System.exit(0);
            break;
        case "drank":
            try {
                sayDrank(input);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter in the format: drank [fluid name] [volume] [dd/yy/yyyy]");
            }
            break;
        default:
        }
    }
}
