package seedu.duke;

import seedu.duke.gym.ScheduleTracker;
import seedu.duke.gym.WorkoutTracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class Storage {

    protected String filePath;
    protected ScheduleTracker scheduleTracker;
    protected WorkoutTracker workoutTracker;
    protected Meal meal;
    protected Fluid fluid;
    protected WeightTracker weightTracker;

    public Storage(String filePath,Fluid fluid, Meal meal, ScheduleTracker scheduleTracker, WorkoutTracker workoutTracker,
                   WeightTracker weightTracker) {
        this.filePath = filePath;
        this.fluid = fluid;
        this.meal = meal;
        this.scheduleTracker = scheduleTracker;
        this.workoutTracker = workoutTracker;
        this.weightTracker = weightTracker;
    }

    public void saveAllTasks() throws IOException {
        String filePath = new File(this.filePath).getAbsolutePath();
        FileWriter fw = new FileWriter(filePath, false);
        String todayDate;
        String currentDate;
        String currentMeal;
        String currentFluid;
        String customMeal;
        String customFluid;
        int indexNumber = 1;
        todayDate = "Today's date: " + Parser.getSystemDate();
        Files.write(Paths.get(filePath), todayDate.getBytes(), StandardOpenOption.APPEND);
        for (String date : DateTracker.dates) {
            currentDate = "Date: " + date;
            Files.write(Paths.get(filePath), currentDate.getBytes(), StandardOpenOption.APPEND);
            for (String meal : meal.meals) {
                currentMeal = indexNumber + ". " + meal;
                Files.write(Paths.get(filePath), currentMeal.getBytes(), StandardOpenOption.APPEND);
                indexNumber += 1;
            }
            indexNumber = 1;
            for (String fluid : fluid.fluidArray) {
                currentFluid = indexNumber + ". " + fluid;
                Files.write(Paths.get(filePath), currentFluid.getBytes(), StandardOpenOption.APPEND);
                indexNumber += 1;
            }
        }
        indexNumber = 1;
        for (String meal : FoodBank.meals) {
            customMeal = indexNumber + ". " + meal;
            Files.write(Paths.get(filePath), customMeal.getBytes(), StandardOpenOption.APPEND);
            indexNumber += 1;
        }
        indexNumber = 1;
        for (String fluid : FoodBank.fluids) {
            customFluid = indexNumber + ". " + fluid;
            Files.write(Paths.get(filePath), customFluid.getBytes(), StandardOpenOption.APPEND);
            indexNumber += 1;
        }
        fw.close();
    }
}





