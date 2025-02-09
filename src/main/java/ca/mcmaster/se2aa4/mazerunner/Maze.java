package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;
import java.util.Objects;

public class Maze implements IMaze {
    private IMatrix<Cell> maze;
    private Integer width;
    private Integer height;

    public Maze(String fileInput) {
        initMaze(fileInput);
    }

    private void initMaze(String fileInput) {
        int row = 0, col = 0;
        height = fileInput.split("\n").length;
        width = fileInput.split("\n")[0].length();

        maze = new Matrix<Cell>(height, width);

        for (String line : fileInput.split("\n")) {
            String[] s = line.split("");

            for (int i = 0; i < width; i++) {
                try {
                    if (Objects.equals(s[i], " ")) {
                        maze.Set(row, col, new EmptyCell());
                    } else if (Objects.equals(s[i], "#")) {
                        maze.Set(row, col, new WallCell());
                    } else {
                        maze.Set(row, col, new EmptyCell());
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    maze.Set(row, col, new EmptyCell());
                }

                col++;
            }

            if (col == width) {
                col = 0;
                row++;
            }
        }
    }

    // returns the value of the maze at (row, col), or -1 if out of bounds.
    public Cell getCellAt(int row, int col) {
        if (maze.Get(row, col) == null) {
            return new NullCell();
        }

        return maze.Get(row, col);
    }

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

    public IMatrix<Cell> getMatrix() {
        return maze;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                sb.append(maze.Get(i,j).toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
