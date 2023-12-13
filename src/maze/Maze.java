package maze;

import java.awt.Point;
import java.util.ArrayList;

public class Maze {
    private char[][] maze;
    private char lowLeft = '\u2554';
    private char upLeft = '\u2557';
    private char lowRight = '\u255A';
    private char upRight = '\u255D';
    private char vertical = '\u2550';
    private char horizontal = '\u2551';
    private char inside = '#';
    private char start = 'S';

    public Maze(String[] xAxis) {
        createMaze(xAxis);
    }

    public char[][] getMaze() {
        return maze;
    }

    public void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }

    }

    public Point getStartPoint() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == start) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public void markSpot(Point from, Direction dir) {
        int x = (int) from.getX();
        int y = (int) from.getY();

        char redraw;

        switch (maze[x][y]) {
            case 'F':
                redraw = lowLeft;
                break;
            case '7':
                redraw = upLeft;
                break;
            case 'L':
                redraw = lowRight;
                break;
            case 'J':
                redraw = upRight;
                break;
            case '-':
                redraw = vertical;
                break;
            case '|':
                redraw = horizontal;
                break;
            case 'S':
                redraw = start;
                break;
            default:
                redraw = ' ';
                break;
        }

        maze[x][y] = redraw;
    }

    public void paintMap(Point current, Direction direction) {
        int x = (int) current.getX();
        int y = (int) current.getY();

        switch (direction) {
            case NORTH:

                // Don't look off-grid.
                if (x < maze.length - 1 && y < maze[x].length - 1) {

                    // If thru-pipe or left turn, look EAST.
                    if ((maze[x][y] == vertical || maze[x][y] == upRight) && !isPipe(maze[x + 1][y])) {
                        maze[x + 1][y] = inside;
                    }

                    // If left turn, look NORTH.
                    if (maze[x][y] == upRight && !isPipe(maze[x][y + 1])) {
                        maze[x][y + 1] = inside;
                    }
                }
                break;
            case SOUTH:

                // Don't look off off-grid.
                if (x > 0 && y > 0) {

                    // If thru-pipe or left turn, look WEST.
                    if ((maze[x][y] == vertical || maze[x][y] == lowLeft) && !isPipe(maze[x - 1][y])) {
                        maze[x - 1][y] = inside;
                    }

                    // If left turn, also look SOUTH.
                    if (maze[x][y] == lowLeft && !isPipe(maze[x][y - 1])) {
                        maze[x][y - 1] = inside;
                    }
                }
                break;
            case EAST:

                // Don't look off grid.
                if (y > 0 && x < maze.length - 1) {

                    // If thru-pipe or left turn, look SOUTH.
                    if ((maze[x][y] == horizontal || maze[x][y] == lowRight) && !isPipe(maze[x][y - 1])) {
                        maze[x][y - 1] = inside;
                    }

                    // If left turn, also look EAST.
                    if (maze[x][y] == lowRight && !isPipe(maze[x + 1][y])) {
                        maze[x + 1][y] = inside;
                    }
                }
                break;
            default: // If we're not going north, south, or east....

                // Don't look off grid.
                if (x > 0 && y < maze[x].length - 1) {

                    // If thru-pipe or left turn, look NORTH.
                    if ((maze[x][y] == horizontal || maze[x][y] == upLeft) && !isPipe(maze[x][y + 1])) {
                        maze[x][y + 1] = inside;
                    }

                    // If left turn, also look WEST.
                    if (maze[x][y] == upLeft && !isPipe(maze[x - 1][y])) {
                        maze[x - 1][y] = inside;
                    }
                }
                break;
        }

    }

    public void clearClutter() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] != start && maze[i][j] != inside && !isPipe(maze[i][j])) {
                    maze[i][j] = ' ';
                }
            }
        }
    }

    public void fillVoids() {
        boolean innerFill = false;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == inside) {
                    innerFill = true;
                } else if (isPipe(maze[i][j])) {
                    innerFill = false;
                } else if (maze[i][j] == ' ' && innerFill) {
                    maze[i][j] = inside;
                }
            }
        }
    }

    private boolean isPipe(char c) {
        if (c == upLeft || c == upRight || c == lowLeft || c == lowRight || c == vertical || c == horizontal) {
            return true;
        } else if (c == start) {
            return true;
        }
        return false;
    }

    public int countInside() {
        int count = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == inside) {
                    count++;
                }
            }
        }

        return count;
    }

    public ArrayList<Direction> findAvailableMoves(Point current) {
        ArrayList<Direction> availableDirections = new ArrayList<>();
        int x = (int) current.getX();
        int y = (int) current.getY();

        if (y + 1 < maze[x].length
                && (maze[x][y + 1] == '7' || maze[x][y + 1] == '-' || maze[x][y + 1] == 'J' || maze[x][y + 1] == 'S')) {
            if (maze[x][y] == 'L' || maze[x][y] == '-' || maze[x][y] == 'F') {
                availableDirections.add(Direction.NORTH);
            }
        }

        if (y > 0
                && (maze[x][y - 1] == 'L' || maze[x][y - 1] == '-' || maze[x][y - 1] == 'F' || maze[x][y - 1] == 'S')) {
            if (maze[x][y] == 'J' || maze[x][y] == '-' || maze[x][y] == '7') {
                availableDirections.add(Direction.SOUTH);
            }
        }

        if (x + 1 < maze.length
                && (maze[x + 1][y] == 'L' || maze[x + 1][y] == '|' || maze[x + 1][y] == 'J' || maze[x + 1][y] == 'S')) {
            if (maze[x][y] == '7' || maze[x][y] == '|' || maze[x][y] == 'F') {
                availableDirections.add(Direction.EAST);
            }
        }

        if (x > 0
                && (maze[x - 1][y] == 'F' || maze[x - 1][y] == '|' || maze[x - 1][y] == '7' || maze[x - 1][y] == 'S')) {
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
