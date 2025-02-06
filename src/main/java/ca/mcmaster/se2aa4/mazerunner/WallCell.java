package ca.mcmaster.se2aa4.mazerunner;

public class WallCell extends Cell {

    public CellType getType() {
        return CellType.WALL;
    }

    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return "#";
    }
}
