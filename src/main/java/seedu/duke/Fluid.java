package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.FluidExceptions;
import seedu.duke.exceptions.FoodBankException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author {P }
public class Fluid extends Tracker {

    protected ArrayList<String> fluidArray;
    protected int fluidNumber;
    protected String description;
    protected int calories;
    protected int volume;
    protected String date;
    protected String time;
    protected int totalCalories;
    protected int totalVolume;
    private static final Logger logr = Logger.getLogger("FluidLogger");

    public Fluid() {
        this.fluidArray = new ArrayList<>();
        this.fluidNumber = 0;
        this.totalCalories = 0;
        logr.setLevel(Level.SEVERE);
    }

    public void generateFluidParameters(String inputArguments) throws DukeException, FoodBankException {
        description = Parser.getDescription(inputArguments);
        calories = Parser.getCalories(inputArguments);
        volume = Parser.getVolume(inputArguments);
        date = Parser.getDate(inputArguments);
        time = Parser.getTime(inputArguments);
    }

    public void addFluid(String inputArguments) throws DukeException, FluidExceptions, FoodBankException {
        logr.entering(getClass().getName(), "addFluid");
        logr.info("going to generate fluid parameters from user input");
        generateFluidParameters(inputArguments);
        logr.info("end of generating fluid parameters");
        if ((description.equals("") || Parser.containsSeparators(description))) {
            throw new FluidExceptions();
        }
        inputArguments = description + " /c " + calories + " /v " + volume + " /d " + date + " /t " + time;
        fluidArray.add(inputArguments);
        logr.info("fluid intake has been added");
        fluidNumber += 1;
        totalCalories += calories;
        totalVolume += volume;
        System.out.println("Noted! CLI.ckFit has recorded your drink of " + description + " of " + calories
                + " calories and " + volume + " ml on " + date + " " + time + "." + "\n");
        logr.exiting(getClass().getName(), "addFluid");
    }

    public void deleteFluid(String inputArguments) throws DukeException, FoodBankException {
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
        totalCalories -= calories;
        totalVolume -= volume;
        System.out.println("Noted! CLI.ckFit has deleted your drink of " + description + " of " + calories
                + " calories and " + volume + " ml on " + date + " " + time + "." + "\n");
        logr.exiting(getClass().getName(), "deleteFluid");
    }

    public void listFluids(String date) throws FoodBankException, DukeException {
        logr.entering(getClass().getName(), "listFluids");
        if (date.equals("all")) {
            int i = 1;
            totalCalories = 0;
            fluidNumber = 0;
            for (String fluid : fluidArray) {
                logr.log(Level.INFO, "generating fluid parameters");
                generateFluidParameters(fluid);
                logr.log(Level.INFO, "fluid parameters generated");
                System.out.println(i + ". " + description);
                System.out.println("Calories: " + calories);
                System.out.println("Volume: " + volume);
                System.out.println("Date: " + date);
                System.out.println("Time: " + time + "\n");
                i += 1;
                totalCalories += calories;
                fluidNumber += 1;
            }
        } else {
            assert fluidNumber != 0;
            logr.entering(getClass().getName(), "listFluids");
            int i = 1;
            logr.log(Level.INFO, "entering for loop");
            totalCalories = 0;
            fluidNumber = 0;
            for (String fluid : fluidArray) {
                if (fluid.contains(date)) {
                    logr.log(Level.INFO, "generating fluid parameters");
                    generateFluidParameters(fluid);
                    logr.log(Level.INFO, "fluid parameters generated");
                    System.out.println(i + ". " + description);
                    System.out.println("Calories: " + calories);
                    System.out.println("Volume: " + volume);
                    System.out.println("Date: " + date);
                    System.out.println("Time: " + time + "\n");
                    i += 1;
                    totalCalories += calories;
                    fluidNumber += 1;
                }
            }
        }
        System.out.println("Total number of fluids: " + fluidNumber);
        System.out.println("Total calories: " + totalCalories);
        logr.log(Level.INFO, "fluid list printed");
        logr.exiting(getClass().getName(), "listFluids");
    }

    public int getCalories(String date) throws DukeException, FoodBankException {
        int calorieTotal = 0;
        for (String fluid : fluidArray) {
            if (fluid.contains(date)) {
                generateFluidParameters(fluid);
                calorieTotal += calories;
            }
        }
        return calorieTotal;
    }

    public int getVolume(String date) throws DukeException, FoodBankException {
        int volumeTotal = 0;
        for (String fluid : fluidArray) {
            if (fluid.contains(date)) {
                generateFluidParameters(fluid);
                volumeTotal += volume;
            }
        }
        return volumeTotal;
    }
}
