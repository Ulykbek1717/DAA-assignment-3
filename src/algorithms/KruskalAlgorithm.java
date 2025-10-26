package algorithms;

import model.Edge;
import model.Graph;

import java.util.*;
import java.util.stream.Collectors;

public class KruskalAlgorithm {
    public static class Result {
        public List<Edge> mstEdges = new ArrayList<>();
        public double totalCost = 0.0;
        public long operationsCountFind = 0;
        public long operationsCountUnion = 0;
        public long comparisonChecks = 0;
        public double executionTimeMs = 0.0;
    }

    private static class UnionFind {
        private final Map<String, String> parent = new HashMap<>();
        private final Map<String, Integer> rank = new HashMap<>();
        private long findCalls = 0;
        private long unionCalls = 0;

        public UnionFind(Collection<String> elements) {
            for (String e : elements) {
                parent.put(e, e);
                rank.put(e, 0);
            }
        }

        public String find(String x) {
            findCalls++;
            String p = parent.get(x);
            if (p == null) return null;
            if (!p.equals(x)) {
                String root = find(p);
                parent.put(x, root);
                return root;
            }
            return p;
        }

        public boolean union(String x, String y) {
            unionCalls++;
            String rx = find(x);
            String ry = find(y);
            if (rx == null || ry == null) return false;
            if (rx.equals(ry)) return false;
            int rxRank = rank.getOrDefault(rx, 0);
            int ryRank = rank.getOrDefault(ry, 0);
            if (rxRank < ryRank) {
                parent.put(rx, ry);
            } else {
                parent.put(ry, rx);
                if (rxRank == ryRank) {
                    rank.put(rx, rxRank + 1);
                }
            }
            return true;
        }

        public long getFindCalls() { return findCalls; }
        public long getUnionCalls() { return unionCalls; }
    }

    public Result run(Graph graph) {
        Result res = new Result();
        long start = System.nanoTime();

        // Sort edges by weight ascending
        List<Edge> sorted = graph.edges.stream()
                .sorted(Comparator.comparingDouble(e -> e.weight))
                .collect(Collectors.toList());

        UnionFind uf = new UnionFind(graph.nodes);

        for (Edge e : sorted) {
            res.comparisonChecks++;
            String u = e.from;
            String v = e.to;
            String ru = uf.find(u); // increments findCalls inside
            String rv = uf.find(v);
            // count of find calls will be reported later
            if (!Objects.equals(ru, rv)) {
                boolean merged = uf.union(u, v);
                if (merged) {
                    res.mstEdges.add(new Edge(u, v, e.weight));
                    res.totalCost += e.weight;
                }
            }
            // stop early if MST complete
            if (res.mstEdges.size() == graph.getNumVertices() - 1) break;
        }

        long end = System.nanoTime();
        res.executionTimeMs = (end - start) / 1_000_000.0;
        res.operationsCountFind = uf.getFindCalls();
        res.operationsCountUnion = uf.getUnionCalls();
        return res;
    }
}
