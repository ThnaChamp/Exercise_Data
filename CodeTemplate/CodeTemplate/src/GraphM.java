// implement you code in this file
public class GraphM extends AdjM {
    boolean isDirected;
    int numVertices;
    int numEdges;

    // Constructore
    public GraphM(boolean isDirected, int numVertices) {
        super(numVertices); // เรียก Constructore Class Adjm
        this.numVertices = numVertices;
        this.isDirected = isDirected;
    }

    public void addEdge(int i, int j, int weight) {
        this.matrix[i][j] = weight;
        if (!isDirected) {
            this.matrix[j][i] = weight;
        }
    }
}
