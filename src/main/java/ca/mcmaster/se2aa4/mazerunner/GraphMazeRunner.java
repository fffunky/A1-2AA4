package ca.mcmaster.se2aa4.mazerunner;

import java.util.Objects;

public class GraphMazeRunner implements MazeRunner {
    private final Maze maze;
    private Position position;
    private Position target;
    private final Path path = new Path();
    private final Compass compass = new Compass(Heading.EAST);

    public GraphMazeRunner(Maze maze) {
        this.maze = maze;
        this.position = maze.getStart();
        this.target = maze.getEnd();
    }

    public void setCommand(Action a, Runnable r) {

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
