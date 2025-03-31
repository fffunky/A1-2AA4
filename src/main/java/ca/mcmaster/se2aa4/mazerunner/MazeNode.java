package ca.mcmaster.se2aa4.mazerunner;

public interface MazeNode {
    public Integer ID();
    public Cell getCell();
    public CellType getCellType();
    public boolean isCellEmpty();
    public Position getPosition();
    public Integer X();
    public Integer Y();
    public Boolean isEntranceNode();
}
