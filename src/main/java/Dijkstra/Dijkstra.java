package Dijkstra;

import java.util.*;

public class Dijkstra {

    private final int NONE = -1;
    private final int INFINITY = Integer.MAX_VALUE;

    private Graph graphData;
    private boolean hasOneToAllData;

    private int[] dijkstraResult;
    private int[] dijkstraPath;
    private int fromNode = -1;

    public Dijkstra(Graph data) {
        graphData = data;
    }

    public void executeOneToAll(int from) {
        fromNode = from;
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(value -> value[1]));
        dijkstraResult = new int[graphData.getTotalNodes()];
        Arrays.fill(dijkstraResult, INFINITY);
        dijkstraPath = new int[graphData.getTotalNodes()];
        Arrays.fill(dijkstraPath, NONE);
        boolean[] visited = new boolean[graphData.getTotalNodes()];
        int current, edge, index, target, shortest;
        dijkstraResult[from] = 0;
        queue.add(new int[] { from, 0 });
        while (!queue.isEmpty()) {
            current = queue.poll()[0];
            if (!visited[current]) {
                visited[current] = true;
                for (edge = graphData.getEdgesFor(current); --edge >= 0;) {
                    index = graphData.getOffsetFor(current) + edge;
                    target = graphData.getTargetFor(index);
                    if (!visited[target]) {
                        shortest = graphData.getDistanceFor(index) + dijkstraResult[current];
                        if (shortest < dijkstraResult[target]) {
                            dijkstraResult[target] = shortest;
                            dijkstraPath[target] = current;
                            queue.add(new int[] { target, shortest });
                        }
                    }
                }
            }
        }
        hasOneToAllData = true;
    }

    public void calculateShortest(int from, int to) {
        hasOneToAllData = false;
        fromNode = from;
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(value -> value[1]));
        dijkstraResult = new int[graphData.getTotalNodes()];
        Arrays.fill(dijkstraResult, INFINITY);
        dijkstraPath = new int[graphData.getTotalNodes()];
        Arrays.fill(dijkstraPath, NONE);
        boolean[] visited = new boolean[graphData.getTotalNodes()];
        int current, edge, index, target, shortest;
        dijkstraResult[from] = 0;
        queue.add(new int[] { from, 0 });
        while (!queue.isEmpty()) {
            current = queue.poll()[0];
            if (!visited[current]) {
                visited[current] = true;
                for (edge = graphData.getEdgesFor(current); --edge >= 0;) {
                    index = graphData.getOffsetFor(current) + edge;
                    target = graphData.getTargetFor(index);
                    if (!visited[target]) {
                        shortest = graphData.getDistanceFor(index) + dijkstraResult[current];
                        if (shortest < dijkstraResult[target]) {
                            dijkstraResult[target] = shortest;
                            dijkstraPath[target] = current;
                            queue.add(new int[] { target, shortest });
                        }
                    }
                }
            }
            if (current == to) {
                break;
            }
        }
    }

    public int getShortestPathDistance(int toNode) {
        return dijkstraResult[toNode];
    }

    public List<Integer> getShortestPath(int toNode) {
        List<Integer> dijkstraShortestPath = new ArrayList<>();
        if (dijkstraResult == null || fromNode == toNode) {
            return dijkstraShortestPath;
        }
        dijkstraShortestPath.add(toNode);
        int value = dijkstraPath[toNode];
        while (value != NONE && value != fromNode) {
            dijkstraShortestPath.add(value);
            value = dijkstraPath[value];
        }
        dijkstraShortestPath.add(fromNode);
        return dijkstraShortestPath;
    }

    public boolean hasOneToAllData() {
        return hasOneToAllData;
    }

    public int getOneToAllNode() {
        return fromNode;
    }

    public int getClosestNodeId(double lat, double lng) {
        return graphData.getClosestNodeFor(lat, lng);
    }

    public double[] getLatLngFor(int node) {
        return graphData.getLatLngFor(node);
    }



/*

    private static final int NO_PARENT = -1;

    public static DijkstraData computeDijkstra(Graph graph, int startID) {
        if(!(graph.hasEdges(startID))) {
            return new DijkstraData(-1, null, null);
        }
        int[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        int vertexCount = adjacencyMatrix[0].length;
        int[] shortestDistances = new int[vertexCount];
        boolean[] added = new boolean[vertexCount];

        for (int vertexIndex = 0; vertexIndex < vertexCount; vertexIndex++) {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
        }

        shortestDistances[startID] = 0;

        int[] parents = new int[vertexCount];

        parents[startID] = NO_PARENT;

        for (int i = 1; i < vertexCount; i++) {
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < vertexCount; vertexIndex++) {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }
            added[nearestVertex] = true;

            for (int vertexIndex = 0; vertexIndex < vertexCount; vertexIndex++) {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];
                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
        }
        return new DijkstraData(startID, shortestDistances, parents);
    }
*/


}
