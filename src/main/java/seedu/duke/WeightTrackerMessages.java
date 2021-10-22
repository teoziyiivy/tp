package seedu.duke;

//@@author teoziyiivy
public class WeightTrackerMessages {
    public static void printGenerateParametersException() {
        System.out.println("CLI.ckFit could not generate your parameters.");
    }

    public static void printAddWeightResponse(int weight, String date) {
        System.out.println("Noted! CLI.ckFit has recorded your weight as "
                + weight + " on " + date + ". Check back for your progress!");
    }

    public static void printAddWeightException() {
        System.out.println("CLI.ckFit encountered a problem adding your weight.\n"
                + "Please follow the format: addweight <weight> /d <DD/MM/YYYY>");
    }

    public static void printDeleteWeightException() {
        System.out.println("CLI.ckFit encountered a problem deleting your weight.\n"
                + "Please follow the format: deleteweight <index>");
    }

    public static void printDeleteWeightIndexException() {
        System.out.println("CLI.ckFit encountered a problem deleting your weight.\n"
                + "Please ensure the index is within the list.");
    }

    public static void printNoWeightsException() {
        System.out.println("CLI.ckFit has no recorded weights.");
    }

    public static void printNoFoundWeightsException() {
        System.out.println("CLI.ckFit did not find any corresponding weights for that date.");
    }
}
