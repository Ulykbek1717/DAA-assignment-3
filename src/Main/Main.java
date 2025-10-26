package Main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import algorithms.KruskalAlgorithm;
import algorithms.PrimAlgorithms;
import model.Graph;
import util.JSONUtils;

public class Main {
    public static void main(String[] args) {
        String inputPath = "ass_3_input.json";
        String outputPath = "ass_3_output.json";

        if (args.length >= 1) inputPath = args[0];
        if (args.length >= 2) outputPath = args[1];

        System.out.println("Reading input from: " + new File(inputPath).getAbsolutePath());
        try {
            List<Graph> graphs = JSONUtils.readInputFile(inputPath);
            JSONUtils.OutputRoot outRoot = new JSONUtils.OutputRoot();

            for (Graph g : graphs) {
                System.out.println("Processing graph id=" + g.id + " vertices=" + g.getNumVertices() + " edges=" + g.getNumEdges());

                // Run Prim
                PrimAlgorithms prim = new PrimAlgorithms();
                PrimAlgorithms.Result primRes = prim.run(g);

                // Run Kruskal
                KruskalAlgorithm kruskal = new KruskalAlgorithm();
                KruskalAlgorithm.Result kruskalRes = kruskal.run(g);

                // Build output item
                JSONUtils.ResultItem item = new JSONUtils.ResultItem();
                item.graph_id = g.id;

                JSONUtils.InputStats stats = new JSONUtils.InputStats();
                stats.vertices = g.getNumVertices();
                stats.edges = g.getNumEdges();
                item.input_stats = stats;

                JSONUtils.AlgoResult primOut = new JSONUtils.AlgoResult();
                primOut.mst_edges = new ArrayList<>(primRes.mstEdges);
                primOut.total_cost = roundDouble(primRes.totalCost);
                // For Prim we aggregate two operation counters into one operations_count to match output schema.
                primOut.operations_count = primRes.heapOperations + primRes.neighborChecks;
                primOut.execution_time_ms = roundDouble(primRes.executionTimeMs);
                item.prim = primOut;

                JSONUtils.AlgoResult kruskalOut = new JSONUtils.AlgoResult();
                kruskalOut.mst_edges = new ArrayList<>(kruskalRes.mstEdges);
                kruskalOut.total_cost = roundDouble(kruskalRes.totalCost);
                // Aggregate operations: find + union + comparisons
                kruskalOut.operations_count = kruskalRes.operationsCountFind + kruskalRes.operationsCountUnion + kruskalRes.comparisonChecks;
                kruskalOut.execution_time_ms = roundDouble(kruskalRes.executionTimeMs);
                item.kruskal = kruskalOut;

                outRoot.results.add(item);

                // Console summary
                System.out.printf(" Graph %d — Prim cost=%.2f edges=%d time=%.3fms ops=%d\n",
                        g.id, primOut.total_cost, primOut.mst_edges.size(), primOut.execution_time_ms, primOut.operations_count);
                System.out.printf(" Graph %d — Kruskal cost=%.2f edges=%d time=%.3fms ops=%d\n",
                        g.id, kruskalOut.total_cost, kruskalOut.mst_edges.size(), kruskalOut.execution_time_ms, kruskalOut.operations_count);
            }

            JSONUtils.writeOutputFile(outputPath, outRoot);
            System.out.println("Output written to: " + new File(outputPath).getAbsolutePath());

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static double roundDouble(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}
