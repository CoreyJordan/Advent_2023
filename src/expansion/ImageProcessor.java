package expansion;

import java.util.ArrayList;
import java.awt.Point;

public class ImageProcessor {
    private String galaxy;
    private ArrayList<String> image;

    public ImageProcessor(ArrayList<String> image, String galaxy) {
        this.image = image;
        this.galaxy = galaxy;
        expandImage();
    }

    private void expandImage() {
        for (int i = 0; i < image.size(); i++) {
            if (!image.get(i).contains(galaxy)) {
                image.add(i, image.get(i));
                i++;
            }
        }
    }

    public ArrayList<String> getImage() {
        return image;
    }

    // public ArrayList<Point> plotGalaxies(ArrayList<String> image) {
    // ArrayList<Point> galaxies = new ArrayList<>();
    // for (int j = 0; j < image.size(); j++) {
    // for (int i = 0; i < image.get(j).length(); i++) {
    // if (image.get(j).charAt(i) == galaxy) {
    // galaxies.add(new Point(j, i));
    // }
    // }
    // }
    // return galaxies;
    // }
}
