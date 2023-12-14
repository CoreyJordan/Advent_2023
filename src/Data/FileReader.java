package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader implements java.lang.AutoCloseable {
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

    public ArrayList<Integer> findNumbers(String s) {
        ArrayList<Integer> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            list.add(Integer.parseInt(matcher.group()));
        }
        return list;
    }

    public long findNumber(String s) {
        String number = "";
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                number += s.charAt(i);
            }
        }
        return Long.parseLong(number);
    }

    @Override
    public void close() throws Exception {
        System.out.close();
    }

}
