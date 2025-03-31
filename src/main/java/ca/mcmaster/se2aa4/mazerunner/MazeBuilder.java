package ca.mcmaster.se2aa4.mazerunner;

public interface MazeBuilder {
    public Matrix<Cell> parseMazeString(String mazeString);
    public Integer getMazeWidth(String mazeString);
    public Integer getMazeHeight(String mazeString);
    public Maze buildMaze();
}
