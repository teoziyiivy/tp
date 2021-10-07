package seedu.duke;

public class WeightTracker {
    private String weight;
    private String date;

    public WeightTracker(String weight, String date) {
        this.weight = weight;
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public String getDate() {
        return date;
    }

    public void checkWeight() {
        System.out.println("Your weight was " + getWeight() + " on " + getDate() + ".");
    }
}
