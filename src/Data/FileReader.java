package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    private File file;

    public FileReader(String fileName) {
        file = new File(fileName);
    }

    public ArrayList<String> getLines() throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        ArrayList<String> fileLines = new ArrayList<>();

        while (reader.hasNextLine()) {
            fileLines.add(reader.nextLine());
        }

        reader.close();
        return fileLines;
    }

}
