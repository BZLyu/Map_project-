package Dijkstra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class GraphFileReader {

    public static Graph readFile(File graphFile) {
        Graph data;
        String[] lineParts;
        int count = 0, index;

        try (BufferedReader reader = new BufferedReader(new FileReader(graphFile))) {
            for (int skip = 0; skip < 5; skip++) {
                reader.readLine();
            }

            int totalNodes = Integer.parseInt(reader.readLine());
            int totalEdges = Integer.parseInt(reader.readLine());
            data = new Graph(totalNodes, totalEdges);
            for (int nodes = 0; nodes < totalNodes; nodes++) {
                lineParts = reader.readLine().split(" ");
                index = Integer.parseInt(lineParts[0]);
                data.setLatitudeFor(index, Double.parseDouble(lineParts[2]));
                data.setLongitudeFor(index, Double.parseDouble(lineParts[3]));
            }
            for (int edges = 0; edges < totalEdges; edges++) {
                lineParts = reader.readLine().split(" ");
                index = Integer.parseInt(lineParts[0]);
                if (data.getOffsetFor(index) == -1) {
                    data.setOffsetFor(index, count);
                }
                data.incrementEdgesFor(index);
                data.setTargetFor(count, Integer.parseInt(lineParts[1]));
                data.setDistanceFor(count++, Integer.parseInt(lineParts[2]));
            }
        } catch (Exception ex) {
            data = null;
            ex.printStackTrace();
        }
        return data;
    }
}
