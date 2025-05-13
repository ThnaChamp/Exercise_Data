
import java.util.*;

public class TestBFS {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        GraphL graph = new GraphL(false, n, e);
        for (int i = 0; i < e; i++) {
            int ver1 = sc.nextInt();
            int ver2 = sc.nextInt();
            int weight = sc.nextInt();
            graph.addEdge(ver1, ver2, weight);
        }
        MyAlgorithm myAl = new MyAlgorithm();
        List<Integer> result = new ArrayList<>();
        int startNode = sc.nextInt();
        int destNode = sc.nextInt();
        result = myAl.bfs(graph, startNode);
        System.out.println(result);
        int distance = myAl.bfsDistance1(graph, startNode, destNode);
        System.out.println(distance);
    }
}
