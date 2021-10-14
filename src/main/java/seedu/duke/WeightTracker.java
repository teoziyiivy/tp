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
            printGenerateParametersException();
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
        case "checkweight":
            try {
                printWeight();
            } catch (NoWeightsException e) {
                logger.log(Level.WARNING, "caught empty list error", e);
                printNoWeightsException();
            }
            break;
        case "addweight":
            try {
                addWeight(input);
            } catch (AddWeightException e) {
                logger.log(Level.WARNING, "caught incorrect input format error", e);
                printAddWeightException();
            }
            break;
        case "deleteweight":
            try {
                deleteWeight(input);
            } catch (DeleteWeightException e) {
                logger.log(Level.WARNING, "caught empty input error", e);
                printDeleteWeightException();
            } catch (DeleteWeightIndexException e) {
                logger.log(Level.WARNING, "caught invalid index error", e);
                printDeleteWeightIndexException();
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

    public void printGenerateParametersException() {
        System.out.println("CLI.ckFit could not generate your parameters.");
    }

    public void printAddWeightResponse(int weight, String date) {
        System.out.println("Noted! CLI.ckFit has recorded your weight as "
                + weight + " on " + date + ". Check back for your progress!");
    }

    public void printAddWeightException() {
        System.out.println("CLI.ckFit encountered a problem adding your weight.\n"
                + "Please follow the format: addweight <weight> /d <DD/MM/YYYY>");
    }

    public void printDeleteWeightException() {
        System.out.println("CLI.ckFit encountered a problem deleting your weight.\n"
                + "Please follow the format: deleteweight <index>");
    }

    public void printDeleteWeightIndexException() {
        System.out.println("CLI.ckFit encountered a problem deleting your weight.\n"
                + "Please ensure the index is within the list.");
    }

    public void printNoWeightsException() {
        System.out.println("CLI.ckFit has no recorded weights.");
    }

    public void addWeight(String input) throws AddWeightException, DateTimeParseException {
        logger.entering(getClass().getName(), "addWeight");
        logger.log(Level.INFO, "going to add a weight and date to the list");
        generateWeightParameters(input);
        if (!input.matches("(.*) /d (.*)")) {
            throw new AddWeightException();
        } else {
            //weights.add(new WeightTracker(weight, date));
            printAddWeightResponse(weight, date);
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
            String indexToRemove = weightsArray.get(weightIndex - 1);
            generateWeightParameters(indexToRemove);
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
            System.out.println("Here are your recorded weights:");
            for (int i = 0; i < numberOfWeights; i++) {
                String indexToPrint = weightsArray.get(i);
                generateWeightParameters(indexToPrint);
                System.out.println((i + 1) + ". Weight: " + weight + " on Date: " + date);
            }
        }
        logger.exiting(getClass().getName(), "printWeight");
        logger.log(Level.INFO, "end of processing printweight command");
    }
}
