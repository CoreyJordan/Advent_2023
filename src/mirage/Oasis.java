package mirage;

import java.util.ArrayList;

public class Oasis {
    public static void main(String[] args) {
        OasisReader reader = new OasisReader("inputs/readings.txt");

        ArrayList<Long> testList = new ArrayList<>() {
            {
                add((long) 10);
                add((long) 13);
                add((long) 16);
                add((long) 21);
                add((long) 30);
                add((long) 45);
            }
        };

        ArrayList<String> histories = new ArrayList<>() {
            {
                add("0 3 6 9 12 15");
                add("1 3 6 10 15 21");
                add("10 13 16 21 30 45");
            }
        };

        // Difference down to zero
        ArrayList<ArrayList<Long>> sequenceLists = new ArrayList<>();
        sequenceLists.add(testList);
        boolean allZeroes = false;
        while (!allZeroes) {
            sequenceLists.add(plotDifferences(sequenceLists.getLast()));

            if (listSum(sequenceLists.getLast()) == 0) {
                allZeroes = true;
            }
        }

        // Initialize the extrapolation
        sequenceLists.remove(sequenceLists.getLast());
        long delta = 0;

        // Extrapolate to original list
        while (sequenceLists.size() > 1) {
            sequenceLists.getLast().add(sequenceLists.getLast().getLast() + delta);
            delta = sequenceLists.getLast().getLast();
            sequenceLists.remove(sequenceLists.getLast());
        }
        // Final delta
        long extrapolation = sequenceLists.getLast().getLast() + delta;

        System.out.println(extrapolation);

    }

    private static ArrayList<Long> plotDifferences(ArrayList<Long> sequence) {
        ArrayList<Long> differences = new ArrayList<>();

        for (int i = 0; i < sequence.size() - 1; i++) {
            differences.add(sequence.get(i + 1) - sequence.get(i));
        }

        return differences;
    }

    private static long listSum(ArrayList<Long> list) {
        long sum = 0;
        for (Long number : list) {
            sum += number;
        }
        return sum;
    }
}
