package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;
import java.util.Objects;

public class GraphMazeRunner implements MazeRunner {
    private final Maze maze;
    private Position position;
    private Position target;
    private final Path path = new Path();
    private final Compass compass = new Compass(Heading.EAST);

    public HashMap<Action, Runnable> cmdMap = new HashMap<>();

    public GraphMazeRunner(Maze maze) {
        this.maze = maze;
        this.position = maze.getStart();
        this.target = maze.getEnd();
    }

    public void setCommand(Action a, Runnable r) {
        cmdMap.put(a, r);
    }

    public void moveFwd() {
        cmdMap.get(Action.FORWARD).run();
    }

    public void leftTurn() {
        cmdMap.get(Action.LEFT).run();
    }

    public void rightTurn() {
        cmdMap.get(Action.RIGHT).run();
    }

    public void uTurn() {
        cmdMap.get(Action.UTURN).run();
    }

    @Override
    public Integer getX() {
        return this.position.X();
    }

    @Override
    public Integer getY() {
        return this.position.Y();
    }

    public void setPosition(Position p) {
            this.position = p;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Position getTarget() {
        return this.target;
    }

    public Maze getMaze() {
        return maze;
    }

    public Compass getCompass() {
        return compass;
    }

    @Override
    public boolean isValidSolution(Path p) {
        return false;
    }
}
