package expansion;

import java.util.ArrayList;

import data.FileReader;

public class PathFinder {
    public static void main(String[] args) {
        final String GALAXY = "#";
        ArrayList<String> imageLines = new ArrayList<>();
        try (FileReader reader = new FileReader("inputs/test.txt")) {
            imageLines = reader.getLines();

            ImageProcessor processor = new ImageProcessor(imageLines, GALAXY);

        } catch (Exception e) {
            System.out.println("Houston, we have a " + e.getMessage());
        }
    }
}
