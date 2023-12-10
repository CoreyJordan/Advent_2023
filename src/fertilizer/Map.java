package fertilizer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Map {
    private long destinationStart;
    private long sourceStart;
    private long rangeLength;

    public Map(long to, long from, long range) {
        destinationStart = to;
        sourceStart = from;
        rangeLength = range;
    }

    public long getDestinationStart() {
        return destinationStart;
    }

    public long getSourceStart() {
        return sourceStart;
    }

    public long getRangeLength() {
        return rangeLength;
    }

    public static ArrayList<Map> findMaps(String mapTitle, ArrayList<String> list) {
        int mapStart = findIndex(list, mapTitle, 0) + 1;
        int mapEnd = findIndex(list, "", mapStart);
        ArrayList<Map> maps = new ArrayList<>();

        ArrayList<Long> mapValues = new ArrayList<>();
        for (int i = mapStart; i < mapEnd; i++) {
            findNumbers(mapValues, list.get(i));
            maps.add(new Map(mapValues.get(0), mapValues.get(1), mapValues.get(2)));
            mapValues.clear();
        }
        return maps;
    }

    public static void findNumbers(ArrayList<Long> list, String s) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            list.add(Long.parseLong(matcher.group()));
        }
    }

    public static long convertToNext(long source, ArrayList<Map> maps) {

        for (int i = 0; i < maps.size(); i++) {
            long lowerBound = maps.get(i).getSourceStart();
            long upperBound = lowerBound + maps.get(i).getRangeLength();
            if (source >= lowerBound && source <= upperBound) {
                long conversion = source - lowerBound;
                return maps.get(i).getDestinationStart() + conversion;
            }
        }

        return source;
    }

    private static int findIndex(ArrayList<String> list, String title, int index) {
        while (index < list.size()) {
            if (list.get(index).equals(title)) {
                return index;
            }
            index++;
        }
        return list.size();
    }
}
