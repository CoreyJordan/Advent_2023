package scratchCards;

import java.util.ArrayList;
import data.FileReader;

public class App {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("cards.txt");
        ArrayList<String> lineList = reader.getLines();

        ArrayList<String> linesListNoLabel = new ArrayList<>();
        for (String line : lineList) {
            String[] droppedLabels = line.split(":");
            linesListNoLabel.add(droppedLabels[1]);
        }

        ArrayList<ScratchCard> scratchCards = new ArrayList<>();
        for (String line : linesListNoLabel) {
            String[] cardHalves = line.split("\\|");
            scratchCards.add(new ScratchCard(cardHalves[0].trim(), cardHalves[1].trim()));
        }

        // long totalValue = 0;
        // for (ScratchCard scratchCard : scratchCards) {
        // totalValue += scratchCard.calculateCardValue();
        // }

        // System.out.println(totalValue);

        int[] cards = new int[scratchCards.size()];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = 1;
        }

        for (int i = 0; i < scratchCards.size(); i++) {
            int matches = scratchCards.get(i).countMatches();

            int start = i + 1;
            int end = start + matches;
            if (end > scratchCards.size()) {
                end = scratchCards.size();
            }

            for (int j = start; j < end; j++) {
                cards[j] += cards[i];
            }
        }

        int cardTotal = 0;
        for (int i : cards) {
            cardTotal += i;
        }
        System.out.println(cardTotal);

    }

}
