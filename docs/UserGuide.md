# CLI.ckFit User Guide

## Introduction

CLI.ckFit is a desktop-based fitness app which can be accessed easily via CLI. CLI.ckFit allows you to input your calories, 

weight, foods, and workouts throughout the day. It also allows you to save your data and view it whenever you wish to. 

It also comes with a BMI and recommended caloric intake calculator which can give you an idea of your current fitness 

level. You can also schedule a variety of workouts such as running, cycling, gym etc. It is suitable for students 

staying on campus, especially existing people already involved in some level of exercise, such as casual gym goers or 

even student athletes. Prior knowledge in fitness and gym-related terminologies is beneficial, but not necessary.

[**>>Skip to Table of Contents<<**](#table-of-contents)

## Motivations

University students staying on-campus have always found it difficult to juggle their hall activities, academic workload, 

and social activities. This makes it difficult for them to track their health & fitness. Furthermore, most students 

don’t want to budget for a fitness app which may be inconvenient to access. Lastly, one's foray into serious fitness may 

be a daunting and a confusing process. The abundance of apps in the market that each provide different services can 

exacerbate this issue.

## Quick Start

1. The BMI calculator and recommended caloric intake calculator gives the user an idea of where his current fitness 

level stands. Users can also choose to skip using the calculators by entering the appropriate keystrokes.

* *NOTE* : Please follow _**exactly**_ the explicitly _**required**_ input formats for answering the calculator 

questions. Any deviating inputs will 

result in the question being repeated so that the user is able to know exactly what inputs and formats are needed by the 

calculator to calculate BMI and recommended daily caloric intake.

2. The user can then access the calorie manager to track their caloric intake, while also using the workout schedule manager

to track the calories burned.

3. Workout schedule manager also allows the user to schedule future workouts

4. For the long term outlook, weight tracker lets the user monitor his weight over the length of usage of CLI.ckFit as an 

indicator of the user's progress

## Technical Start Up

1. Ensure that you have Java 11 or above installed.

2. Download the latest version of `CLI.ckFit` from [here](http://link.to/duke).

3. Go to the folder you saved the `CLIckFit.jar` file and note the absolute file path.

4. If you are using Windows, open up a Command prompt terminal cmd.exe or powershell.exe and for

   Mac and Linux users, do the same with the terminal of your respective systems.

5. Navigate to the folder where the `CLIckFit.jar` file is stored.

6. Execute `java -jar CLIckFit.jar` in the terminal, and the application will start running.

## Common Terminologies and Definitions

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

## Known limitations

* CLI.ckFit may not handle illogical inputs correctly due to limitations of data types. For instance, if you enter an

overly large and nonsensical integer value for calories such as `2147483647` there may be overflow during computation. 

  E.g., when calculating total calories, summation may result in an overflow, producing negative calories. 
  
* Separators such as the date separator `/d`, time separator `/t`, calorie separator `/c`, volume separator `/v` and 

  activity separator `/a` should be entered in the **same order** as shown in their respective command formats. 

  CLI.ckFit **does not** actively support the shuffling of separators when taking user input. 
  
* The separators as shown in the command format should be input **once**. Typing multiple identical separators 

  unnecessarily *may* cause incorrect parsing of user input. 
  
* To ensure correct processing of user inputs you should only enter the **necessary** number of arguments. 

  For instance if you want to add a meal of `300` calories, only enter a **single** integer for your calories.

  E.g., enter `/c 300` instead of something like `/c 300 20 10`. The same applies to other arguments like date and time.
  
* Scheduled workouts with the same activity breakdowns in a different order **are not** considered duplicated

  in the current version of CLI.ckFit. If multiple activities with the same name are input in the same activity 

  breakdown, only the activity quantifier of the latest activity will be taken. This is due to limitations based

  on the choice usage of HashMaps in implementation.
  
* The current version CLI.ckFit only supports 3 types of distance based activities, 

  namely swimming, running and cycling. **ALL** other activities are assumed to be sets/repetitions based. 

## Important FAQs

* In order to load the data of your previous session, the user *MUST* press "enter" when prompted with the following 

question: 

`Would you like to load up the records of your fitness journey?

Key in 'y' to skip or press enter to load data from your previous session

Note: Keying in 'y' to skip this prompt will result in the previous session's databeing deleted!`

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

  - [**List volumes**](#list-volumes)

- #### Help

  - [**Access user help**](#help-command)

- #### Close CLI.ckFit

  - [**Bye**](#bye)

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

### List Manager

- [**List everything**](#list-everything)

### Miscellaneous

- [**FAQ**](#faq)

- [**Command Summary**](#command-summary)

# *Features:*

## Adding a meal

Command Word: `add meal`

Description: Adds a new meal to the list of meals, with its associated calories, date and time of consumption.

Format: `add meal MEAL NAME </c MEAL CALORIES /d DATE /t TIME>`

* The `MEAL_NAME` can contain spaces.

* Prefixes cannot be swapped and must follow the order shown above. No duplicates allowed.

* Each prefix only accepts one input after it.

* The `MEAL_CALORIES` can only contain positive integers inclusive of 0.

* You can only omit putting `MEAL_CALORIES` if you have saved the meal in your [meal library](#adding-meal-to-library) already.

* The `DATE` is in dd/mm/yyyy.

* The `TIME` is in hh:mm.

* If `DATE` or `TIME` is not specified, the system current date and time will be taken.

* Meals can be added to a future date if desired

  Example of usage:

`add meal risotto /c 250 /d 14/10/2021 /t 08:30`


## Adding fluids

Command Word: `add fluid`

Description: Adds a new fluid to the list of fluid items, with its associated calories, date and time of consumption.


Format: `add fluid FLUID_NAME </c FLUID_CALORIES /v VOLUME /d DATE /t TIME>`


* The `FLUID_NAME` can contain spaces.

* Prefixes cannot be swapped and must follow the order shown above. No duplicates allowed.

* Each prefix only accepts one input after it.

* The `FLUID_CALORIES` can only contain positive integers inclusive of 0.

* You can only omit putting `FLUID_CALORIES` if you have saved the drink in your [fluid library](#adding-fluid-to-library) already.

* The `VOLUME` can only contain positive integers inclusive of 0.

* The `DATE` is in dd/mm/yyyy.

* The `TIME` is in hh:mm.

* Fluids can be added to a future date if desired

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

* The `WEIGHT` cannot contain spaces and must be a non-negative number to maximum of 1 decimal place.

* Prefixes cannot be swapped and must follow the order shown above. No duplicates allowed.

* Each prefix only accepts one input after it.

* The `WEIGHT` has to be realistic (<1000.0 kg) due to limitation of integer overflow.

* The `DATE` is in dd/mm/yyyy.

* If `DATE` is not specified, the current system date will be taken.

* Only 1 `WEIGHT` should be included in your input.

* If multiple `/d DATE` or `DATE` is written only the first input will be taken.

Example of usage:

`add weight 50 /d 03/04/2021`

## Adding workout

Command Word: `add workout`

Description: Adds a new workout to the list of workout items, with its associated calories, date and time of workout.

Format: `add workout WORKOUT_NAME /c CALORIES_BURNED </d DATE /t TIME>`

* The `WORKOUT_NAME` can contain spaces.

* Prefixes cannot be swapped and must follow the order shown above. No duplicates allowed.

* Each prefix only accepts one input after it.

* The `CALORIES_BURNT` can only contain positive integers inclusive of 0.

* The `DATE` is in dd/mm/yyyy.

* The `TIME` is in hh:mm.

* If `DATE` or `TIME` is not specified, the system current date and time will be taken.

* Workouts can be added to a future date if desired

Example of usage:

* `add workout jog /c 250 /d 07/08/2021 /t 15:00` records a *workout* with the

  description `jog` and `250` calories burned on `07/08/2021` at `15:00`.

* `add workout jog /c 250` records a *workout* with the

    description `jog` and `250` calories burned on **today's date and current time**.

## Adding scheduled workout


Command Word: `add schedule`

Description: Adds a new scheduled workout to the list of scheduled workout items with the option to include 

activity breakdowns, with date and time of workout.

### With no activity breakdown:

Format: `add schedule WORKOUT_NAME /d DATE /t TIME </r>`

* The `WORKOUT_NAME` can contain spaces.

* Prefixes cannot be swapped and must follow the order shown above. No duplicates allowed.

* Each prefix only accepts one input after it.

* The `DATE` is in dd/mm/yyyy.

* The `TIME` is in hh:mm.

* The `DATE` or `TIME` is compulsory for schedules.

* The `/r` flag at the end is an ***optional*** flag for recurrence, which schedules a weekly *recurring* workout.

* The ***optional*** `/r` flag, **if** included, **must** be at the end of the command.

Example of usage:

* `add schedule chest day /d 22/12/2021 /t 15:00` adds a *non-recurring workout* to your schedule with the 

  description `chest day` on `22/12/2021` at `15:00`.

* `add schedule weekly chest day /d 07/12/2021 /t 13:50 /r` adds a *recurring* workout to your schedule with the

  description `weekly chest day` on `07/12/2021` at `13:50`.

### With activity breakdown

Format: `add schedule WORKOUT_NAME /d DATE /t TIME </a ACTIVITY_NAME:ACTIVITY_QUANTIFIER, ...> </r>`

* The `/a` separator is optional.

* The `ACTIVITY_NAME` can contain spaces and `:` ***must*** follow after it.

* Prefixes cannot be swapped and must follow the order shown above. No duplicates allowed.

* Each prefix only accepts one input after it.

* CLI.ckFit supports 3 special **case-sensitive** keywords for `ACTIVITY_NAME`, namely `running/swimming/cycling`.

  If any of these 3 keywords are used, then `ACTIVITY_QUANTIFIER` takes in one **positive non-zero integer** `[DISTANCE]`

  in **metres** for the activity.

* For **ALL** other kinds of `ACTIVITY_NAME`, `ACTIVITY_QUANTIFIER` takes in two **positive non-zero integers** 

  in the form `[SETS]x[REPS]`. In other words it is **assumed** that the activities are sets/repetitions

  activities. (Read more about Sets and Reps under [**Common Terminologies**](#common-terminologies-and-definitions))

* Multiple activities can be entered as long as they are separated by a comma `,`.

* The `/r` flag at the end is an ***optional*** flag for recurrence, which schedules a weekly recurring workout.

* The ***optional*** `/r` flag, if included, **must** be at the end of the command.


Example of usage:

* `add schedule weekly chest day /d 07/12/2021 /t 15:00 /a bench press:5x12, pushups:5x20 /r` adds a *recurring* workout

  to your schedule with the description `weekly chest day` on `07/12/2021` at `15:00`. Furthermore, an activity breakdown of

  `bench press` with `5` sets and `12` reps as well as `pushups` with `5` sets of `20` reps will also be added.

* `add schedule triathlon training /d 07/12/2021 /t 15:00 /a running:3000, swimming:1000, cycling:4000` adds a *non-recurring* workout

  to your schedule with the description `traithlon training` on `07/12/2021` at `15:00`. Furthermore, an activity breakdown of

  `running` for `3000` metres, `swimming` for `1000` metres as well as `cycling` for `4000` metres will also be added.

## Adding meal to library

Command Word: `library addmeal`

Description: Add a new meal record to the library, with its associated calories.

Format: `library addmeal MEAL_NAME /c MEAL_CALORIES`

* The MEAL_NAME can contain spaces.

* Prefixes cannot be swapped and must follow the order shown above. No duplicates allowed.

* Each prefix only accepts one input after it.

* The MEAL_CALORIES can only contain positive integers inclusive of 0.

Example of usage:

`library addmeal chocolate cake /c 110`

## Adding fluid to library

Command Word: `library addfluid`

Description: Adds a new fluid record to the library, with its associated calories.

Format: `library addfluid FLUID_NAME /c FLUID_CALORIES`

* The FLUID_NAME can contain spaces.

* Prefixes cannot be swapped and must follow the order shown above. No duplicates allowed.

* Each prefix only accepts one input after it.  

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

[comment]: <> (## Delete a fluid)

[comment]: <> (Command Word: `delete fluid`)

[comment]: <> (Description: Removes a fluid from the list of fluid items.)

[comment]: <> (Format: `delete fluid INDEX`)

[comment]: <> (* The `INDEX` can only contain integers from the list.)

[comment]: <> (Example of usage:)

[comment]: <> (`delete fluid 2`)

[comment]: <> (## Delete a weight)

[comment]: <> (Command Word: `delete weight`)

[comment]: <> (Description: Deletes a meal from the list of meals.)

[comment]: <> (Format: `delete weight INDEX`)

[comment]: <> (* Use `list weights all` to determine the index of the meal you wish to delete.)

[comment]: <> (* The `INDEX` can only contain integers from the list.)

[comment]: <> (* Only 1 `INDEX` should be included in your input.)

[comment]: <> (Example of usage:)

[comment]: <> (`delete weight 2`)

[comment]: <> (## Delete a workout)

[comment]: <> (Command Word: `delete workout`)

[comment]: <> (Description: Deletes a workout from the list of workout items.)

[comment]: <> (Format: `delete workout INDEX`)

[comment]: <> (* Use `list workouts all` to determine the index of the workout you wish to delete.)

[comment]: <> (* The `INDEX` can only contain integers from the list.)

[comment]: <> (Example of usage:)

[comment]: <> (* `delete workout 3` will delete the workout with index `3` as seen in the list from `list schedule all` if the index is valid.)

[comment]: <> (## Delete a scheduled workout)

[comment]: <> (Command Word: `delete schedule`)

[comment]: <> (Description: Deletes a workout from the list of workout items.)

[comment]: <> (Format: `delete schedule INDEX`)

[comment]: <> (* Use `list schedule all` to determine the index of the scheduled workout you wish to delete.)

[comment]: <> (* The `INDEX` can only contain integers from the list.)

[comment]: <> (Example of usage:)

[comment]: <> (* `delete schedule 3` will delete the schedule workout with index `3` as seen in the list from `list schedule all` if the index is valid.)

[comment]: <> (## Delete a meal from library)

[comment]: <> (Command Word: `library deletemeal`)

[comment]: <> (Description: Deletes a meal record from the library.)

[comment]: <> (Format: `library deletemeal INDEX`)

[comment]: <> (* Use `library listmeals` to determine the index of the meal you wish to delete.)

[comment]: <> (Example of usage:)

[comment]: <> (`library deletemeal 2`)

[comment]: <> (## Delete a fluid from library)

[comment]: <> (Command Word: `library deletefluid`)

[comment]: <> (Description: Deletes a fluid record from the library.)

[comment]: <> (Format: `library deletefluid INDEX`)

[comment]: <> (* Use `library listfluids` to determine the index of the meal you wish to delete.)

[comment]: <> (Example of usage:)

[comment]: <> (`library deletefluid 2`)

[comment]: <> (## List meals)

[comment]: <> (Command Word: `list meals`)

[comment]: <> (Description: Lists all meals, with its associated calories, date and time of consumption.)

[comment]: <> (Format: `list meals <DATE>`)

[comment]: <> (* The `DATE` is in dd/mm/yyyy.)

[comment]: <> (* If `DATE` is left empty, the meals recorded **today** will be returned.)

[comment]: <> (* If the word `all` is written in place of `DATE`, **ALL** stored meals will be listed.)

[comment]: <> (Example of usage:)

[comment]: <> (`list meals`, `list meals 22/10/2021`, `list meals all`)


[comment]: <> (## List fluids)

[comment]: <> (Command Word: `list fluids`)

[comment]: <> (Description: Lists all fluids, with its associated calories, date and time of consumption.)

[comment]: <> (Format: `list fluids <DATE>`)

[comment]: <> (* The `DATE` is in dd/mm/yyyy.)

[comment]: <> (* If `DATE` is left empty, the fluids recorded today will be returned.)

[comment]: <> (* If the word `all` is written in place of DATE, ALL stored fluids will be listed.)

[comment]: <> (Example of usage:)

[comment]: <> (`list fluids`, `list fluids 22/10/2021`, `list fluids all`)

[comment]: <> (## List weights)

[comment]: <> (Command Word:`list weights`)

[comment]: <> (Description: Lists weight depending on date of entry.)

[comment]: <> (Format: `list weights <DATE>`)

[comment]: <> (* The `DATE` is in dd/mm/yyyy.)

[comment]: <> (* If `DATE` is left empty, the weights recorded **today** will be returned.)

[comment]: <> (* If the word `all` is written in place of `DATE`, **ALL** stored weights will be listed.)

[comment]: <> (* Only 1 `DATE` should be included in your input.)

[comment]: <> (Example of usage:)

[comment]: <> (`list weights`, `list weights 22/10/2021`, `list weights all`)

[comment]: <> (## List workouts)

[comment]: <> (Command Word:`list workouts`)

[comment]: <> (Description: Lists out all stored workout descriptions, )

[comment]: <> (calories burned, date and time depending on date of entry.)

[comment]: <> (Format: `list workouts <DATE>`)

[comment]: <> (* The `DATE` is in dd/mm/yyyy.)

[comment]: <> (* If `DATE` is left empty, the workouts recorded **today** will be returned.)

[comment]: <> (* If the word `all` is written in place of `DATE`, **ALL** stored workouts will be listed.)


[comment]: <> (Example of usage:)

[comment]: <> (* `list workouts` will list the recorded workouts for today's date.)

[comment]: <> (* `list workouts 22/10/2021` will list the recorded workouts on `22/10/2021`.)

[comment]: <> (* `list workouts all` will list all your recorded workouts.)

[comment]: <> (## List scheduled workouts)

[comment]: <> (Command Word: `list schedule`)

[comment]: <> (Description: Lists out stored scheduled )

[comment]: <> (workout descriptions, date and time as well as their activity breakdowns depending on date )

[comment]: <> (Format: `list schedule <DATE>`)

[comment]: <> (* The `DATE` is in dd/mm/yyyy.)

[comment]: <> (* If `DATE` is left empty, the schedule for the **today** will be returned.)

[comment]: <> (* If the word `all` is written in place of `DATE`, **ALL** stored scheduled workouts will be listed.)

[comment]: <> (Example of usage:)

[comment]: <> (* `list schedule` will list all the schedule for today's date.)

[comment]: <> (* `list schedule 22/10/2022` will list the schedule for `22/10/2022`.)

[comment]: <> (* `list schedule all` will list your full schedule.)

[comment]: <> (## List Volumes)

[comment]: <> (Command Word: `list volumes`)

[comment]: <> (Description: List volumes of all fluids consumed for a specific date)

[comment]: <> (Format: `list volumes <DATE>`)

[comment]: <> (## List Calories)

[comment]: <> (Command Word: `list calories`)

[comment]: <> (Description:  List total number of calories consumed, total number of calories burned and net calories)

[comment]: <> (for a chosen date)

[comment]: <> (Format: `list calories <DATE> `)

[comment]: <> (## List meals stored in library)

[comment]: <> (Command Word: `library listmeals`)

[comment]: <> (Description: Lists all meals stored in the library.)

[comment]: <> (Format: `library listmeals`)

[comment]: <> (## List fluids stored in library)

[comment]: <> (Command Word: `library listfluids`)

[comment]: <> (Description: Lists all fluids stored in the library.)

[comment]: <> (Format: `library listfluids`)

[comment]: <> (## List everything on current date)

[comment]: <> (Command Word: `list`)

[comment]: <> (Description: Lists all meals, fluids, weight, workouts and scheduled workouts on a particular date)

[comment]: <> (Format `list <DATE`)

[comment]: <> (* The `DATE` is in dd/mm/yyyy.)

[comment]: <> (* If `DATE` is left empty **today's** date will be taken.)

[comment]: <> (Example of usage:)

[comment]: <> (* `list` will list all data from meals, fluids, weight, workouts and schedule workouts for today's date.)

[comment]: <> (* `list 22/10/2021` will list all data on `22/10/2021`.)

[comment]: <> (## List everything on all dates)

[comment]: <> (Command Word: `list`)

[comment]: <> (Description: Lists all meals, fluids, weight, workouts and scheduled workouts on all dates)

[comment]: <> (Format `list all`)

[comment]: <> (Example of usage:)

[comment]: <> (`list all`)

[comment]: <> (Expected output)

[comment]: <> (```)

[comment]: <> (1. cookie)

[comment]: <> (Calories: 22)

[comment]: <> (Date: 15/02/2022)

[comment]: <> (Time: 19:38)

[comment]: <> (Total number of meals: 1)

[comment]: <> (Total calories: 22)

[comment]: <> (1. cola)

[comment]: <> (Calories: 123)

[comment]: <> (Volume: 0)

[comment]: <> (Date: 12/12/2024)

[comment]: <> (Time: 19:38)

[comment]: <> (Total number of fluids: 1)

[comment]: <> (Total calories: 123)

[comment]: <> (Your weight list is empty!)

[comment]: <> (Your workout list is empty!)

[comment]: <> (Your workout schedule is empty!)

[comment]: <> (```)

[comment]: <> (## Help Command)

[comment]: <> (Command Word: `help`)

[comment]: <> (Description: Lists out the formats of input for meal, fluid, workout, weight and library commands and )

[comment]: <> (provides the hyperlink to the user guide for more tech-savvy users to read.)

[comment]: <> (Example of usage:)

[comment]: <> (`help`)

[comment]: <> (## Bye)

[comment]: <> (Command Word: `bye`)

[comment]: <> (Description: Terminates CLI.ckFit program.)

[comment]: <> (Example of usage:)

[comment]: <> (`bye`)


[comment]: <> (## FAQ)

[comment]: <> (Q: Are the commands case-sensitive?)

[comment]: <> (A: Yes, please follow the specified casing. All commands are to be in lower-case. )

[comment]: <> (Q: How do I transfer my CLIckFit data to another computer?)

[comment]: <> (A: Transfer the data text files located in the same directory as your CLIckFit.jar file over)

[comment]: <> (to your other computer. Place it in the same directory as the CLIckFit.jar file on your other computer. Your)

[comment]: <> (data will then be loaded from the text files when you run CLIckFit.jar on your new computer.)

[comment]: <> (Q: Can I check and alter my CLIckFit data myself?)

[comment]: <> (A: Yes. You can open up the files, "Weight.txt", "Schedule.txt", "Workout.txt", "Food.txt" and)

[comment]: <> ("FoodBank.txt". These files contain the associated data which you can view and also update manually in the correct format if you wish to.)

[comment]: <> (## Command Summary)

[comment]: <> (*Psssstttttt click on the commands to skip sections!*)

[comment]: <> (* Parameters not enclosed in any brackets are compulsory while those enclosed in `<>` are ***optional***.)

[comment]: <> (* You can only omit putting calories if you have already saved the meal/fluid in their respective libraries.)

[comment]: <> (Command | Format of input)

[comment]: <> (------------ | -------------)

[comment]: <> ([**Add meal**]&#40;#adding-a-meal&#41;| `add meal MEAL_NAME </c MEAL_CALORIES /d DATE /t TIME>`)

[comment]: <> ([**Add fluid**]&#40;#adding-fluids&#41;| `add fluid FLUID_NAME </c FLUID_CALORIES /v VOLUME /d DATE /t TIME>`)

[comment]: <> ([**Add weight**]&#40;#adding-weight&#41;| `add weight WEIGHT /d <DATE>`)

[comment]: <> ([**Add workout**]&#40;#adding-workout&#41;| `add workout WORKOUT_NAME /c CALORIES_BURNED </d DATE /t TIME>`)

[comment]: <> ([**Add scheduled workout**]&#40;#adding-scheduled-workout&#41;| `add schedule WORKOUT_NAME /d DATE /t TIME </a ACTIVITY_NAME:ACTIVITY_QUANTIFIER, ...> </r>`)

[comment]: <> ([**Add meal to library**]&#40;#adding-meal-to-library&#41;| `library addmeal MEAL_NAME /c MEAL_CALORIES`)

[comment]: <> ([**Add fluid to library**]&#40;#adding-fluid-to-library&#41;| `library addfluid FLUID_NAME /c FLUID_CALORIES`)

[comment]: <> ([**Remove meal**]&#40;#delete-a-meal&#41;| `delete meal INDEX`)

[comment]: <> ([**Remove fluid**]&#40;#delete-a-fluid&#41;| `delete fluid INDEX`)

[comment]: <> ([**Remove weight**]&#40;#delete-a-weight&#41;| `delete weight INDEX`)

[comment]: <> ([**Remove workout**]&#40;#delete-a-workout&#41;| `delete workout INDEX`)

[comment]: <> ([**Remove scheduled workout**]&#40;#delete-a-scheduled-workout&#41;| `delete schedule INDEX`)

[comment]: <> ([**Remove meal from library**]&#40;#delete-a-meal-from-library&#41;| `library deletemeal INDEX`)

[comment]: <> ([**Remove fluid from library**]&#40;#delete-a-fluid-from-library&#41;| `library deletefluid INDEX`)

[comment]: <> ([**List meals**]&#40;#list-meals&#41;| `list meals <DATE>`)

[comment]: <> ([**List fluids**]&#40;#list-fluids&#41;| `list fluids <DATE>`)

[comment]: <> ([**List weights**]&#40;#list-weights&#41;| `list weights <DATE>`)

[comment]: <> ([**List workouts**]&#40;#list-workouts&#41;| `list workouts <DATE>`)

[comment]: <> ([**List calories**]&#40;#list-calories&#41;| `list calories <DATE>`)

[comment]: <> ([**List volume**]&#40;#list-volumes&#41;| `list volumes <DATE>`)

[comment]: <> ([**List scheduled workouts**]&#40;#list-scheduled-workouts&#41;| `list schedule <DATE>`)

[comment]: <> ([**List meals from library**]&#40;#list-meals-stored-in-library&#41;| `library listmeals`)

[comment]: <> ([**List fluids from library**]&#40;#list-fluids-stored-in-library&#41;| `library listfluids`)

[comment]: <> ([**List everything**]&#40;#list-everything&#41;| `list <DATE>`)

[comment]: <> ([**Access user help**]&#40;#help-command&#41;| `help`)