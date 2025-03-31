package ca.mcmaster.se2aa4.mazerunner;

public class TurnRightCommand implements Command {
    private MazeRunner runner;

    public TurnRightCommand(MazeRunner runner) {
        this.runner = runner;
    }

    @Override
    public void execute() {
        this.runner.getCompass().toRight();
    }
}
