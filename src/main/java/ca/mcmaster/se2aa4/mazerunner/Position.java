package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

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

    public List<Integer> Coords() {
        List<Integer> coords = new ArrayList<>();
        coords.add(this.x);
        coords.add(this.y);
        return coords;
    }
}
