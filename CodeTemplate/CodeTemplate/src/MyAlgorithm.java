//implement your algorithm here

import java.util.*;

public class MyAlgorithm extends Algorithm {

    public MyAlgorithm() {
    }

    public List<Integer> preorder(BST tree) {
        List<Integer> preorderList = new ArrayList<>();
        preorderTraversal(tree.root, preorderList);
        return preorderList;
    }

    private void preorderTraversal(BinNode node, List<Integer> preorderList) {
        if (node == null) {
            return;
        }
        preorderList.add(node.node);
        preorderTraversal(node.leftChild, preorderList);
        preorderTraversal(node.rightChild, preorderList);
    }

    public List<Integer> inorder(BST tree) {
        List<Integer> inorderList = new ArrayList<>();
        inorderTraversal(tree.root, inorderList);
        return inorderList;
    }

    private void inorderTraversal(BinNode node, List<Integer> inorderList) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.leftChild, inorderList);
        inorderList.add(node.node);
        inorderTraversal(node.rightChild, inorderList);
    }

    public List<Integer> postorder(BST tree) {
        List<Integer> postorderList = new ArrayList<>();
        postorderTraversal(tree.root, postorderList);
        return postorderList;
    }

    private void postorderTraversal(BinNode node, List<Integer> postorderList) {
        postorderTraversal(node.leftChild, postorderList);
        postorderTraversal(node.rightChild, postorderList);
        postorderList.add(node.node);
    }

    public List<Integer> dfs(GraphM graph, int startNode) {
        boolean[] isVisited = new boolean[graph.numVertices];
        Arrays.fill(isVisited, false);
        List<Integer> result = new ArrayList<>();
        dfsUtil(graph, startNode, isVisited, result);
        return result;
    }

    private void dfsUtil(GraphM graph, int startNode, boolean[] isVisited, List<Integer> dfsList) {
        if (!isVisited[startNode]) {
            isVisited[startNode] = true;
            dfsList.add(startNode);
            for (int i = 0; i < graph.numVertices; i++) {
                if (graph.matrix[startNode][i] == 0 && !isVisited[i]) {
                    dfsUtil(graph, i, isVisited, dfsList);
                }
            }
        }
    }

    public List<Integer> bfs(GraphL graph, int startNode) {
        boolean[] isVisited = new boolean[graph.numVertices];
        List<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        isVisited[startNode] = true;
        q.add(startNode);

        while (!q.isEmpty()) {
            int curNode = q.poll();
            result.add(curNode);
            List<Pair<Integer, Integer>> neighbors = graph.adjacencyList.get(curNode);
            if (neighbors != null) {
                for (Pair<Integer, Integer> Pair : neighbors) {
                    int adj = Pair.first;
                    if (!isVisited[adj]) {
                        isVisited[adj] = true;
                        q.add(adj);
                    }
                }
            }
        }
        return result;
    }

    public int bfsDistance(GraphL graph, int startNode, int destNode) {
        boolean[] isVisited = new boolean[graph.numVertices];
        int[] dist = new int[graph.numVertices];
        Queue<Integer> q = new LinkedList<>();
        isVisited[startNode] = true;
        q.add(startNode);

        while (!q.isEmpty()) {
            int curNode = q.poll();
            List<Pair<Integer, Integer>> neighbors = graph.adjacencyList.get(curNode);
            if (neighbors != null) {
                for (Pair<Integer, Integer> Pair : neighbors) {
                    int adj = Pair.first;
                    if (!isVisited[adj]) {
                        isVisited[adj] = true;
                        dist[adj] = dist[curNode] + 1;
                        if (adj == destNode) {
                            return dist[destNode];
                        }
                        q.add(adj);
                    }
                }
            }
        }
        return -1;
    }

    public int floyd(GraphM graph, int startNode, int destNode) {
        int[][] dist = new int[graph.numVertices][graph.numVertices];
        int inf = Integer.MAX_VALUE / 2;
        for (int i = 0; i < graph.numVertices; i++) {
            for (int j = 0; j < graph.numVertices; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else if (graph.matrix[i][j] != 0) {
                    dist[i][j] = graph.matrix[i][j];
                } else {
                    dist[i][j] = inf;
                }
            }
        }

        for (int k = 0; k < graph.numVertices; k++) {
            for (int i = 0; i < graph.numVertices; i++) {
                for (int j = 0; j < graph.numVertices; j++) {
                    if ((dist[i][k] != inf && dist[k][j] != inf) && (dist[i][k] + dist[k][j] < dist[i][j])) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        if (dist[startNode][destNode] == inf) {
            return -1;
        } else {
            return dist[startNode][destNode];
        }
    }

    public boolean warshall(GraphM graph, int startNode, int destNode) {
        boolean[][] reach = new boolean[graph.numVertices][graph.numVertices];
        for (int i = 0; i < graph.numVertices; i++) {
            for (int j = 0; j < graph.numVertices; j++) {
                reach[i][j] = (i == j) || (graph.matrix[i][j] != 0);
            }
        }

        for (int k = 0; k < graph.numVertices; k++) {
            for (int i = 0; i < graph.numVertices; i++) {
                for (int j = 0; j < graph.numVertices; j++) {
                    if (reach[i][j] || (reach[i][k] && reach[k][j])) {
                        reach[i][j] = true;
                    }
                }
            }
        }
        return reach[startNode][destNode];
    }

    public int dijkstra(GraphL graph, int startNode, int destNode) {
        boolean[] isVisited = new boolean[graph.numVertices];
        int[] dist = new int[graph.numVertices];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startNode] = 0;

        for (int i = 0; i < graph.numVertices; i++) {
            int curNode = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < graph.numVertices; j++) {
                if (dist[j] < min && !isVisited[j]) {
                    min = dist[j];
                    curNode = j;
                }
            }
            if (curNode == -1) {
                break;
            }
            isVisited[curNode] = true;
            List<Pair<Integer, Integer>> neighbors = graph.adjacencyList.get(curNode);
            for (Pair<Integer, Integer> p : neighbors) {
                int neighborNode = p.first;
                int weight = p.second;
                if (!isVisited[neighborNode] && (weight + dist[curNode]) < dist[neighborNode]) {
                    dist[neighborNode] = weight + dist[curNode];

                }
            }
        }
        if (dist[destNode] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return dist[destNode];
        }
    }

    public int balanceFactor(BST tree, int node) {
        BinNode target = searchNode(tree.root, node);
        if (target == null) {
            return 0;
        }
        int heightLeft = height(target.leftChild);
        int heightRight = height(target.rightChild);
        return heightLeft - heightRight;
    }

    private BinNode searchNode(BinNode root, int key) {
        if (root.node == key || root == null) {
            return root;
        }
        if (key < root.node) {
            return searchNode(root.leftChild, key);
        } else {
            return searchNode(root.rightChild, key);
        }
    }

    private int height(BinNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.leftChild), height(root.rightChild));
    }
}
