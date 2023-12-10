package trebuchet;

public class CalibrationValue {
    private int index;
    private String value;

    public CalibrationValue(int index_in, String value_in) {
        index = index_in;
        value = value_in;
    }

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }

    public String getActual() {
        return switch (value) {
            case "zero" -> "0";
            case "one" -> "1";
            case "two" -> "2";
            case "three" -> "3";
            case "four" -> "4";
            case "five" -> "5";
            case "six" -> "6";
            case "seven" -> "7";
            case "eight" -> "8";
            case "nine" -> "9";
            default -> value;
        };
    }
}
