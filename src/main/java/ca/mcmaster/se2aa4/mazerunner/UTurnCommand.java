package ca.mcmaster.se2aa4.mazerunner;

public class UTurnCommand implements Command {
    private MazeRunner runner;

    public UTurnCommand(MazeRunner runner) {
        this.runner = runner;
    }

    @Override
    public void execute() {
        this.runner.getCompass().toLeft();
        this.runner.getCompass().toLeft();
    }
}
