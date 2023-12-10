package wasteland;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Navigate2 {
    public static void main(String[] args) throws FileNotFoundException {
        MapReader reader = new MapReader("inputs/left-right.txt");
        ArrayList<String> inpuStrings = reader.getLines();

        ArrayList<Node> nodes = reader.mapNodes(inpuStrings);
        ArrayList<Node> searchNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node.getNodeIn().charAt(2) == 'A') {
                searchNodes.add(node);
            }
        }

        String directions = inpuStrings.get(0);
        long step = 0;
        int index = 0;
        boolean exit = false;
        while (!exit) {

            for (int i = 0; i < searchNodes.size(); i++) {
                // Get next direction
                String findNode = switch (directions.charAt(index)) {
                    case 'L' -> searchNodes.get(i).getLeftOut();
                    default -> searchNodes.get(i).getRightOut();
                };

                // Get next node
                for (Node potentialNode : nodes) {
                    if (potentialNode.getNodeIn().equals(findNode)) {
                        searchNodes.remove(i);
                        searchNodes.add(i, potentialNode);
                    }
                }

            }

            step++;
            index++;
            if (index == directions.length()) {
                index = 0;
            }

            exit = true;
            for (int i = 0; i < searchNodes.size(); i++) {
                if (searchNodes.get(i).getNodeIn().charAt(2) != 'Z') {
                    exit = false;
                } else {
                    System.out.println(step);
                    searchNodes.remove(i);

                }
            }

        }

    }
}
