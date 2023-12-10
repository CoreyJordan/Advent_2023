package gearRatios;

import java.util.ArrayList;

public class Parser {

    public static void findNumbers(ArrayList<PartNumber> numbers, int row, String line) {
        int searchAt = 0;
        int numberstart = 0;
        int numberEnd;
        String foundNumber = "";

        while (searchAt < line.length()) {
            if (!Character.isDigit(line.charAt(searchAt))) {
                searchAt++;
            } else {
                numberstart = searchAt;
                numberEnd = searchAt;
                while (searchAt < line.length() && Character.isDigit(line.charAt(searchAt))) {
                    numberEnd = searchAt;
                    foundNumber += line.charAt(searchAt);
                    searchAt++;

                }

                numbers.add(new PartNumber(foundNumber, row, numberstart, numberEnd));
                foundNumber = "";
            }

        }
    }

    public static void findParts(ArrayList<Part> parts, int row, String line) {
        for (int i = 0; i < line.length(); i++) {
            if (!Character.isLetterOrDigit(line.charAt(i)) && line.charAt(i) != '.') {
                parts.add(new Part(line.charAt(i), row, i));
            }
        }
    }

    public static void findPartNumbers(ArrayList<Integer> partNumbers,
            ArrayList<Part> parts,
            ArrayList<PartNumber> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            int row = numbers.get(i).getRow();
            int start = numbers.get(i).getStart() - 1;
            int end = numbers.get(i).getEnd() + 1;
            boolean isPart = false;

            for (Part part : parts) {
                if (part.getRow() >= row - 1 && part.getRow() <= row + 1) {
                    if (part.getColumn() >= start && part.getColumn() <= end) {
                        isPart = true;
                    }
                }
            }

            if (isPart) {
                partNumbers.add(numbers.get(i).getValue());
            }

        }
    }

    public static void findGears(ArrayList<Part> gears, ArrayList<Part> parts) {
        for (Part part : parts) {
            if (part.getType() == '*') {
                gears.add(part);
            }
        }
    }

    public static void findRatios(ArrayList<Integer> gearRatios,
            ArrayList<Part> gears,
            ArrayList<PartNumber> numbers) {
        int partRow;
        int column;
        int numRow;
        int start;
        int end;

        ArrayList<Integer> candidates = new ArrayList<>();
        for (int i = 0; i < gears.size(); i++) {
            partRow = gears.get(i).getRow();
            column = gears.get(i).getColumn();

            for (PartNumber number : numbers) {
                numRow = number.getRow();
                start = number.getStart() - 1;
                end = number.getEnd() + 1;

                if (numRow >= partRow - 1 && numRow <= partRow + 1) {
                    if (column >= start && column <= end) {
                        candidates.add(number.getValue());
                    }
                }
            }

            if (candidates.size() == 2) {
                int ratio = candidates.get(0) * candidates.get(1);
                gearRatios.add(ratio);
            }

            candidates.clear();
        }
    }

}
