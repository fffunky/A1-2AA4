package ca.mcmaster.se2aa4.mazerunner;

public class NullNode implements MazeNode {
    private final Integer ID;
    private final Cell cell = new NullCell();
    private final Position position;
    private final boolean isEntrance;

    public NullNode(Integer ID) {
        this.ID = ID;
        this.position = null;
        this.isEntrance = false;
    }

    public NullNode(Integer ID, Integer x, Integer y) {
        this.ID = ID;
        this.position = new Position(x, y);
        this.isEntrance = false;
    }

    public NullNode(Integer ID, Integer x, Integer y, Boolean isEntrance) {
        this.ID = ID;
        this.position = new Position(x, y);
        this.isEntrance = isEntrance;
    }

    @Override
    public Integer ID() {
        return this.ID;
    }

    @Override
    public Cell getCell() {
        return this.cell;
    }

    @Override
    public CellType getCellType() {
        return this.cell.getType();
    }

    @Override
    public boolean isCellEmpty() {
        return false;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Integer X() {
        return this.position == null ? null: this.position.X();
    }

    @Override
    public Integer Y() {
        return this.position == null ? null: this.position.Y();
    }

    @Override
    public Boolean isEntranceNode() {
        return false;
    }
}
