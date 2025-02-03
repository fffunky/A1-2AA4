package ca.mcmaster.se2aa4.mazerunner;

public class Compass {
    private Heading heading;

    Compass(Heading heading) {
        this.heading = heading;
    }

    public Heading getHeading() {
        return heading;
    }

    public void setHeading(Heading heading) {
        this.heading = heading;
    }

    public void toRight() {
        if (heading == Heading.NORTH) {
            setHeading(Heading.EAST);
        } else if (heading == Heading.EAST) {
            setHeading(Heading.SOUTH);
        } else if (heading == Heading.SOUTH) {
            setHeading(Heading.WEST);
        } else {
            setHeading(Heading.NORTH);
        }
    }

    public void toLeft() {
        if (heading == Heading.NORTH) {
            setHeading(Heading.WEST);
        } else if (heading == Heading.EAST) {
            setHeading(Heading.NORTH);
        } else if (heading == Heading.SOUTH) {
            setHeading(Heading.EAST);
        } else {
            setHeading(Heading.SOUTH);
        }
    }

    @Override
    public String toString() {
        return heading.toString();
    }
}
