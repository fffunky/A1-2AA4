package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class GraphMaze implements Maze {

    private Integer nodeCount;
    private Integer edgeCount;
    private List<List<MazeNode>> adjList;
    private List<MazeNode> nodes = new ArrayList<>();
    private Integer width;
    private Integer height;

    public GraphMaze(Integer nodeCount) {
        this.nodeCount = nodeCount;
        this.edgeCount = 0;

        this.adjList = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public GraphMaze(Integer nodeCount, List<List<MazeNode>> edges) {
        this(nodeCount);
        for (List<MazeNode> edge : edges) {
            this.addEdge(edge.get(0), edge.get(1));

        }
    }

    public void addEdge(MazeNode v, MazeNode w) {
        adjList.get(v.ID()).add(w);
        adjList.get(w.ID()).add(v);

        if (!nodes.contains(v)) {
            nodes.add(v);
        }

        if (!nodes.contains(w)) {
            nodes.add(w);
        }

        edgeCount++;
    }

    public List<MazeNode> Adjacents(MazeNode v) {
        return this.adjList.get(v.ID());
    }

    public Integer getNodeCount() {
        return this.nodeCount;
    }

    public Integer getEdgeCount() {
        return this.edgeCount;
    }

    public MazeNode getNodeFromId(Integer id) {
        for (MazeNode node : nodes) {
            if (node.ID().equals(id)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public Cell getCellAt(int row, int col) {
        Integer nID = (row * this.getWidth()) + col;
        if (this.getNodeFromId(nID) == null) {
            return new WallCell();
        } else {
            return this.getNodeFromId(nID).getCell();
        }
    }

    @Override
    public Position getStart() {
        List<MazeNode> entrances = new ArrayList<>();
        for (MazeNode node : nodes) {
            if (node.isEntranceNode()) {
                entrances.add(node);
            }
        }

        for (MazeNode node : entrances) {
            if (node.X() == 0) {
                return node.getPosition();
            }
        }

        return null;
    }

    @Override
    public Position getEnd() {
        List<MazeNode> entrances = new ArrayList<>();
        for (MazeNode node : nodes) {
            if (node.isEntranceNode()) {
                entrances.add(node);
            }
        }

        for (MazeNode node : entrances) {
            if (node.X() == this.width - 1) {
                return node.getPosition();
            }
        }

        return null;

    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public Integer getWidth() {
        return this.width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public Integer getHeight() {
        return this.height;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int i = 0;
        for (List<MazeNode> edge : adjList) {
            sb.append(i).append(": ").append(edge.toString());
            if (this.getNodeFromId(i) != null) {
                if (this.getNodeFromId(i).isEntranceNode()) {
                    sb.append(" (Entrance)");
                }
            }
            sb.append("\n");
            i++;
        }
        sb.append(edgeCount).append("\n");

        return sb.toString();
    }
}
