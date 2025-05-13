
import java.util.*;

public class MagicPowder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        GraphL graph = new GraphL(false, n + 1, e);
        int center1 = 0;
        int center2 = 0;
        for (int i = 0; i < e; i++) {
            int ver1 = sc.nextInt();
            String bond = sc.next();
            int ver2 = sc.nextInt();
            if (bond.equals("=")) {
                center1 = ver1;
                center2 = ver2;
            }
            graph.addEdge(ver1, ver2, 1);
        }

        List<Integer> leaf = new ArrayList<>();

        for (int node : graph.adjacencyList.keySet()) {
            if (graph.adjacencyList.get(node).size() == 1) {
                leaf.add(node);
            }
        }

        MyAlgorithm myAl = new MyAlgorithm();

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < leaf.size(); i++) {
            int minCheck1 = myAl.dijkstra1(graph, center1, leaf.get(i));
            if (minCheck1 < min) {
                min = minCheck1;
            }
            int minCheck2 = myAl.dijkstra1(graph, center2, leaf.get(i));
            if (minCheck2 < min) {
                min = minCheck2;
            }
        }
        System.out.print(min + 1);
    }
}