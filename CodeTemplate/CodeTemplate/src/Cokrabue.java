import java.util.*;

public class Cokrabue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        GraphM graph = new GraphM(false, n);
        while (true) {
            String per1 = sc.next();
            if (per1.equals("0"))
                break;
            String per2 = sc.next();
            int perInt1 = per1.charAt(0) - 'A';
            int perInt2 = per2.charAt(0) - 'A';
            graph.addEdge(perInt1, perInt2, 1);
        }

        MyAlgorithm myAl = new MyAlgorithm();
        int q = sc.nextInt();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            String per1 = sc.next();
            String per2 = sc.next();
            int perInt1 = per1.charAt(0) - 'A';
            int perInt2 = per2.charAt(0) - 'A';
            boolean yn = myAl.warshall(graph, perInt1, perInt2);
            result.add(yn ? "Yes" : "No");
        }
        for (String i : result) {
            System.out.println(i);
        }
    }
}
