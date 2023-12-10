package waitForIt;

import java.util.ArrayList;

import data.FileReader;

public class App {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("inputs/races.txt");
        ArrayList<String> stringList = reader.getLines();

        ArrayList<Integer> times = reader.findNumbers(stringList.get(0));
        ArrayList<Integer> distances = reader.findNumbers(stringList.get(1));

        ArrayList<Race> races = new ArrayList<>();
        for (int i = 0; i < times.size(); i++) {
            races.add(new Race(times.get(i), distances.get(i)));
        }

        ArrayList<Integer> solutionTotals = new ArrayList<>();
        for (Race race : races) {
            solutionTotals.add(countWinningSolutions(race.getTime(), race.getDistance()));
        }

        int solutionsProduct = solutionTotals.get(0);
        for (int i = 1; i < solutionTotals.size(); i++) {
            solutionsProduct *= solutionTotals.get(i);
        }
        System.out.println(solutionsProduct);

        long singleTime = reader.findNumber(stringList.get(0));
        long singleDistance = reader.findNumber(stringList.get(1));

        int singleRaceSolutions = countWinningSolutions(singleTime, singleDistance);
        System.out.println(singleRaceSolutions);

    }

    private static int countWinningSolutions(long time, long distance) {
        int solutions = 0;
        for (int speed = 1; speed < time; speed++) {
            if (speed * (time - speed) > distance) {
                solutions++;
            }
        }
        return solutions;
    }
}
