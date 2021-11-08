# Wang Zhao Yu, Edward - Project Portfolio Page

## Overview
CLI.ckFit is a desktop-based fitness app which can be accessed easily via CLI. CLI.ckFit allows you to input your calories,
weight, foods, and workouts throughout the day. It also comes with a BMI and recommended caloric intake calculator
which can give you an idea of your current fitness level.

### Summary of Contributions
#### Code contributed
https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=EdwardZYWang&tabRepo=AY2122S1-CS2113T-F14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false

#### Enhancements implemented
* Command Manager Class:
  * A class that makes sense of a user's inputs. After extracting the commands of the user's input to decide the destination
  class and intended actions, it feeds the 
  remaining part of the string, the inputArguments, to the respective Meal, Fluid, Workout and Weight classes for further 
  processing.
  * It is needed so that the various classes we have developed all have a common starting point, allowing us to debug 
  faster and also standardise the logic pathway of making sense of the user's inputs.
  * Due to the length of the original command manager, we decided to split the command manager into different components
  namely, foodbank parser, command manager, list parser, add parser, delete parser, to make debugging easier and the code neater

* Implemented miscellaneous classes like userhelp, calculator and contributed to Ui classes to improve user experience
  * userhelp class handles the "help" command with its unique output
  * memoryStartup method in Ui class reads all the information in the text files through Storage class methods and organises them in a reader-friendly 
  format, before printing the finished summary for the user to read. It also supports the function of deleting all
  content in the text files if the user wishes to start afresh.
  
* Contributions to Storage, Parser classes.
  * contributed methods in Parser classes and Storage classes
  * Storage class methods were written to allow user to access a summary of all data stored in text files
  * Other methods in Storage class were written to address needs of other classes like workout, meal, fluids and weight

* Implemented BMI calculator in calculator class
  * a method which allows the user to calculate his or her BMI 
  * it is needed to let the user know whether he or she needs to gain or lose weight
  * works well with recommended daily caloric intake

#### Contributions to UG
Wrote the following sections:
* Introduction
* Quick start
* Common terminologies and definitions
* Important FAQs
* Table of contents
* Help command
* Command Summary

Handled standardisation and formatting for the user guide

#### Contributions to DG
Wrote the following sections:
* User Interface (Ui) and its subsections
* UML diagrams for Getting BMI and daily caloric intake and Memory startup method
* Added Manual Testing for BMI and recommended daily caloric intake calculators

Contributed to:
* Appendix B User Stories


#### Contributions to team-based tasks
* Reformatting of the Design & implementation section in the UG.
* Added hyperlink support to table of contents in UG
* Added summary table of commands in UG and its hyperlinks
* Maintaining the issue tracker for PED and others
* Release management for v2.0
* Wrote meeting agendas and took down notes during meetings for team member's reference
* Took down notes during meetings with professors and tutors for team member's reference


#### Review/mentoring contributions
* Group mate PR reviews:
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/21](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/21)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/166](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/166)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/138](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/138)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/123](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/123)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/122](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/122)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/102](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/102)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/83](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/83)
  * [https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/143](https://github.com/AY2122S1-CS2113T-F14-3/tp/pull/143)