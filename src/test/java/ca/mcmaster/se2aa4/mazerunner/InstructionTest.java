package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class InstructionTest {

    private static Instruction dummyInstance = new Instruction("1F");

    private static void invokeValidateString(Instruction instruction, String input) throws Throwable {
        try {
            Method method = Instruction.class.getDeclaredMethod("validateString", String.class);
            method.setAccessible(true);
            method.invoke(instruction, input);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException("Failed to access validateString", e);
        }
    }

    @Test
    @DisplayName("Cases when validateString method should not throw any errors")
    public void TestValidateStringWithValidInstruction() {
        List<String> testCases = List.of(
                "F", "L", "R", "1F", "10L", "999R", "0F", "1234567890L"
        );

        for (String tc : testCases) {
            assertDoesNotThrow(() -> invokeValidateString(dummyInstance, tc));
        }
    }

    @Test
    @DisplayName("Cases when validateString method should throw errors")
    public void TestValidateStringWithInvalidInstruction() {
        // null case
        Throwable thrown = assertThrows(Exception.class, () -> invokeValidateString(dummyInstance, ""));
        assertEquals("Instruction is null or empty", thrown.getMessage());

        // empty case
        thrown = assertThrows(Exception.class, () -> invokeValidateString(dummyInstance, null));
        assertEquals("Instruction is null or empty", thrown.getMessage());

        // non-digit characters before end
        List<String> cases = List.of(
                "A1F", "1AF", "10 R", "F1L", " 1F"
        );
        for (String tc : cases) {
            thrown = assertThrows(Exception.class, () -> invokeValidateString(dummyInstance, tc));
            assertTrue(thrown.getMessage().startsWith("Expected digit, found "));
        }

        // invalid last character
        cases = List.of(
                "1A", "10X", "99%", "5f", "10l", "1r", "123", "A", "5"
        );
        for (String tc : cases) {
            thrown = assertThrows(Exception.class, () -> invokeValidateString(dummyInstance, tc));
            assertEquals(thrown.getMessage(), "Last instruction character should be 'F', 'L', or 'R'");
        }
    }
}
