package seedu.duke;

import seedu.duke.exceptions.weight.AddWeightException;
import seedu.duke.exceptions.weight.DeleteWeightException;
import seedu.duke.exceptions.weight.DeleteWeightIndexException;
import seedu.duke.exceptions.weight.NoWeightsException;
import seedu.duke.exceptions.DukeException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author { I}
public class WeightTracker extends Tracker {
    protected ArrayList<String> weightsArray;
    protected int numberOfWeights;
    protected double weight;
    protected String date;
    private static Logger logger = Logger.getLogger("WeightTrackerLogger");

    public WeightTracker() {
        this.weightsArray = new ArrayList<>();
        this.numberOfWeights = 0;
        logger.setLevel(Level.SEVERE);
    }

    public void generateWeightParameters(String inputArguments) {
        try {
            logger.entering(getClass().getName(), "generateWeightParameters");
            logger.log(Level.INFO, "going to generate weight and date parameters from user input");
            weight = Parser.getWeight(inputArguments);
            date = Parser.getDate(inputArguments);
        } catch (NumberFormatException | DukeException e) {
            System.out.println(ClickfitMessages.WEIGHT_PARAMETERS_ERROR);
        }
        logger.exiting(getClass().getName(), "generateWeightParameters");
        logger.log(Level.INFO, "end of generating weight parameters");
    }

    public void addWeight(String input) throws AddWeightException, DateTimeParseException {
        logger.entering(getClass().getName(), "addWeight");
        logger.log(Level.INFO, "going to add a weight and date to the list");

        generateWeightParameters(input);
        logger.log(Level.INFO, "weight parameters generated");

        if (input.isEmpty()) {
            throw new AddWeightException();
        } else if (!input.matches("(.*) /d (.*)")) {
            input = weight + " /d " + date;
            WeightTrackerMessages.printAddWeightResponse(weight, date);
            weightsArray.add(input);
            numberOfWeights += 1;
            assert numberOfWeights > 0 : "number of logged weights should be more than zero";
        } else {
            WeightTrackerMessages.printAddWeightResponse(weight, date);
            weightsArray.add(input);
            numberOfWeights += 1;
            assert numberOfWeights > 0 : "number of logged weights should be more than zero";
        }
        logger.exiting(getClass().getName(), "addWeight");
        logger.log(Level.INFO, "end of processing addweight command");
    }

    public void deleteWeight(String input) throws DeleteWeightException, DeleteWeightIndexException {
        logger.entering(getClass().getName(), "deleteWeight");
        logger.log(Level.INFO, "going to remove a weight and date from the list");
        numberOfWeights = weightsArray.size();
        if (input.isEmpty() || input.matches("deleteweight")) {
            throw new DeleteWeightException();
        }
        int weightIndex = Parser.parseStringToInteger(input) - 1;
        if (weightIndex > numberOfWeights) {
            throw new DeleteWeightIndexException();
        } else {
            generateWeightParameters(weightsArray.get(weightIndex));
            WeightTrackerMessages.printDeleteWeightResponse(weight, date);
            weightsArray.remove(weightIndex);
            numberOfWeights--;
            assert numberOfWeights >= 0 : "number of logged weights should be more than or equal to zero";
        }
        logger.exiting(getClass().getName(), "deleteWeight");
        logger.log(Level.INFO, "end of processing deleteweight command");
    }

    public void listWeights(String date) throws NoWeightsException {
        numberOfWeights = weightsArray.size();
        if (numberOfWeights == 0) {
            throw new NoWeightsException();
        } else if (date.equals("all")) {
            listAllWeights();
        } else {
            listSpecificWeights(date);
        }
    }

    public void listSpecificWeights(String date) {
        logger.entering(getClass().getName(), "listSpecificWeights");
        logger.log(Level.INFO, "going to list specific logged weights and dates");
        int i = 1;
        int numberOfSpecificWeights = 0;
        for (String weights : weightsArray) {
            if (weights.contains(date)) {
                logger.log(Level.INFO, "generating weight parameters");
                generateWeightParameters(weights);
                logger.log(Level.INFO, "weight parameters generated");
                System.out.print(i + ". ");
                System.out.println("Weight: " + weight);
                i++;
                numberOfSpecificWeights++;
            }
        }
        System.out.println("Total number of weights: " + numberOfSpecificWeights);
        logger.exiting(getClass().getName(), "listSpecificWeights");
        logger.log(Level.INFO, "end of processing listSpecificWeights command");
    }

    public void listAllWeights() {
        logger.entering(getClass().getName(), "listAllWeights");
        logger.log(Level.INFO, "going to list all logged weights and dates");
        int i = 1;
        System.out.println("Here are your recorded weights:");
        for (String weights : weightsArray) {
            generateWeightParameters(weights);
            System.out.print(i + ". ");
            System.out.print(" Weight: " + weight + " ");
            System.out.println(" Date: " + date + System.lineSeparator());
            i++;
        }
        System.out.println("Total number of weights: " + (i - 1));
        logger.exiting(getClass().getName(), "listAllWeights");
        logger.log(Level.INFO, "end of processing listAllWeights command");
    }
}
