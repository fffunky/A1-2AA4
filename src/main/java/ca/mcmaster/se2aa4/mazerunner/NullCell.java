package ca.mcmaster.se2aa4.mazerunner;

public class NullCell implements Cell {

    public CellType getType() {
        return CellType.NULL;
    }

    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return "NULL";
    }

}
