package seedu.duke;

public class Fluid {

    protected String fluidName;
    protected int volume;
    protected String date;

    protected String[] fluidArray;

    //constructor
    public Fluid(String description, int volume, String date) {
        fluidArray = new String[100];
        this.fluidName = description;
        this.volume = volume;
        this.date = date;
    }

    public void toPrint() {
        System.out.println("Noted! CLI.ckFit has recorded your drink of " + fluidName + " of " + volume + " ml on " + date + "." + "\n");
    }
}
