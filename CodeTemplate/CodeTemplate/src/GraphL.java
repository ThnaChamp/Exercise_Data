//implement you code in this file

import java.util.ArrayList;

public class GraphL extends AdjL {
    boolean isDirected;
    int numVertices;
    int numEdges;

    // Constructore
    public GraphL(boolean isDirected, int numVertices, int numEdges) {
        super(); // เรียก Constructore Class Adjm
        this.isDirected = isDirected;
        this.numVertices = numVertices;
        this.numEdges = numEdges;
    }

    public void addEdge(int src, int dest, int weight) {
        this.adjacencyList.putIfAbsent(src, new ArrayList<>());
        this.adjacencyList.putIfAbsent(dest, new ArrayList<>());

        this.adjacencyList.get(src).add(new Pair<>(dest, weight));
        if(!this.isDirected){
            this.adjacencyList.get(dest).add(new Pair<>(src, weight));
        }
    }

}
