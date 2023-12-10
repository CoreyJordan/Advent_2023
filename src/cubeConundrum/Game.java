package cubeConundrum;

import java.util.ArrayList;

public class Game {
    private int number;
    private ArrayList<Show> shows;

    public Game(String gameLine) {
        setGameNumber(gameLine);
        setShows(gameLine);
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Show> getShows() {
        return shows;
    }

    public Show getShow(int index) {
        if (index < shows.size()) {
            return shows.get(index);
        }
        return null;
    }

    public boolean isPossible(Show possible) {
        for (Show show : shows) {
            if (show.getRedCount() > possible.getRedCount()) {
                return false;
            }

            if (show.getGreenCount() > possible.getGreenCount()) {
                return false;
            }

            if (show.getBlueCount() > possible.getBlueCount()) {
                return false;
            }

        }

        return true;
    }

    public Show getMinimumShow() {
        if (shows.size() == 1) {
            return shows.get(0);
        }

        int minRed = shows.get(0).getRedCount();
        int minGreen = shows.get(0).getGreenCount();
        int minBlue = shows.get(0).getBlueCount();

        for (int i = 1; i < shows.size(); i++) {
            if (shows.get(i).getRedCount() > minRed) {
                minRed = shows.get(i).getRedCount();
            }

            if (shows.get(i).getGreenCount() > minGreen) {
                minGreen = shows.get(i).getGreenCount();
            }

            if (shows.get(i).getBlueCount() > minBlue) {
                minBlue = shows.get(i).getBlueCount();
            }
        }

        return new Show(minRed, minGreen, minBlue);
    }

    private void setGameNumber(String gameLine) {
        int gameNumber;
        String[] splitGameLine = gameLine.split(": ");
        String[] splitGameTitle = splitGameLine[0].split(" ");

        try {
            gameNumber = Integer.parseInt(splitGameTitle[1]);
        } catch (NumberFormatException nfe) {
            gameNumber = -1;
        }

        number = gameNumber;
    }

    private void setShows(String gameLine) {
        shows = new ArrayList<>();

        String[] numberAndPulls = gameLine.split(": ");
        String[] pulls = numberAndPulls[1].split("; ");

        int red = 0;
        int green = 0;
        int blue = 0;

        for (String pull : pulls) {
            String[] cubes = pull.split(", ");
            for (String cube : cubes) {
                String[] cubeSplit = cube.split(" ");

                switch (cubeSplit[1]) {
                    case "red":
                        red = Integer.parseInt(cubeSplit[0]);
                        break;
                    case "green":
                        green = Integer.parseInt(cubeSplit[0]);
                        break;
                    default:
                        blue = Integer.parseInt(cubeSplit[0]);
                        break;
                }
            }
            shows.add(new Show(red, green, blue));
        }

    }

}
