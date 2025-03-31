package ca.mcmaster.se2aa4.mazerunner;

public class TurnLeftCommand implements Command {
    private MazeRunner runner;

    public TurnLeftCommand(MazeRunner runner) {
        this.runner = runner;
    }

    @Override
    public void execute() {
        this.runner.getCompass().toLeft();
    }
}
