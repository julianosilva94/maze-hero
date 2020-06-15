import java.util.*;

public class Graph {
    private Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();

    public void addVertex(Integer vertex) {
        adjacencyList.put(vertex, new HashSet<Integer>());
    }

    public void addEdge(Integer source, Integer destination) {
        if (!adjacencyList.containsKey(source)) {
            this.addVertex(source);
        }

        if (!adjacencyList.containsKey(destination)) {
            this.addVertex(destination);
        }

        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
    }

    public Set<Integer> getAdjacentVertices(Integer vertex) {
        return adjacencyList.get(vertex);
    }

    public int findShortestPathDistance(Integer root, Integer destination) {
        Map<Integer, Integer> distsToRoot = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(root);
        visited.add(root);
        distsToRoot.put(root, 0);

        while (! queue.isEmpty()) {
            if (visited.contains(destination)) {
                break;
            }

            Integer vertex = queue.poll();

            this.getAdjacentVertices(vertex).forEach(adjacentVertex -> {
                if (! visited.contains(adjacentVertex)) {
                    queue.add(adjacentVertex);
                    visited.add(adjacentVertex);
                    distsToRoot.put(adjacentVertex, distsToRoot.get(vertex) + 1);
                }
            });
        }

        return distsToRoot.get(destination);
    }
}