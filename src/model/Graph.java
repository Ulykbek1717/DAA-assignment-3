package model;


import java.util.List;

public class Graph {
    public int id;
    public List<String> nodes;
    public List<Edge> edges;

    public Graph() {}

    public int getNumVertices() {
        return nodes == null ? 0 : nodes.size();
    }

    public int getNumEdges() {
        return edges == null ? 0 : edges.size();
    }
}