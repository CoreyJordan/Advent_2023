package mirage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class Oasis {
    public static void main(String[] args) throws FileNotFoundException {
        OasisReader reader = new OasisReader("inputs/readings.txt");

        ArrayList<String> histories = reader.getLines();

        long extrapolationsTotal = 0;
        for (int i = 0; i < histories.size(); i++) {
            // Get readings from the history and add to list of sequences
            ArrayList<Long> history = reader.findReadings(histories.get(i));
            Collections.reverse(history);
            ArrayList<ArrayList<Long>> sequenceLists = new ArrayList<>();
            sequenceLists.add(history);

            // Difference down to zero
            boolean allZeroes = false;
            while (!allZeroes) {
                sequenceLists.add(plotDifferences(sequenceLists.getLast()));

                allZeroes = true;
                for (Long number : sequenceLists.getLast()) {
                    if (number != 0) {
                        allZeroes = false;
                    }
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
            long difference = sequence.get(i + 1) - sequence.get(i);
            // difference = Math.abs(difference);
            differences.add(difference);
        }

        return differences;
    }
}
