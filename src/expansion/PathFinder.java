package expansion;

import java.awt.Point;
import java.util.ArrayList;
import data.PointCalculator;

public class PathFinder {
    public static void main(String[] args) {
        final String GALAXY = "#";

        try (ImageReader reader = new ImageReader("inputs/test.txt")) {
            ArrayList<String> imageLines = reader.getLines();

            ImageProcessor processor = new ImageProcessor(imageLines, GALAXY);
            ArrayList<Point> galaxies = processor.plotGalaxies();

            PointCalculator calculator = new PointCalculator();

        } catch (Exception e) {
            System.out.println("Houston, we have a " + e.getMessage());
        }
    }
}
