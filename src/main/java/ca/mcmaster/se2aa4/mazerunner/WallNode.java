package ca.mcmaster.se2aa4.mazerunner;

public class WallNode implements MazeNode {

    private final Integer ID;
    private final Cell cell = new WallCell();
    private final Position position;
    private final boolean isEntrance;

    public WallNode(Integer ID, Integer x, Integer y) {
        this.ID = ID;
        this.position = new Position(x, y);
        this.isEntrance = false;
    }

    public WallNode(Integer ID, Integer x, Integer y, Boolean isEntrance) {
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
        return this.cell.isEmpty();
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Integer X() {
        return this.position.X();
    }

    @Override
    public Integer Y() {
        return this.position.Y();
    }

    @Override
    public Boolean isEntranceNode() {
        return this.isEntrance;
    }

    @Override
    public String toString() {
        return this.ID().toString();
    }
}
