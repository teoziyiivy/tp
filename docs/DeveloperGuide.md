# Developer Guide

## Acknowledgements
The UML Diagrams were generated with the help of: [PlantUML](https://plantuml.com/)

Written with reference to:
* https://se-education.org/addressbook-level2/DeveloperGuide.html
* https://se-education.org/addressbook-level3/DeveloperGuide.html

## Design & implementation

### Ui(User Interface): Class diagram

The proposed Ui class has the class level attributes of sex, weight, height, age and activityLevel. It consists of 3 
methods that in turn reference from different classes.

It implements the following operations:

* welcomeMessage()
* getInfo()
* memoryStartup()

These operations will be illustrated through UML diagrams.

### Printing the welcome message
The user launches the CLI for the first time. The welcomeMessage() is called first and prints out the messages imported from Clickfitmessages class.

### Getting BMI and recommended daily caloric intake
![Imgur](https://i.imgur.com/TZbe6Qh.png)

The user is then greeted with a prompt that asks whether he or she wished to enter the calculator function of CLI.ckFit. 
The calculator takes in the following inputs as shown in the UML diagram through the instantiating of a new calculator
object that takes in the class-level attributes of Ui to calculate the user's BMI in getBmi and the user's recommended 
daily caloric intake through getIdealCalories().

### Get summary of all info stored in text files
The user is next greeted with a second prompt that asks whether he or she wishes to get a summary of all meals, fluids 
and workouts he has eaten or completed.
memoryStartup() is used to return a Boolean value. If it returns ture, it enters an if-loop in Duke class which calls 
the method printLoadedLists() in Storage class. In storage class, the text files(storage) of Meal, Fluid and Workout 
classes are first converted
to arrayLists, which are converted to arrayLists, which are then referenced by PrintLoadedLists() to be formatted and
printed as a summary of all stored information iu the text files.


### Meal: Listing Meals
![](https://user-images.githubusercontent.com/69350459/138880611-c82f4574-037f-4b64-9631-90d914f71701.png)


### WeightTracker: Class diagram
![WeightTracker_class](https://user-images.githubusercontent.com/69446729/138873653-d5db5c99-1f22-4c68-86af-188f1ea2c593.png)

Above are the UML class level diagrams of `WeightTracker`, `WeightTrackerMessages`, `Tracker` and relevant exception classes. 
As seen in the diagram, the `WeightTracker` class is dependent on the `WeightTrackerMessages` class and inherits from the 
`Tracker` class. The `WeightTracker` class also throws 4 exceptions which inherit from the `WeightException` class.
This class diagram has been simplified for better readability.

#### WeightTracker: Adding weight
![WeightTracker_add_sequence](https://user-images.githubusercontent.com/69446729/138879720-3c3632c5-0765-4215-a2f8-5df7eea45277.png)

The UML sequence diagram above shows what happens when the input command is recognised as `addweight`.
The `WeightTracker` class calls the `generateWeightParameters` function which updates the `weight` and `date` 
variables. Then the `weight` and `date` variables are added to weight array list and `printAddWeightResponse`
is called from the `WeightTrackerMessages` class for both the typical input and missing date cases. However,
when an exception is encountered, the `WeightTracker` class will throw `AddWeightException()` instead.

### ScheduleTracker
![diagram-2070120484733536202](https://user-images.githubusercontent.com/69461398/138324203-ea286780-6611-43f4-af77-3ea7cb59a42c.png)

#### Class diagram
Above are the UML class level diagrams of `ScheduleTracker` and `ScheduledWorkout`. As seen in the diagram, one 
`ScheduleTracker` object keeps track/is linked to **any** number of `ScheduledWorkout` objects, thus have a 
multiplicity of `0..*`. This association forms through a private attribute `scheduledWorkouts` which is of type 
`ArrayList<ScheduledWorkout>`. 

Each `ScheduledWorkout` object also has a private attribute called `activities` which is 
of type `ArrayList<WorkoutActivity>` where `activities` keeps track of **any** number of `WorkoutActivity` objects.
Do note that **not all** class attributes and methods are present in the diagram for 
the sake of better comprehensibility.

#### Sequence diagram for adding scheduled workout

![diagram-18374474381804594155](https://user-images.githubusercontent.com/69461398/138323717-0975d9b3-392a-4c41-99c4-73a8915933be.png)

The UML sequence diagram above shows what happens when the method `addScheduledWorkout(...)` is called. 
Parameters are generated and a `ScheduledWorkout` object is added into the `scheduledWorkouts` ArrayList.
There are a few method calls which are omitted from this diagram. The main focus is on `cleanUpScheduleList()`. 

Once called, **if** there are any overdue workouts, a `loop` is entered and calls `updateOrDeleteScheduledWorkout(...)` 
repeatedly until the `loop` condition is satisfied. Essentially, depending on whether the overdue workout detected is 
recurring, the workout is either deleted or rescheduled appropriately. Once the `loop` block exits, `sortScheduleList()` is called and the 
`scheduledWorkouts` is sorted in *ascending* order of `DateTime`

This "updating" is done to ensure a correctly sorted and cleaned up list is always output to the user. 
This also ensures the `scheduledWorkouts` ArrayList remains free of overdue workouts.

#### Meal: Class Diagram

![](https://user-images.githubusercontent.com/69350459/138307467-cef8cdd8-06ce-4284-92b5-9fe5e1ef50ef.png)

Above are the UML class level diagrams of `Meal`, and `Tracker`. As seen in
the diagram, the `Meal` class inherits from the `Tracker` class. This class diagram has been simplified for better readability.

#### Fluid: Class diagram

![](https://user-images.githubusercontent.com/69446495/138308110-c73bc021-3744-4164-98dc-52b7f76cb4c0.png)

Above are the UML class level diagrams of `Fluid`, `FluidExceptions` and `Tracker`. As seen in the diagram, the `Fluid` class is dependent on the `FluidExceptions` and the `Fluid`class inherits from the `Tracker` class. This class diagram has been simplified for better readability.

#### Fluid: Adding weight sequence diagram
![](https://user-images.githubusercontent.com/69446495/138881867-cff1b0a7-e836-43dd-b654-5b7db96ea1a3.png)

The UML sequence diagram above shows what happens when the input command is recognised as `add fluid`.
`generateFluidParameters` method in the `Fluid class` is called upon which updates variables relevent to a fluid, such its `description`, `calories`, `volume`, `date` and `time`. These variables are then concatenated together as a string called `inputArguments` and added to the `fluidArray` list.

## Product scope
### Target user profile
Specifically made for people getting into serious fitness routines such as athletes but helpful for general populace.

### Value proposition

CLI.ckFit is a comprehensive workout client with the two main components of a workout diary and a calorie manager
with the peripherals being weight tracking and BMI/recommended caloric intake calculator. These features can be
conveniently accessed via the Command Line Interface (CLI).

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|fitness enthusiast|record my fitness activities|plan my extensive workout schedule|
|v1.0|user|update how many calories I have burned through my workouts|keep track of my daily calories|
|v1.0|athlete|record my weight|keep track of and maintain a competitive weight|
|v2.0|user|have the app remember my user data|access my data anytime| 
|v2.0|user|have scheduled workouts in the list to be sorted by the nearest dates|easily keep track of upcoming workouts|
|v2.0|frequent gym goer| be able to schedule recurring weekly workouts| have a routine schedule without having to reschedule the same workout every week|
|v2.0|serious athlete|breakdown my workout into smaller activities|track things like sets, reps and distance|


## Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed (Java has backward compatibility).
2. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should 
   be able to accomplish most of the tasks faster using commands than using the mouse.

## Glossary

* *recurring workouts* - workouts that are scheduled ***weekly***
* *overdue workouts* - workouts whose dates are before the current date

## Instructions for manual testing

### I/O tests
#### Windows

1. Open a terminal window in the test folder

2. Run the `runtests.bat` script

3. If the script reports that there is no difference between ACTUAL.TXT and EXPECTED.TXT, the test has passed

#### Mac/Unix/Linux

1. Open a terminal window in the test folder

2. Run the `runtests.sh` script

3. If the script reports that there is no difference between ACTUAL.TXT and EXPECTED.TXT, the test has passed

### JUnit tests
1. In IntelliJ, right-click on the test folder and choose Run 'All Tests'

### Troubleshooting test failures
**Q:** How do I examine the exact differences between ACTUAL.TXT and EXPECTED.TXT? <br/>
**A:** You can use a diff/merge tool e.g. file compare (fc) on Windows

**Q:** The two files look exactly the same, but the test script reports they are different.<br/>
**A:** This can happen because the line endings used by Windows is different from Unix-based OSes. 
Convert the ACTUAL.TXT to the format used by your OS using some utility.

**Q:** Test fails during the only for the first time.<br/>
**A:** The output of the very first test run could be slightly different because the program creates a new storage file. 
Tests should pass from the 2nd run onwards.
