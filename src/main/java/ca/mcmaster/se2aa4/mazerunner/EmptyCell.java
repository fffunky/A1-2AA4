package ca.mcmaster.se2aa4.mazerunner;

public class EmptyCell implements Cell {

    public CellType getType() {
        return CellType.EMPTY;
    }

    public boolean isEmpty() {
        return true;
    }

    @Override
    public String toString() {
        return " ";
    }

}
