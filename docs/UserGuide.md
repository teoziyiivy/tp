# User Guide
## Introduction

CLI.ckFit is a desktop app for managing your nutrition and fitness needs via a Command Line Interface (CLI). 
It allows you to track your meals, fluid intakes, recipes, calories and exercise regimen conveniently.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `CLI.ckFit` from [here](http://link.to/duke).
3. Go to the folder you saved the CLIckFit.jar file and note the absolute file path.
4. If you are using Windows, open up a Command prompt terminal cmd.exe or powershell.exe and for
   Mac and Linux users, do the same with the terminal of your respective systems.
5. Navigate to the folder where the CLIckFit.jar file is stored.
6. Execute java -jar CLIckFit.jar in the terminal, and the application will start running.

## Command Summary
*Psssstttttt click on the commands to skip sections!*

Command | Format of input
------------ | -------------
[**Add meal**](#adding-a-meal)| `ate <MEAL_NAME> /c <MEAL_CALORIES> /d <DATE> /t <TIME>`
[**Add fluid**](#adding-fluids)| `add fluid <FLUID NAME> /c <FLUID_CALORIES> /v <VOLUME> /d <DATE> /t <TIME>`
[**Add weight**](#adding-weight)| `addweight <WEIGHT> /d <DATE>`
[**Add workout**](#adding-workout)| `workout <WORKOUT_NAME> /c <CALORIES_BURNT> /d <DATE> /t <TIME>`
[**Add scheduled workout**](#adding-scheduled-workout)| `schedule <WORKOUT_NAME> /d <DATE> /t <TIME>`
[**Remove meal**](#delete-a-meal)| `delete meal <INDEX>`
[**Remove fluid**](#delete-fluids)| `delete fluid <INDEX>`
[**Remove weight**](#delete-weight)| `delete weight <INDEX>`
[**Remove workout**](#delete-workout)| `delete workout <INDEX>`
[**Remove scheduled workout**](#delete-workout-schedule)| `deleteschedule <INDEX>`
[**List meals**](#list-meals)| `list meals <DATE>`
[**List fluids**](#list-fluids)| `list fluids <DATE>`
[**List weights**](#list-weights)| `list weights <DATE>`
[**List workouts**](#list-workouts)| `list workouts <DATE>`
[**List scheduled workouts**](#list-scheduled-workouts)| `listschedule`
[**Access user help**](#help-Commands)| `help commands`
[**Access user guide**](#help-UG)| `help UG`

# *Features:*

# Adding a meal 

Command word:`ate`

Description: `Adds a new meal to the list of meal items.`

Format: `ate MEAL_NAME /c MEAL_CALORIES /d DATE /t TIME`

* The `MEAL_NAME` can contain spaces.
* The `MEAL_CALORIES` can only contain positive integers inclusive of 0.
* The `DATE` is in dd/mm/yyyy.
* The `TIME` is in hh:mm.

Example of usage: 

`ate chicken /c 250 /d 14/10/2021 /t 08:30`


# Adding fluids

Command Word: `add fluid`

Description: `Adds a new fluid to the list of fluid items.`

Format: `add fluid {FLUID NAME} /c <FLUID_CALORIES> /v <VOLUME> /d <DATE> /t <TIME>`

* The `FLUID_NAME` can contain spaces.
* The `FLUID_CALORIES` can only contain positive integers inclusive of 0.
* The `VOLUME` can only contain positive integers inclusive of 0.  
* The `DATE` is in dd/mm/yyyy.
* The `TIME` is in hh:mm.

Example of usage:

`add fluid milk /c 180 /v 100 /d 08/09/2021 /t 07:40`

Expected outcome:

```
Noted! CLI.ckFit has recorded your drink of milk of 180 calories and 100 ml on 08/09/2021 07:40.
```

# Adding weight

Command Word: `add weight`

Description: `Adds a new weight to the list of weight items.`

Format: `add weight WEIGHT /d DATE`

* The `WEIGHT` cannot contain spaces.
* The `DATE` is in dd/mm/yyyy.

Example of usage:

`addweight 50 /d 03/04/2021`

# Adding workout

Command Word: `workout`

Description: `Adds a new workout to the list of workout items.`

Format: `workout WORKOUT_NAME /c CALORIES_BURNT /d DATE /t TIME`

* The `WORKOUT_NAME` can contain spaces.
* The `CALORIES_BURNT` can only contain positive integers inclusive of 0.
* The `DATE` is in dd/mm/yyyy.
* The `TIME` is in hh:mm.

Example of usage:

`workout jog /c 250 /d 07/08/2021 /t 15:00`

# Adding scheduled workout

Command Word: `schedule`

Description: `Adds a new scheduled workout to the list of scheduled workout items with the option to include 
activity breakdowns.`

Format: `schedule WORKOUT_NAME /d DATE /t TIME /a ACTIVITY_NAME:ACTIVITY_QUANTIFIER, ... /r`

* The `WORKOUT_NAME` can contain spaces.
* The `/a` separator is optional.
* The `ACTIVITY_NAME` can contain spaces and `:` must follow after it.
* If `ACTIVITY_NAME` is either `running/swimming/cycling` then `ACTIVITY_QUANTIFIER` takes in an integer `[DISTANCE]`
  in metres for the activity.
* For other `ACTIVITY_NAME`, `ACTIVITY_QUANTIFIER` takes in two integers in the form `[SETS]x[REPS]`.
* Multiple activities can be entered as long as they are separated by a comma `,`.
* The `/r` flag at the end is an optional flag for recurrence, which schedules a weekly recurring workout.
* The `DATE` is in dd/mm/yyyy.
* The `TIME` is in hh:mm.

Example of usage:

`schedule chest day /d 22/12/2021 /t 15:00`, `schedule weekly chest day /d 07/12/2021 /t 13:50 /r`, <br/>
`schedule weekly chest day /d 07/08/2021 /t 15:00 /a bench press:5x12, pushups:5x20 /r`, <br/>
`schedule triathlon training /d 07/08/2021 /t 15:00 /a running:3000, swimming:1000, cycling:4000`

# Delete a meal

Command Word: `delete meal`

Description: `Remove a meal from the list of meal items.`

Format: `delete meal INDEX`

* The `INDEX` can only contain integers from the list.

* Example of usage:

`delete meal 1`

# Delete fluids

Command Word: `delete fluid`

Description: `Removes a fluid from the list of fluid items.`

Format: `delete fluid {INDEX OF FLUID}`

* The `INDEX` can only contain integers from the list.

Example of usage:

`delete fluid 2`

# Delete weight

Command Word:`delete weight`

Description: `Remove a weight from the list of weight items.`

Format: `delete weight INDEX`

* The `INDEX` can only contain integers from the list.

Example of usage:

`delete weight 1`

# Delete workout

Command Word:`delete workout`

Description: `Remove a workout from the list of workout items.`

Format: `delete workout INDEX`

* The `INDEX` can only contain integers from the list.

Example of usage:

`delete workout 3`

# Delete workout schedule

Command Word: `delete schedule`

Description: `Remove a workout from the list of workout items.`

Format: `delete schedule INDEX`

* The `INDEX` can only contain integers from the list.

Example of usage:

`delete schedule 3`

# List meals

Command Word: `list meals <DATE>`

Description: `Lists all meal entries made for that specific date.`

* The `DATE` is in dd/mm/yyyy.
* If `DATE` is left empty, the weights recorded **today** will be returned.
* If the word `all` is written in place of `DATE`, **ALL** stored weights will be listed.

Example of usage:

`list meals`

# List fluids

Command Words:`list fluids <DATE>`

Description: `Lists all fluid entries made for that specific date.`

* The `DATE` is in dd/mm/yyyy.
* If `DATE` is left empty, the weights recorded **today** will be returned.
* If the word `all` is written in place of `DATE`, **ALL** stored weights will be listed.

Example of usage:

`list fluids`

# List weights

Command Words:`list weights <DATE>`

Description: `Lists all weight entries made for that specific date.`

* The `DATE` is in dd/mm/yyyy.
* If `DATE` is left empty, the weights recorded **today** will be returned.
* If the word `all` is written in place of `DATE`, **ALL** stored weights will be listed.

Example of usage:

`list weights`

# List workouts

Command Word:`listworkout`

Description: `Lists out all stored workout descriptions, calories burned, date and time.`

* The `DATE` is in dd/mm/yyyy.
* If `DATE` is left empty, the weights recorded **today** will be returned.
* If the word `all` is written in place of `DATE`, **ALL** stored weights will be listed.

Example of usage:

`listworkout`

# List scheduled workouts

Command Word: `listschedule`

Description: `Lists out stored scheduled 
workout descriptions, date and time as well as their activity breakdowns on a specific date`

Format: `listschedule DATE`
* The `DATE` is in dd/mm/yyyy.
* If `DATE` is left empty, the schedule for the **today** will be returned.
* If the word `all` is written in place of `DATE`, **ALL** stored scheduled workouts will be listed.

Example of usage:

`listschedule`, `listschedule 22/10/2021`, `listschedule all`

# Help Commands

Command Word: `help commands`

Description: `Lists out the formats for the meal, fluid, gym and weight functions.`

Example of usage:

`help commands`

# Help UG

Command Word: `help UG`

Description: `Provides the hyperlink to the user guide for more tech-savvy users to read.`

Example of usage:

`help UG`

## FAQ

**Q**: How many meals/fluids/weights or workouts can I add?

**A**: The sky is the limit.

**Q**: How do I do well for CS2113T?

**A**: Just try your best bro.

**Q**: Can I A- this mod?

**A**: Dunno bro, but if it helps, you are already an A+ in my eyes :)

