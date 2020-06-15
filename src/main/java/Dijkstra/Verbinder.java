package Dijkstra;

import java.io.File;
import java.util.List;

public class Verbinder {

    public static Dijkstra dijkstra = null;
    private static String fullPath = null;


    public static Dijkstra getDijkstra() {
        if (dijkstra == null) {
            dijkstra = new Dijkstra(GraphFileReader.readFile(new File(fullPath)));
        }
        return dijkstra;
    }

    public static void setPath(String path) {
        fullPath = path;
    }


}
