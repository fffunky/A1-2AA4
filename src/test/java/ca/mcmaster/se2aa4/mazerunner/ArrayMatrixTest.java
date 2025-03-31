package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ArrayMatrixTest<T> {
    @Test
    @DisplayName("Matrix constructor and initialization")
    public void ConstructorTest() {
        class ConstructorTestCase {
            private final int rows;
            private final int cols;
            private final int expectedHeight;
            private final int expectedWidth;
            ConstructorTestCase(int rows, int cols, int expectedHeight, int expectedWidth) {
                this.rows = rows;
                this.cols = cols;
                this.expectedHeight = rows;
                this.expectedWidth = cols;
            }
        }

        List<ConstructorTestCase> testCases = List.of(
                new ConstructorTestCase(0, 0, 0, 0),
                new ConstructorTestCase(1, 1, 1, 1),
                new ConstructorTestCase(1, 2, 1, 2),
                new ConstructorTestCase(2, 1, 2, 1),
                new ConstructorTestCase(20, 20, 20, 20),
                new ConstructorTestCase(20, 0, 20, 0),
                new ConstructorTestCase(0, 20, 0, 20)
        );

        for (ConstructorTestCase tc : testCases) {
            Matrix<Integer> m = new ArrayMatrix<Integer>(tc.rows, tc.cols);

            int rowCount = 0;
            while (true) {
                try {
                    m.getRow(rowCount);
                    rowCount++;
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
            assertEquals(rowCount, tc.expectedHeight);

            int colCount = 0;
            while (true) {
                try {
                    m.getCol(colCount);
                    colCount++;
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
            assertEquals(colCount, tc.expectedWidth);
        }
    }

    @Test
    @DisplayName("Test getting and setting function for valid indices")
    public void GetSetTest() {
        int rows = 5;
        int cols = 5;
        Matrix<Integer> m = new ArrayMatrix<Integer>(rows, cols);

        // Populate matrix with valid indices
        Integer counter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m.Set(i, j, counter);
                counter++;
            }
        }

        // Check valid indices for the values we just set
        counter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                assertEquals(m.Get(i, j), counter);
                counter++;
            }
        }

        // Overwrite previous values with new ones to check updating
        counter = 25;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m.Set(i, j, counter);
                counter--;
            }
        }

        // Check to see if values were overwritten
        counter = 25;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                assertEquals(m.Get(i, j), counter);
                counter--;
            }
        }

        assertThrows(IndexOutOfBoundsException.class, () -> {m.Set(-1, 1, 100);});
        assertThrows(IndexOutOfBoundsException.class, () -> {m.Set(1, -1, 100);});
        assertThrows(IndexOutOfBoundsException.class, () -> {m.Set(-1, -1, 100);});
        assertThrows(IndexOutOfBoundsException.class, () -> {m.Get(-1, 1);});
        assertThrows(IndexOutOfBoundsException.class, () -> {m.Get(1, -1);});
        assertThrows(IndexOutOfBoundsException.class, () -> {m.Get(-1, -1);});
    }


}
