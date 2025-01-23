package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private static final Logger logger = LogManager.getLogger();
    private final List<List<Integer>> matrix = new ArrayList<>();

    public Matrix(Integer width, Integer height) {

        for (int i = 0; i < height; i++) {
            this.matrix.add(new ArrayList<>());
            for (int j = 0; j < width; j++) {
                this.matrix.get(i).add(0);
            }
        }
    }

    public void Set(int row, int col, int value) {
        try {
            this.matrix.get(row).set(col, value);
        } catch (IndexOutOfBoundsException e) {
            logger.error(" - Out of Bounds: {}", e.getMessage());
        }
    }

    // Returns the value at index (row, col), or null if out of bounds.
    public Integer Get(int row, int col) {
        Integer value;
        try {
            value = this.matrix.get(row).get(col);
            return value;
        } catch (IndexOutOfBoundsException e) {
            logger.error(" - Out of Bounds: {}", e.getMessage());
            return null;
        }
    }

    public List<Integer> getRow(int idx) {
        return this.matrix.get(idx);
    }

    public List<Integer> getCol(int idx) {
        List<Integer> col = new ArrayList<>();

        for (int i = 0; i < this.matrix.size(); i++) {
            col.add(this.matrix.get(i).get(idx));
        }

        return col;
    }

    @Override
    public String toString() {
        return "Matrix{" + matrix +
                '}';
    }
}
