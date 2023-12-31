package maze;

import java.awt.Point;

public class MazeRunner {
    private Point current;
    private Point from;

    public MazeRunner(Point point) {
        current = new Point(point.getLocation());
        from = new Point();
    }

    public Point getCurrent() {
        return current;
    }

    public Point getFrom() {
        return from;
    }

    /**
     * Moves the runner in one of four cardinal directions. Movement is based
     * on a 2D plane with an x-axis and y-axis. If using a 2D array, North
     * will be pointing to the right to maintain intuitive 1 for 1 use.
     * 
     * @param direction
     */
    public void move(Direction direction) {
        from.setLocation(current.getLocation());
        switch (direction) {
            case NORTH:
                current.translate(0, 1);
                break;
            case SOUTH:
                current.translate(0, -1);
                break;
            case EAST:
                current.translate(1, 0);
                break;
            case WEST:
                current.translate(-1, 0);
            default:
                // Do nothing
                break;
        }
    }

}
