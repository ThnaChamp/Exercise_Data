import java.util.*;

public class BalanceMode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST process = new BST();
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(3);
        list.add(2);
        list.add(4);
        list.add(1);
        list.add(6);
        for (int i = 0; i < list.size(); i++) {
            process.insertNode(list.get(i));
        }
        MyAlgorithm myAl = new MyAlgorithm();
        int id = sc.nextInt();
        int balanceFactor = myAl.balanceFactor(process, id);
        System.out.println(balanceFactor);
        
    }
}
