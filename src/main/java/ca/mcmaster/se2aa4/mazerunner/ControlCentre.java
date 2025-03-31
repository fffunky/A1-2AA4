package ca.mcmaster.se2aa4.mazerunner;

public interface ControlCentre {
    public void initCommands();
    public void executeCommand(Command c);
    public Path runPathfinder();
    public void followPath(Path p);
    public boolean isValidSolution(Path p);
}
