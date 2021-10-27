# CLI.ckFit User Guide

## Introduction
CLI.ckFit is a desktop-based fitness app which be accessed easily via CLI. CLI.ckFit allows you to input your calories, 
weight, foods, and workouts throughout the day. It also allows you to save your data and view it whenever you wish to. 
It also comes with a BMI and recommended caloric intake calculator which can give you an idea of your current fitness 
level. You can also schedule a variety of workouts such as running, cycling, gym etc. It is suitable for students 
staying on campus, especially existing people already involved in some level of exercise, such as casual gym goers or 
even student athletes. Prior knowledge in fitness and gym-related terminologies is beneficial, but not necessary.

## Motivations
University students staying on-campus have always found it difficult to juggle their hall activities, academic workload, 
and social activities. This makes it difficult for them to track their health & fitness. Furthermore, most students 
don’t want to budget for a fitness app which may be inconvenient to access. Lastly, one's foray into serious fitness may 
be a daunting and a confusing process. The abundance of apps in the market that each provide different services can 
exacerbate this issue.

## Quick Start
1. The BMI calculator and recommended caloric intake calculator gives the user an idea of where his current fitness 
level stands. Users can also choose to skip using the calculators by entering the appropriate keystrokes.
2. The user can then access the calorie manager to track their caloric intake, while also using the workout schedule manager
to track the calories burned.
3. Workout schedule manager also allows the user to schedule future workouts
4. For the long term outlook, weight tracker lets the user monitor his weight over the length of usage of CLI.ckFit as an 
indicator of the user's progress

## Technical Start Up
1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `CLI.ckFit` from [here](http://link.to/duke).
3. Go to the folder you saved the CLIckFit.jar file and note the absolute file path.
4. If you are using Windows, open up a Command prompt terminal cmd.exe or powershell.exe and for
   Mac and Linux users, do the same with the terminal of your respective systems.
5. Navigate to the folder where the CLIckFit.jar file is stored.
6. Execute java -jar CLIckFit.jar in the terminal, and the application will start running.

## Common terminologies in CLI.ckFit
* Calories are in kcal
* Weight is in kg
* Height is in cm
* Volume is in ml
* Date is in DD/MM/YYYY (e.g. 26/10/2021)
* Time is in HH:MM (e.g. 23:59)

### What is a rep?

A rep is short for repetitions. Repetitions define the number of times
to perform an exercise. For example if you do 12 squats, then stop, the
12 squats you perform are considered 12 repetitions.

### What is a set?

Sets refer to how many times you will repeat that exercise for the set
number of repetitions. For example, you do 12 squats and rest. Then
you do another 12 squats, rest, and then another 12. You have now
completed three sets of 12 reps.

Quick response hotline:

Vishal +65 9457 #### 24/7 gym buddy and trainer

or

https://www.instagram.com/thebuffessor/?hl=en

## Table of Contents

### Calorie Manager
- #### Add 
  - [**Add meal**](#adding-a-meal)
  - [**Add fluid**](#adding-fluids)
  - [**Add weight**](#adding-weight)
- #### Delete
  - [**Remove meal**](#delete-a-meal)
  - [**Remove fluid**](#delete-a-fluid)
  - [**Remove weight**](#delete-a-weight)
- #### List
  - [**List meals**](#list-meals)
  - [**List fluids**](#list-fluids)
  - [**List weights**](#list-weights)
  - [**List calories**](#list-calories)
  - [**List volume**](#list-volume)
- #### Help
  - [**Access user help**](#help-commands)
  - [**Access user guide**](#help-ug)

### Workout Manager
- #### Add
  - [**Add workout**](#adding-workout)
  - [**Add scheduled workout**](#adding-scheduled-workout)
- #### Delete
  - [**Remove workout**](#delete-a-workout)
  - [**Remove scheduled workout**](#delete-a-scheduled-workout)
- #### List
  - [**List workouts**](#list-workouts)
  - [**List scheduled workouts**](#list-scheduled-workouts)

### Library Manager
- #### Add
  - [**Add meal to library**](#adding-meal-to-library)
  - [**Add fluid to library**](#adding-fluid-to-library)
- #### Delete
    - [**Remove meal from library**](#delete-a-meal-from-library)
    - [**Remove fluid from library**](#delete-a-fluid-from-library)
- #### List
    - [**List meals from library**](#list-meals-stored-in-library)
    - [**List fluids from library**](#list-fluids-stored-in-library)

### Miscellaneous
- [**FAQ**](#faq)
- [**Command Summary**](#command-summary)

# *Features:*

## Adding a meal

Command Word: `add meal`

Description: Adds a new meal to the list of meals, with its associated calories, date and time of consumption.

Format: `add meal MEAL NAME </c MEAL CALORIES /d DATE /t TIME>`

* The `MEAL_NAME` can contain spaces.
* The `MEAL_CALORIES` can only contain positive integers inclusive of 0.
* You can only omit putting `MEAL_CALORIES` if you have saved the meal in your meal library already.
* The `DATE` is in dd/mm/yyyy.
* The `TIME` is in hh:mm.
* If `DATE` or `TIME` is not specified, the system current date and time will be taken.

Example of usage:

`add meal risotto /c 250 /d 14/10/2021 /t 08:30`


## Adding fluids

Command Word: `add fluid`

Description: Adds a new fluid to the list of fluid items, with its associated calories, date and time of consumption.


Format: `add fluid FLUID_NAME </c FLUID_CALORIES /v VOLUME /d DATE /t TIME>`


* The `FLUID_NAME` can contain spaces.
* The `FLUID_CALORIES` can only contain positive integers inclusive of 0.
* You can only omit putting `FLUID_CALORIES` if you have saved the drink in your fluid library already.
* The `VOLUME` can only contain positive integers inclusive of 0.
* The `DATE` is in dd/mm/yyyy.
* The `TIME` is in hh:mm.

Example of usage:

`add fluid milk /c 180 /v 100 /d 08/09/2021 /t 07:40`


Expected outcome:

```
Noted! CLI.ckFit has recorded your drink of milk of 180 calories and 100 ml on 08/09/2021 07:40.
```

## Adding weight

Command Word: `add weight`

Description: Adds a new weight to the list of weight items, with its associated date.


Format: `add weight WEIGHT </d DATE>`

* The `WEIGHT` cannot contain spaces.
* The `DATE` is in dd/mm/yyyy.
* If `DATE` is not specified, the system current date will be taken.

Example of usage:

`add weight 50 /d 03/04/2021`

## Adding workout

Command Word: `add workout`

Description: Adds a new workout to the list of workout items, with its associated calories, date and time of workout.

Format: `add workout WORKOUT_NAME /c CALORIES_BURNED </d DATE /t TIME>`

* The `WORKOUT_NAME` can contain spaces.
* The `CALORIES_BURNT` can only contain positive integers inclusive of 0.
* The `DATE` is in dd/mm/yyyy.
* The `TIME` is in hh:mm.
* If `DATE` or `TIME` is not specified, the system current date and time will be taken.

Example of usage:

`add workout jog /c 250 /d 07/08/2021 /t 15:00`

## Adding scheduled workout


Command Word: `add schedule`

Description: Adds a new scheduled workout to the list of scheduled workout items with the option to include 
activity breakdowns, with date and time of workout.

### With no activity breakdown:
Format: `add schedule WORKOUT_NAME /d DATE /t TIME`

* The `WORKOUT_NAME` can contain spaces.
* The `DATE` is in dd/mm/yyyy.
* The `TIME` is in hh:mm.
* The `DATE` or `TIME` is compulsory for schedules.

Example of usage:

`schedule chest day /d 22/12/2021 /t 15:00`

### With activity breakdown:
Format: `add schedule WORKOUT_NAME /d DATE /t TIME </a ACTIVITY_NAME:ACTIVITY_QUANTIFIER, ...> </r>`

* The `/a` separator is optional.
* The `ACTIVITY_NAME` can contain spaces and `:` ***must*** follow after it.
* If `ACTIVITY_NAME` is either `running/swimming/cycling` then `ACTIVITY_QUANTIFIER` takes in an integer `[DISTANCE]`
  in metres for the activity.
* For other `ACTIVITY_NAME`, `ACTIVITY_QUANTIFIER` takes in two integers in the form `[SETS]x[REPS]`.
* Multiple activities can be entered as long as they are separated by a comma `,`.
* The `/r` flag at the end is an ***optional*** flag for recurrence, which schedules a weekly recurring workout.


Example of usage:

`schedule weekly chest day /d 07/12/2021 /t 13:50 /r`, <br/>
`schedule weekly chest day /d 07/08/2021 /t 15:00 /a bench press:5x12, pushups:5x20 /r`, <br/>
`schedule triathlon training /d 07/08/2021 /t 15:00 /a running:3000, swimming:1000, cycling:4000`

## Adding meal to library

Command Word: `library addmeal`

Description: Add a new meal record to the library, with its associated calories.

Format: `library addmeal MEAL_NAME /c MEAL_CALORIES`

* The MEAL_NAME can contain spaces.
* The MEAL_CALORIES can only contain positive integers inclusive of 0.

Example of usage:

`library addmeal chocolate cake /c 110`

## Adding fluid to library

Command Word: `library addfluid`

Description: Adds a new fluid record to the library, with its associated calories.

Format: `library addfluid FLUID_NAME /c FLUID_CALORIES`

* The FLUID_NAME can contain spaces.
* The FLUID_CALORIES can only contain positive integers inclusive of 0.

Example of usage:

`library addfluid chocolate milk /c 200`

## Delete a meal

Command Word: `delete meal`

Description: Remove a meal from the list of meal items.

Format: `delete meal INDEX`

* The `INDEX` can only contain integers from the list.

Example of usage:

`delete meal 1`

## Delete a fluid

Command Word: `delete fluid`

Description: Removes a fluid from the list of fluid items.

Format: `delete fluid INDEX`

* The `INDEX` can only contain integers from the list.

Example of usage:

`delete fluid 2`

## Delete a weight

Command Word: `delete weight`

Description: Deletes a meal from the list of meals.

Format: `delete weight INDEX`

* Use `list weights all` to determine the index of the meal you wish to delete.

Example of usage:

`delete weight 2`

## Delete a workout

Command Word: `delete workout`

Description: Remove a workout from the list of workout items.

Format: `delete workout INDEX`

* The `INDEX` can only contain integers from the list.

Example of usage:

`delete workout 3`

## Delete a scheduled workout

Command Word: `delete schedule`

Description: Remove a workout from the list of workout items.

Format: `delete schedule INDEX`

* The `INDEX` can only contain integers from the list.

Example of usage:

`delete schedule 3`

## Delete a meal from library

Command Word: `library deletemeal`

Description: Deletes a meal record from the library.

Format: `library deletemeal INDEX`
* Use `library listmeals` to determine the index of the meal you wish to delete.

Example of usage:

`library deletemeal 2`

## Delete a fluid from library

Command Word: `library deletefluid`

Description: Deletes a fluid record from the library.

Format: `library deletefluid INDEX`
* Use `library listfluids` to determine the index of the meal you wish to delete.

Example of usage:

`library deletefluid 2`

## List meals

Command Word: `list meals`

Description: Lists all meals, with its associated calories, date and time of consumption.

Format: `list meals <DATE>`

* The `DATE` is in dd/mm/yyyy.
* If `DATE` is left empty, the meals recorded **today** will be returned.
* If the word `all` is written in place of `DATE`, **ALL** stored meals will be listed.

Example of usage:

`list meals`, `list meals 22/10/2021`, `list meals all`


## List fluids

Command Word: `list fluids`

Description: Lists all fluids, with its associated calories, date and time of consumption.

Format: `list fluids <DATE>`

* The `DATE` is in dd/mm/yyyy.
* If `DATE` is left empty, the fluids recorded today will be returned.
* If the word `all` is written in place of DATE, ALL stored fluids will be listed.

Example of usage:

`list fluids`, `list fluids 22/10/2021`, `list fluids all`

## List weights

Command Word:`list weights`

Description: Lists weight depending on date of entry.

Format: `list weights <DATE>`
* The `DATE` is in dd/mm/yyyy.
* If `DATE` is left empty, the weights recorded **today** will be returned.
* If the word `all` is written in place of `DATE`, **ALL** stored weights will be listed.

Example of usage:

`list weights`, `list weights 22/10/2021`, `list weights all`

## List workouts

Command Word:`list workouts`

Description: Lists out all stored workout descriptions, 
calories burned, date and time depending on date of entry.

Format: `list workouts <DATE>`
* The `DATE` is in dd/mm/yyyy.
* If `DATE` is left empty, the workouts recorded **today** will be returned.
* If the word `all` is written in place of `DATE`, **ALL** stored workouts will be listed.


Example of usage:

`list workouts`, `list workouts 22/10/2021`, `list workouts all`

## List scheduled workouts

Command Word: `list schedule`

Description: Lists out stored scheduled 
workout descriptions, date and time as well as their activity breakdowns depending on date 

Format: `list schedule <DATE>`
* The `DATE` is in dd/mm/yyyy.
* If `DATE` is left empty, the schedule for the **today** will be returned.
* If the word `all` is written in place of `DATE`, **ALL** stored scheduled workouts will be listed.

Example of usage:

`list schedule`, `list schedule 22/10/2021`, `list schedule all`

## List meals stored in library

Command Word: `library listmeals`

Description: Lists all meals stored in the library.

Format: `library listmeals`

## List fluids stored in library

Command Word: `library listfluids`

Description: Lists all fluids stored in the library.

Format: `library listfluids`

## List Volume

Command Word: ` `

Description: 

Format: ` `

## List Calories

Command Word: ` `

Description: 

Format: ` `

## Help Commands

Command Word: `help commands`

Description: Lists out the formats of input for meal, fluid, gym and weight commands.

Example of usage:

`help commands`

## Help UG

Command Word: `help UG`

Description: Provides the hyperlink to the user guide for more tech-savvy users to read.

Example of usage:

`help UG`

## FAQ

Q: Are the commands case-sensitive?

A: Yes, please follow the specified casing. All commands are to be in lower-case. 

Q: How do I transfer my CLIckFit data and all associated data to another computer?

A: Transfer the data text files located in the same directory as your CLIckFit.jar file over
to your other computer. Place it in the same directory as the CLIckFit.jar file on your other computer. Your
data will then be loaded from the text files when you run CLIckFit.jar on your new computer.

## Command Summary
*Psssstttttt click on the commands to skip sections!*

Parameters not enclosed in any brackets are compulsory while those enclosed in `<>` are ***optional***.


Command | Format of input
------------ | -------------
[**Add meal**](#adding-a-meal)| `add meal MEAL_NAME </c MEAL_CALORIES /d DATE /t TIME>`
[**Add fluid**](#adding-fluids)| `add fluid FLUID_NAME </c FLUID_CALORIES /v VOLUME /d DATE /t TIME>`
[**Add weight**](#adding-weight)| `add weight WEIGHT /d <DATE>`
[**Add workout**](#adding-workout)| `add workout WORKOUT_NAME /c CALORIES_BURNED </d DATE /t TIME>`
[**Add scheduled workout**](#adding-scheduled-workout)| `add schedule WORKOUT_NAME /d DATE /t TIME </a ACTIVTY_NAME:ACTIVITY_QUANITIFER, ...> </r>`
[**Add meal to library**](#adding-meal-to-library)| `library addmeal MEAL_NAME /c MEAL_CALORIES`
[**Add fluid to library**](#adding-fluid-to-library)| `library addfluid FLUID_NAME /c FLUID_CALORIES`
[**Remove meal**](#delete-a-meal)| `delete meal INDEX`
[**Remove fluid**](#delete-a-fluid)| `delete fluid INDEX`
[**Remove weight**](#delete-a-weight)| `delete weight INDEX`
[**Remove workout**](#delete-a-workout)| `delete workout INDEX`
[**Remove scheduled workout**](#delete-a-scheduled-workout)| `delete schedule INDEX`
[**Remove meal from library**](#delete-a-meal-from-library)| `library deletemeal INDEX`
[**Remove fluid from library**](#delete-a-fluid-from-library)| `library deletefluid INDEX`
[**List meals**](#list-meals)| `list meals <DATE>`
[**List fluids**](#list-fluids)| `list fluids <DATE>`
[**List weights**](#list-weights)| `list weights <DATE>`
[**List workouts**](#list-workouts)| `list workouts <DATE>`
[**List calories**](#list-calories)| `list calories <DATE>`
[**List volume**](#list-volume)| `list calories <DATE>`
[**List scheduled workouts**](#list-scheduled-workouts)| `list schedule <DATE>`
[**List meals from library**](#list-meals-stored-in-library)| `library listmeals`
[**List fluids from library**](#list-fluids-stored-in-library)| `library listfluids`
[**Access user help**](#help-commands)| `help commands`
[**Access user guide**](#help-ug)| `help UG`
