package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    public Integer x;
    public Integer y;

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer X() {
        return this.x;
    }

    public Integer Y() {
        return this.y;
    }

    public Position Copy() {
        return new Position(this.x, this.y);
    }

    public boolean Equals(Position p) {
        return this.X() == p.X() && this.Y() == p.Y();
    }

    public List<Integer> Coords() {
        List<Integer> coords = new ArrayList<>();
        coords.add(this.x);
        coords.add(this.y);
        return coords;
    }

    public Position toNorth() {
        return new Position(this.x, this.y - 1);
    }

    public Position toEast() {
        return new Position(this.x + 1, this.y);
    }

    public Position toSouth() {
        return new Position(this.x, this.y + 1);
    }

    public Position toWest() {
        return new Position(this.x - 1, this.y);
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
