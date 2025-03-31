package ca.mcmaster.se2aa4.mazerunner;

public interface MazeRunner {
    public void setCommand(Action a, Runnable r);
    public Integer getX();
    public Integer getY();
    public Position getPosition();
    public void setPosition(Position p);
    public Position getTarget();
    public boolean isValidSolution(Path p);
    public Path runPathfinder();
    public Maze getMaze();
    public Compass getCompass();
}
