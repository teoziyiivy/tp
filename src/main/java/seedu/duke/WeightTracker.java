package seedu.duke;

import java.util.ArrayList;

public class WeightTracker extends Tracker {
    protected ArrayList<String> weightsArray;
    protected int numberOfWeights;


    public WeightTracker() {
        this.weightsArray = new ArrayList<>();
        this.numberOfWeights = 0;
    }

    public String getWeight(String input) {
        return input.replaceAll(" /d.+", "");
    }

    public String getDate(String input) {
        return input.replaceAll(".+/d ", "");
    }

    public void readInput(String input) {
        String[] splitLine = input.split(" ", 2);
        String command = splitLine[0];
        input = input.replaceAll("^" + command + " ", "");
        if (command.equals("checkweight")) {
            printWeight();
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

    public void printAddWeightResponse(String weight, String date) {
        System.out.println("Noted! CLI.ckFit has recorded your weight as "
                + weight + " on " + date + ". Check back for your progress!");
    }

    public void printAddWeightException() {
        System.out.println("There was a problem adding your weight.\n"
                + "Please follow the format: addweight <weight> /d <date>");
    }

    public void printDeleteWeightException() {
        System.out.println("There was a problem deleting your weight.\n"
                + "Please follow the format: deleteweight <index>");
    }

    public void printDeleteWeightIndexException() {
        System.out.println("There was a problem deleting your weight.\n"
                + "Please ensure the index is within the list.");
    }

    public void addWeight(String input) throws AddWeightException {
        if (!input.matches("(.*) /d (.*)")) {
            throw new AddWeightException();
        } else {
            //extracting the weight and date
            String weight = getWeight(input);
            String date = getDate(input);
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
            String weightToRemove = weightsArray.get(weightIndex - 1);
            System.out.println("Noted! CLI.ckFit has successfully deleted your weight of "
                    + getWeight(weightToRemove) + " on " + getDate(weightToRemove) + ".");
            weightsArray.remove(weightIndex - 1);
        }
    }

    public void printWeight() {
        System.out.println("Here are your recorded weights:");
        for (int i = 0; i < numberOfWeights; i++) {
            System.out.println((i + 1) + ". " + getWeight(weightsArray.get(i)) + " on "
                    + getDate(weightsArray.get(i)));
        }
    }
}
