package seedu.duke;

import org.junit.jupiter.api.Test;

class CommandManagerTest {
    /*
    @Test
    void commandManager() {
        String input = "drank cola 300 12/12/2021";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals(Keywords.INPUT_DRINKS);
    }
     */

    @Test
    void commandManager1() {
        String input = "workout leg day /at 23:59 /c 388";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals(Keywords.INPUT_ADD_WORKOUT);
    }

    /*
    @Test
    void commandManager2() {
        String input = "ate risotto and noodle 200 12/12/2022";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals(Keywords.INPUT_MEAL);
    }
     */

    @Test
    void commandManager3() {
        String input = "addweight 30 12/12/2021";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals(Keywords.INPUT_ADD_WEIGHT);
    }

    @Test
    void commandManager4() {
        String input = "bye";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals(Keywords.INPUT_BYE);
    }

    @Test
    void commandManager5() {
        String input = "help";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals(Keywords.INPUT_HELP);
    }

    @Test
    void commandManager6() {
        String input = "deletemeal";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals(Keywords.DELETE_MEAL);
    }

    @Test
    void commandManager7() {
        String input = "listfluids";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals(Keywords.LIST_DRINKS);
    }
}