package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public interface Matrix<T> {
    public T Get(int row, int col);
    public void Set(int row, int col, T value);
    public List<T> getRow(int row);
    public List<T> getCol(int col);
}
