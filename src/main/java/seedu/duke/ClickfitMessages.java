package seedu.duke;

//@@author { ALL}
public class ClickfitMessages {

    public static final String INITIAL_PROMPT = "What would you like to start with?";

    public static final String MESSAGE_A = System.lineSeparator() + "Lets get your fitness journey "
            + "started! input any commands to get started! Type \"help commands\" to get started! "
            + "If you want to read the UG, type in \"help UG\"!" + System.lineSeparator()
            + "Lets work hard together in your fitness journey!";

    public static final String CREDITS = System.lineSeparator() + "Thank you for the hardwork today. "
            + "CLI.ckFit wishes you a good day" + System.lineSeparator() + "Team CLI.ckFit is proudly "
            + "brought to you by Jiewen, Vishal, Pragyan, Ivy and Edward."
            + System.lineSeparator() + "See you soon!";

    public static final String MEMORY_STARTUP_PROMPT = System.lineSeparator() + "Would you "
            + "like to load up the records of your fitness journey?" + System.lineSeparator()
            + "Key in y or press enter keystroke to skip!";

    public static final String INCORRECT_INPUT = System.lineSeparator() + "CLI.ckFit has detected a "
            + "wrong input, kindly check your inputs or type \"help commands\" for input examples.";

    public static final String MEMORY_STARTUP_N_INPUT = System.lineSeparator() + "Understood! CLI.ckFit "
            + "wishes you all the best for the rest of the day";

    public static final String MEMORY_STARTUP_Y_INPUT = System.lineSeparator() + "Understood! "
            + "CLI.ckFit is accessing your storage...";

    public static final String MEMORY_STARTUP_INCORRECT_INPUT = System.lineSeparator() + "CLI.ckFit "
            + "has detected a wrong input, kindly type in either a \"Y\" or a \"N\".";

    public static final String HELP_COMMANDS =
            "parameters encapsulated by angle brackets \"< >\" are optional" + System.lineSeparator()
                    + "NOTE: You can only omit putting MEAL_CALORIES if you have saved the meal in your meal library."
                    + System.lineSeparator()
            + System.lineSeparator() + "[Add meal] | `add meal MEAL_NAME </c MEAL_CALORIES /d DATE /t TIME>`"
            + System.lineSeparator() + "[Add fluid] | `add fluid FLUID_NAME </c FLUID_CALORIES "
                    + "/v VOLUME /d DATE /t TIME>`"
            + System.lineSeparator() + "[Add weight] | `add weight WEIGHT /d <DATE>`"
            + System.lineSeparator() + "[Add workout] | `add workout WORKOUT_NAME /c "
                    + "CALORIES_BURNED </d DATE /t TIME>`"
            + System.lineSeparator() + "[Add scheduled workout] | `add schedule WORKOUT_NAME /d "
                    + "DATE /t TIME </a ACTIVTY_NAME:ACTIVITY_QUANITIFER, ...> </r>`"
            + System.lineSeparator() + "[Add meal to library] | `library addmeal MEAL_NAME /c MEAL_CALORIES`"
            + System.lineSeparator() + "[Add fluid to library] | `library addfluid "
                    + "FLUID_NAME /c FLUID_CALORIES`"
                    + System.lineSeparator()
            + System.lineSeparator() + "[Remove meal] | `delete meal INDEX`"
            + System.lineSeparator() + "[Remove fluid] | `delete fluid INDEX`"
            + System.lineSeparator() + "[Remove weight] | `delete weight INDEX`"
            + System.lineSeparator() + "[Remove workout] | `delete workout INDEX`"
            + System.lineSeparator() + "[Remove scheduled workout] | `delete schedule INDEX`"
            + System.lineSeparator() + "[Remove meal from library] | `library deletemeal INDEX`"
            + System.lineSeparator() + "[Remove fluid from library] | `library deletefluid INDEX`"
                    + System.lineSeparator()
            + System.lineSeparator() + "[List meals] | `list meals <DATE>`"
            + System.lineSeparator() + "[List fluids] | `list fluids <DATE>`"
            + System.lineSeparator() + "[List weights] | `list weights <DATE>`"
            + System.lineSeparator() + "[List workouts] | `list workouts <DATE>`"
            + System.lineSeparator() + "[List calories] | `list calories <DATE>`"
            + System.lineSeparator() + "[List volume] | `list calories <DATE>`"
            + System.lineSeparator() + "[List scheduled workouts] | `list schedule <DATE>`"
            + System.lineSeparator() + "[List meals from library] | `library listmeals`"
            + System.lineSeparator() + "[List fluids from library] | `library listfluids`"
                    + System.lineSeparator()
            + System.lineSeparator() + "[Access user help] | `help commands`"
            + System.lineSeparator() + "[Access user guide] | `help UG`"  + System.lineSeparator()
                    + System.lineSeparator() + "Here is the link to our User Guide! "
            + "https://ay2122s1-cs2113t-f14-3.github.io/tp/UserGuide.html";

    public static final String DATE_ERROR = "Please enter your date in the right format. "
            + "It should be \"DD/MM/YYYY\"";

    public static final String DATE_TIME_ERROR = "Please enter your date and time in the right format. "
            + "It should be \"DD/MM/YYYY\" and \"HH:MM\" respectively.";

    public static final String NUMBER_ERROR = "Please enter a positive integer.";

    public static final String FLUID_ADD_FORMAT_ERROR = "Please enter in the format: drank [fluid_name]"
            + " /c [calorie_intake] " + "/v [volume] /d [dd/mm/yyyy] /t [hh:mm]";

    public static final String CALCULATOR_PROMPT = System.lineSeparator() + "Welcome back! Would you "
            + "like to check your BMI and recommended caloric intake?" + System.lineSeparator()
            + "Key in y or press enter keystroke to skip!";

    public static final String MEAL_PRINT_FORMAT = System.lineSeparator() + "[Meal Summary:]"
            + System.lineSeparator() + "======================";

    public static final String FLUID_PRINT_FORMAT = System.lineSeparator() + "[Fluid Summary:]"
            + System.lineSeparator() + "======================";

    public static final String WORKOUT_PRINT_FORMAT = System.lineSeparator() + "[Workout Summary:]"
            + System.lineSeparator() + "======================";

    public static final String WEIGHT_PRINT_FORMAT = System.lineSeparator() + "[Weight Summary:]"
            + System.lineSeparator() + "======================";

    public static final String ENDLINE_PRINT_FORMAT = "======================";

    public static final String SCHEDULE_DATA_NOT_FOUND = "Unable to locate ScheduleTracker data file.";

    public static final String INCORRECT_LOADING_SCHEDULE_DATA =
            "There were some errors during loading of ScheduleTracker data, some data may have been lost.";

    public static final String IO_EXCEPTION_MESSAGE = "Error when loading data from files";

    public static final String FOOD_BANK_EXCEPTION_MESSAGE = "No such food or fluid with "
            + "its associated calories is stored"
            + "within your library.";

    public static final String DELETE_OR_UPDATE_SCHEDULE_MESSAGE = "CLI.ckFit has detected some overdue scheduled "
            + "workouts and has deleted/rescheduled them!";

    public static final String WEIGHT_ADD_FORMAT_ERROR = "CLI.ckFit encountered a problem adding your weight."
            + System.lineSeparator() + "Please follow the format: addweight <weight> /d <DD/MM/YYYY>";

    public static final String WEIGHT_DELETE_FORMAT_ERROR = "CLI.ckFit encountered a problem deleting your weight."
            + System.lineSeparator() + "Please follow the format: deleteweight <index>";

    public static final String WEIGHT_DELETE_INDEX_ERROR = "CLI.ckFit encountered a problem deleting your weight."
            + System.lineSeparator() + "Please ensure the index is within the list.";

    public static final String WEIGHT_EMPTY_ERROR = "Your weight list is empty!";

    public static final String EMPTY_SCHEDULE_LIST_MESSAGE = "Your workout schedule is empty!";

    public static final String EMPTY_WORKOUT_LIST_MESSAGE = "Your workout list is empty!";

    public static final String WEIGHT_PARAMETERS_ERROR = "CLI.ckFit could not generate your parameters.";

    public static final String DONT_UNDERSTAND = "OOPS!!! I'm sorry, but I don't know what that means";
}
