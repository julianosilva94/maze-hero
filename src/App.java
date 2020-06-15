import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Maze maze = new Maze();
        File file = new File(args[0]);

        int x = 0, y = 0;
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                for (char character : line.toCharArray()) {
                    maze.add(x, y, character);
                    x++;
                }

                if (scanner.hasNextLine()) {
                    x = 0;
                    y++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Matrix size: " + (y+1) + "x" + x);

        long start = System.currentTimeMillis();
        Graph graph = maze.buildGraph();
        System.out.println("buildGraph - " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        int distance = graph.findShortestPathDistance(maze.findHeroPosition(), maze.findVillainPosition());
        System.out.println("findShortestPathDistance - " + (System.currentTimeMillis() - start) + "ms");

        System.out.println("Distância entre os pontos A e B : " + distance);
    }
}
