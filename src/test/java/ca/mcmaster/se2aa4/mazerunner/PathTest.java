package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PathTest {

    @Test
    @DisplayName("Test conversion of string to path")
    public void StringToPathTest() {
        class StringToPathTestCase {
            public final String input;
            public final String expected;
            public StringToPathTestCase(String input, String expected) {
                this.input = input;
                this.expected = expected;
            }
        }

        List<StringToPathTestCase> testCases = List.of(
                new StringToPathTestCase("", ""),
                new StringToPathTestCase("F", "1F "),
                new StringToPathTestCase("FFFF", "1F 1F 1F 1F "),
                new StringToPathTestCase("4F", "4F "),
                new StringToPathTestCase("L", "1L "),
                new StringToPathTestCase("LLLL", "1L 1L 1L 1L "),
                new StringToPathTestCase("4L", "4L "),
                new StringToPathTestCase("R", "1R "),
                new StringToPathTestCase("RRRR", "1R 1R 1R 1R "),
                new StringToPathTestCase("4R", "4R "),
                new StringToPathTestCase("LFRLFRRR", "1L 1F 1R 1L 1F 1R 1R 1R "),
                new StringToPathTestCase("1L1F1R1L1F3R", "1L 1F 1R 1L 1F 3R "),
                new StringToPathTestCase("3F 2R 4F 1L 2F 1R 6F R FF", "3F 2R 4F 1L 2F 1R 6F 1R 1F 1F ")
        );

        for (StringToPathTestCase tc : testCases) {
            Path result = Path.pathFromString(tc.input);

            assertEquals(tc.expected, result.toString());
        }
    }

    @Test
    @DisplayName("Check proper factorization of path")
    public void FactorizedTest() {
        class FactorizedTestCase {
            public final String input;
            public final String expectedFactorized;
            public FactorizedTestCase(String input, String expectedFactorized) {
                this.input = input;
                this.expectedFactorized = expectedFactorized;
            }
        }

        List<FactorizedTestCase> testCases = List.of(
                new FactorizedTestCase("F", "F"),
                new FactorizedTestCase("L", "L"),
                new FactorizedTestCase("R", "R"),
                new FactorizedTestCase("FFF", "3F"),
                new FactorizedTestCase("LLL FF RR L F R", "3L 2F 2R L F R"),
                new FactorizedTestCase("2L3F2RL3F", "2L 3F 2R L 3F")
        );

        for (FactorizedTestCase tc : testCases) {
            Path result = Path.pathFromString(tc.input);
            assertEquals(tc.expectedFactorized, result.Factorized());
        }
    }

    @Test
    @DisplayName("Check proper canonicalization of path")
    public void CanonicalTest() {
        class CanonicalTestCase {
            public final String input;
            public final String expectedCanonical;
            public CanonicalTestCase(String input, String expectedCanonical) {
                this.input = input;
                this.expectedCanonical = expectedCanonical;
            }
        }

        List<CanonicalTestCase> testCases = List.of(
                new CanonicalTestCase("F", "F"),
                new CanonicalTestCase("L", "L"),
                new CanonicalTestCase("R", "R"),
                new CanonicalTestCase("FFF", "FFF"),
                new CanonicalTestCase("LLL FF RR L F R", "LLL FF RR L F R"),
                new CanonicalTestCase("2L3F2RL3F", "LL FFF RR L FFF")
        );

        for (CanonicalTestCase tc : testCases) {
            Path result = Path.pathFromString(tc.input);
            assertEquals(tc.expectedCanonical, result.Canonical());
        }
    }
}

