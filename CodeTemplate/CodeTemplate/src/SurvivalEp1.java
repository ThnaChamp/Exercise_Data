import java.util.*;

public class SurvivalEp1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Integer, List<Integer>> result = new HashMap<>();
        int n = sc.nextInt();
        GraphM graph = new GraphM(true, n);
        for (int i = 0; i < n; i++) {
            int c1 = sc.nextInt();
            int c2 = sc.nextInt();
            int dist = sc.nextInt();
            graph.addEdge(c1, c2, dist);
        }
        boolean[][] check = new boolean[n][n];
        MyAlgorithm myAl = new MyAlgorithm();
        for (int i = 0; i < graph.numVertices; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < graph.numVertices; j++) {
                check[i][j] = myAl.warshall(graph, i, j);
                if (check[i][j] == true) {
                    list.add(j);
                }
            }
            result.put(i, list);
        }

        for (int i = 0; i < graph.numVertices; i++) {
            System.out.println("From Cave " + i + ", reachable caves:");
            System.out.println(result.get(i));
        }
    }
}
