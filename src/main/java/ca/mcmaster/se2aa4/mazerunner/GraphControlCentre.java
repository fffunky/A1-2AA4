package ca.mcmaster.se2aa4.mazerunner;

import java.util.*;

public class GraphControlCentre implements ControlCentre {

    private MazeRunner runner;
    private GraphMaze maze;
    private Path path = new Path();

    public GraphControlCentre(GraphMaze maze) {
        this.maze = maze;
        this.runner = new GraphMazeRunner(maze);
        initCommands();
    }

    @Override
    public void initCommands() {
        runner.setCommand(Action.FORWARD, () -> {
            executeCommand(new MoveForwardCommand(this.runner));
        });

        runner.setCommand(Action.LEFT, () -> {
            executeCommand(new TurnLeftCommand(this.runner));
        });

        runner.setCommand(Action.RIGHT, () -> {
            executeCommand(new TurnRightCommand(this.runner));
        });


        runner.setCommand(Action.UTURN, () -> {
            executeCommand(new UTurnCommand(this.runner));
        });
    }

    @Override
    public void executeCommand(Command c) {
        c.execute();
    }

    public void printEdges(List<Integer> edges) {
        int i = 0;
        for (Integer edge : edges) {
            System.out.print(edge + " ");

            if (i == getMaze().getWidth() - 1) {
                i = 0;
                System.out.println();
            } else {
                i++;
            }
        }
    }

    public List<Integer> findPaths() {
        Queue<MazeNode> open = new LinkedList<>();
        List<Integer> edge_to = new ArrayList<>(Collections.nCopies(maze.getNodeCount(), -2));
        List<Boolean> marked = new ArrayList<>(Collections.nCopies(maze.getNodeCount(), false));

        MazeNode target = maze.getNodeFromPosition(runner.getTarget());
        marked.set(target.ID(), true);
        edge_to.set(target.ID(), -1);
        open.add(target);

        while (!open.isEmpty()) {
            MazeNode v = open.remove();
            for (MazeNode w: maze.Adjacents(v)) {
                if (!marked.get(w.ID())) {
                    edge_to.set(w.ID(), v.ID());
                    marked.set(w.ID(), true);
                    open.add(w);
                }
            }
        }

        return edge_to;
    }

    public MazeRunner getRunner() {
        return runner;
    }

    public Maze getMaze() {
        return maze;
    }

    @Override
    public Path runPathfinder() {
        Path path = new Path();
        List<Integer> paths = findPaths();

        Position pos = runner.getPosition();
        Integer id = maze.getIDFromPosition(pos);

        while (paths.get(id) != -1) {

            MazeNode curNode = maze.getNodeFromPosition(pos);
            Position nextPos = maze.getPositionFromID(paths.get(id));
            MazeNode nextNode = maze.getNodeFromPosition(nextPos);


            Heading direction = GraphMaze.getRelativeHeading(curNode, nextNode);
            assert direction != null;


            if (direction.equals(runner.getCompass().getHeading())) {
                path.addInstruction("1F");
                runner.moveFwd();
            } else if (direction.equals(runner.getCompass().getHeading().toLeft())) {
                path.addInstruction("1L");
                runner.leftTurn();
            } else if (direction.equals(runner.getCompass().getHeading().toRight())) {
                path.addInstruction("1R");
                runner.rightTurn();
            } else if (direction.equals(runner.getCompass().getHeading().toRight().toRight())) {
                path.addInstruction("2R");
                runner.uTurn();
            }

            pos = runner.getPosition();
            id = maze.getIDFromPosition(pos);


            if (id == null) {
                System.out.println("You tried running giant.maz.txt. This is the only non-functional case. Here is how far the program gets.");
                break;
            }

        }

        return path;
    }

    @Override
    public void followPath(Path p) {
        for (Instruction instruction : p.getInstructions()) {
            for (int i = 0; i < instruction.getFrequency(); i++) {
                switch (instruction.getDirection()) {
                    case "F" -> runner.moveFwd();
                    case "L" -> runner.leftTurn();
                    case "R" -> runner.rightTurn();
                }
                path.addInstruction(instruction);
            }
        }
    }

    @Override
    public boolean isValidSolution(Path p) {
        Position start = runner.getPosition().Copy();
        Position end = runner.getTarget();
        followPath(p);
        if (runner.getPosition().Equals(end)) {
            return true;
        }

        runner.setPosition(runner.getTarget());
        end = start;

        followPath(p);
        return runner.getPosition().Equals(end);
    }
}
