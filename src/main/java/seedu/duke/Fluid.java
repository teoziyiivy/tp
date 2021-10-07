package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Fluid {

    protected ArrayList<String> fluidArray;
    protected int numberOfFluids;

    //constructor
    public Fluid() {
        this.fluidArray = new ArrayList<>();
        this.numberOfFluids = 0;
    }

    public void toPrint(String fluidName, int volume, String date) {
        System.out.println("Noted! CLI.ckFit has recorded your drink of "
                + fluidName
                + " of "
                + volume
                + " ml on "
                + date + "."
                + "\n");
    }

    public void sayDrank(String input) {               //drank coca cola 300 17/10/2021
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
        toPrint(fluidDescription, volume, date);
        fluidArray.add(input);
        numberOfFluids += 1;
    }
}
