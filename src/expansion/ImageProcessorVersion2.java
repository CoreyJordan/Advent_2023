package expansion;

import java.util.ArrayList;

public class ImageProcessorVersion2 extends ImageProcessor {

    public ImageProcessorVersion2(ArrayList<String> image, String galaxy) {
        this.galaxy = galaxy;
        this.image = image;
    }

    public ArrayList<Integer> findHorizontalExpansions() {
        ArrayList<Integer> expansions = new ArrayList<>();
        for (int i = 0; i < image.size(); i++) {
            if (!image.get(i).contains(galaxy)) {
                expansions.add(i);
            }
        }
        return expansions;
    }

    public ArrayList<Integer> findVerticalExpansions() {
        ArrayList<Integer> expansions = new ArrayList<>();
        boolean isEmpty;
        for (int i = 0; i < image.get(0).length(); i++) {
            isEmpty = true;
            for (int j = 0; j < image.size(); j++) {
                if (image.get(j).substring(i, i + 1).equals(galaxy)) {
                    isEmpty = false;
                    break;
                }
            }

            if (isEmpty) {
                expansions.add(i);
            }
        }

        return expansions;
    }

}
