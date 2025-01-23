package ca.mcmaster.se2aa4.mazerunner;

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
            for (String ch : line.split("")) {
                if (Objects.equals(ch, " ")) {
                    maze.Set(row, col, 0);
                } else if (Objects.equals(ch, "#")) {
                    maze.Set(row, col, 1);
                }
                col++;
            }
            if (col == this.width) {
                col = 0;
                row++;
            }
        }
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
                    sb.append(" ");
                } else {
                    sb.append("#");
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
