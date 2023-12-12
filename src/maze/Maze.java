package maze;

import java.awt.Point;
import java.util.ArrayList;

public class Maze {
    private char[][] maze;

    public Maze(String[] xAxis) {
        createMaze(xAxis);
    }

    public char[][] getMaze() {
        return maze;
    }

    public Point getStartPoint() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'S') {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public ArrayList<Direction> findAvailableMoves(Point current) {
        ArrayList<Direction> availableDirections = new ArrayList<>();
        int x = (int) current.getX();
        int y = (int) current.getY();

        if (y < maze[x].length && (maze[x][y + 1] == '7' || maze[x][y + 1] == '-' || maze[x][y + 1] == 'J')) {
            if (maze[x][y] == 'L' || maze[x][y] == '-' || maze[x][y] == 'F') {
                availableDirections.add(Direction.NORTH);
            }
        }

        if (y > 0 && (maze[x][y - 1] == 'L' || maze[x][y - 1] == '-' || maze[x][y - 1] == 'F')) {
            if (maze[x][y] == 'J' || maze[x][y] == '-' || maze[x][y] == '7') {
                availableDirections.add(Direction.SOUTH);
            }
        }

        if (x < maze.length && (maze[x + 1][y] == 'L' || maze[x + 1][y] == '|' || maze[x + 1][y] == 'J')) {
            if (maze[x][y] == '7' || maze[x][y] == '|' || maze[x][y] == 'F') {
                availableDirections.add(Direction.EAST);
            }
        }

        if (x > 0 && (maze[x - 1][y] == 'F' || maze[x - 1][y] == '|' || maze[x - 1][y] == '7')) {
            if (maze[x][y] == 'L' || maze[x][y] == '|' || maze[x][y] == 'J') {
                availableDirections.add(Direction.WEST);
            }
        }

        return availableDirections;
    }

    public ArrayList<Direction> findFirstMoves(Point current) {
        ArrayList<Direction> availableDirections = new ArrayList<>();
        int x = (int) current.getX();
        int y = (int) current.getY();

        if (y < maze[x].length && (maze[x][y + 1] == 'F' || maze[x][y + 1] == '-' || maze[x][y + 1] == '7')) {
            availableDirections.add(Direction.NORTH);
        }

        if (y > 0 && (maze[x][y - 1] == 'L' || maze[x][y - 1] == '-' || maze[x][y - 1] == 'F')) {
            availableDirections.add(Direction.SOUTH);
        }

        if (x < maze.length && (maze[x + 1][y] == 'L' || maze[x + 1][y] == '|' || maze[x + 1][y] == 'J')) {
            availableDirections.add(Direction.EAST);
        }

        if (x > 0 && (maze[x - 1][y] == 'F' || maze[x - 1][y] == '|' || maze[x - 1][y] == '7')) {
            availableDirections.add(Direction.WEST);
        }

        return availableDirections;
    }

    private void createMaze(String[] xAxis) {
        maze = new char[xAxis.length][];

        for (int i = 0; i < xAxis.length; i++) {
            char[] yAxis = xAxis[i].toCharArray();
            maze[i] = yAxis;
        }

    }

}
