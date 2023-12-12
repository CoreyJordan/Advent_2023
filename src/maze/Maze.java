package maze;

import java.awt.Point;

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

    private void createMaze(String[] xAxis) {
        maze = new char[xAxis.length][];

        for (int i = 0; i < xAxis.length; i++) {
            char[] yAxis = xAxis[i].toCharArray();
            maze[i] = yAxis;
        }

    }

}
