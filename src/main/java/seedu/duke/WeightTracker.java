package seedu.duke;

import seedu.duke.exceptions.*;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class WeightTracker extends Tracker {
    protected ArrayList<String> weightsArray;
    protected int numberOfWeights;
    protected int weight;
    protected String date;

    public WeightTracker() {
        this.weightsArray = new ArrayList<>();
        this.numberOfWeights = 0;
    }

    public void generateWeightParameters(String inputArguments) {
        try {
            weight = Parser.getWeight(inputArguments);
            date = Parser.getDate(inputArguments);
        } catch (NumberFormatException | DukeException e) {
            printAddWeightException();
        }
    }

    public void readInput(String input)
            throws NumberFormatException, DukeException, DateTimeParseException {
        String[] splitLine = input.split(" ", 2);
        String command = splitLine[0];
        input = input.replaceAll("^" + command + " ", "");
        if (command.equals("checkweight")) {
            try {
                printWeight();
            } catch (NoWeightsException e) {
                printNoWeightsException();
            }
        } else if (command.equals("addweight")) {
            try {
                addWeight(input);
            } catch (AddWeightException e) {
                printAddWeightException();
            }
        } else if (command.equals("deleteweight")) {
            try {
                deleteWeight(input);
            } catch (DeleteWeightException e) {
                printDeleteWeightException();
            } catch (DeleteWeightIndexException e) {
                printDeleteWeightIndexException();
            }
        }
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
        generateWeightParameters(input);
        if (!input.matches("(.*) /d (.*)")) {
            throw new AddWeightException();
        } else {
            //weights.add(new WeightTracker(weight, date));
            printAddWeightResponse(weight, date);
            weightsArray.add(input);
            numberOfWeights += 1;
        }
    }

    public void deleteWeight(String input) throws DeleteWeightException, DeleteWeightIndexException {
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
        }
    }

    public void printWeight() throws NoWeightsException {
        if (numberOfWeights == 0) {
            throw new NoWeightsException();
        } else {
            System.out.println("Here are your recorded weights:");
            for (int i = 0; i < numberOfWeights; i++) {
                String indexToPrint = weightsArray.get(i);
                generateWeightParameters(indexToPrint);
                System.out.println((i + 1) + ". " + weight + " on " + date);
            }
        }
    }
}
