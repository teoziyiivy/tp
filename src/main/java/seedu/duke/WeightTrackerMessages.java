package seedu.duke;

public class WeightTrackerMessages {
    public static void printGenerateParametersException() {
        System.out.println("CLI.ckFit could not generate your parameters.");
    }

    public static void printAddWeightResponse(double weight, String date) {
        System.out.println("Noted! CLI.ckFit has recorded your weight as "
                + weight + " on " + date + ". Check back for your progress!");
    }
}
