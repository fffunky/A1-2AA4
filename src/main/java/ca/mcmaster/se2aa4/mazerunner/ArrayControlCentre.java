package ca.mcmaster.se2aa4.mazerunner;

public class ArrayControlCentre implements ControlCentre {

    private ArrayMazeRunner runner;
    private Maze maze;

    public ArrayControlCentre(Maze maze) {
        this.maze = maze;
        this.runner = new ArrayMazeRunner(maze);
        initCommands();
    }

    @Override
    public void initCommands() {
        runner.setCommand(Action.FORWARD, () -> {
            executeCommand(new MoveForwardCommand(this.runner));
        });

        runner.setCommand(Action.LEFT, () -> {
            executeCommand(new TurnLeftCommand(this.runner));
        });

        runner.setCommand(Action.RIGHT, () -> {
            executeCommand(new TurnRightCommand(this.runner));
        });


        runner.setCommand(Action.UTURN, () -> {
            executeCommand(new UTurnCommand(this.runner));
        });
    }

    public MazeRunner getRunner() {
        return runner;
    }

    @Override
    public void executeCommand(Command c) {
        c.execute();
    }

    @Override
    public Path runPathfinder() {
        boolean found = false;
        while (!found) {

            Cell ahead = runner.peekAhead();
            Cell right = runner.peekRight();
            Cell left = runner.peekLeft();


            assert right != null;
            assert left != null;
            assert ahead != null;
            if ((right.isEmpty() && ahead.isEmpty() && left.isEmpty()) ||
                    (right.isEmpty() && ahead.isEmpty() && !left.isEmpty()) ||
                    (right.isEmpty() && !ahead.isEmpty() && left.isEmpty()) ||
                    (right.isEmpty() && !ahead.isEmpty() && !left.isEmpty())) {
                runner.rightTurn();
                runner.moveFwd();
                runner.getPath().addInstruction("1R");
                runner.getPath().addInstruction("1F");
            } else if ((!right.isEmpty() && ahead.isEmpty() && left.isEmpty()) ||
                    (!right.isEmpty() && ahead.isEmpty() && !left.isEmpty())) {
                runner.moveFwd();
                runner.getPath().addInstruction("1F");
            } else if (left.isEmpty() && !ahead.isEmpty() && !right.isEmpty()) {
                runner.leftTurn();
                runner.getPath().addInstruction("1L");
                runner.getPath().addInstruction("1F");
            } else {
                runner.uTurn();
                runner.moveFwd();
                runner.getPath().addInstruction("2R");
                runner.getPath().addInstruction("1F");
            }

            if (runner.getX() == runner.getTarget().X() && runner.getY() == runner.getTarget().Y()) {
                found = true;
            }
        }

        return runner.getPath();
    }

    @Override
    public void followPath(Path p) {

    }

    @Override
    public boolean isValidSolution(Path p) {
        return false;
    }
}
