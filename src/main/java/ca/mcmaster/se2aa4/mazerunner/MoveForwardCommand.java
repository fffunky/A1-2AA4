package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MoveForwardCommand implements Command {

    private static final Logger log = LogManager.getLogger(MoveForwardCommand.class);
    private MazeRunner runner;

    public MoveForwardCommand(MazeRunner runner) {
        this.runner = runner;
    }

    private Position positionAhead() {
        return switch (runner.getCompass().getHeading()) {
            case NORTH -> runner.getPosition().toNorth();
            case EAST -> runner.getPosition().toEast();
            case SOUTH -> runner.getPosition().toSouth();
            case WEST -> runner.getPosition().toWest();
        };
    }

    @Override
    public void execute() {
        Position posAhead = positionAhead();
        runner.setPosition(posAhead);
    }
}
