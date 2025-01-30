package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class MazeRunner {
    private static final Logger logger = LogManager.getLogger();

    public enum Heading {
        NORTH, EAST, SOUTH, WEST;

        public Heading toRight() {
            if (this == NORTH) {
                return EAST;
            } else if (this == EAST) {
                return SOUTH;
            } else if (this == SOUTH) {
                return WEST;
            } else {
                return NORTH;
            }
        }

        public Heading toLeft() {
            if (this == NORTH) {
                return WEST;
            } else if (this == EAST) {
                return NORTH;
            } else if (this == SOUTH) {
                return EAST;
            } else {
                return SOUTH;
            }
        }
    }

    private Maze maze;
    private Position position;
    private Position target;
    private Path path = new Path();
    private Heading heading = Heading.EAST;

    public MazeRunner(Maze maze) {
        this.maze = maze;
        this.position = maze.getStart();
        this.target = maze.getEnd();
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

    public Heading getHeading() {
        return this.heading;
    }

    public Path getPath() {
        return this.path;
    }

    public void followPath(Path p) {

        for (Instruction instruction : p.getInstructions()) {
            for (int i = 0; i < instruction.getFrequency(); i++) {
                switch (instruction.getDirection()) {
                    case "F":
                        moveForward();
                        break;
                    case "L":
                        turnLeft();
                        break;
                    case "R":
                        turnRight();
                        break;
                }
                path.addInstruction(instruction);
            }
        }
    }

    public Path runPathfinder() {
        boolean found = false;
        while (!found) {

            Cell ahead = this.peekAhead();
            Cell right = this.peekRight();
            Cell left = this.peekLeft();


            assert right != null;
            assert left != null;
            assert ahead != null;
            if ( (right.isEmpty() && ahead.isEmpty() && left.isEmpty()) ||
                    (right.isEmpty() && ahead.isEmpty() && !left.isEmpty() ) ||
                    (right.isEmpty() && !ahead.isEmpty() && left.isEmpty()) ||
                    (right.isEmpty() && !ahead.isEmpty() && !left.isEmpty()) ) {
                turnRight();
                moveForward();
                path.addInstruction("1R");
                path.addInstruction("1F");
            } else if ( (!right.isEmpty() && ahead.isEmpty() && left.isEmpty()) ||
                    (!right.isEmpty() && ahead.isEmpty() && !left.isEmpty())) {
                moveForward();
                path.addInstruction("1F");
            } else if (left.isEmpty() && !ahead.isEmpty() && !right.isEmpty()) {
                turnLeft();
                moveForward();
                path.addInstruction("1L");
                path.addInstruction("1F");
            } else {
                turnRight();
                turnRight();
                moveForward();
                path.addInstruction("2R");
                path.addInstruction("1F");
            }

            if (position.X() == target.X() && position.Y() == target.Y()) {
                found = true;
            }

        }

        return this.path;
    }

    // returns a 1 or 0 if there is a wall or space ahead, respectively,
    // or -1 if the space ahead is out of bounds.
    private Cell peekAhead() {
        if (this.heading == Heading.NORTH) {
            return this.peekNorth();
        } else if (this.heading == Heading.EAST) {
            return this.peekEast();
        } else if (this.heading == Heading.SOUTH) {
            return this.peekSouth();
        } else if (this.heading == Heading.WEST) {
            return this.peekWest();
        }
        return null;
    }

    private Cell peekRight() {
        if (this.heading == Heading.NORTH) {
            return this.peekEast();
        } else if (this.heading == Heading.EAST) {
            return this.peekSouth();
        } else if (this.heading == Heading.SOUTH) {
            return this.peekWest();
        } else if (this.heading == Heading.WEST) {
            return this.peekNorth();
        }
        return null;
    }

    private Cell peekLeft() {
        if (this.heading == Heading.NORTH) {
            return this.peekWest();
        } else if (this.heading == Heading.EAST) {
            return this.peekNorth();
        } else if (this.heading == Heading.SOUTH) {
            return this.peekEast();
        } else if (this.heading == Heading.WEST) {
            return this.peekSouth();
        }
        return null;
    }

    // returns the coordinate of the position ahead, or null if that position is out of bounds
    private Position getPositionAhead() {
        if (this.heading == Heading.NORTH) {
            return this.getNorthPosition();
        } else if (this.heading == Heading.EAST) {
            return this.getEastPosition();
        } else if (this.heading == Heading.SOUTH) {
            return this.getSouthPosition();
        } else if (this.heading == Heading.WEST) {
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
        this.heading = this.heading.toRight();
    }

    private void turnLeft() {
        this.heading = this.heading.toLeft();
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
        sb.append("Heading: " + this.heading.toString() + "\n");
        sb.append("Position: " + this.position.toString() + "\n");

        return sb.toString();
    }
}
