package ca.mcmaster.se2aa4.mazerunner;

public class TestCell extends Cell {
    private final CellType type;

    public TestCell(CellType type) {
        this.type = type;
    }

    @Override
    CellType getType() {
        return this.type;
    }

    @Override
    boolean isEmpty() {
        return this.type.equals(CellType.EMPTY);
    }
}
