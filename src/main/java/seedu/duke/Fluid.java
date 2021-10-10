package seedu.duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Fluid extends Tracker {

    protected ArrayList<String> fluidArray;
    protected int fluidNumber;
    protected String description;
    protected int calories;
    protected int volume;
    protected String date;
    protected String time;

    public Fluid() {
        this.fluidArray = new ArrayList<>();
        this.fluidNumber = 0;
    }

    public void generateFluidParameters(String inputArguments) {
        try {
            description = Parser.getDescription(inputArguments);
            calories = Parser.getCalories(inputArguments);
            volume = Parser.getVolume(inputArguments);
            date = Parser.getDate(inputArguments);
            time = Parser.getTime(inputArguments);
        } catch (NumberFormatException e) {

        } catch (DukeException e) {

        } catch (DateTimeParseException e) {

        }
    }

    public void addFluid(String inputArguments) {               //drank cola /c 60 /v 100 /d 12/12/2021 /t 10:30
        fluidArray.add(inputArguments);                         //drank water /c 0 /v 300 /d 11/11/2021 /t 12:30
        fluidNumber += 1;                                       //drank juice /c 30 /v 75 /d 03/10/2021 /t 22:30
        System.out.println("Noted! CLI.ckFit has recorded your drink of " + description + " of " + calories
                + " calories and " + volume + " ml on " + date + " " + time + "." + "\n");
    }

    public void deleteFluid(String inputArguments) {
        int taskNumber = Parser.parseStringToInteger(inputArguments) - 1;
        generateFluidParameters(fluidArray.get(taskNumber));
        fluidArray.remove(taskNumber);
        fluidNumber -= 1;
        System.out.println("Noted! CLI.ckFit has deleted your drink of " + description + " of " + calories
                + " calories and " + volume + " ml on " + date + " " + time + "." + "\n");
    }

    public void listFluid() {
        try {
            int i = 1;
            for (String fluid : fluidArray) {
                generateFluidParameters(fluid);
                System.out.println(i + ". " + description + " of " + calories
                        + " calories and " + volume + " ml on " + date + " " + time + "." + "\n");
                i++;
            }
            System.out.println("\n");
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }

}
