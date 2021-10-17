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

## Features

### Adding a meal: `ate`

Adds a new meal to the list of meal items.

Format: `ate MEAL_NAME /c MEAL_CALORIES /d DATE /t TIME`

* The `MEAL_NAME` can contain spaces.
* The `MEAL_CALORIES` can only contain positive integers inclusive of 0.
* The `DATE` is in dd/mm/yyyy.
* The `TIME` is in hh:mm.

* Example of usage: 

`ate chicken /c 250 /d 14/10/2021 /t 08:30`

### Adding fluids: `drank`

Adds a new fluid to the list of fluid items.

Format: `drank FLUID_NAME /c FLUID_CALORIES /v VOLUME /d DATE /t TIME`

* The `FLUID_NAME` can contain spaces.
* The `FLUID_CALORIES` can only contain positive integers inclusive of 0.
* The `VOLUME` can only contain positive integers inclusive of 0.  
* The `DATE` is in dd/mm/yyyy.
* The `TIME` is in hh:mm.

Example of usage:

`drank milk /c 180 /d 08/09/2021 /t 07:40`

### Adding weight: `addweight`

Adds a new weight to the list of weight items.

Format: `addweight WEIGHT /d DATE`

* The `WEIGHT` cannot contain spaces.
* The `DATE` is in dd/mm/yyyy.

Example of usage:

`addweight 50 /d 03/04/2021`

### Adding workout: `workout`

Adds a new workout to the list of workout items.

Format: `workout WORKOUT_NAME /c CALORIES_BURNT /d DATE /t TIME`

* The `WORKOUT_NAME` can contain spaces.
* The `CALORIES_BURNT` can only contain positive integers inclusive of 0.
* The `DATE` is in dd/mm/yyyy.
* The `TIME` is in hh:mm.

Example of usage:

`workout jog /c 250 /d 07/08/2021 /t 15:00`

### Adding scheduled workout: `schedule`

Adds a new scheduled workout to the list of scheduled workout items.

Format: `schedule WORKOUT_NAME /d DATE /t TIME`

* The `WORKOUT_NAME` can contain spaces.
* The `DATE` is in dd/mm/yyyy.
* The `TIME` is in hh:mm.

Example of usage:

`schedule chest day /d 07/08/2021 /t 15:00`

### Delete a meal: `deletemeal`

Remove a meal from the list of meal items.

Format: `deletemeal INDEX`

* The `INDEX` can only contain integers from the list.

* Example of usage:

`deletemeal 1`

### Delete fluids: `deletefluid`

Remove a fluid from the list of fluid items.

Format: `deletefluid INDEX`

* The `INDEX` can only contain integers from the list.

Example of usage:

`deletefluid 2`

### Delete weight: `deleteweight`

Remove a weight from the list of weight items.

Format: `deleteweight INDEX`

* The `INDEX` can only contain integers from the list.

Example of usage:

`deleteweight 1`

### Delete workout: `deleteworkout`

Remove a workout from the list of workout items.

Format: `deleteworkout INDEX`

* The `INDEX` can only contain integers from the list.

Example of usage:

`deleteworkout 3`

### Delete workout: `deleteschedule`

Remove a workout from the list of workout items.

Format: `deleteschedule INDEX`

* The `INDEX` can only contain integers from the list.

Example of usage:

`deleteschedule 3`

### List meals(TODO)

### List fluids

Lists all fluid entries made.

Format: `listfluids`

Example of usage:

`listfluids`

### List/check? weights(TODO)

### List workouts: `listworkout`

Lists out all stored workout descriptions, calories burned, date and time.

Format: `listworkout`

Example of usage:

`listworkout`

### List scheduled workouts: `listschedule`

Lists out all stored scheduled workout descriptions, date and time.

Format: `listschedule`

Example of usage:

`listschedule`

###Help Commands: `help commands`

Lists out the formats for the meal, fluid, gym and weight functions.

Format: `help commands`

Example of usage:
`help commands`

###Help Commands: `help UG`

Provides the hyperlink to the user guide for more tech-savvy users to read.

Format: `help UG`

Example of usage:
`help UG`

## FAQ

**Q**: How many meals/fluids/weights or workouts can I add?

**A**: The sky is the limit.

**Q**: How do I do well for CS2113T?

**A**: Just try your best bro.

**Q**: Can I A- and above this mod?

**A**: Dunno bro, but if it helps, you are already an A+ in my eyes ;)

## Command Summary

* Add meal: `ate MEAL_NAME /c MEAL_CALORIES /d DATE /t TIME`
* Add fluid: `drank FLUID_NAME /c FLUID_CALORIES /v VOLUME /d DATE /t TIME`
* Add weight: `addweight WEIGHT /d DATE`
* Add workout: `workout WORKOUT_NAME /c CALORIES_BURNT /d DATE /t TIME`
* Add scheduled workout: `schedule WORKOUT_NAME /d DATE /t TIME`
* Remove meal: `deletemeal INDEX`
* Remove fluid: `deletefluid INDEX`
* Remove weight: `deleteweight INDEX`
* Remove workout: `deleteworkout INDEX`
* Remove scheduled workout: `deleteschedule INDEX`
* Access user help: `help commands`
* Access user guide: `help UG`
* list meals: `listmeals`
* List fluids: `listfluids`
* List weights: 
* List workouts: `listworkouts`
* List scheduled workouts: `listschedule`
