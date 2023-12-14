package data;

import java.awt.Point;
import java.math.BigInteger;
import java.util.ArrayList;

public class PointCalculator {

    public int findDistance(Point a, Point b) {
        int deltaX = Math.abs((int) (a.getX() - b.getX()));
        int deltaY = Math.abs((int) (a.getY() - b.getY()));
        return deltaX + deltaY;
    }

    public int findLongDistance(Point a, Point b, ArrayList<Integer> xExpand, ArrayList<Integer> yExpand, long rate) {

        int deltaX = Math.abs((int) (a.getX() - b.getX()));
        // Account for x axis expansion
        for (int i = 0; i < xExpand.size(); i++) {
            int plane = xExpand.get(i);
            if ((plane > a.getX() && plane < b.getX())
                    || (plane > b.getX() && plane < a.getX())) {
                deltaX += rate - 1;
            }
        }

        int deltaY = Math.abs((int) (a.getY() - b.getY()));
        // Account for y axis expansion.
        for (int i = 0; i < yExpand.size(); i++) {
            int plane = yExpand.get(i);
            if ((plane > a.getY() && plane < b.getY())
                    || (plane > b.getY() && plane < a.getY())) {
                deltaY += rate - 1;
            }
        }

        return deltaX + deltaY;
    }

}
