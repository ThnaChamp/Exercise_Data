import java.util.*;

public class SurvivalEp2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        GraphL graph = new GraphL(false, n, m);
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.addEdge(u, v, 1);
        }
        int s = sc.nextInt();
        int v = sc.nextInt();
        int z = sc.nextInt();
        MyAlgorithm myAl = new MyAlgorithm();
        int sv = myAl.dijkstra(graph, s, v);
        int zv = myAl.dijkstra(graph, z, v);
        System.out.println(sv <= zv ? "SAFE" : "TOO LATE");
    }
}
