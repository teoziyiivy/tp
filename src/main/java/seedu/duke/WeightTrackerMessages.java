package seedu.duke;

//@@author teoziyiivy

/**
 * Contains functions for printing responses for <code>WeightTracker</code> class which
 * requires <code>weight</code> and <code>date</code> parameters.
 */
public class WeightTrackerMessages {
    /**
     * Prints response for weight has been added to weight array successfully.
     *
     * @param weight Weight recorded in weight array.
     * @param date Date recorded in weight array.
     */
    public static void printAddWeightResponse(double weight, String date) {
        System.out.println("Noted! CLI.ckFit has recorded your weight as "
                + weight + " on " + date + ". Check back for your progress!");
    }

    /**
     * Prints response for weight has been deleted from weight array successfully.
     *
     * @param weight Weight recorded in weight array.
     * @param date Date recorded in weight array.
     */
    public static void printDeleteWeightResponse(double weight, String date) {
        System.out.println("Noted! CLI.ckFit has successfully deleted your weight of "
                + weight + " on " + date + ".");
    }
}
