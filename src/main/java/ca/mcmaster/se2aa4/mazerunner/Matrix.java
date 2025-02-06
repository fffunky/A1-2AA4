package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Matrix<T> implements IMatrix<T> {
    private final int rows;
    private final int cols;
    private final List<List<T>> data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            List<T> row = new ArrayList<>();
            for (int c = 0; c < cols; c++) {
                row.add(null); // initialize matrix will null values
            }
            data.add(row);
        }
    }

    public void Set(int row, int col, T value) {
        if (isValidIndex(row, col)) {
            data.get(row).set(col, value);
        } else {
            throw new IndexOutOfBoundsException("Invalid row or column index");
        }
    }

    public T Get(int row, int col) {
        if (isValidIndex(row, col)) {
            return data.get(row).get(col);
        } else {
            throw new IndexOutOfBoundsException("Invalid row or column index");
        }
    }

    public List<T> getRow(int row) {
        if (row >= 0 && row < rows) {
            return new ArrayList<>(data.get(row));
        } else {
            throw new IndexOutOfBoundsException("Invalid row index");
        }
    }

    public List<T> getCol(int col) {
        if (col >= 0 && col < cols) {
            List<T> column = new ArrayList<>();
            for (List<T> row : data) {
                column.add(row.get(col));
            }
            return column;
        } else {
            throw new IndexOutOfBoundsException("Invalid column index");
        }
    }

    private boolean isValidIndex(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<T> row : data) {
            sb.append(row.toString()).append("\n");
        }
        return sb.toString();
    }
}
