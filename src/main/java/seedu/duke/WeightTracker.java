package seedu.duke;

import java.util.ArrayList;

public class WeightTracker {
    protected ArrayList<String> weightsArray;
    protected int numberOfWeights;


    public WeightTracker() {
        this.weightsArray = new ArrayList<>();
        this.numberOfWeights = 0;
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
        }
    }

    public void printAddWeightResponse(String weight, String date) {
        System.out.println("Noted! CLI.ckFit has recorded your weight as "
                + weight + " on " + date + ". Check back for your progress!");
    }

    public void printAddWeightException() {
        System.out.println("There was a problem adding your weight.");
    }

    public void addWeight(String input) throws AddWeightException {
        if (!input.matches("(.*) (.*)")) {
            throw new AddWeightException();
        } else {
            //extracting the weight and date
            String weight = input.replaceAll(" .+", "");
            String date = input.replaceAll(".+ ", "");
            //weights.add(new WeightTracker(weight, date));
            printAddWeightResponse(weight, date);
            weightsArray.add(input);
            numberOfWeights += 1;
        }
    }

    public void printWeight() {
        System.out.println("Here are your recorded weights:");
        for (String a : weightsArray) {
            System.out.println(a);
        }
    }
}
