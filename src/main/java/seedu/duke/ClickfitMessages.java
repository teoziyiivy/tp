package seedu.duke;

public class ClickfitMessages {

    public static final String MESSAGE_A = System.lineSeparator() + "Lets get your fitness journey "
            + "started! input any commands to get started! Type \"help commands\" to get started! "
            + "If you want to read the UG, type in \"help UG\"!" + System.lineSeparator()
            + "Lets work hard together in your fitness journey!";

    public static final String MESSAGE_B = System.lineSeparator() + "Welcome back! Ready to mark "
            + "another day of progress? As said before, type \"help commands\" "
            + "if you get dizzy from all that fitness training!";

    public static final String CREDITS = System.lineSeparator() + "Thank you for the hardwork today. "
            + "CLI.ckFit wishes you a good day" + System.lineSeparator() + "Team CLI.ckFit is proudly "
            + "brought to you by Jiewen, Vishal, Pragyan, Ivy and Edward."
            + System.lineSeparator() + "See you soon!";

    public static final String MEMORY_STARTUP_PROMPT = "Would you "
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
            System.lineSeparator() + "ScheduleDescription:"
                    + System.lineSeparator()
                    + "<command> <description> /d <date> /t <time>"
                    + System.lineSeparator() + "\n"
                    + "Fluids:"
                    + System.lineSeparator()
                    + "<command> <description> /c <calories> /v <volume> /d <date> /t <time>"
                    + System.lineSeparator() + "\n"
                    + "Add weight:"
                    + System.lineSeparator()
                    + "<command> <weight> /d <date>"
                    + System.lineSeparator() + "\n"
                    + "All else:" + System.lineSeparator()
                    + "<command> <description> /c <calories> /d <date> /t <time>";


    public static final String HELP_UG = "Here is the link to our User Guide! "
            + "https://ay2122s1-cs2113t-f14-3.github.io/tp/UserGuide.html";

    public static final String DATE_ERROR = "Please enter your date in the right format. "
            + "It should be \"DD/MM/YYYY\"";

    public static final String TIME_ERROR = "Please enter your time in the right format. "
            + "It should be \"HH:MM\"";

    public static final String NAME_ERROR = "Please enter the name or description of your meal/fluid";

    public static final String NUMBER_ERROR = "Please enter the calories/volume properly";

    public static final String MEAL_NAME_ERROR = "Please enter the name of your meal";

    public static final String FLUID_ADD_FORMAT_ERROR = "Please enter in the format: drank [fluid_name]"
            + " /c [calorie_intake] " + "/v [volume] /d [dd/mm/yyyy] /t [hh:mm]";

    public static final String FLUID_DELETE_ERROR = "You have no existing fluid entries to delete.";

    public static final String FLUID_DELETE_FORMAT_ERROR = "Please enter in the format: deletefluid [entry_number]";

    public static final String FLUID_LIST_ERROR = "You have no fluid entries yet.";

    public static final String CALCULATOR_PROMPT = System.lineSeparator() + "Welcome back! Would you "
            + "like to check your BMI and recommended caloric intake?" + System.lineSeparator()
            + "Key in y or press enter keystroke to skip!";

    public static final String MEAL_PRINT_FORMAT = System.lineSeparator() + "Meal Summary:"
            + System.lineSeparator() + "======================";

    public static final String FLUID_PRINT_FORMAT = System.lineSeparator() + "Fluid Summary:"
            + System.lineSeparator() + "======================";

    public static final String WORKOUT_PRINT_FORMAT = System.lineSeparator() + "Workout Summary:"
            + System.lineSeparator() + "======================";

    public static final String WEIGHT_PRINT_FORMAT = System.lineSeparator() + "Weight Summary:"
            + System.lineSeparator() + "======================";

    public static final String ENDLINE_PRINT_FORMAT = "======================";

    public static final String SCHEDULE_DATA_NOT_FOUND = "Unable to locate ScheduleTracker data file.";

    public static final String INCORRECT_LOADING_SCHEDULE_DATA =
            "There were some errors during loading of ScheduleTracker data, some data may have been lost";
}
