package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.schedule.ScheduleTracker;
import seedu.duke.schedule.ScheduledWorkout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import static seedu.duke.ClickfitMessages.MEAL_PRINT_FORMAT;
import static seedu.duke.ClickfitMessages.FLUID_PRINT_FORMAT;
import static seedu.duke.ClickfitMessages.WORKOUT_PRINT_FORMAT;
import static seedu.duke.ClickfitMessages.WEIGHT_PRINT_FORMAT;
import static seedu.duke.ClickfitMessages.ENDLINE_PRINT_FORMAT;

public class Storage {

    public static final String SCHEDULE_FILE_PATH = "Schedule.txt";
    public static final String WORKOUT_FILE_PATH = "Workout.txt";
    public static final String foodFile = "Food.txt";
    public static final String libraryFile = "FoodBank.txt";
    public static final String weightFile = "Weight.txt";

    public Storage() {
        initializeFoodFile();
        initializeFoodBankFile();
        initializeWeightFile();
        initializeScheduleFile();
        initializeWorkoutFile();
    }

    public void saveFood(Fluid fluid, Meal meal) throws IOException {
        String currentDate;
        String currentMeal;
        String currentFluid;
        String header;
        String filePath = new File(foodFile).getAbsolutePath();
        FileWriter fw = new FileWriter(filePath, false);
        int headerFlag;
        header = "Meals" + "\n";
        Files.write(Paths.get(filePath), header.getBytes(), StandardOpenOption.APPEND);
        fw.close();
        for (String date : DateTracker.dates) {
            headerFlag = 0;
            for (String m : meal.meals) {
                if (m.contains(date) && (headerFlag == 0)) {
                    currentDate = "Date: " + date + "\n";
                    Files.write(Paths.get(filePath), currentDate.getBytes(), StandardOpenOption.APPEND);
                    fw.close();
                    headerFlag = 1;
                }
                if (m.contains(date)) {
                    currentMeal = m + "\n";
                    Files.write(Paths.get(filePath), currentMeal.getBytes(), StandardOpenOption.APPEND);
                    fw.close();
                }
            }
        }
        header = "Fluids" + "\n";
        Files.write(Paths.get(filePath), header.getBytes(), StandardOpenOption.APPEND);
        fw.close();
        for (String date : DateTracker.dates) {
            headerFlag = 0;
            for (String f : fluid.fluidArray) {
                if (f.contains(date) && (headerFlag == 0)) {
                    currentDate = "Date: " + date + "\n";
                    Files.write(Paths.get(filePath), currentDate.getBytes(), StandardOpenOption.APPEND);
                    fw.close();
                    headerFlag = 1;
                }
                if (f.contains(date)) {
                    currentFluid = f + "\n";
                    Files.write(Paths.get(filePath), currentFluid.getBytes(), StandardOpenOption.APPEND);
                    fw.close();
                }
            }
        }
    }

    public void saveLibrary() throws IOException {
        String customMeal;
        String customFluid;
        String header;
        String filePath = new File(libraryFile).getAbsolutePath();
        FileWriter fw = new FileWriter(filePath, false);
        header = "Meals" + "\n";
        Files.write(Paths.get(filePath), header.getBytes(), StandardOpenOption.APPEND);
        fw.close();
        for (String m : FoodBank.meals) {
            customMeal = m + "\n";
            Files.write(Paths.get(filePath), customMeal.getBytes(), StandardOpenOption.APPEND);
            fw.close();

        }
        header = "Fluids" + "\n";
        Files.write(Paths.get(filePath), header.getBytes(), StandardOpenOption.APPEND);
        fw.close();
        for (String f : FoodBank.fluids) {
            customFluid = f + "\n";
            Files.write(Paths.get(filePath), customFluid.getBytes(), StandardOpenOption.APPEND);
            fw.close();
        }
    }

    public void saveWeight(WeightTracker weight) throws IOException, DukeException {
        String currentDate;
        String currentWeight;
        String header;
        String filePath = new File(weightFile).getAbsolutePath();
        FileWriter fw = new FileWriter(filePath, false);
        int headerFlag;
        header = "Weights" + "\n";
        Files.write(Paths.get(filePath), header.getBytes(), StandardOpenOption.APPEND);
        fw.close();
        for (String date : DateTracker.dates) {
            headerFlag = 0;
            for (String w : weight.weightsArray) {
                if (w.contains(date) && (headerFlag == 0)) {
                    currentDate = "Date: " + date + "\n";
                    Files.write(Paths.get(filePath), currentDate.getBytes(), StandardOpenOption.APPEND);
                    fw.close();
                    headerFlag = 1;
                }
                if (w.contains(date)) {
                    if (w.contains(date)) {
                        currentWeight = w + "\n";
                        Files.write(Paths.get(filePath), currentWeight.getBytes(), StandardOpenOption.APPEND);
                        fw.close();
                    }
                }
            }
        }
    }

    //@@author arvejw
    public void saveWorkout(WorkoutTracker workoutTracker) throws IOException {
        FileWriter fileWriter = new FileWriter(WORKOUT_FILE_PATH, true);
        Set<String> workoutSet = new LinkedHashSet<>(workoutTracker.workouts);
        workoutSet.addAll(loadWorkouts());
        ArrayList<String> workouts = new ArrayList<>(workoutSet);
        DateTracker.sortDateAndTime(workouts);
        FileWriter fileCleaner = new FileWriter(WORKOUT_FILE_PATH, false);
        fileCleaner.write("");
        fileCleaner.close();
        for (String w : workouts) {
            fileWriter.write(w + System.lineSeparator());
        }
        fileWriter.close();
    }

    //@@author arvejw
    public void saveSchedule(ScheduleTracker scheduleTracker) throws IOException {
        FileWriter fileWriter = new FileWriter(SCHEDULE_FILE_PATH, true);
        ArrayList<String> currentScheduleStringList = new ArrayList<>();
        for (ScheduledWorkout w : scheduleTracker.getScheduledWorkouts()) {
            currentScheduleStringList.add(w.getScheduledWorkoutAsString());
        }
        Set<String> scheduleSet = new LinkedHashSet<>(currentScheduleStringList);
        scheduleSet.addAll(loadSchedule());
        ArrayList<String> schedule = new ArrayList<>(scheduleSet);
        DateTracker.sortDateAndTime(schedule);
        FileWriter fileCleaner = new FileWriter(SCHEDULE_FILE_PATH, false);
        fileCleaner.write("");
        fileCleaner.close();
        for (String s : schedule) {
            fileWriter.write(s + System.lineSeparator());
        }
        fileWriter.close();
    }

    public ArrayList<String> loadMeals() throws IOException {
        ArrayList<String> meals = new ArrayList<>();
        String newFilePath = new File(foodFile).getAbsolutePath();
        File f = new File(newFilePath);
        Scanner s = new Scanner(f);
        String textFromFile;
        int flag = 0;
        while ((s.hasNext()) && (flag == 0)) {
            textFromFile = s.nextLine();
            if (textFromFile.equals("Fluids")) {
                flag = 1;
            } else if (textFromFile.contains(Parser.CALORIE_SEPARATOR)) {
                meals.add(textFromFile);
            } else if (textFromFile.contains("Date")) {
                String[] date = textFromFile.split(" ");
                DateTracker.checkIfDateExists(date[1]);
            }
        }
        return meals;
    }

    public ArrayList<String> loadFluids() throws IOException {
        ArrayList<String> fluids = new ArrayList<>();
        String newFilePath = new File(foodFile).getAbsolutePath();
        File f = new File(newFilePath);
        Scanner s = new Scanner(f);
        String textFromFile;
        int flag = 0;
        while (s.hasNext()) {
            textFromFile = s.nextLine();
            if ((flag == 1) && (textFromFile.contains(Parser.CALORIE_SEPARATOR))) {
                fluids.add(textFromFile);
            } else if (textFromFile.equals("Fluids")) {
                flag = 1;
            } else if (textFromFile.contains("Date")) {
                String[] date = textFromFile.split(" ");
                DateTracker.checkIfDateExists(date[1]);
            }
        }
        return fluids;
    }

    public ArrayList<String> loadWeights() throws IOException {
        ArrayList<String> weights = new ArrayList<>();
        String newFilePath = new File(weightFile).getAbsolutePath();
        File f = new File(newFilePath);
        Scanner s = new Scanner(f);
        String textFromFile;
        int flag = 0;
        while (s.hasNext()) {
            textFromFile = s.nextLine();
            if (flag == 1) {
                weights.add(textFromFile);
            } else if (textFromFile.equals("Weights")) {
                flag = 1;
            } else if (textFromFile.contains("Date")) {
                String[] date = textFromFile.split(" ");
                DateTracker.checkIfDateExists(date[1]);
            }
        }
        return weights;
    }

    public ArrayList<String> loadMealLibrary() throws IOException {
        ArrayList<String> meals = new ArrayList<>();
        String newFilePath = new File(libraryFile).getAbsolutePath();
        File f = new File(newFilePath);
        Scanner s = new Scanner(f);
        String textFromFile;
        int flag = 0;
        while ((s.hasNext()) && (flag == 0)) {
            textFromFile = s.nextLine();
            if (textFromFile.equals("Fluids")) {
                flag = 1;
            } else if (textFromFile.contains(Parser.CALORIE_SEPARATOR)) {
                meals.add(textFromFile);
            } else if (textFromFile.contains("Date")) {
                String[] date = textFromFile.split(" ");
                DateTracker.checkIfDateExists(date[1]);
            }
        }
        return meals;
    }

    public ArrayList<String> loadFluidLibrary() throws IOException {
        ArrayList<String> fluids = new ArrayList<>();
        String newFilePath = new File(libraryFile).getAbsolutePath();
        File f = new File(newFilePath);
        Scanner s = new Scanner(f);
        String textFromFile;
        int flag = 0;
        while (s.hasNext()) {
            textFromFile = s.nextLine();
            if ((flag == 1) && (textFromFile.contains(Parser.CALORIE_SEPARATOR))) {
                fluids.add(textFromFile);
            } else if (textFromFile.equals("Fluids")) {
                flag = 1;
            } else if (textFromFile.contains("Date")) {
                String[] date = textFromFile.split(" ");
                DateTracker.checkIfDateExists(date[1]);
            }
        }
        return fluids;
    }

    //@@author arvejw
    public ArrayList<String> loadWorkouts() throws IOException {
        ArrayList<String> workout = new ArrayList<>();
        File dataFile = new File(WORKOUT_FILE_PATH);
        Scanner fileScanner = new Scanner(dataFile);
        String textFromFile;
        while (fileScanner.hasNext()) {
            textFromFile = fileScanner.nextLine();
            if (Parser.containsCalorieSeparator(textFromFile) && Parser.containsDateSeparator(textFromFile)
                    && Parser.containsTimeSeparator(textFromFile)) {
                workout.add(textFromFile);
            }
        }
        return workout;
    }

    //@@author arvejw
    public ArrayList<String> loadSchedule() throws IOException {
        ArrayList<String> schedule = new ArrayList<>();
        File dataFile = new File(SCHEDULE_FILE_PATH);
        Scanner fileScanner = new Scanner(dataFile);
        String textFromFile;
        while (fileScanner.hasNext()) {
            textFromFile = fileScanner.nextLine();
            if (Parser.containsDateSeparator(textFromFile) && Parser.containsTimeSeparator(textFromFile)) {
                schedule.add(textFromFile);
            }
        }
        return schedule;
    }

    //@@author arvejw
    public static void initializeScheduleFile() {
        File dataFile = new File(SCHEDULE_FILE_PATH);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ioe) {
                System.out.println("Error during data file creation for ScheduleTracker.");
            }
        }
    }

    public static void initializeFoodFile() {
        File dataFile = new File(foodFile);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ioe) {
                System.out.println("Error during data file creation for meals and fluids.");
            }
        }
    }

    public static void initializeFoodBankFile() {
        File dataFile = new File(libraryFile);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ioe) {
                System.out.println("Error during data file creation for meals and fluids.");
            }
        }
    }

    public static void initializeWeightFile() {
        File dataFile = new File(weightFile);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ioe) {
                System.out.println("Error during data file creation for meals and fluids.");
            }
        }
    }

    //@@author arvejw
    public static void initializeWorkoutFile() {
        File dataFile = new File(WORKOUT_FILE_PATH);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ioe) {
                System.out.println("Error during data file creation for WorkoutTracker.");
            }
        }
    }

    public void mealSummary() {
        int totalCalories = 0;
        int i = 1;
        try {
            for (String m : loadMeals()) {
                if (m.contains("[")) {
                    String[] descriptor = m.substring(1).split(" /c ");
                    String[] calorie = descriptor[1].split(" /d ");
                    String description = descriptor[0];
                    System.out.println(i + ". " + description);
                    String calorieIndiv = calorie[0];
                    totalCalories += Integer.parseInt(calorieIndiv);
                    i++;
                } else {
                    String[] descriptor = m.split(" /c ");
                    String[] calorie = descriptor[1].split(" /d ");
                    String description = descriptor[0];
                    System.out.println(i + ". " + description);
                    String calorieIndiv = calorie[0];
                    totalCalories += Integer.parseInt(calorieIndiv);
                    i++;
                }
            }
            System.out.println(System.lineSeparator() + "Total number of meals = " + (i - 1));
            System.out.println("Total calories = " + totalCalories);

        } catch (IOException e) {
            System.out.println("Error during printing arrayList");
        }
    }

    public void fluidSummary() {
        int totalCalories = 0;
        int totalVolume = 0;
        int i = 1;
        try {
            for (String f : loadFluids()) {
                if (f.contains("[")) {
                    String[] descriptor = f.substring(1).split(" /c ");
                    String[] calorie = descriptor[1].split(" /v ");
                    String[] volumeSplitter = calorie[1].split(" /d ");
                    String description = descriptor[0];
                    System.out.println(i + ". " + description);
                    String calorieIndiv = calorie[0];
                    String volumeIndiv = volumeSplitter[0];
                    totalCalories += Integer.parseInt(calorieIndiv);
                    totalVolume += Integer.parseInt((volumeIndiv));
                    i++;
                } else {
                    String[] descriptor = f.split(" /c ");
                    String[] calorie = descriptor[1].split(" /v ");
                    String[] volumeSplitter = calorie[1].split(" /d ");
                    String description = descriptor[0];
                    System.out.println(i + ". " + description);
                    String calorieIndiv = calorie[0];
                    String volumeIndiv = volumeSplitter[0];
                    totalCalories += Integer.parseInt(calorieIndiv);
                    totalVolume += Integer.parseInt((volumeIndiv));
                    i++;
                }
            }
            if (totalVolume > 0) {
                System.out.println("Total volume consumed = " + totalVolume);
            } else {
                System.out.println(System.lineSeparator() + "Total variety of drinks = " + (i - 1));
            }
            System.out.println("Total calories = " + totalCalories);
        } catch (IOException e) {
            System.out.println("Error during printing arrayList");
        }
    }

    public void weightSummary() {
        int i = 0;
        try {
            for (String ignored : loadWeights()) {
                i++;
            }
            System.out.println(System.lineSeparator() + "Total number of weights = " + i);
        } catch (IOException e) {
            System.out.println("Error during printing arrayList");
        }
    }

    public void workoutSummary() {
        int totalCalories = 0;
        int i = 1;
        try {
            for (String w : loadWorkouts()) {
                if (w.contains("[")) {
                    String[] descriptor = w.substring(1).split(" /c ");
                    String[] calorie = descriptor[1].split(" /d ");
                    String description = descriptor[0];
                    System.out.println(i + ". " + description);
                    String calorieIndiv = calorie[0];
                    totalCalories += Integer.parseInt(calorieIndiv);
                    i++;
                } else {
                    String[] descriptor = w.split(" /c ");
                    String[] calorie = descriptor[1].split(" /d ");
                    String description = descriptor[0];
                    System.out.println(i + ". " + description);
                    String calorieIndiv = calorie[0];
                    totalCalories += Integer.parseInt(calorieIndiv);
                    i++;
                }
            }
            System.out.println(System.lineSeparator() + "Completed Workouts = " + (i - 1));
            System.out.println("Total calories burned = " + totalCalories);

        } catch (IOException e) {
            System.out.println("Error during printing arrayList");
        }
    }

    public void printLoadedLists() {
        System.out.println(MEAL_PRINT_FORMAT);
        mealSummary();
        System.out.println(ENDLINE_PRINT_FORMAT);
        System.out.println(FLUID_PRINT_FORMAT);
        fluidSummary();
        System.out.println(ENDLINE_PRINT_FORMAT);
        System.out.println(WORKOUT_PRINT_FORMAT);
        workoutSummary();
        System.out.println(ENDLINE_PRINT_FORMAT);
        System.out.println(WEIGHT_PRINT_FORMAT);
        weightSummary();
        System.out.println(ENDLINE_PRINT_FORMAT);
        System.out.println(System.lineSeparator() + "Updated as of: "
                + Parser.getSystemDate() + " " + Parser.getSystemTime());
    }
}