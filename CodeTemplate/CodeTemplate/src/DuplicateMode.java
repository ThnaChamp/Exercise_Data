import java.util.*;

public class DuplicateMode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST process = new BST();
        List<Integer> list = new ArrayList<>();
        list.add(4537);
        list.add(3844);
        list.add(1231);
        list.add(4074);
        list.add(1088);
        list.add(8972);
        for (int i = 0; i < list.size(); i++) {
            process.insertNode(list.get(i));
        }
        MyAlgorithm myAl = new MyAlgorithm();
        List<Integer> result1 = new ArrayList<>();
        result1 = myAl.preorder(process);
        System.out.println("Process: " + result1);
        BST newProcess = new BST();
        for (int i = 0; i < result1.size(); i++) {
            newProcess.insertNode(result1.get(i));
        }
        
        List<Integer> result2 = new ArrayList<>();
        result2 = myAl.preorder(newProcess); 
        System.out.println("New Process: " + result2);
    }
}
