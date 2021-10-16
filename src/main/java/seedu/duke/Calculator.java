package seedu.duke;

public class Calculator {

    protected int height;
    protected int weight;
    protected int bmi;
    protected int idealCalories;
    protected int age;
    protected String sex;
    protected int activityLevel;
    protected double multiplier;

    public Calculator(String sex, int weight, int height, int age, int activityLevel) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        this.activityLevel = activityLevel;
        this.bmi = 0;
        this.idealCalories = 0;
        this.multiplier = 0;
    }

    public void getBmi() {
        System.out.println(System.lineSeparator() + "Your BMI outcome is " + System.lineSeparator());
        String getBmiOutcome;
        bmi = (weight / ((height / 100) ^ 2));

        if (bmi < 18.5) {
            getBmiOutcome = "Underweight";
        } else if ((bmi >= 18.5) && (bmi < 24.9)) {
            getBmiOutcome = "Normal weight";
        } else if ((bmi >= 25) && (bmi < 29.9)) {
            getBmiOutcome = "Overweight";
        } else {
            getBmiOutcome = "Obesity";
        }
        System.out.println(getBmiOutcome);
    }

    public void getIdealCalories() {
        System.out.println(System.lineSeparator() + "Your ideal number of calories to maintain your weight is "
                + System.lineSeparator());
        if (activityLevel == 1) {
            multiplier = 1.2;
        } else if (activityLevel == 2) {
            multiplier = 1.375;
        } else if (activityLevel == 3) {
            multiplier = 1.55;
        } else if (activityLevel == 4) {
            multiplier = 1.725;
        } else if (activityLevel == 5) {
            multiplier = 1.90;
        } else {
            System.out.println("go check yourself");
        }

        if (sex.equals("M")) {
            idealCalories = (int) ((66.5 + (13.75 * weight) + (5.003 * height) - (6.775 * age)) * multiplier);
        } else {
            idealCalories = (int) ((655.1 + (9.563 * weight) + (1.850 * height) - (4.676 * age)) * multiplier);
        }
        System.out.println(idealCalories);
    }
}
