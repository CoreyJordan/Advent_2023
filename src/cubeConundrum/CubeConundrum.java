package cubeConundrum;

import java.util.ArrayList;

public class CubeConundrum {
    public static void main(String[] args) throws Exception {
        final Show POSSIBLE = new Show(12, 13, 14);

        FileReader bagOfGames = new FileReader("game.txt");
        ArrayList<String> gamesList = bagOfGames.getLines();
        ArrayList<Game> games = new ArrayList<>();

        for (String game : gamesList) {
            games.add(new Game(game));
        }

        int sum = 0;
        for (Game game : games) {
            if (game.isPossible(POSSIBLE)) {
                sum += game.getNumber();
            }
        }

        int minPower;
        int sumPower = 0;
        for (Game game : games) {
            minPower = game.getMinimumShow().getRedCount() *
                    game.getMinimumShow().getGreenCount() *
                    game.getMinimumShow().getBlueCount();

            sumPower += minPower;
        }

        System.out.println("Total of possible game IDs: " + sum);
        System.out.println("Total of power of minimums: " + sumPower);

    }
}
