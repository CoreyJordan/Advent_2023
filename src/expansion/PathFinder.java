package expansion;

import java.awt.Point;
import java.util.ArrayList;

import data.PointCalculator;

public class PathFinder {
    public static void main(String[] args) {
        final String GALAXY = "#";
        final long RATE_OF_EXPANSION = 1_000_000;

        // try (ImageReader reader = new ImageReader("inputs/image.txt")) {
        // ArrayList<String> imageLines = reader.getLines();

        // ImageProcessor processor = new ImageProcessor(imageLines, GALAXY);
        // ArrayList<Point> galaxies = processor.plotGalaxies();

        // PointCalculator calculator = new PointCalculator();
        // int sumOfDistances = 0;
        // for (int i = 0; i < galaxies.size(); i++) {
        // for (int j = i + 1; j < galaxies.size(); j++) {
        // int distance = calculator.findDistance(galaxies.get(i), galaxies.get(j));
        // sumOfDistances += distance;

        // }
        // }
        // System.out.println(sumOfDistances);

        // } catch (Exception e) {
        // System.out.println("Houston, we have a " + e.getMessage());
        // }

        // Part Two
        try (ImageReader reader = new ImageReader("inputs/image.txt")) {
            ArrayList<String> imageLines = reader.getLines();

            ImageProcessorVersion2 processor = new ImageProcessorVersion2(imageLines, GALAXY);
            ArrayList<Point> galaxies = processor.plotGalaxies();
            ArrayList<Integer> xExpansions = processor.findExpansionX();
            ArrayList<Integer> yExpansions = processor.findExpansionY();

            PointCalculator calculator = new PointCalculator();
            long sumOfDistances = 0;
            for (int i = 0; i < galaxies.size(); i++) {
                for (int j = i + 1; j < galaxies.size(); j++) {
                    long distance = calculator.findLongDistance(galaxies.get(i), galaxies.get(j), xExpansions,
                            yExpansions, RATE_OF_EXPANSION);
                    sumOfDistances += distance;
                }
            }
            System.out.println(sumOfDistances);

        } catch (Exception e) {
            System.out.println("Houston, we have a " + e.getMessage());
        }
    }
}
