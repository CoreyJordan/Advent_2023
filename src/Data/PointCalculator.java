package data;

import java.awt.Point;

public class PointCalculator {

    public int findDistance(Point a, Point b) {
        int deltaX = (int) (a.getX() - b.getX());
        int deltaY = (int) (a.getY() - b.getY());
        return Math.abs(deltaX + deltaY);
    }

}
