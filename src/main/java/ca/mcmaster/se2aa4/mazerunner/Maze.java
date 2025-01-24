package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;
import java.util.Objects;

public class Maze {
    private Matrix maze;
    private Integer width;
    private Integer height;

    public Maze(String fileInput) {
        initMaze(fileInput);
    }

    private void initMaze(String fileInput) {
        int row = 0, col = 0;
        this.height = fileInput.split("\n").length;
        this.width = fileInput.split("\n")[0].length();
        this.maze = new Matrix(width, height);

        for (String line : fileInput.split("\n")) {

            String[] s = line.split("");

            for (int i = 0; i < this.width; i++) {

                try {
                    if (Objects.equals(s[i], " ")) {
                        maze.Set(row, col, 0);
                    } else if (Objects.equals(s[i], "#")) {
                        maze.Set(row, col, 1);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    maze.Set(row, col, 0);
                }

                col++;
            }


            if (col == this.width) {
                col = 0;
                row++;
            }
        }

    }

    // returns the value of the maze at (row, col), or -1 if out of bounds.
    public Integer getCellAt(int row, int col) {
        if (this.maze.Get(row, col) == null) {
            return -1;
        }

        return this.maze.Get(row, col);
    }

    public Position getStart() {
        List<Integer> col = this.getMatrix().getCol(0);

        for (int row = 0; row < col.size(); row++) {
            if (col.get(row) == 0) {
                return new Position(0, row);
            }
        }
        // shouldn't happen. means bad input.
        return null;
    }

    public Position getEnd() {
        List<Integer> col = this.getMatrix().getCol(this.getWidth() - 1);

        for (int row = 0; row < col.size(); row++) {
            if (col.get(row) == 0) {
                return new Position(this.getWidth() - 1, row);
            }
        }
        // shouldn't happen. means bad input.
        return null;
    }

    public Matrix getMatrix() {
        return this.maze;
    }

    public Integer getWidth() {
        return this.width;
    }

    public Integer getHeight() {
        return this.height;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                if (maze.Get(i, j) == 0) {
                    sb.append("  ");
                } else {
                    sb.append("##");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
