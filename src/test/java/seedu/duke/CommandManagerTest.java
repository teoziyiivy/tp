package seedu.duke;

import org.junit.jupiter.api.Test;

class CommandManagerTest {
    @Test
    void commandManager() {
        String input = "drank cola 300 12/12/2021";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals("drank");
    }

    @Test
    void commandManager1() {
        String input = "workout leg day /at 23:59 /c 388";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals("workout");
    }

    @Test
    void commandManager2() {
        String input = "ate risotto and noodle 200 12/12/2022";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals("ate");
    }

    @Test
    void commandManager3() {
        String input = "addweight 30 12/12/2021";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals("addweight");
    }
}