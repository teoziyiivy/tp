package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.fluid.NoVolumeEntered;
import seedu.duke.exceptions.foodbank.FoodBankException;
import seedu.duke.exceptions.fluid.DeleteEmptyFluidListException;
import seedu.duke.exceptions.fluid.FluidExceptions;
import seedu.duke.exceptions.fluid.InvalidFluidDescription;
import seedu.duke.exceptions.fluid.NoDeleteFluidIndexException;
import seedu.duke.exceptions.fluid.NoFluidToDelete;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author pragyan01
/**
 * Class responsible for handling fluid entries.
 *
 * @author pragyan01
 */
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

    /**
     * Constructor of fluid class
     *
     */
    public Fluid() {
        this.fluidArray = new ArrayList<>();
        this.fluidNumber = 0;
        this.totalCalories = 0;
        logr.setLevel(Level.SEVERE);
    }

    /**
     * This method splits user input to extract parameters such as description, calories, volume, date & time.
     *
     * @param inputArguments user input provided
     * @throws FoodBankException if calories are not provided
     * @throws FluidExceptions if description not provided
     *
     * @author pragyan01
     */
    public void generateFluidParameters(String inputArguments) throws FoodBankException, FluidExceptions {
        try {
            description = Parser.getDescription(inputArguments);
            calories = Parser.getCalories(inputArguments);
            volume = Parser.getVolume(inputArguments);
            date = Parser.getDate(inputArguments);
            time = Parser.getTime(inputArguments);
        } catch (DukeException e) {
            throw new InvalidFluidDescription();
        }
    }

    /**
     * This method adds a fluid entry to the fluid array.
     *
     * @param inputArguments user input provided
     * @throws FluidExceptions if description or volume is not provided
     * @throws FoodBankException if calories are not provided
     *
     * @author pragyan01
     */
    public void addFluid(String inputArguments) throws FluidExceptions, FoodBankException {
        logr.entering(getClass().getName(), "addFluid");
        logr.info("going to generate fluid parameters from user input");
        if (inputArguments == null) {
            throw new InvalidFluidDescription();
        }
        generateFluidParameters(inputArguments);
        logr.info("end of generating fluid parameters");
        if (description.equals("") || Parser.containsSeparators(description)) {
            throw new InvalidFluidDescription();
        }
        if ((calories > 0) && (volume == 0)) {
            throw new NoVolumeEntered();
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

    /**
     * This method deletes a fluid entry from the fluid array.
     *
     * @param inputArguments user input provided
     * @throws FluidExceptions if fluid entry index is not provided
     * @throws FoodBankException if the fluid entry does not exist
     *
     * @author pragyan01
     */
    public void deleteFluid(String inputArguments) throws FoodBankException, FluidExceptions {
        logr.entering(getClass().getName(), "deleteFluid");
        if (inputArguments == null) {
            throw new NoDeleteFluidIndexException();
        }
        if (fluidArray.size() == 0) {
            throw new DeleteEmptyFluidListException();
        }
        assert fluidArray.size() != 0 : "Fluid array should not be empty";
        int taskNumber = Parser.parseStringToInteger(inputArguments) - 1;
        if ((taskNumber < 0) || (taskNumber > (fluidNumber - 1))) {
            throw new NoFluidToDelete();
        }
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

    /**
     * This method lists out all fluid entries stored.
     *
     *@param userDate date provided by user
     *@throws FluidExceptions if description for a fluid entry is not found
     *@throws FoodBankException if calories for a fluid entry is not found
     *
     * @author pragyan01
     */
    public void listFluids(String userDate) throws FoodBankException, FluidExceptions {
        if (fluidArray.size() == 0) {
            System.out.println("Your fluid list is empty!");
        }
        logr.entering(getClass().getName(), "listFluids");
        if (userDate.equals("all")) {
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
                if (fluid.contains(userDate)) {
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

    /**
     * This method sums up the calorie total for a specific date.
     *
     *@param date date provided by user
     *@throws FluidExceptions if description for a fluid entry is not found
     *@throws FoodBankException if calories for a fluid entry is not found
     *@return total calorie for the specific date
     *
     *@author pragyan01
     */
    public int getCalories(String date) throws FoodBankException, FluidExceptions {
        int calorieTotal = 0;
        for (String fluid : fluidArray) {
            if (fluid.contains(date)) {
                generateFluidParameters(fluid);
                calorieTotal += calories;
            }
        }
        return calorieTotal;
    }

    /**
     * This method sums up the volume total for a specific date.
     *
     *@param date date provided by user
     *@throws FluidExceptions if description for a fluid entry is not found
     *@throws FoodBankException if calories for a fluid entry is not found
     *@return total volume for the specific date
     *
     *@author pragyan01
     */
    public int getVolume(String date) throws FoodBankException, FluidExceptions {
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