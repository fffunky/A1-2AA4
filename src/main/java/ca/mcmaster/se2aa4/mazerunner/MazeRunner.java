package ca.mcmaster.se2aa4.mazerunner;

public class MazeRunner {

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
    private Path path;
    private Heading heading = Heading.EAST;

    public MazeRunner(Maze maze) {
        this.maze = maze;
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

    public Heading getHeading() {
        return this.heading;
    }

    public Path getPath() {
        return this.path;
    }

    public Path runPathfinder() {
        return null;
    }

    private Integer peekAhead() {
        return null;
    }
}
