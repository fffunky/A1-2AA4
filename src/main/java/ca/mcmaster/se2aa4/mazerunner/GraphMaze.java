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
            nodes.add(new WallNode(i));
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
            nodes.set(v.ID(), v);
        }

        if (!nodes.contains(w)) {
            nodes.set(w.ID(), w);
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

    public MazeNode getNodeFromPosition(Position p) {
        for (MazeNode node : nodes) {
            if (node.getPosition() != null) {
                if (node.getPosition().Equals(p)) {return node;}
            }
        }
        return null;
    }

    public Integer getIDFromPosition(Position p) {
        for (MazeNode node : nodes) {
            if (node.getPosition() != null) {
                if (node.getPosition().Equals(p)) {return node.ID();}
            }
        }
        return null;
    }

    public Position getPositionFromID(Integer id) {
        MazeNode node = this.getNodeFromId(id);
        return node.getPosition();
    }

    @Override
    public boolean isValidIndex(Integer row, Integer col) {
        return row >= 0 && row < this.height && col >= 0 && col < this.width;
    }

    @Override
    public Cell getCellAt(int row, int col) {
        Integer nID = (row * this.getHeight()) + col;
        if (this.getNodeFromId(nID) == null) {
            return new WallCell();
        } else {
            return this.getNodeFromId(nID).getCell();
        }
    }

    @Override
    public Cell getCellAt(Position p) {
        Integer row = p.Y();
        Integer col = p.X();
        Integer nID = (row * this.getHeight()) + col;
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

    // The heading returned gives the relative direction assuming the first parameter
    // is the origin. i.e., endNode is [heading output] relative to startNode
    public static Heading getRelativeHeading(MazeNode startNode, MazeNode endNode) {
        if (startNode.X() < endNode.X() && startNode.Y().equals(endNode.Y())) {
            return Heading.EAST;
        } else if (startNode.X() > endNode.X() && startNode.Y().equals(endNode.Y())) {
            return Heading.WEST;
        } else if (startNode.X().equals(endNode.X()) && startNode.Y() < endNode.Y()) {
            return Heading.SOUTH;
        } else if (startNode.X().equals(endNode.X()) && startNode.Y() > endNode.Y()) {
            return Heading.NORTH;
        } else {
            // same node or not at 90 degrees to each other
            return null;
        }
    }

    public String mazeString() {
        StringBuilder sb = new StringBuilder();

        int col = 0;
        for (MazeNode node : nodes) {
            sb.append(node.getCell().toString());
            sb.append(node.getCell().toString());
            sb.append(" ");
            col++;
            if (col == width) {
                sb.append("\n");
                col = 0;
            }
        }

        return sb.toString();
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
