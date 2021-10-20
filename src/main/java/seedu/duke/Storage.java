package seedu.duke;

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

public class Storage {

    protected String filePath;
    public static final String SCHEDULE_DATA_FILE_PATH = "scheduleData.txt";
    public static final String WORKOUT_DATA_FILE_PATH = "workoutData.txt";

    public Storage(String filePath) {
        this.filePath = filePath;
        initializeScheduleDataFile();
        initializeWorkoutDataFile();
    }

    public void saveAllTasks(Fluid fluid, Meal meal,
                             WeightTracker weightTracker) throws IOException {
        String currentDate;
        String currentMeal;
        String currentFluid;
        String header;
        //String customMeal;
        //String customFluid;
        String filePath = new File(this.filePath).getAbsolutePath();
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
        /*
        headerFlag = 0;
        for (String m : FoodBank.meals) {
            if (headerFlag == 0) {
                header = "Meal Library" + "\n";
                Files.write(Paths.get(filePath), header.getBytes(), StandardOpenOption.APPEND);
                fw.close();
                headerFlag = 1;
            }
            customMeal = m + "\n";
            Files.write(Paths.get(filePath), customMeal.getBytes(), StandardOpenOption.APPEND);
            fw.close();
        }
        headerFlag = 0;
        for (String f : FoodBank.fluids) {
            if (headerFlag == 0) {
                header = "Fluid Library" + "\n";
                Files.write(Paths.get(filePath), header.getBytes(), StandardOpenOption.APPEND);
                fw.close();
                headerFlag = 1;
            }
            customFluid = f + "\n";
            Files.write(Paths.get(filePath), customFluid.getBytes(), StandardOpenOption.APPEND);
            fw.close();
        }

         */
    }

    public ArrayList<String> loadMeals() throws IOException {
        System.out.println("fuck");
        ArrayList<String> meals = new ArrayList<>();
        String newFilePath = new File(this.filePath).getAbsolutePath();
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
            }
        }
        return meals;
    }

    public ArrayList<String> loadFluids() throws IOException {
        ArrayList<String> fluids = new ArrayList<>();
        String newFilePath = new File(this.filePath).getAbsolutePath();
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
            }
        }
        return fluids;
    }

    public ArrayList<String> loadTasks() throws IOException {
        String newFilePath = new File(this.filePath).getAbsolutePath();
        ArrayList<String> loadedTasks = new ArrayList<>();
        File f = new File(newFilePath);
        Scanner s = new Scanner(f);
        String textFromFile;
        while (s.hasNext()) {
            textFromFile = s.nextLine();
            loadedTasks.add(textFromFile);
        }
        //System.out.println(Ui.STARTING_MESSAGE);
        return loadedTasks;
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
        String filePath = new File(this.filePath).getAbsolutePath();
        FileWriter fw = new FileWriter(filePath, false);
        String currentDate;
        String currentMeal;
        String currentFluid;
        String header;
        String customMeal;
        String customFluid;

    }
}