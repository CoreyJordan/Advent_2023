package maze;

public class Run {
    public static void main(String[] args) {
        String test1 = ".....";
        String test2 = ".F-7.";
        String test3 = ".|.S.";
        String test4 = ".L-J.";
        String test5 = ".....";

        String[] testXAxis = { test1, test2, test3, test4, test5 };

        Maze maze = new Maze(testXAxis);
        MazeRunner runner = new MazeRunner(maze.getStartPoint());

        System.out.println(maze.getStartPoint());

    }
}
