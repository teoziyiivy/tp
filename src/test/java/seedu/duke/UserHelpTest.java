package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserHelpTest {

    @Test
    void generateUserHelpParameters() {
        String input = "commands";
        assertTrue(input.trim().equals("commands"));
    }
}