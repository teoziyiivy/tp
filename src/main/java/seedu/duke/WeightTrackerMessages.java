package seedu.duke;

public class WeightTrackerMessages {
    public static void printAddWeightResponse(double weight, String date) {
        System.out.println("Noted! CLI.ckFit has recorded your weight as "
                + weight + " on " + date + ". Check back for your progress!");
    }

    public static void printDeleteWeightResponse(double weight, String date) {
        System.out.println("Noted! CLI.ckFit has successfully deleted your weight of "
                + weight + " on " + date + ".");
    }
}
