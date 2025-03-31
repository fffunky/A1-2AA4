package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GraphMazeBuilder implements MazeBuilder {

    private ArrayMatrix<Cell> matrixRepresentation;
    private List<MazeNode> nodes = new ArrayList<>();
    private Integer width;
    private Integer height;

    public GraphMazeBuilder(String mazeString) {
        this.width = this.getMazeWidth(mazeString);
        this.height = this.getMazeHeight(mazeString);
        this.matrixRepresentation = (ArrayMatrix<Cell>) parseMazeString(mazeString);
        this.nodes = this.buildNodes();
    }

    @Override
    public Matrix<Cell> parseMazeString(String mazeString) {
        int row = 0, col = 0;

        Matrix<Cell> maze = new ArrayMatrix<Cell>(this.height, this.width);

        for (String line : mazeString.split("\n")) {
            String[] s = line.split("");

            for (int i = 0; i < width; i++) {
                try {
                    if (Objects.equals(s[i], " ")) {
                        maze.Set(row, col, new EmptyCell());
                    } else if (Objects.equals(s[i], "#")) {
                        maze.Set(row, col, new WallCell());
                    } else {
                        maze.Set(row, col, new EmptyCell());
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    maze.Set(row, col, new EmptyCell());
                }

                col++;
            }

            if (col == width) {
                col = 0;
                row++;
            }
        }
        return maze;
    }

    public List<MazeNode> buildNodes() {
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                Cell cur = this.matrixRepresentation.getRow(row).get(col);
                if (cur instanceof WallCell) {
                    nodes.add(new WallNode(((row * this.width) + col), col, row));
                } if (cur instanceof EmptyCell) {
                    if (col == this.width - 1 || col == 0) {
                        nodes.add(new EmptyNode((row * this.width) + col, col, row, true));
                    } else {
                        nodes.add(new EmptyNode((row * this.width) + col, row, col, false));
                    }
                }
            }
        }
        return nodes;
    }

    @Override
    public Integer getMazeWidth(String mazeString) {
        return mazeString.split("\n")[0].length();
    }

    @Override
    public Integer getMazeHeight(String mazeString) {
        return mazeString.split("\n").length;
    }

    public List<MazeNode> getAdjacentNodes(MazeNode node) {
        List<MazeNode> adj = new ArrayList<>();

        if (node instanceof EmptyNode) {
            Integer nx = node.X();
            Integer ny = node.Y();

            if (matrixRepresentation.isValidIndex(nx + 1, ny)) {
                Cell eastNode = matrixRepresentation.Get(nx + 1, ny);
                int nodeID = ((nx + 1) * this.width) + ny;
                if (eastNode instanceof EmptyCell) {
                    adj.add(this.nodes.get(nodeID));
                }
            }

            if (matrixRepresentation.isValidIndex(nx - 1, ny)) {
                Cell westNode = matrixRepresentation.Get(nx - 1, ny);
                int nodeID = ((nx - 1) * this.width) + ny;
                if (westNode instanceof EmptyCell) {
                    adj.add(this.nodes.get(nodeID));
                }
            }

            if (matrixRepresentation.isValidIndex(nx, ny + 1)) {
                Cell southNode = matrixRepresentation.Get(nx, ny + 1);
                int nodeID = (nx * this.width) + (ny + 1);
                if (southNode instanceof EmptyCell) {
                    adj.add(this.nodes.get(nodeID));
                }
            }

            if (matrixRepresentation.isValidIndex(nx, ny - 1)) {
                Cell northNode = matrixRepresentation.Get(nx, ny-1);
                int nodeID = (nx * this.width) + (ny - 1);
                if (northNode instanceof EmptyCell) {
                    adj.add(this.nodes.get(nodeID));
                }
            }
        }

        return adj;
    }

    @Override
    public Maze buildMaze() {
        Integer nodeCount = this.nodes.size();
        GraphMaze gm = new GraphMaze(nodeCount);

        for (MazeNode node : this.nodes) {
            if (node instanceof EmptyNode) {
                List<MazeNode> adj = this.getAdjacentNodes(node);
                for (MazeNode adjNode: adj) {
                    if (!gm.Adjacents(node).contains(adjNode)) {
                        gm.addEdge(node, adjNode);
                    }
                }
            }
        }

        gm.setWidth(this.width);
        gm.setHeight(this.height);

        return gm;
    }
}
