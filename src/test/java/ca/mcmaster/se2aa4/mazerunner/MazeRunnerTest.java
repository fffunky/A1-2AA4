package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MazeRunnerTest {
    @Test
    void ConstructorTest() {
        class ConstructorTestCase {
            private final IMaze maze;
            private final Position expectedStart;
            private final Position expectedTarget;
            private ConstructorTestCase(String mazeString, Position expectedStart, Position expectedTarget) {
                this.maze = new TestMaze(mazeString);
                this.expectedStart = expectedStart;
                this.expectedTarget = expectedTarget;
            }
        }

        List<ConstructorTestCase> testCases = List.of(
                new ConstructorTestCase("""
                        ####
                        ####
                        
                        ####
                        ####
                        """, new Position(0, 2), new Position(3, 2)),
                new ConstructorTestCase("""                
                #####################
                #                   #
                #
                #                   #
                                    #
                #                   #
                #                   #
                #                   #
                #####################
                """, new Position(0, 4), new Position(20, 2)
                )
        );

        for (ConstructorTestCase tc : testCases) {
            MazeRunner mr = new MazeRunner(tc.maze);
            assertEquals(mr.getPosition().Coords(), tc.expectedStart.Coords());
            assertEquals(mr.getTarget().Coords(), tc.expectedTarget.Coords());
            assertEquals(mr.getHeading(), Heading.EAST);
            assertTrue(mr.getPath().getInstructions().isEmpty());
        }
    }
}
