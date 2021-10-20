# Developer Guide

## Acknowledgements
The UML Diagrams were generated with the help of: [PlantUML](https://plantuml.com/), ...

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

### ScheduleTracker: Class diagram
![diagram-14958284492886246209](https://user-images.githubusercontent.com/69461398/137697609-fd0641bf-3b2a-455b-adcc-78528d8549f4.png)

Above are the UML class level diagrams of `ScheduleTracker` and `ScheduledWorkout`. As seen in the diagram, one 
`ScheduleTracker` object keeps track/is linked to **any** number of `ScheduledWorkout` objects, thus have a 
multiplicity of `0..*`. This association forms through a private attribute `scheduledWorkouts` which is of type 
`ArrayList<ScheduledWorkout>`. Do note that not all class attributes and methods are present in the diagram for 
the sake of better comprehensibility.

#### ScheduleTracker: Adding scheduled workout

![diagram-8825593443024174765](https://user-images.githubusercontent.com/69461398/137699150-d537d8aa-6edd-4bdc-a559-30e33b0e7637.png)


The UML sequence diagram above shows what happens when the method `addScheduledWorkout(...)` is called. 
Parameters are generated and returned as `String[]` of length `3` through `generateParameters(...)`. These parameters are 
used to add a `ScheduledWorkout` object into the `scheduledWorkouts` ArrayList. Subsequently, cleanUpScheduleList is 
called. **If** there are any overdue workouts, a `loop` is entered and calls `updateOrDeleteScheduledWorkout(...)` 
repeatedly until the `loop` condition is satisfied. Essentially depending on whether the overdue workout detected is recurring, 
the workout is either deleted or updated. Once the `loop` block is exited, `sortScheduleList()` is called and the 
`scheduledWorkouts` is sorted in *ascending* order of `DateTime`

This "updating" is done in any method call that outputs something to the user to ensure a correct sorted and cleaned up
list is output to the user. Namely to prevent overdue workouts from being displayed.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

CLI.ckFit is a convenient and centralized hub that allows users to store data throughout their fitness journey. 
It allows you to track your meals, weight, calories, fluid intake and gym exercise regimen conveniently all in one 
place. This makes the process of accessing stored data more seamless and convenient.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|fitness enthusiast|record my fitness activities|plan my extensive workout schedule|
|v1.0|user|update how many calories I have burned through my workouts|keep track of my daily calories|
|v2.0|user|have scheduled workouts in the list to be sorted by the nearest dates|easily keep track of upcoming workouts|
|v2.0|As a frequent gym goer| be able to schedule recurring weekly workouts| have a routine schedule without having to reschedule the same workout every week|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
