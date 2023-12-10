package wasteland;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Navigate {
    public static void main(String[] args) throws FileNotFoundException {
        MapReader reader = new MapReader("inputs/left-right.txt");
        ArrayList<String> inpuStrings = reader.getLines();

        ArrayList<Node> nodes = reader.mapNodes(inpuStrings);

        Node atNode = nodes.get(0);
        for (Node node : nodes) {
            if (node.getNodeIn() == "AAA") {
                atNode = node;
            }
        }
        String directions = inpuStrings.get(0);
        int step = 0;
        int index = 0;
        while (atNode.getNodeIn() != "ZZZ") {
            String findNode = switch (directions.charAt(index)) {
                case 'L' -> atNode.getLeftOut();
                default -> atNode.getRightOut();
            };
            for (Node node : nodes) {
                if (node.getNodeIn() == findNode) {
                    atNode = node;
                }
            }
            step++;
            index++;
            if (index == directions.length()) {
                index = 0;
            }
        }
        System.out.println(step);

    }
}
