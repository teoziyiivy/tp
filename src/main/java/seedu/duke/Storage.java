package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.workout.ScheduleTracker;
import seedu.duke.workout.WorkoutTracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.ClickfitMessages.MEAL_PRINT_FORMAT;
import static seedu.duke.ClickfitMessages.FLUID_PRINT_FORMAT;
import static seedu.duke.ClickfitMessages.WORKOUT_PRINT_FORMAT;
import static seedu.duke.ClickfitMessages.ENDLINE_PRINT_FORMAT;

public class Storage {

    protected String foodFile;
    protected String libraryFile;
    protected String weightFile;
    public static final String SCHEDULE_DATA_FILE_PATH = "scheduleData.txt";
    public static final String WORKOUT_DATA_FILE_PATH = "workoutData.txt";

    public Storage(String foodFile, String libraryFile, String weightFile) {
        this.foodFile = foodFile;
        this.libraryFile = libraryFile;
        this.weightFile = weightFile;
        initializeScheduleDataFile();
        initializeWorkoutDataFile();
    }

    public void saveFood(Fluid fluid, Meal meal) throws IOException {
        String currentDate;
        String currentMeal;
        String currentFluid;
        String header;
        String filePath = new File(this.foodFile).getAbsolutePath();
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
        String filePath = new File(this.libraryFile).getAbsolutePath();
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
        String filePath = new File(this.weightFile).getAbsolutePath();
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
                    String[] splitLine = w.split(" ", 2);
                    String command = splitLine[0];
                    w = w.replaceAll("^" + command + " ", "");
                    currentWeight = "Weight: " + String.valueOf(Parser.getWeight(w)) + "\n";
                    Files.write(Paths.get(filePath), currentWeight.getBytes(), StandardOpenOption.APPEND);
                    fw.close();
                }
            }
        }
    }

    public ArrayList<String> loadMeals() throws IOException {
        ArrayList<String> meals = new ArrayList<>();
        String newFilePath = new File(this.foodFile).getAbsolutePath();
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
        String newFilePath = new File(this.foodFile).getAbsolutePath();
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

    public ArrayList<String> loadMealLibrary() throws IOException {
        ArrayList<String> meals = new ArrayList<>();
        String newFilePath = new File(this.libraryFile).getAbsolutePath();
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
        String newFilePath = new File(this.libraryFile).getAbsolutePath();
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

    public static void initializeScheduleDataFile() {
        File dataFile = new File(SCHEDULE_DATA_FILE_PATH);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ioe) {
                System.out.println("Error during data file creation for ScheduleTracker.");
            }
        }
    }

    public static void writeToScheduleDataFile(String textToWrite) throws IOException {
        FileWriter fileWriter = new FileWriter(SCHEDULE_DATA_FILE_PATH);
        fileWriter.write(textToWrite);
        fileWriter.close();
    }

    public static void saveScheduleData(ScheduleTracker scheduleTracker) {
        if (scheduleTracker == null) {
            System.out.println("Unable to find ScheduleTracker object.");
            return;
        }
        try {
            writeToScheduleDataFile(scheduleTracker.getScheduleListAsString());
        } catch (IOException ioe) {
            System.out.println("Error during writing to data file for ScheduleTracker.");
        }
    }

    public static void initializeWorkoutDataFile() {
        File dataFile = new File(WORKOUT_DATA_FILE_PATH);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ioe) {
                System.out.println("Error during data file creation for WorkoutTracker.");
            }
        }
    }

    public static void writeToWorkoutDataFile(String textToWrite) throws IOException {
        FileWriter fileWriter = new FileWriter(WORKOUT_DATA_FILE_PATH);
        fileWriter.write(textToWrite);
        fileWriter.close();
    }

    public static void saveWorkoutData(WorkoutTracker workoutTracker) {
        if (workoutTracker == null) {
            System.out.println("Unable to find WorkoutTracker object.");
            return;
        }
        try {
            writeToWorkoutDataFile(workoutTracker.getWorkoutListAsString());
        } catch (IOException ioe) {
            System.out.println("Error during writing to data file for WorkoutTracker.");
        }
    }

    public void loadAllTasks(Fluid fluid, Meal meal, ScheduleTracker scheduleTracker, WorkoutTracker workoutTracker,
                             WeightTracker weightTracker) throws IOException {
        String filePath = new File(this.foodFile).getAbsolutePath();
        FileWriter fw = new FileWriter(filePath, false);
        String currentDate;
        String currentMeal;
        String currentFluid;
        String header;
        String customMeal;
        String customFluid;
    }

    public ArrayList<String> loadWorkouts() throws IOException {
        ArrayList<String> workout = new ArrayList<>();
        File dataFile = new File(Storage.WORKOUT_DATA_FILE_PATH);
        Scanner fileScanner = new Scanner(dataFile);
        String textFromFile;
        int flag = 0;
        while ((fileScanner.hasNext())) {
            textFromFile = fileScanner.nextLine();
            if (textFromFile.contains(Parser.CALORIE_SEPARATOR)) {
                workout.add(textFromFile);
            }
        }
        return workout;
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
        System.out.println(System.lineSeparator() + "Updated as of: "
                + Parser.getSystemDate() + " " + Parser.getSystemTime());
    }
}