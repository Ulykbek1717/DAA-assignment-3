package algorithms;

import model.Edge;
import model.Graph;

import java.util.*;

public class PrimAlgorithms {
    public static class Result {
        public List<Edge> mstEdges = new ArrayList<>();
        public double totalCost = 0.0;
        public long neighborChecks = 0;
        public long heapOperations = 0;
        public double executionTimeMs = 0.0;
    }

    private static class PQEntry implements Comparable<PQEntry> {
        double weight;
        String from;
        String to;
        public PQEntry(double weight, String from, String to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
        @Override
        public int compareTo(PQEntry o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    public Result run(Graph graph) {
        Result res = new Result();
        long start = System.nanoTime();

        if (graph.nodes == null || graph.nodes.isEmpty()) {
            res.executionTimeMs = 0.0;
            return res;
        }

        // build adjacency list
        Map<String, List<Edge>> adj = new HashMap<>();
        for (String v : graph.nodes) adj.put(v, new ArrayList<>());
        for (Edge e : graph.edges) {
            adj.get(e.from).add(new Edge(e.from, e.to, e.weight));
            adj.get(e.to).add(new Edge(e.to, e.from, e.weight));
        }

        Set<String> visited = new HashSet<>();
        PriorityQueue<PQEntry> pq = new PriorityQueue<>();

        String startNode = graph.nodes.get(0);
        visited.add(startNode);
        // push initial neighbors
        for (Edge nb : adj.get(startNode)) {
            pq.add(new PQEntry(nb.weight, nb.from, nb.to));
            res.heapOperations++;
        }

        while (!pq.isEmpty() && visited.size() < graph.getNumVertices()) {
            PQEntry e = pq.poll();
            res.heapOperations++;
            // if 'to' already visited, skip
            res.neighborChecks++;
            if (visited.contains(e.to)) continue;
            // add edge
            visited.add(e.to);
            res.mstEdges.add(new Edge(e.from, e.to, e.weight));
            res.totalCost += e.weight;
            // push neighbors of new node
            for (Edge nb : adj.get(e.to)) {
                res.neighborChecks++;
                if (!visited.contains(nb.to)) {
                    pq.add(new PQEntry(nb.weight, nb.from, nb.to));
                    res.heapOperations++;
                }
            }
        }

        long end = System.nanoTime();
        res.executionTimeMs = (end - start) / 1_000_000.0;
        return res;
    }
}