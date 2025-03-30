package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CompassTest {
    @Test
    public void TestSetGetHeading() {
        class SetHeadingTestCases {
            final Heading startHeading;
            final Heading setHeading;
            SetHeadingTestCases(Heading startHeading, Heading setHeading) {
                this.startHeading = startHeading;
                this.setHeading = setHeading;
            }
        }

        List<SetHeadingTestCases> testCases = List.of(
                new SetHeadingTestCases(Heading.NORTH, Heading.NORTH),
                new SetHeadingTestCases(Heading.NORTH, Heading.EAST),
                new SetHeadingTestCases(Heading.NORTH, Heading.SOUTH),
                new SetHeadingTestCases(Heading.NORTH, Heading.WEST),
                new SetHeadingTestCases(Heading.EAST, Heading.NORTH),
                new SetHeadingTestCases(Heading.EAST, Heading.EAST),
                new SetHeadingTestCases(Heading.EAST, Heading.SOUTH),
                new SetHeadingTestCases(Heading.EAST, Heading.WEST),
                new SetHeadingTestCases(Heading.SOUTH, Heading.NORTH),
                new SetHeadingTestCases(Heading.SOUTH, Heading.EAST),
                new SetHeadingTestCases(Heading.SOUTH, Heading.SOUTH),
                new SetHeadingTestCases(Heading.SOUTH, Heading.WEST),
                new SetHeadingTestCases(Heading.WEST, Heading.NORTH),
                new SetHeadingTestCases(Heading.WEST, Heading.EAST),
                new SetHeadingTestCases(Heading.WEST, Heading.SOUTH),
                new SetHeadingTestCases(Heading.WEST, Heading.WEST)
        );

        for (SetHeadingTestCases tc : testCases) {
            Compass c = new Compass(tc.startHeading);
            assertEquals(tc.startHeading, c.getHeading());

            c.setHeading(tc.setHeading);
            assertEquals(tc.setHeading, c.getHeading());
        }
    }

    @Test
    public void TestToRightToLeft() {
        Compass c = new Compass(Heading.NORTH);
        c.toRight();
        assertEquals(Heading.EAST, c.getHeading());

        c.toRight();
        assertEquals(Heading.SOUTH, c.getHeading());

        c.toRight();
        assertEquals(Heading.WEST, c.getHeading());

        c.toRight();
        assertEquals(Heading.NORTH, c.getHeading());

        c.toLeft();
        assertEquals(Heading.WEST, c.getHeading());

        c.toLeft();
        assertEquals(Heading.SOUTH, c.getHeading());

        c.toLeft();
        assertEquals(Heading.EAST, c.getHeading());

        c.toLeft();
        assertEquals(Heading.NORTH, c.getHeading());
    }
}
