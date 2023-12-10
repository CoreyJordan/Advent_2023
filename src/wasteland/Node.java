package wasteland;

public class Node {
    private String nodeIn;
    private String leftOut;
    private String rightOut;

    public Node(String node, String left, String right) {
        nodeIn = node;
        leftOut = left;
        rightOut = right;

    }

    public String getNodeIn() {
        return nodeIn;
    }

    public String getLeftOut() {
        return leftOut;
    }

    public String getRightOut() {
        return rightOut;
    }
}
