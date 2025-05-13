import java.util.*;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST bst = new BST();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int ver = sc.nextInt();
            bst.insertNode(ver);
        }
        MyAlgorithm myAl = new MyAlgorithm();
        List<Integer> result = new ArrayList<>();
        result = myAl.inorder(bst);
        System.out.print(result);
    }
}