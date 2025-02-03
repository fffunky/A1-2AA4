package ca.mcmaster.se2aa4.mazerunner;

interface IMazeRunner {
    public Integer getX();
    public Integer getY();
    public Position getPosition();
    public Position getTarget();
    public void followPath(Path p);
    public Path runPathfinder();
}
