package ca.mcmaster.se2aa4.mazerunner;

public interface IMaze {
    public Cell getCellAt(int row, int col);
    public Position getStart();
    public Position getEnd();
    public IMatrix<Cell> getMatrix();
    public Integer getWidth();
    public Integer getHeight();
}
