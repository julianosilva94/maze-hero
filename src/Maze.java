import java.util.ArrayList;
import java.util.List;

public class Maze {
    List<List<Character>> map = new ArrayList<List<Character>>();

    public void add(int x, int y, Character value) {
        if (y >= this.map.size()) {
            List<Character> newList = new ArrayList<Character>();
            newList.add(x, value);

            this.map.add(y, newList);
        } else {
            this.map.get(y).add(x, value);
        }
    }

    public Graph buildGraph() {
        Graph graph = new Graph();

        int position = 0;
        int height = this.map.size();

        for (int y = 0; y < height; y++) {
            List<Character> row = this.map.get(y);
            int width = row.size();

            for (int x = 0; x < width; x++) {
                position++;

                if (this.isPositionWalkable(x, y)) {
                    // check left neighbor
                    if (x > 0) {
                        if (this.isPositionWalkable(x-1, y)) {
                            graph.addEdge(position, position - 1);
                        }
                    }

                    // check upper neighbor
                    if (y > 0) {
                        if (this.isPositionWalkable(x, y-1)) {
                            graph.addEdge(position, position - width);
                        }
                    }

                    // check right neighbor
                    if (x < width - 1) {
                        if (this.isPositionWalkable(x+1, y)) {
                            graph.addEdge(position, position + 1);
                        }
                    }

                    // check bottom neighbor
                    if (y < height - 1) {
                        if (this.isPositionWalkable(x, y+1)) {
                            graph.addEdge(position, position + width);
                        }
                    }
                }
            }
        }

        return graph;
    }

    private boolean isPositionWalkable(int x, int y) {
        return this.map.get(y).get(x) != '#';
    }

    public int findHeroPosition() {
        int position = 0;

        List<List<Character>> lists = this.map;
        for (int y = 0; y < lists.size(); y++) {
            List<Character> row = lists.get(y);
            for (int x = 0; x < row.size(); x++) {
                position++;

                Character character = row.get(x);
                if (character == 'A') {
                    return position;
                }
            }
        }

        return position;
    }

    public int findVillainPosition() {
        int position = 0;

        List<List<Character>> lists = this.map;
        for (int y = 0; y < lists.size(); y++) {
            List<Character> row = lists.get(y);
            for (int x = 0; x < row.size(); x++) {
                position++;

                Character character = row.get(x);
                if (character == 'B') {
                    return position;
                }
            }
        }

        return position;
    }
}
