package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;
import java.util.Objects;

public class TestMaze implements Maze {
    private int width;
    private int height;
    private Position start;
    private Position end;
    private Matrix<Cell> maze;
    private static final Cell WALL = new TestCell(CellType.WALL);
    private static final Cell EMPTY = new TestCell(CellType.EMPTY);
    private static final Cell NULL_CELL = new TestCell(CellType.NULL);

    public TestMaze(String mazeString) {
        initMaze(mazeString);
    }

    private void initMaze(String mazeString) {
        int row = 0, col = 0;
        height = mazeString.split("\n").length;
        width = mazeString.split("\n")[0].length();

        maze = new ArrayMatrix<Cell>(height, width);

        for (String line : mazeString.split("\n")) {
            String[] s = line.split("");

            for (int i = 0; i < width; i++) {
                try {
                    if (Objects.equals(s[i], " ")) {
                        maze.Set(row, col, EMPTY);
                    } else if (Objects.equals(s[i], "#")) {
                        maze.Set(row, col, WALL);
                    } else {
                        maze.Set(row, col, EMPTY);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    maze.Set(row, col, EMPTY);
                }

                col++;
            }

            if (col == width) {
                col = 0;
                row++;
            }
        }
    }

    @Override
    public Cell getCellAt(int row, int col) {
        if (maze.Get(row, col) == null) {
            return NULL_CELL;
        }

        return maze.Get(row, col);
    }

    @Override
    public Cell getCellAt(Position p) {
        Integer row = p.X();
        Integer col = p.Y();
        if (maze.Get(row, col) == null) {
            return NULL_CELL;
        }

        return maze.Get(row, col);
    }

    @Override
    public Position getStart() {
        List<Cell> col = getMatrix().getCol(0);

        for (int row = 0; row < col.size(); row++) {
            if (col.get(row).isEmpty()) {
                return new Position(0, row);
            }
        }
        // shouldn't happen. means bad input.
        return null;
    }

    @Override
    public Position getEnd() {
        List<Cell> col = getMatrix().getCol(this.getWidth() - 1);

        for (int row = 0; row < col.size(); row++) {
            if (col.get(row).isEmpty()) {
                return new Position(this.getWidth() - 1, row);
            }
        }
        // shouldn't happen. means bad input.
        return null;
    }

    public Matrix<Cell> getMatrix() {
        return this.maze;
    }

    @Override
    public Integer getWidth() {
        return this.width;
    }

    @Override
    public Integer getHeight() {
        return this.height;
    }
}
