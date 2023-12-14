package data;

import java.awt.Point;

public class PointCalculator {

    public int findDistance(Point a, Point b) {
        int deltaX = Math.abs((int) (a.getX() - b.getX()));
        int deltaY = Math.abs((int) (a.getY() - b.getY()));
        return deltaX + deltaY;
    }

}
