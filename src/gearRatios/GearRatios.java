package gearRatios;

import java.util.ArrayList;

import Data.FileReader;

public class GearRatios {
    public static void main(String[] args) throws Exception {
        FileReader engSpec = new FileReader("inputs/schematic.txt");
        ArrayList<String> specRows = engSpec.getLines();

        ArrayList<PartNumber> numbers = new ArrayList<>();
        for (String row : specRows) {
            Parser.findNumbers(numbers, specRows.indexOf(row), row);
        }

        ArrayList<Part> parts = new ArrayList<>();
        for (String row : specRows) {
            Parser.findParts(parts, specRows.indexOf(row), row);
        }

        ArrayList<Integer> partNumbers = new ArrayList<>();
        Parser.findPartNumbers(partNumbers, parts, numbers);

        int sum = 0;
        for (Integer value : partNumbers) {
            sum += value;
        }
        System.out.println(sum);

        ArrayList<Part> gears = new ArrayList<>();
        Parser.findGears(gears, parts);

        ArrayList<Integer> gearRatios = new ArrayList<>();
        Parser.findRatios(gearRatios, gears, numbers);

        int gearSum = 0;
        for (Integer ratio : gearRatios) {
            gearSum += ratio;
        }
        System.out.println(gearSum);
    }
}
