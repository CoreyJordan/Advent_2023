package fertilizer;

import java.util.ArrayList;
import java.util.Comparator;

public class App {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("inputs/seedMap.txt");
        ArrayList<String> inputLines = reader.getLines();

        // List seeds
        ArrayList<Long> seeds = new ArrayList<>();
        Map.findNumbers(seeds, inputLines.get(0));

        // Create map lists
        ArrayList<Map> soilMaps = Map.findMaps("seed-to-soil map:", inputLines);
        ArrayList<Map> fertilizerMaps = Map.findMaps("soil-to-fertilizer map:", inputLines);
        ArrayList<Map> waterMaps = Map.findMaps("fertilizer-to-water map:", inputLines);
        ArrayList<Map> lightMaps = Map.findMaps("water-to-light map:", inputLines);
        ArrayList<Map> tempMaps = Map.findMaps("light-to-temperature map:", inputLines);
        ArrayList<Map> humidityMaps = Map.findMaps("temperature-to-humidity map:", inputLines);
        ArrayList<Map> locationMaps = Map.findMaps("humidity-to-location map:", inputLines);
        ArrayList<ArrayList<Map>> maps = new ArrayList<>() {
            {
                add(soilMaps);
                add(fertilizerMaps);
                add(waterMaps);
                add(lightMaps);
                add(tempMaps);
                add(humidityMaps);
                add(locationMaps);
            }
        };

        ArrayList<Long> locations = new ArrayList<>();
        for (Long seed : seeds) {
            locations.add(convertToLocation(seed, maps));
        }

        locations.sort(Comparator.naturalOrder());
        System.out.println(locations.getFirst());

        // Part Two
        // Iterate through the seeds list but only pulling evens as seed numbers
        // We will use odds as ranges.
        long lowestLocation = convertToLocation(seeds.get(0), maps);

        for (int i = 0; i < seeds.size(); i += 2) {
            long startSeed = seeds.get(i);
            long endSeed = startSeed + seeds.get(i + 1);
            for (long j = startSeed; j < endSeed; j++) {
                long location = convertToLocation(j, maps);
                if (location < lowestLocation) {
                    lowestLocation = location;
                }
            }
        }

        System.out.println(lowestLocation);

    }

    private static long convertToLocation(long seed, ArrayList<ArrayList<Map>> maps) {
        long soil = Map.convertToNext(seed, maps.get(0));
        long fertilizer = Map.convertToNext(soil, maps.get(1));
        long water = Map.convertToNext(fertilizer, maps.get(2));
        long light = Map.convertToNext(water, maps.get(3));
        long temp = Map.convertToNext(light, maps.get(4));
        long humidity = Map.convertToNext(temp, maps.get(5));

        return Map.convertToNext(humidity, maps.get(6));
    }

}
