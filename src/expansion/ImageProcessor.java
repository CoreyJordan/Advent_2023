package expansion;

import java.util.ArrayList;

public class ImageProcessor {
    private String galaxy;
    private ArrayList<String> image;

    public ImageProcessor(ArrayList<String> image, String galaxy) {
        this.image = image;
        this.galaxy = galaxy;
        expandImage();
    }

    private void expandImage() {
        // Vertical expansion
        int i = 0;
        while (i < image.get(0).length()) {
            boolean expand = true;
            for (int j = 0; j < image.size(); j++) {
                if (image.get(j).substring(i, i + 1).equals(galaxy)) {
                    expand = false;
                }
            }
            for (int k = 0; k < image.size(); k++) {
                if (expand) {
                    String expanded = image.get(k).substring(0, i + 1) + image.get(k).substring(i);
                    image.add(k, expanded);
                    image.remove(k + 1);
                }
            }
            if (expand) {
                i++;
            }
            i++;

        }

        // Horizontal expansion
        for (int j = 0; j < image.size(); j++) {
            if (!image.get(j).contains(galaxy)) {
                image.add(j, image.get(j));
                j++;
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
