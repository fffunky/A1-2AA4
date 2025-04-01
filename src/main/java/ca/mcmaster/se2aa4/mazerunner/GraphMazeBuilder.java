package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Arrays;
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

        Matrix<Cell> maze = new ArrayMatrix<>(this.height, this.width);

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
            }
            row++;
        }

        return maze;
    }

    public List<MazeNode> buildNodes() {
        Integer id = 0;
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                Cell cur = this.matrixRepresentation.Get(row, col);

                if (cur instanceof WallCell) {
                    nodes.add(new WallNode(id));
                } if (cur instanceof EmptyCell) {
                    if (col == this.width - 1 || col == 0) {
                        nodes.add(new EmptyNode(id, col, row, true));
                    } else {
                        nodes.add(new EmptyNode(id, col, row, false));
                    }
                }
                id++;
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

            if ((ny + 1) < this.height) {
                Cell southNode = matrixRepresentation.Get(ny + 1, nx);
                int nodeID = node.ID() + this.width;
                if (southNode instanceof EmptyCell) {
                    adj.add(this.nodes.get(nodeID));
                }
            }

            if ((ny - 1) >= 0) {
                Cell northNode = matrixRepresentation.Get(ny - 1, nx);
                int nodeID = node.ID() - this.width;
                if (northNode instanceof EmptyCell) {
                    adj.add(this.nodes.get(nodeID));
                }
            }

            if ((nx + 1) < this.width) {
                Cell eastNode = matrixRepresentation.Get(ny, nx + 1);
                int nodeID = node.ID() + 1;
                if (eastNode instanceof EmptyCell) {
                    adj.add(this.nodes.get(nodeID));
                }
            }

            if ((nx - 1) >= 0) {
                Cell westNode = matrixRepresentation.Get(ny, nx-1);
                int nodeID = node.ID() - 1;
                if (westNode instanceof EmptyCell) {
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
