package wasteland;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapReader extends data.FileReader {

    public MapReader(String fileName) {
        super(fileName);
    }

    public ArrayList<Node> mapNodes(ArrayList<String> lines) {
        ArrayList<Node> n = new ArrayList<>();

        for (int i = 2; i < lines.size(); i++) {
            Pattern pattern = Pattern.compile("[A-Z]+");
            Matcher matcher = pattern.matcher(lines.get(i));
            ArrayList<String> nodeParts = new ArrayList<>();

            while (matcher.find()) {
                nodeParts.add(matcher.group(0));
            }
            n.add(new Node(nodeParts.get(0), nodeParts.get(1), nodeParts.get(2)));
        }

        return n;
    }

}
