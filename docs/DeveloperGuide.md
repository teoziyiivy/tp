# Developer Guide

## Acknowledgements
The UML Diagrams were generated with the help of: [PlantUML](https://plantuml.com/)

Written with reference to:
* https://se-education.org/addressbook-level2/DeveloperGuide.html
* https://se-education.org/addressbook-level3/DeveloperGuide.html

## Table of Contents
- [**Design & Implementation**](#design-&-implementation)
  - [**User Interface**](#user-interface-(ui))
  - [**Meal Tracker**](#mealtracker)
  - [**Fluid Tracker**](#fluidtracker)
  - [**Weight Tracker**](#weighttracker)
  - [**Schedule Tracker**](#scheduletracker)
- [**Appendix A: Product Scope**](#appendix-a-product-scope)
- [**Appendix B: User Stories**](#appendix-b-user-stories)
- [**Appendix C: Non-Functional Requirements**](#appendix-c-non-functional-requirements)
- [**Appendix D: Glossary**](#appendix-d-glossary)
- [**Appendix E: Instructions for manual testing**](#appendix-e-instructions-for-manual-testing)

## Design & implementation

### User Interface (Ui)

The proposed Ui class has the class level attributes of sex, weight, height, age and activityLevel. It consists of 3 
methods that in turn reference from different classes.

It implements the following operations:

* welcomeMessage()
* getInfo()
* memoryStartup()

These operations will be illustrated through UML diagrams.

#### Printing the welcome message
The user launches the CLI for the first time. The welcomeMessage() is called first and prints out the messages imported from Clickfitmessages class.

#### Getting BMI and recommended daily caloric intake
![Imgur](https://i.imgur.com/TZbe6Qh.png)

The user is then greeted with a prompt that asks whether he or she wished to enter the calculator function of CLI.ckFit. 
The calculator takes in the following inputs as shown in the UML diagram through the instantiating of a new calculator
object that takes in the class-level attributes of Ui to calculate the user's BMI in getBmi and the user's recommended 
daily caloric intake through getIdealCalories().

#### Get summary of all info stored in text files
The user is next greeted with a second prompt that asks whether he or she wishes to get a summary of all meals, fluids 
and workouts he has eaten or completed.
memoryStartup() is used to return a Boolean value. If it returns ture, it enters an if-loop in Duke class which calls 
the method printLoadedLists() in Storage class. In storage class, the text files(storage) of Meal, Fluid and Workout 
classes are first converted
to arrayLists, which are converted to arrayLists, which are then referenced by PrintLoadedLists() to be formatted and
printed as a summary of all stored information iu the text files.


### MealTracker

#### Class Diagram

![](https://user-images.githubusercontent.com/69350459/138307467-cef8cdd8-06ce-4284-92b5-9fe5e1ef50ef.png)

Above are the UML class level diagrams of `Meal`, and `Tracker`. As seen in
the diagram, the `Meal` class inherits from the `Tracker` class. This class diagram has been simplified for better readability.

#### Listing Meals
![](https://user-images.githubusercontent.com/69350459/138880611-c82f4574-037f-4b64-9631-90d914f71701.png)
The UML sequence diagram above shows what happens when the input command is recognised as `addweight`.
The `WeightTracker` class calls the `generateWeightParameters` function which updates the `weight` and `date`
variables. Then the `weight` and `date` variables are added to weight array list and `printAddWeightResponse`
is called from the `WeightTrackerMessages` class for both the typical input and missing date cases. However,
when an exception is encountered, the `WeightTracker` class will throw `AddWeightException()` instead.

### FluidTracker
#### Class diagram

![](https://user-images.githubusercontent.com/69446495/138308110-c73bc021-3744-4164-98dc-52b7f76cb4c0.png)

Above are the UML class level diagrams of `Fluid`, `FluidExceptions` and `Tracker`. As seen in the diagram, the `Fluid` class is dependent on the `FluidExceptions` and the `Fluid`class inherits from the `Tracker` class. This class diagram has been simplified for better readability.

#### Adding fluid sequence diagram
![](https://user-images.githubusercontent.com/69446495/140606905-45c62251-ae7e-43f5-a5aa-b37d7a12ec1a.png)

The UML sequence diagram above shows what happens when the input command is recognised as `add fluid`.
`generateFluidParameters` method in the `Fluid class` is called upon which updates variables relevent to a fluid, such its `description`, `calories`, `volume`, `date` and `time`. An `if` block checks for possible errors in user input, which are caught by their respective exceptions. Otherwise, variables are then concatenated together as a string called `inputArguments` and added to the `fluidArray` list to be saved.

#### Deleting fluid sequence diagram
![](https://user-images.githubusercontent.com/69446495/140607494-11daab2e-4c64-482c-80e9-4bf435ef554d.png)

The UML sequence diagram above shows what happens when the input command is recognised as `delete fluid`.
`generateFluidParameters` method in the `Fluid class` is called upon which updates variables relevent to a fluid, such its `description`, `calories`, `volume`, `date` and `time`. `taskNumber`, which refers to the respective fluid's entry index is parsed from user input. An `if` block checks for possible errors in user input, which are caught by their respective exceptions. Otherwise, `fluidArray.remove(taskNumber)` is called, which deletes the relevant entry from the `fluidArray` list.

#### Get total calories for specific date fluid sequence diagram
![](https://user-images.githubusercontent.com/69446495/140621687-7f221499-f29b-4003-a0f5-90d26ecf7f16.png)

The UML sequence diagram above shows what happens when the input command is recognised as `list calories`. For all fluid entries stored in `fluidArray`, if the entries contain the date as provided, `generateFluidParameters` method in the `Fluid class` is called upon which updates variables relevent to a fluid, such its `description`, `calories`, `volume`, `date` and `time`. The `calorie` parameter for each fluid entry in the `fluidArray` is added up, which returns `calorieTotal` at the end of the method's lifeline.

### WeightTracker

#### Class diagram
![WeightTracker_class](https://user-images.githubusercontent.com/69446729/138873653-d5db5c99-1f22-4c68-86af-188f1ea2c593.png)

Above are the UML class level diagrams of `WeightTracker`, `WeightTrackerMessages`, `Tracker` and relevant exception classes. 
As seen in the diagram, the `WeightTracker` class is dependent on the `WeightTrackerMessages` class and inherits from the 
`Tracker` class. The `WeightTracker` class also throws 4 exceptions which inherit from the `WeightException` class.
This class diagram has been simplified for better readability.

#### Adding weight
![WeightTracker_add_sequence](https://user-images.githubusercontent.com/69446729/138879720-3c3632c5-0765-4215-a2f8-5df7eea45277.png)

The UML sequence diagram above shows what happens when the input command is recognised as `addweight`.
The `WeightTracker` class calls the `generateWeightParameters` function which updates the `weight` and `date` 
variables. Then the `weight` and `date` variables are added to weight array list and `printAddWeightResponse`
is called from the `WeightTrackerMessages` class for both the typical input and missing date cases. However,
when an exception is encountered, the `WeightTracker` class will throw `AddWeightException()` instead.

### ScheduleTracker

#### Class diagram

![ScheduleTracker_class_diag](https://user-images.githubusercontent.com/69461398/138324203-ea286780-6611-43f4-af77-3ea7cb59a42c.png)

Above are the UML class level diagrams of `ScheduleTracker` and `ScheduledWorkout`. As seen in the diagram, one 
`ScheduleTracker` object keeps track/is linked to **any** number of `ScheduledWorkout` objects, thus have a 
multiplicity of `0..*`. This association forms through a private attribute `scheduledWorkouts` which is of type 
`ArrayList<ScheduledWorkout>`. 

Each `ScheduledWorkout` object also has a private attribute called `activities` which is 
of type `ArrayList<WorkoutActivity>` where `activities` keeps track of **any** number of `WorkoutActivity` objects.
Do note that **not all** class attributes and methods are present in the diagram for 
the sake of better comprehensibility.

#### Adding scheduled workout
![ScheduleTracker_add_diag](https://user-images.githubusercontent.com/69461398/140618156-1622efcb-7c48-48a6-b611-788138ec955f.png)

The UML sequence diagram above shows what happens when the method `addScheduledWorkout(...)` is called. 
Parameters are generated and a `ScheduledWorkout` object is added into the `scheduledWorkouts` ArrayList.
There are a few method calls which are omitted from this diagram. The main focus is on `cleanUpScheduleList()` where
the rescheduling or deleting of overdue workouts take place. This process as represented by the ref frame 
`updateOrDeleteScheduledWorkout`, will be elaborated on in the next section.

#### Updating and deleting of overdue workouts
![ScheduleTracker_update_diag](https://user-images.githubusercontent.com/69461398/140618246-1fea0a8f-46b2-499a-a05f-cf85f38133d5.png)


If there are any overdue workouts, a `loop` continues until there are no more overdue workouts in the schedule list.
Essentially, depending on whether the overdue workout detected is recurring, the workout is deleted or rescheduled 
appropriately.

This process is done to ensure a correctly sorted and cleaned up list is always output to the user. 
This also ensures the `scheduledWorkouts` ArrayList remains free of overdue workouts.

## Appendix A: Product scope
### Target user profile
Specifically made for people getting into serious fitness routines such as athletes but helpful for general populace.

### Value proposition

CLI.ckFit is a comprehensive workout client with the two main components of a workout diary and a calorie manager
with the peripherals being weight tracking and BMI/recommended caloric intake calculator. These features can be
conveniently accessed via the Command Line Interface (CLI).

## Appendix B: User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|fitness enthusiast|record my fitness activities|plan my extensive workout schedule|
|v1.0|user|update how many calories I have burned through my workouts|keep track of my daily calories|
|v1.0|athlete|record my weight|keep track of and maintain a competitive weight|
|v2.0|user|have the app remember my user data|access my data anytime| 
|v2.0|user|have scheduled workouts in the list to be sorted by the nearest dates|easily keep track of upcoming workouts|
|v2.0|frequent gym goer| be able to schedule recurring weekly workouts| have a routine schedule without having to reschedule the same workout every week|
|v2.0|serious athlete|breakdown my workout into smaller activities|track things like sets, reps and distance|


## Appendix C: Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed (Java has backward compatibility).
2. A user with above average typing speed in English text (i.e. not coding/system admin commands) should 
   be able to accomplish most of the tasks faster using commands than using the mouse.

## Appendix D: Glossary

* *recurring workouts* - workouts that are scheduled ***weekly***
* *overdue workouts* - workouts whose dates are before the current date

## Appendix E: Instructions for manual testing

### Launching CLI.ckFit
1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `CLI.ckFit` from [here](http://link.to/duke).
3. Go to the folder you saved the `CLIckFit.jar` file and note the absolute file path.
4. If you are using Windows, open up a Command prompt terminal cmd.exe or powershell.exe and for
   Mac and Linux users, do the same with the terminal of your respective systems.
5. Navigate to the folder where the `CLIckFit.jar` file is stored.
6. Execute `java -jar CLIckFit.jar` in the terminal, and the application will start running.

**Expected**: CLI.ckFit will launch and a welcome message will be displayed.

### Testing command behavior
1. Refer to the commands and expected behavior as stated in CLI.ckFit's current [User Guide](https://ay2122s1-cs2113t-f14-3.github.io/tp/UserGuide.html)

**Note**: During testing, please consider what is written in the **Known limitations** section of the User Guide.

### Saving Data
 Data is saved in "Food.txt", "FoodBank.txt", "Weight.txt", "Workout.txt" and "Schedule.txt" in the same folder
as your `CLIckFit.jar`.
   * **Test Case**:
      1. Run `CLIckFit.jar`.
         * **Note**: Ensure this is done in a new isolated folder or all data is already wiped prior.
      3. Add one valid meal, fluid, weight, workout and schedule entry each.
      4. Verify that there is a new entry in all the respective data files.
         * **Note**: Closing and reopening the data files might be required to see the changes.
      5. Exit the application.
      6. Verify that data is still saved in their respective data files.
      7. Run `CLIckFit.jar` again and load the saved data.
      8. Verify that data is loaded correctly by listing the respective entries.
      9. Delete all entries added in *step 3*.
      9. Exit the application.
   * **Expected**: "Food.txt", "FoodBank.txt", "Weight.txt", "Workout.txt" and "Schedule.txt" will be empty.
    

