package maze;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import data.FileReader;

public class Run {
    public static void main(String[] args) throws FileNotFoundException {
        // String test1 = "..F7.";
        // String test2 = ".FJ|.";
        // String test3 = "SJ.L7";
        // String test4 = "|F--J";
        // String test5 = "LJ...";

        // String test1 = "...........";
        // String test2 = ".S-------7.";
        // String test3 = ".|F-----7|.";
        // String test4 = ".||.....||.";
        // String test5 = ".||.....||.";
        // String test6 = ".|L-7.F-J|.";
        // String test7 = ".|..|.|..|.";
        // String test8 = ".L--J.L--J.";
        // String test9 = "...........";

        // String[] testXAxis = { test1, test2, test3, test4, test5, test6, test7,
        // test8, test9 };

        FileReader reader = new FileReader("inputs/maze.txt");
        ArrayList<String> lines = reader.getLines();

        String[] xAxis = new String[lines.size()];
        for (int i = 0; i < xAxis.length; i++) {
            xAxis[i] = lines.get(i);
        }

        Maze maze = new Maze(xAxis);
        MazeRunner runner = new MazeRunner(maze.getStartPoint());
        ArrayList<Direction> madeMoves = new ArrayList<>();

        // Part Two
        // Make first move
        ArrayList<Direction> possibleMoves = maze.findFirstMoves(runner.getCurrent());
        runner.move(possibleMoves.getLast());
        madeMoves.add(possibleMoves.getLast());

        // Run the maze
        while (!runner.getCurrent().equals(maze.getStartPoint())) {
            possibleMoves = maze.findAvailableMoves(runner.getCurrent());

            for (Direction direction : possibleMoves) {
                MazeRunner testRun = new MazeRunner(runner.getCurrent());
                testRun.move(direction);
                if (!testRun.getCurrent().equals(runner.getFrom())) {
                    runner.move(direction);
                    madeMoves.add(direction);
                    maze.markSpot(runner.getFrom(), direction);
                    break;
                }
            }
        }

        // Rerun maze based on listed directions (Clockwise)
        for (Direction direction : madeMoves) {
            runner.move(direction);
            maze.paintMap(runner.getCurrent(), direction);
        }

        maze.clearClutter();
        maze.printMaze();
        // System.out.println(maze.countInside());

        // Part One

        // // Make first move
        // ArrayList<Direction> possibleMoves =
        // maze.findFirstMoves(runner.getCurrent());
        // runner.move(possibleMoves.getFirst());
        // int moves = 1;

        // // Run the maze
        // while (!runner.getCurrent().equals(maze.getStartPoint())) {
        // possibleMoves = maze.findAvailableMoves(runner.getCurrent());

        // for (Direction direction : possibleMoves) {
        // MazeRunner testRun = new MazeRunner(runner.getCurrent());
        // testRun.move(direction);
        // if (!testRun.getCurrent().equals(runner.getFrom())) {
        // runner.move(direction);
        // moves++;
        // break;
        // }
        // }
        // }

        // System.out.println(runner.getCurrent());
        // System.out.println(moves);

        // // Brute force rerun maze in reverse
        // possibleMoves = maze.findFirstMoves(runner.getCurrent());
        // runner.move(possibleMoves.getLast());
        // int halfRun = moves / 2;
        // moves = 1;

        // for (int i = 1; i < halfRun; i++) {
        // possibleMoves = maze.findAvailableMoves(runner.getCurrent());

        // for (Direction direction : possibleMoves) {
        // MazeRunner testRun = new MazeRunner(runner.getCurrent());
        // testRun.move(direction);
        // if (!testRun.getCurrent().equals(runner.getFrom())) {
        // runner.move(direction);
        // moves++;
        // break;
        // }
        // }
        // }

        // System.out.println(runner.getCurrent());
        // System.out.println(moves);
    }
}
