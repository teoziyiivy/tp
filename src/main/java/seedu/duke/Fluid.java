package seedu.duke;

import seedu.duke.exceptions.DukeException;

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

    public void generateFluidParameters(String inputArguments) throws DukeException {
        description = Parser.getDescription(inputArguments);
        calories = Parser.getCalories(inputArguments);
        volume = Parser.getVolume(inputArguments);
        date = Parser.getDate(inputArguments);
        time = Parser.getTime(inputArguments);
    }

    public void addFluid(String inputArguments) throws DukeException {
        generateFluidParameters(inputArguments);
        fluidArray.add(inputArguments);
        fluidNumber += 1;
        System.out.println("Noted! CLI.ckFit has recorded your drink of " + description + " of " + calories
                + " calories and " + volume + " ml on " + date + " " + time + "." + "\n");
    }

    public void deleteFluid(String inputArguments) throws DukeException {
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
                        + " calories and " + volume + " ml on " + date + " at " + time + "." + "\n");
                i++;
            }

            System.out.println("\n");
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            return;
        }
    }

}
