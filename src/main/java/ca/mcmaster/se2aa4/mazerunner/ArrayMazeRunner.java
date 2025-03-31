package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Objects;

public class ArrayMazeRunner implements MazeRunner {
    private static final Logger logger = LogManager.getLogger();

    private final Maze maze;
    private Position position;
    private Position target;
    private final Path path = new Path();
    private final Compass compass = new Compass(Heading.EAST);

    public HashMap<Action, Runnable> cmdMap = new HashMap<>();

    public ArrayMazeRunner(Maze maze) {
        this.maze = maze;
        this.position = maze.getStart();
        this.target = maze.getEnd();

        this.cmdMap.put(Action.FORWARD, () -> {});
        this.cmdMap.put(Action.LEFT, () -> {});
        this.cmdMap.put(Action.RIGHT, () -> {});
        this.cmdMap.put(Action.UTURN, () -> {});
    }

    public void setCommand(Action a, Runnable r) {
        this.cmdMap.put(a, r);
    }

    public Integer getX() {
        return this.position.X();
    }

    public Integer getY() {
        return this.position.Y();
    }

    public Position getPosition() {
        return this.position;
    }

    public Position getTarget() {
        return this.target;
    }

    public void setPosition(Position p) {
        if (!Objects.equals(maze.getCellAt(p.X(), p.Y()), new NullCell())) {
            this.position = p;
        }
    }

    public void setTarget(Position t) {
        if (!Objects.equals(maze.getCellAt(t.X(), t.Y()), new NullCell())) {
            this.target = t;
        }
    }

    public Heading getHeading() {
        return compass.getHeading();
    }

    public Compass getCompass() {
        return compass;
    }

    public Path getPath() {
        return this.path;
    }

    public Maze getMaze() {
        return this.maze;
    }

    public boolean isValidSolution(Path p) {
        Position startPos = position.Copy();

        followPath(p);
        if ( position.Equals(target) ) {
            return true;
        }


        // switch exit and entry and try following the path again.
        Position newPos = target.Copy();
        setPosition(newPos);
        setTarget(startPos);

        compass.setHeading(Heading.WEST);

        followPath(p);

        return position.Equals(target);
    }

    private void followPath(Path p) {

        for (Instruction instruction : p.getInstructions()) {
            for (int i = 0; i < instruction.getFrequency(); i++) {
                switch (instruction.getDirection()) {
                    case "F":
                        moveFwd();
                        break;
                    case "L":
                        leftTurn();
                        break;
                    case "R":
                        rightTurn();
                        break;
                }
                path.addInstruction(instruction);
            }
        }
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

    // returns a 1 or 0 if there is a wall or space ahead, respectively,
    // or -1 if the space ahead is out of bounds.
    public Cell peekAhead() {
        if (compass.getHeading() == Heading.NORTH) {
            return this.peekNorth();
        } else if (compass.getHeading() == Heading.EAST) {
            return this.peekEast();
        } else if (compass.getHeading() == Heading.SOUTH) {
            return this.peekSouth();
        } else if (compass.getHeading() == Heading.WEST) {
            return this.peekWest();
        }
        return null;
    }

    public Cell peekRight() {
        if (compass.getHeading() == Heading.NORTH) {
            return this.peekEast();
        } else if (compass.getHeading() == Heading.EAST) {
            return this.peekSouth();
        } else if (compass.getHeading() == Heading.SOUTH) {
            return this.peekWest();
        } else if (compass.getHeading() == Heading.WEST) {
            return this.peekNorth();
        }
        return null;
    }

    public Cell peekLeft() {
        if (compass.getHeading() == Heading.NORTH) {
            return this.peekWest();
        } else if (compass.getHeading() == Heading.EAST) {
            return this.peekNorth();
        } else if (compass.getHeading() == Heading.SOUTH) {
            return this.peekEast();
        } else if (compass.getHeading() == Heading.WEST) {
            return this.peekSouth();
        }
        return null;
    }

    // returns the coordinate of the position ahead, or null if that position is out of bounds
    private Position getPositionAhead() {
        if (compass.getHeading() == Heading.NORTH) {
            return this.getNorthPosition();
        } else if (compass.getHeading() == Heading.EAST) {
            return this.getEastPosition();
        } else if (compass.getHeading() == Heading.SOUTH) {
            return this.getSouthPosition();
        } else if (compass.getHeading() == Heading.WEST) {
            return this.getWestPosition();
        }
        return null;
    }

    private Cell peekNorth() {
        try {
            return this.maze.getCellAt(this.position.Y()-1, this.position.X());
        } catch (Exception e) {
            return new NullCell();
        }
    }

    private Cell peekEast() {
        try {
            return this.maze.getCellAt(this.position.Y(), this.position.X()+1);
        } catch (Exception e){
            return new NullCell();
        }

    }

    private Cell peekSouth() {
        try {
            return this.maze.getCellAt(this.position.Y()+1, this.position.X());
        } catch (Exception e){
            return new NullCell();
        }
    }
    private Cell peekWest() {
        try {
            return this.maze.getCellAt(this.position.Y(), this.position.X()-1);
        } catch (Exception ignored){
            return new NullCell();
        }

    }

    private Position getNorthPosition() {
        if (this.peekNorth() == null) {
            return null;
        } else {
            return new Position(this.position.X(), this.position.Y() - 1);
        }
    }

    private Position getEastPosition() {
        if (this.peekEast() == null) {
            return null;
        } else {
            return new Position(this.position.X() + 1, this.position.Y());
        }
    }

    private Position getSouthPosition() {
        if (this.peekSouth() == null) {
            return null;
        } else {
            return new Position(this.position.X(), this.position.Y() + 1);
        }
    }

    private Position getWestPosition() {
        if (this.peekWest() == null) {
            return null;
        } else {
            return new Position(this.position.X() - 1, this.position.Y());
        }
    }

    private void moveForward() {
        Cell cellAhead = this.peekAhead();

        assert cellAhead != null;
        if (cellAhead.isEmpty()) {
            // if the space is empty then we move forward
            this.position = getPositionAhead();
        }
        // otherwise the position doesn't change
    }

    private void turnRight() {
        compass.toRight();
    }

    private void turnLeft() {
        compass.toLeft();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (maze.getCellAt(i, j).isEmpty()) {
                    if (position.X() == j && position.Y() == i) {
                        sb.append("<>");
                    } else {
                        sb.append("  ");
                    }
                } else {
                    sb.append("##");
                }
            }
            sb.append("\n");
        }
        sb.append("Heading: " + compass.getHeading().toString() + "\n");
        sb.append("Position: " + this.position.toString() + "\n");

        return sb.toString();
    }
}
