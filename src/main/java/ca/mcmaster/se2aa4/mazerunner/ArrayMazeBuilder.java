package ca.mcmaster.se2aa4.mazerunner;

import java.util.Objects;

public class ArrayMazeBuilder implements MazeBuilder {

    private Integer width;
    private Integer height;
    private ArrayMatrix<Cell> matrix;

    public ArrayMazeBuilder(String mazeString) {
        this.height = this.getMazeHeight(mazeString);
        this.width = this.getMazeWidth(mazeString);
        this.matrix = (ArrayMatrix<Cell>) this.parseMazeString(mazeString);
    }

    @Override
    public Matrix<Cell> parseMazeString(String mazeString) {
        int row = 0, col = 0;

        Matrix<Cell> maze = new ArrayMatrix<Cell>(this.height, this.width);

        for (String line : mazeString.split("\n")) {
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
        return maze;
    }

    @Override
    public Integer getMazeWidth(String mazeString) {
        return mazeString.split("\n")[0].length();
    }

    @Override
    public Integer getMazeHeight(String mazeString) {
        return mazeString.split("\n").length;
    }

    @Override
    public Maze buildMaze() {
        return new ArrayMaze(this.matrix, this.width, this.height);
    }
}
