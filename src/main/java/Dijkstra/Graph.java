package Dijkstra;

import java.util.Arrays;

public class Graph {

    private int totalNodes;
    private int totalEdges;

    private double[] latitude;
    private double[] longitude;
    private int[] offset;
    private int[] edges;

    private int[] target;
    private int[] distance;

    Graph(int totalNodes, int totalEdges) {
        this.totalNodes = totalNodes;
        this.totalEdges = totalEdges;
        latitude = new double[totalNodes];
        longitude = new double[totalNodes];
        offset = new int[totalNodes];
        Arrays.fill(offset, -1);
        edges = new int[totalNodes];
        target = new int[totalEdges];
        distance = new int[totalEdges];
    }

    public int getTotalNodes() {
        return totalNodes;
    }

    public int getTotalEdges() {
        return totalEdges;
    }

    public double getLatitudeFor(int index) {
        return latitude[index];
    }

    public double getLongitudeFor(int index) {
        return longitude[index];
    }

    public int getOffsetFor(int index) {
        return offset[index];
    }

    public int getEdgesFor(int index) {
        return edges[index];
    }

    public int getTargetFor(int index) {
        return target[index];
    }

    public int getDistanceFor(int index) {
        return distance[index];
    }

    public void setLatitudeFor(int index, double value) {
        latitude[index] = value;
    }

    public void setLongitudeFor(int index, double value) {
        longitude[index] = value;
    }

    public void setOffsetFor(int index, int value) {
        offset[index] = value;
    }

    public void setEdgesFor(int index, int value) {
        edges[index] = value;
    }

    public void setTargetFor(int index, int value) {
        target[index] = value;
    }

    public void setDistanceFor(int index, int value) {
        distance[index] = value;
    }

    public void incrementEdgesFor(int index) {
        edges[index]++;
    }

    public int getClosestNodeFor(double lat, double lng) {
        int result = -1, i;
        double minDist = Double.MAX_VALUE, tempDist, value;
        for (i = 0; i < totalNodes; i++) {
            value = Math.pow(Math.sin(Math.toRadians(lat - latitude[i]) / 2), 2) +
                    Math.pow(Math.sin(Math.toRadians(lng - longitude[i]) / 2), 2) *
                            Math.cos(Math.toRadians(latitude[i])) * Math.cos(Math.toRadians(lat));
            tempDist = 2 * Math.atan2(Math.sqrt(value), Math.sqrt(1 - value)) * 6371000;
            if (tempDist < minDist) {
                minDist = tempDist;
                result = i;
            }
        }
        return result;
    }

    public int getClosestNodeForFast(double lat, double lng) {
        int result = -1, i;
        double minDist = Double.MAX_VALUE, tempDist;
        for (i = 0; i < totalNodes; i++) {
            tempDist = Math.sqrt(Math.pow(lat - latitude[i], 2) + Math.pow(lng - longitude[i], 2));
            if (tempDist < minDist) {
                minDist = tempDist;
                result = i;
            }
        }
        return result;
    }

    public double[] getLatLngFor(int node) {
        return new double[] { latitude[node], longitude[node] };
    }

/*
    private int vertexCount;
    private int edgeCount;
    private int[][] adjacencyMatrix;
    private double[][] vertices;

    public Graph(final int vertexCount, final int edgeCount, final int[][] adjacencyMatrix, final double[][] vertices) {
        this.vertexCount = vertexCount;
        this.edgeCount = edgeCount;
        this.adjacencyMatrix = adjacencyMatrix;
        this.vertices = vertices;
    }

    public boolean hasEdges(int vertex) {
        int length = adjacencyMatrix[0].length;
        for(int i = 0; i < length; i++) {
            if(adjacencyMatrix[vertex][i] != 0) {
                return true;
            }
        }
        return false;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void setVertexCount(final int vertexCount) {
        this.vertexCount = vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void setEdgeCount(final int edgeCount) {
        this.edgeCount = edgeCount;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void setAdjacencyMatrix(final int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public double[][] getVertices() {
        return vertices;
    }

    public void setVertices(final double[][] vertices) {
        this.vertices = vertices;
    }*/


}
