//implement you code in this file
public class BST extends BinTree {
    public BST() {
        super();
    }

    public void insertNode(int node) {
        BinNode newNode = new BinNode(node);
        if (root == null) { //ครั้งแรก
            root = newNode;
        } else {
            insertNode(root, newNode);
        }
    }

    private void insertNode(BinNode parent, BinNode newNode) {
        if (newNode.node < parent.node) { 
            if (parent.leftChild == null) {
                parent.leftChild = newNode;
            } else {
                insertNode(parent.leftChild, newNode);
            }
        } else {
            if (parent.rightChild == null) {
                parent.rightChild = newNode;
            }
            else{
                insertNode(parent.rightChild,newNode);
            }
        }
    }
}
