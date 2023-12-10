package gearRatios;

public class Part {
    private char type;
    private int locationRow;
    private int locationCol;

    public Part(char t, int row, int col) {
        type = t;
        locationRow = row;
        locationCol = col;
    }

    public int getColumn() {
        return locationCol;
    }

    public int getRow() {
        return locationRow;
    }

    public char getType() {
        return type;
    }

}
