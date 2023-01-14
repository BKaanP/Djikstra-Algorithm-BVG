import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class Lab {

    public static void main(String[] args) throws IOException {
        // Read the BVG file and store the values in a 2D array
        String[][] bvgValues = readBVGFile();
        System.out.println(bvgValues[0][0]);
        System.out.println(bvgValues[0][1]);
        System.out.println(bvgValues[0][2]);
        // Read the index file and store the values in a hashmap
        HashMap<String, String> index = readIndexFile();

        // Create a map to store the graph
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        // Iterate over the BVG values and add them to the graph
        for (String[] values : bvgValues) {
            String start = values[0];
            Map<String, Integer> neighbors = new HashMap<>();
            for (int i = 1; i < values.length; i++) {
                String[] edge = values[i].split(",");
                neighbors.put(edge[0], Integer.parseInt(edge[1]));
            }
            graph.put(start, neighbors);
//            System.out.println(graph.toString());
        }

        Random random = new Random();

        int startIndex = random.nextInt(bvgValues.length);
        String startNode = bvgValues[startIndex][0];

        int endIndex;
        do {
            endIndex = random.nextInt(bvgValues.length);
        } while (endIndex == startIndex);
        String endNode = bvgValues[endIndex][0];

        // Find the shortest path between the start and end nodes using Dijkstra's
        // algorithm
        Map<String, Integer> distances = dijkstra(graph, "060192001006");

        // Print the distance from the start node to the end node
        System.out.println("Distance from " + index.get("060192001006") + " to " +
                index.get("070201074701") + ": "
                + timeToString(distances.get("070201074701")) + " / "+distances.get("070201074701") + " seconds" );

        // System.out.println(distances.get(endNode) + Integer.MAX_VALUE);
        // System.out.println(endNode);
    }
    // FUNCTION PART

    /**
     * This method transforms the values of the BVG File into a 2D Array containing
     * the starting point as the first value and the connected points as the
     * following values
     * 
     * @return {String[][]} - a String[][] containing each bvg entry
     * @throws IOException
     */
    public static String[][] readBVGFile() throws IOException {
        List<String[]> values = new ArrayList();

        BufferedReader reader = new BufferedReader(new FileReader("bvg.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            values.add(line.split(" "));
        }
        reader.close();

        return values.toArray(new String[0][]);
    }

    public static class Node implements Comparable<Node> {
        public String id;
        public int distance;

        public Node(String id, int distance) {
            this.id = id;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(distance, other.distance);
        }
    }

    public static Map<String, Integer> dijkstra(Map<String, Map<String, Integer>> graph, String start) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();

        // Initialize distances and queue
        for (String node : graph.keySet()) {
            distances.put(node, node.equals(start) ? 0 : 1000000);
            queue.add(new Node(node, distances.get(node)));
        }

        // Process nodes in queue
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            Map<String, Integer> neighbors = graph.get(node.id);
            for (String neighbor : neighbors.keySet()) {
                int distance = node.distance + neighbors.get(neighbor);
                if (distance < distances.get(neighbor)) {
                    distances.put(neighbor, distance);
                    queue.add(new Node(neighbor, distance));
                }
            }
        }
        return distances;
    }

    // UI PART

    /**
     * This method transforms the values of the IndexFile into a HashMap to later on
     * use it to find index values
     * 
     * 
     * @return {Hashmap<String,String>} - a HashMap with the Key being the number
     *         part and the station being the value
     * @throws IOException
     */
    public static HashMap<String, String> readIndexFile() throws IOException {
        HashMap<String, String> map = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader("stations.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(",");
            String key = tokens[0].trim();
            String value = tokens[1].trim();
            map.put(key, value);
        }

        reader.close();

        return map;
    }

    /**
     * Transforms an int value depicting time in seconds into a minutes format
     * 
     * @param time
     * @return {String} - a String containing ' time/60 +" minutes" '
     */
    public static String timeToString(int time) {
        return (double) time / 60 + " minutes";
    }

}
