import java.util.*;

public class NetworkEngineer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ns = sc.nextInt();
        int c = sc.nextInt();
        GraphL graph = new GraphL(false, ns + 1, c);
        for (int i = 0; i < c; i++) {
            int r1 = sc.nextInt();
            int r2 = sc.nextInt();
            int time = sc.nextInt();
            graph.addEdge(r1, r2, time);
        }
        MyAlgorithm myAl = new MyAlgorithm();
        List<Integer> qualityList = new ArrayList<>();
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int quality = 0;
            int si = sc.nextInt();
            for (int node : graph.adjacencyList.keySet()) {
                int time = myAl.dijkstra(graph, si, node);
                if (time > quality) {
                    quality = time;
                }
            }
            qualityList.add(quality);
        }
        for (int i = 0; i < qualityList.size(); i++) {
            System.out.println(qualityList.get(i));
        }
    }
}
