package ca.mcmaster.se2aa4.mazerunner;

public interface Maze {
    public Cell getCellAt(int row, int col);
    public Position getStart();
    public Position getEnd();
    public Integer getWidth();
    public Integer getHeight();
}
