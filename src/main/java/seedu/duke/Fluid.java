package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.FluidExceptions;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Fluid extends Tracker {

    protected static ArrayList<String> fluidArray;
    protected int fluidNumber;
    protected String description;
    protected int calories;
    protected int volume;
    protected String date;
    protected String time;
    private static final Logger logr = Logger.getLogger("FluidLogger");

    public Fluid() {
        fluidArray = new ArrayList<>();
        fluidNumber = 0;
        logr.setLevel(Level.WARNING);
    }

    public void generateFluidParameters(String inputArguments) throws DukeException {
        description = Parser.getDescription(inputArguments);
        calories = Parser.getCalories(inputArguments);
        volume = Parser.getVolume(inputArguments);
        date = Parser.getDate(inputArguments);
        time = Parser.getTime(inputArguments);
    }

    //drank coke /c 60 /v 200 /d 12/12/2021 /t 10:30
    public void addFluid(String inputArguments) throws DukeException, FluidExceptions {
        logr.entering(getClass().getName(), "addFluid");
        logr.info("going to generate fluid parameters from user input");
        generateFluidParameters(inputArguments);
        logr.info("end of generating fluid parameters");
        if ((description.equals("") || Parser.containsSeparators(description))) {
            throw new FluidExceptions();
        }
        fluidArray.add(inputArguments);
        logr.info("fluid intake has been added");
        fluidNumber += 1;
        System.out.println("Noted! CLI.ckFit has recorded your drink of " + description + " of " + calories
                + " calories and " + volume + " ml on " + date + " " + time + "." + "\n");
        logr.exiting(getClass().getName(), "addFluid");
    }

    public void deleteFluid(String inputArguments) throws DukeException {
        logr.entering(getClass().getName(), "deleteFluid");
        assert fluidArray.size() != 0 : "Fluid array should not be empty";
        int taskNumber = Parser.parseStringToInteger(inputArguments) - 1;
        logr.info("task number obtained from user input");
        logr.info("going to generate fluid parameters from user input");
        generateFluidParameters(fluidArray.get(taskNumber));
        logr.info("end of generating fluid parameters");
        fluidArray.remove(taskNumber);
        logr.info("fluid intake has been removed");
        fluidNumber -= 1;
        System.out.println("Noted! CLI.ckFit has deleted your drink of " + description + " of " + calories
                + " calories and " + volume + " ml on " + date + " " + time + "." + "\n");
        logr.exiting(getClass().getName(), "deleteFluid");
    }

    public void listFluid() {
        logr.entering(getClass().getName(), "listFluid");
        assert fluidArray.size() != 0 : "Fluid array should not be empty";
        try {
            logr.info("going to print fluid list");
            int i = 1;
            for (String fluid : fluidArray) {
                generateFluidParameters(fluid);
                System.out.println(i + ". " + description);
                System.out.println("Calories: " + calories);
                System.out.println("Volume: " + volume);
                System.out.println("Date: " + date);
                System.out.println("Time: " + time + "\n");
                i++;
            }
            System.out.println("\n");
            logr.info("finished printing fluid list");
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            return;
        }
        logr.exiting(getClass().getName(), "listFluid");
    }
}
