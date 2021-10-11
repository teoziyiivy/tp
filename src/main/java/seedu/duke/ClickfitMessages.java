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
            + "brought to you by Jie Wen, Vishal, Pragyan, Ivy and Edward."
            + System.lineSeparator() + "See you soon!";

    public static final String MEMORY_STARTUP_PROMPT = System.lineSeparator() + "Welcome back! Would you "
            + "like to load up the records of your fitness journey?" + System.lineSeparator()
            + "Key in Y/N.";

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
                    + "<command> <description> /d <date>"
                    + System.lineSeparator()
                    + System.lineSeparator()
                    + "All else:" + System.lineSeparator()
                    + "<command> <description> /c <calories> /d <date> /t <time>"
                    + System.lineSeparator();

    public static final String HELP_UG = "Here is the link to our User Guide!";

}
