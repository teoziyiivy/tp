# Developer Guide

## Acknowledgements
The UML Diagrams were generated with the help of: [PlantUML](https://plantuml.com/)

Written with reference to:
* https://se-education.org/addressbook-level2/DeveloperGuide.html
* https://se-education.org/addressbook-level3/DeveloperGuide.html

## Table of Contents
- [**Design & Implementation**](#design--implementation)
  - [**User Interface**](#user-interface-ui)
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

#### Meal: Listing Meals
![](https://user-images.githubusercontent.com/69350459/138880611-c82f4574-037f-4b64-9631-90d914f71701.png)
The UML sequence diagram above shows what happens when the user attempts to list their meals. If the user inputs
"list meals all", the if block's condition will be satisfied, and the method "listAllMeals()" will be called. If 
the user inputs "list meals DATE" where date is an arbitrary date, the method "listMealsByDate(userDate)" will be 
called, where userDate is the date specified by the user. Then, the method "printMealListTotals(mealNumber, totalCalories)"
will be called, printing out a message to tell the user the total meal calories and number of meals.

#### Class Diagram
![](https://user-images.githubusercontent.com/69350459/138307467-cef8cdd8-06ce-4284-92b5-9fe5e1ef50ef.png)
Above are the UML class level diagrams of `Meal`, and `Tracker`. As seen in
the diagram, the `Meal` class inherits from the `Tracker` class. This class diagram has been simplified for better readability.

### FoodBank
#### FoodBank: Adding custom meal

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
![WeightTracker_class](https://user-images.githubusercontent.com/69446729/140641329-091135c4-798a-4ebb-a5d4-2af4882fc053.png)
Above are the UML class level diagrams of `WeightTracker`, `WeightTrackerMessages`, `Tracker` and relevant exception classes. 
As seen in the diagram, the `WeightTracker` class is dependent on the `WeightTrackerMessages` class and inherits from the 
`Tracker` class. The `WeightTracker` class also throws 4 exceptions which inherit from the `WeightException` class.
This class diagram has been simplified for better readability.

#### Adding weight
![WeightTracker_add_sequence](https://user-images.githubusercontent.com/69446729/140641320-d243b7a8-a75c-4960-a731-6aeed02fd7ea.png)

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

CLI.ckFit is a desktop-based fitness app which can be accessed easily via CLI. CLI.ckFit allows you to input and track
your calories, weight, foods, and workouts throughout the day. It also allows you to save your data and view it whenever
you wish to. It also comes with a BMI and recommended caloric intake calculator which can give you an idea of 
your current fitness level. All these features come together and complement each other but can also be used 
independently of one another. For instance, CLI.ckFit can simply be used as a stand alone Weight tracker if the user
sees fit.

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

### Command testing
Please check out CLI.ckFit's [User Guide](https://ay2122s1-cs2113t-f14-3.github.io/tp/UserGuide.html) for more detailed
walk through of all commands and their formats.

Take note that the following sections will focus more on command behavior not specified in the User Guide, such as 
expected output for incorrect/invalid inputs. The test cases stated in the following sections are not exhaustive and 
testers are expected to do more exploratory work for more comprehensive testing. 

### Workout Commands

#### Adding a workout
1. Adding a workout omitting date and time
    * **Test Case**: `add workout test /c 123`
    * **Expected**: workout with description "test" and "123" calories burned will be recorded with the current date and time.
    
2. Adding a workout with specified date and time
    * **Test Case**: `add workout test /c 123 /d 08/11/2021 /t 23:59`
    * **Expected**: workout with description "test" and "123" calories burned will be recorded on "08/11/2021" at "23:59".
    
3. Adding a workout with missing description
    * **Test Case**: `add workout /c 123`
    * **Expected**:
      ```
      I am sorry... it appears the description is missing.
      Please enter a workout description!
      ```
   
4. Adding a workout with missing calorie separator "/c"
    * **Test Case**: `add workout test 123`
    * **Expected**:
      ```
      CLI.ckFit is having difficulties finding the calorie separator /c
      Please minimally have the format: add workout [workout_description] /c [calories]
      Do remember to put spaces between your separators!
      ```
5. Adding a workout with invalid date or time format
    * **Test Case**: `add workout test /c 123 /d 08-11-21 /t 5:00pm`
    * **Expected**: 
      ```
      Please enter your date and time in the right format. It should be "DD/MM/YYYY" and "HH:MM" respectively.
      ```
#### Deleting a workout
1. Deleting a workout with valid index
    * **Test Case**: `delete workout 1`
    * **Expected**: deletes the first workout in the workout list.
      
2. Deleting a workout with invalid index
    * **Test Case**: `delete workout -1`
    * **Expected**: 
      ```
      Failed to delete that workout! Please enter an Integer within range.
      ```
3. Deleting a workout without specifying index
    * **Test Case**: `delete workout`
    * **Expected**:
      ```
      Please enter the workout index in the format: delete workout [index]
      ```
#### Listing workouts
1. Listing workouts on date without any recorded workouts
    * **Test Case**: `list workouts 23/12/2021`
    * **Expected**: 
      ```
      No workouts recorded on the date: 23/12/2021
      ```
    
### Schedule Commands

#### Adding a scheduled workout
1. Adding a scheduled workout with missing date or time separators, "/d" and "/t"
    * **Test Case**: `add schedule 12/12/2021 23:59`
    * **Expected**: 
      ```
      CLI.ckFit is having difficulties finding the separators...
      Please enter in the format: add schedule [workout_description] /d [dd/mm/yyyy] /t [hh:mm]
      Do remember to put spaces between your separators.
      ```
2. Adding a scheduled workout with missing description
    * **Test Case**: `add schedule /d 12/12/2021 /t 23:59`
    * **Expected**:
      ```
      I am sorry... it appears the description is missing.
      Please enter a description for your scheduled workout!
      ```
4. Adding a scheduled workout with invalid date or time format
    * **Test Case**: `add schedule test /d 12-12-21 /t 11:59pm`
    * **Expected**:
      ```
      Please enter your date and time in the right format. It should be "DD/MM/YYYY" and "HH:MM" respectively.
      ```
5. Adding a scheduled workout with a date in the past
    * **Test Case**: `add schedule test /d 07/11/2021 /t 13:59`
    * **Expected**:
      ```
      Noted! CLI.ckFit has scheduled your workout of description "test" on 07/11/2021 at 13:59.
      CLI.ckFit has detected some overdue scheduled workouts and has deleted/rescheduled them!
      ```
5. Adding a scheduled workout with activity breakdown, missing activity splitter ":"
    * **Test Case**: `add schedule test /d 12/12/2021 /t 13:59 /a chest8x10, squats:3x10`
    * **Expected**:
      ```
      Missing activity splitter ":" detected.
      Please enter [activity name]:[sets]x[reps] or [activity name]:[distance in metres] for your workout activities
      ```
6. Adding a scheduled workout with activity breakdown, unnecessary quantifier "x"
    * **Test Case**: `add schedule test /d 12/12/2021 /t 13:59 /a swimming:1000x2`
    * **Expected**:
      ```
      Unnecessary activity quantifier splitter "x" detected.
      Please enter [activity name]:[distance in metres] for distance based workout activities if your
      activity name is either running/swimming/cycling.
      E.g. running:8000
      ```
7. Adding a scheduled workout with activity breakdown, missing quantifier "x"
    * **Test Case**: `add schedule test /d 07/11/2021 /t 13:59 /a chest:8x10, squats:3 10`
    * **Expected**:
      ```
      Missing activity quantifier "x" detected.
      Please enter your [sets]x[reps] for your non-distance based workout activities.
      ```
8. Adding a scheduled workout with activity breakdown with non-positive integers for distance or sets and reps
    * **Test Case**: `add schedule test /d 07/11/2021 /t 13:59 /a chest:8x10, squats:3x-10`
    * **Expected**: 
      ```
      There was an issue getting your activity breakdown.
      Please enter a positive integer [distance in metres] for distance based activities(swimming/running/cycling).
      E.g. running:8000
      Enter two positive integers in the format [set]x[reps] for everything else.
      E.g. bench press:3x12
      For multiple activities please separate them by ","
      ```
#### Deleting a scheduled workout
1. Deleting a scheduled workout with valid index
    * **Test Case**: `delete schedule 1`
    * **Expected**: deletes the first workout in the workout list.

2. Deleting a scheduled workout with invalid index
    * **Test Case**: `delete schedule -1`
    * **Expected**:
      ```
      Failed to delete that scheduled workout! Please enter an Integer within range.
      ```
3. Deleting a scheduled workout without specifying index
    * **Test Case**: `delete schedule`
    * **Expected**:
      ```
      Please enter the schedule index in the format: delete schedule [index]
      ```
#### Listing scheduled workouts
1. Listing workout schedule on date without any scheduled workouts
    * **Test Case**: `list schedule 23/12/2021`
    * **Expected**:
      ```
      Workout schedule is empty on the date: 23/12/2021
      ```
      