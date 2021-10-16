package seedu.duke;

public class Calculator {

    protected int height;
    protected int weight;
    protected int bmi;


    public void getBMI() {
        System.out.println(System.lineSeparator() + "Your BMI outcome is " + System.lineSeparator());
        String getBMIOutcome;
        bmi = (weight / ((height/100) ^ 2) );

        if (bmi < 18.5) {
            getBMIOutcome = "Underweight";
        } else if ((bmi >= 18.5) && (bmi < 24.9)) {
            getBMIOutcome = "Normal weight";
        } else if ((bmi>= 25) && (bmi < 29.9)) {
            getBMIOutcome = "Overweight";
        } else {
            getBMIOutcome = "Obesity";
        }
        System.out.println(getBMIOutcome);
    }


}
