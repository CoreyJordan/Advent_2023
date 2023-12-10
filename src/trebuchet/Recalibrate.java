package trebuchet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Recalibrate {

    public static void main(String[] args) throws FileNotFoundException {

        File source = new File("values.txt");
        Scanner reader = new Scanner(source);

        List<String> lines = new ArrayList<>();
        while (reader.hasNextLine()) {
            lines.add(reader.nextLine());
        }
        reader.close();

        String first, last;
        int value;
        int sum = 0;
        for (String line : lines) {
            List<CalibrationValue> foundValues = findValues(line);
            foundValues.sort((o1, o2) -> o1.getIndex() - o2.getIndex());

            first = foundValues.getFirst().getActual();
            last = foundValues.getLast().getActual();
            value = Integer.parseInt(first + last);
            sum += value;
        }

        System.out.println(sum);

    }

    private static List<CalibrationValue> findValues(String line_in) {
        List<CalibrationValue> foundValues = new ArrayList<>();
        char[] chars = line_in.toCharArray();

        // Find all numerics in the string
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                foundValues.add(new CalibrationValue(i, String.valueOf(chars[i])));
            }
        }

        // Find all letter numbers in the string
        String[] numbers = { "zero", "one", "two", "three", "four", "five",
                "six", "seven", "eight", "nine" };
        for (String number : numbers) {
            int startIndex = 0;
            Pattern pattern = Pattern.compile(number);
            Matcher match = pattern.matcher(line_in);
            while (match.find(startIndex)) {
                foundValues.add(new CalibrationValue(match.start(), number));
                startIndex = match.start() + 1;
            }
        }
        return foundValues;
    }
}
