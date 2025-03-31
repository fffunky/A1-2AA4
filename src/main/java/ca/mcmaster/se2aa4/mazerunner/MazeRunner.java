package ca.mcmaster.se2aa4.mazerunner;

interface MazeRunner {
    public Integer getX();
    public Integer getY();
    public Position getPosition();
    public Position getTarget();
    public boolean isValidSolution(Path p);
    public Path runPathfinder();
}
