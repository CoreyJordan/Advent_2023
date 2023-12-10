package gearRatios;

public class PartNumber {
    private int partValue;
    private String partString;
    private int locationRow;
    private int locationColStart;
    private int locationColEnd;

    public PartNumber(String s, int row, int start, int end) {
        partString = s;
        locationRow = row;
        locationColStart = start;
        locationColEnd = end;
    }

    public int getRow() {
        return locationRow;
    }

    public int getStart() {
        return locationColStart;
    }

    public int getEnd() {
        return locationColEnd;
    }

    /**
     * Attempts to pars an integer from a passed String.
     * 
     * @return The integer value of the string. -1 if not a valid integer.
     */
    public int getValue() {
        try {
            partValue = Integer.parseInt(partString);
        } catch (Exception e) {
            partValue = -1;
        }

        return partValue;
    }

}
