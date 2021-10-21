# Developer Guide

## Acknowledgements
The UML Diagrams were generated with the help of: [PlantUML](https://plantuml.com/), ...

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

### WeightTracker: Class diagram
![WeightTracker_class](https://user-images.githubusercontent.com/69446729/138136839-8e4f117b-beb0-47bb-830a-55c58076b946.png)

Above are the UML class level diagrams of `WeightTrackerMessages`, `WeightTracker` and `Tracker`. As seen in
the diagram, the `WeightTracker` class is dependent on the `WeightTrackerMessages` and the `WeightTracker` 
class inherits from the `Tracker` class. This class diagram has been simplified for better readability.

#### WeightTracker: Adding weight
![WeightTracker_add_sequence](https://user-images.githubusercontent.com/69446729/138136616-37fd90e1-4158-4006-85d8-708593153cde.png)

The UML sequence diagram above shows what happens when the input command is recognised as `addweight`.
The `WeightTracker` class calls the `readInput` function which reads the input and calls the `addWeight` 
function which calls the `generateWeightParameters` function which updates the `weight` and `date` 
variables. Then the `weight` and `date` variables are added to weight array list and 
`printAddWeightResponse` is called from the `WeightTrackerMessages` class.

#### WeightTracker: Deleting weight
![WeightTracker_delete_sequence](https://user-images.githubusercontent.com/69446729/138136415-6ae09524-9712-494f-8792-3f945f0601d8.png)

The UML sequence diagram above shows what happens when the input command is recognised as `deleteweight`.
The `WeightTracker` class calls the `readInput` function which reads the input and calls the `deleteWeight`
function which calls the `generateWeightParameters` function which updates the `weight` and `date`
variables with the index parameters to be deleted. Then the `weight` and `date` variables are deleted 
from the weight array list.

#### WeightTracker: Checking weights
![WeightTracker_check_sequence](https://user-images.githubusercontent.com/69446729/138136750-30b7e949-e156-4d88-9ebf-3446c31d7284.png)

The UML sequence diagram above shows what happens when the input command is recognised as `checkweight`.
The `WeightTracker` class calls the `readInput` function which reads the input and calls the `checkWeight`
function which calls the `generateWeightParameters` function which updates the `weight` and `date`
variables with the parameters to be printed. Then the `weight` and `date` variables are 
printed for the entire list.

### ScheduleTracker: Class diagram
![](../diagrams/ScheduleTracker_class.png)

Above are the UML class level diagrams of `ScheduleTracker` and `ScheduledWorkout`. As seen in the diagram, one 
`ScheduleTracker` object keeps track/is linked to **any** number of `ScheduledWorkout` objects, thus have a 
multiplicity of `0..*`. This association forms through a private attribute `scheduledWorkouts` which is of type 
`ArrayList<ScheduledWorkout>`. Each `ScheduledWorkout` object also has a private attribute called `activities` which is 
of type `ArrayList<WorkoutActivity>` where `activities` keeps track of **any** number of `WorkoutActivity` objects.
Do note that **not all** class attributes and methods are present in the diagram for 
the sake of better comprehensibility.

#### ScheduleTracker: Adding scheduled workout

![](../diagrams/ScheduleTracker_add_sequence.png)

The UML sequence diagram above shows what happens when the method `addScheduledWorkout(...)` is called. 
Parameters are generated and a `ScheduledWorkout` object is added into the `scheduledWorkouts` ArrayList.
This is achieved by a few method calls which are omitted from this diagram. The main focus is on `cleanUpScheduleList()`. 
Once called, **if** there are any overdue workouts, a `loop` is entered and calls `updateOrDeleteScheduledWorkout(...)` 
repeatedly until the `loop` condition is satisfied. Essentially, depending on whether the overdue workout detected is 
recurring, the workout is either deleted or rescheduled appropriately. Once the `loop` block exits, `sortScheduleList()` is called and the 
`scheduledWorkouts` is sorted in *ascending* order of `DateTime`

This "updating" is done in any method call that outputs something to the user to ensure a correctly sorted and cleaned up
list is output to the user. This also ensures the `scheduledWorkouts` ArrayList remains free of overdue workouts.

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
|v2.0|user|have the app remember my user data|access my data anytime| 
|v2.0|user|have scheduled workouts in the list to be sorted by the nearest dates|easily keep track of upcoming workouts|
|v2.0|frequent gym goer| be able to schedule recurring weekly workouts| have a routine schedule without having to reschedule the same workout every week|
|v2.0|serious athlete|breakdown my workout into smaller activities|track things like sets, reps and distance|
## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
