package mirage;

import java.util.ArrayList;

public class Oasis {
    public static void main(String[] args) {
        OasisReader reader = new OasisReader("inputs/readings.txt");

        ArrayList<String> histories = new ArrayList<>() {
            {
                add("0 3 6 9 12 15");
                add("1 3 6 10 15 21");
                add("10 13 16 21 30 45");
            }
        };

        long extrapolationsTotal = 0;
        for (int i = 0; i < histories.size(); i++) {
            // Get readings from the history and add to list of sequences
            ArrayList<Long> history = reader.findReadings(histories.get(i));
            ArrayList<ArrayList<Long>> sequenceLists = new ArrayList<>();
            sequenceLists.add(history);

            // Difference down to zero
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
            extrapolationsTotal += extrapolation;

            // Reset the sequence list
            sequenceLists.clear();

        }

        System.out.println("Total of extrapolations: " + extrapolationsTotal);
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
