package maze;

import java.util.ArrayList;

public class Run {
    public static void main(String[] args) {
        String test1 = "..F7.";
        String test2 = ".FJ|.";
        String test3 = "SJ.L7";
        String test4 = "|F--J";
        String test5 = "LJ...";

        String[] testXAxis = { test1, test2, test3, test4, test5 };

        Maze maze = new Maze(testXAxis);
        MazeRunner runner = new MazeRunner(maze.getStartPoint());

        // Make first move
        ArrayList<Direction> possibleMoves = maze.findFirstMoves(runner.getCurrent());
        runner.move(possibleMoves.getFirst());
        int moves = 1;

        // Run the maze
        while (!runner.getCurrent().equals(maze.getStartPoint())) {
            possibleMoves = maze.findAvailableMoves(runner.getCurrent());

            for (Direction direction : possibleMoves) {
                MazeRunner testRun = new MazeRunner(runner.getCurrent());
                testRun.move(direction);
                if (!testRun.getCurrent().equals(runner.getFrom())) {
                    runner.move(direction);
                    moves++;
                    System.out.println("Runner moved " + direction);
                    break;
                }
            }
        }

        System.out.println(runner.getCurrent());
        System.out.println(moves);

        // Brute force rerun maze in reverse
        possibleMoves = maze.findFirstMoves(runner.getCurrent());
        runner.move(possibleMoves.getLast());
        int halfRun = moves / 2;
        moves = 1;

        for (int i = 1; i < halfRun; i++) {
            possibleMoves = maze.findAvailableMoves(runner.getCurrent());

            for (Direction direction : possibleMoves) {
                MazeRunner testRun = new MazeRunner(runner.getCurrent());
                testRun.move(direction);
                if (!testRun.getCurrent().equals(runner.getFrom())) {
                    runner.move(direction);
                    moves++;
                    System.out.println("Runner moved " + direction);
                    break;
                }
            }
        }

        System.out.println(runner.getCurrent());
        System.out.println(moves);
    }
}
