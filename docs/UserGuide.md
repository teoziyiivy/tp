# User Guide

## Introduction

CLI.ckFit is a desktop app for managing your nutrition and fitness needs via a Command Line Interface (CLI). 
It allows you to track your meals, recipes, calories, water intake and exercise regimen conveniently.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `CLI.ckFit` from [here](http://link.to/duke).

## Features

### Adding a meal: `ate`

Adds a new meal to the list of meal items.

Format: `ate MEAL_NAME /c MEAL_CALORIES /d DATE /t TIME`

* The `MEAL_NAME` cannot contain spaces.
* The `MEAL_CALORIES` can only contain integers.
* The `DATE` is in dd/mm/yyyy.
* The `TIME` is in hh:mm.

* Example of usage: 

`ate chicken /c 250 /d 14/10/2021 /t 08:30`

### Adding fluids: `drank`

Adds a new fluid to the list of fluid items.

Format: `drank FLUID_NAME /c FLUID_CALORIES /d DATE /t TIME`

* The `FLUID_NAME` cannot contain spaces.
* The `FLUID_CALORIES` can only contain integers.
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

* The `WORKOUT_NAME` cannot contain spaces.
* The `CALORIES_BURNT` can only contain integers.
* The `DATE` is in dd/mm/yyyy.
* The `TIME` is in hh:mm.

Example of usage:

`workout jog /c 250 /d 07/08/2021 /t 15:00`

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

## FAQ

**Q**: How do I do well for CS2113T?

**A**: Try hard

## Command Summary

* Add meal `ate MEAL_NAME /c MEAL_CALORIES /d DATE /t TIME`
* Add fluid `drank FLUID_NAME /c FLUID_CALORIES /d DATE /t TIME`
* Add weight `addweight WEIGHT /d DATE`
* Add workout `workout WORKOUT_NAME /c CALORIES_BURNT /d DATE /t TIME`
* Remove meal `deletemeal INDEX`
* Remove fluid `deletefluid INDEX`
* Remove weight `deleteweight INDEX`
* Remove workout `deleteworkout INDEX`
