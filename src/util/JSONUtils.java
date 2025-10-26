package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Edge;
import model.Graph;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JSONUtils {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static class InputRoot {
        public List<Graph> graphs;
    }

    public static List<Graph> readInputFile(String path) throws IOException {
        try (Reader r = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)) {
            InputRoot root = GSON.fromJson(r, InputRoot.class);
            if (root == null || root.graphs == null) return Collections.emptyList();
            return root.graphs;
        }
    }

    public static class OutputRoot {
        public List<ResultItem> results = new ArrayList<>();
    }

    public static class ResultItem {
        public int graph_id;
        public InputStats input_stats;
        public AlgoResult prim;
        public AlgoResult kruskal;
    }

    public static class InputStats {
        public int vertices;
        public int edges;
    }

    public static class AlgoResult {
        public List<Edge> mst_edges;
        public double total_cost;
        public long operations_count;
        public double execution_time_ms;
    }
    public static void writeOutputFile(String path, OutputRoot out) throws IOException {
        try (Writer w = new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8)) {
            GSON.toJson(out, w);
        }
    }
}
