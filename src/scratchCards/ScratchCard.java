package scratchCards;

import java.util.ArrayList;

public class ScratchCard {
    private ArrayList<Integer> winningNums;
    private ArrayList<Integer> playNums;
    private String winningTicket;
    private String playTicket;

    public ScratchCard(String win, String play) {
        winningTicket = win;
        playTicket = play;

        try {
            playNums = new ArrayList<>();
            String[] nums = playTicket.split("\\s+");
            for (String number : nums) {
                playNums.add(Integer.parseInt(number));
            }
        } catch (Exception e) {
            playNums.clear();
        }

        try {
            winningNums = new ArrayList<>();
            String[] nums = winningTicket.split("\\s+");
            for (String number : nums) {
                winningNums.add(Integer.parseInt(number));
            }
        } catch (Exception e) {
            winningNums.clear();
            ;
        }
    }

    public ArrayList<Integer> getWinningNums() {
        return winningNums;
    }

    public ArrayList<Integer> getPlayNums() {
        return playNums;
    }

    public int calculateCardValue() {
        int matches = countMatches();
        double value = 0;
        if (matches > 0) {
            value = Math.pow((double) 2, (double) matches - 1);
        }
        return (int) value;
    }

    public int countMatches() {
        int count = 0;
        for (Integer num : playNums) {
            if (winningNums.contains(num)) {
                count++;
            }
        }
        return count;
    }
}
