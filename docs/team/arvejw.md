# Teh Jiewen - Project Portfolio Page

## Overview
CLI.ckFit is a desktop-based fitness app which can be accessed easily via CLI. CLI.ckFit allows you to input your calories,
weight, foods, and workouts throughout the day. It also comes with a BMI and recommended caloric intake calculator 
which can give you an idea of your current fitness level.

### Summary of Contributions
#### Code contributed
https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false

#### Enhancements implemented
##### Weekly recurring workouts and automatic deletion/rescheduling of scheduled workouts

Recurring workouts are implemented with the help of various classes 
and methods from `java.time.*`. After adding a scheduled workout or before listing the schedule list, overdue workouts 
are looked for and deleted or rescheduled. Since the schedule list is sorted by ascending `DateTime`, this is done by 
looking at the first index of the schedule list. If an overdue non-recurring workout is detected, it is deleted. 
If the overdue workout is a recurring workout it's date is rescheduled once by incrementing its' `DateTime` attribute 
by a multiple  of 7 days. This process is repeated until the date of the scheduled workout is not before the current 
date, keeping the list clean. This deleting/rescheduling is also done upon loading of user data. 
E.g. if a user does not touch the app for a month, his/her schedule will be cleaned upon loading, ensuring a non-messy 
or outdated schedule even after not using the app for some period of time. 

While I had some idea of how I wanted to achieve this feature, it proved harder than expected. "Rescheduling" was 
fairly achievable after looking more in `java.time.*` and using the `plusDays()` method 
from `java.time.LocalDateTime`. Integrating this rescheduling in addition to deleting of overdue workouts 
took quite a bit of time and effort as various other factors also had to be considered. For instance there is the 
possibility that a *rescheduled* workout might  be identical to another entry in the schedule, which has to be
taken as a duplicate. Fair bit of new exceptions also had to be added with this new feature.

##### Optional activity breakdown for scheduled workouts

Gave an option for users to include a comprehensive activity breakdown for their schedule workout. 
When parsing activities, an activity description is paired with its respective activity quantifiers 
using HashMaps where the description is the key, and the quantifier(s) is/are the value. 
For 3 special activity descriptions, namely 
running/cycling/swimming, the quantifier will take in one integer to represent the distance in metres. 
Otherwise, the activity will be assumed to be quantified with [sets]x[reps], taking in two integers instead.

Having some knowledge of HashMaps and how they work, but not having any experience actually using them in code proved 
for an interesting learning experience. I had quite a lot of instances of addresses 
being printed instead of the values I was looking for. This was when I started out using an Array as my value 
in the HashMap in the earlier stages of implementation (or rather attempt at implementation). 
However, after consulting the internet and some trial and error, I was able to discover the flaws in my implementation 
and correct them. A new class, `WorkoutActivity` was also created store activity information. Various new methods 
also had to be added to accommodate the new feature, such as storing in the data file and exception handling.

#### Contributions to UG

* Add/delete/list workout command sections
* Add/delete/list schedule commands sections
* Some parts of known limitations sections

#### Contributions to DG

* ScheduleTracker Design & Implementation section
* UML Class and sequence diagrams associated with ScheduleTracker
* Some reformatting of the Design & Implementation section to be neater.
* Added appendix sections in the DG as well as some content to them

#### Contributions to team-based tasks
* Some reformatting of the Design & implementation section in the DG to be neater.
* Added hyperlink support to table of contents in DG


#### Review/mentoring contributions
https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/77
https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/88
https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/104
