package seedu.duke;

import seedu.duke.exceptions.AddWeightException;
import seedu.duke.exceptions.DeleteWeightException;
import seedu.duke.exceptions.DeleteWeightIndexException;
import seedu.duke.exceptions.NoWeightsException;
import seedu.duke.exceptions.DukeException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeightTracker extends Tracker {
    protected ArrayList<String> weightsArray;
    protected int numberOfWeights;
    protected int weight;
    protected String date;
    private static final Logger logger = Logger.getLogger("WeightTrackerLogger");

    public WeightTracker() {
        this.weightsArray = new ArrayList<>();
        this.numberOfWeights = 0;
    }

    public void generateWeightParameters(String inputArguments) {
        try {
            logger.entering(getClass().getName(), "generateWeightParameters");
            logger.log(Level.INFO, "going to generate weight and date parameters from user input");
            weight = Parser.getWeight(inputArguments);
            date = Parser.getDate(inputArguments);
        } catch (NumberFormatException | DukeException e) {
            WeightTrackerMessages.printGenerateParametersException();
        }
        logger.exiting(getClass().getName(), "generateWeightParameters");
        logger.log(Level.INFO, "end of generating weight parameters");
    }

    public void readInput(String input)
            throws NumberFormatException, DukeException, DateTimeParseException {
        logger.setLevel(Level.SEVERE);
        logger.entering(getClass().getName(), "readInput");
        logger.log(Level.INFO, "going to start processing input");
        String[] splitLine = input.split(" ", 2);
        String command = splitLine[0];
        input = input.replaceAll("^" + command + " ", "");
        switch (command) {
        case "listweights":
            try {
                printWeight();
            } catch (NoWeightsException e) {
                logger.log(Level.WARNING, "caught empty list error", e);
                WeightTrackerMessages.printNoWeightsException();
            }
            break;
        case "addweight":
            try {
                addWeight(input);
            } catch (AddWeightException e) {
                logger.log(Level.WARNING, "caught incorrect input format error", e);
                WeightTrackerMessages.printAddWeightException();
            }
            break;
        case "deleteweight":
            try {
                deleteWeight(input);
            } catch (DeleteWeightException e) {
                logger.log(Level.WARNING, "caught empty input error", e);
                WeightTrackerMessages.printDeleteWeightException();
            } catch (DeleteWeightIndexException e) {
                logger.log(Level.WARNING, "caught invalid index error", e);
                WeightTrackerMessages.printDeleteWeightIndexException();
            }
            break;
        default:
            assert false : "detected command should not be null or invalid";
            logger.log(Level.SEVERE, "weight tracker encountered processing error");
            break;
        }
        logger.exiting(getClass().getName(), "readInput");
        logger.log(Level.INFO, "end of processing");
    }

    public void addWeight(String input) throws AddWeightException, DateTimeParseException {
        logger.entering(getClass().getName(), "addWeight");
        logger.log(Level.INFO, "going to add a weight and date to the list");
        if (!input.matches("(.*) /d (.*)")) {
            throw new AddWeightException();
        } else {
            generateWeightParameters(input);
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
        if (input.isEmpty() || input.matches("deleteweight")) {
            throw new DeleteWeightException();
        }
        int weightIndex = Parser.parseStringToInteger(input);
        if (weightIndex > numberOfWeights) {
            throw new DeleteWeightIndexException();
        } else {
            String weightToDelete = weightsArray.get(weightIndex - 1);
            generateWeightParameters(weightToDelete);
            System.out.println("Noted! CLI.ckFit has successfully deleted your weight of "
                    + weight + " on " + date + ".");
            weightsArray.remove(weightIndex - 1);
            numberOfWeights = numberOfWeights - 1;
            assert numberOfWeights >= 0 : "number of logged weights should be more than or equal to zero";
        }
        logger.exiting(getClass().getName(), "deleteWeight");
        logger.log(Level.INFO, "end of processing deleteweight command");
    }

    public void printWeight() throws NoWeightsException {
        logger.entering(getClass().getName(), "printWeight");
        logger.log(Level.INFO, "going to list all logged weights and dates");
        if (numberOfWeights == 0) {
            throw new NoWeightsException();
        } else {
            int i = 1;
            System.out.println("Here are your recorded weights:");
            for (String weights : weightsArray) {
                generateWeightParameters(weights);
                System.out.println(i + ". ");
                System.out.println("Weight: " + weight);
                System.out.println("Date: " + date);
                i++;
            }
        }
        logger.exiting(getClass().getName(), "printWeight");
        logger.log(Level.INFO, "end of processing printweight command");
    }
}
