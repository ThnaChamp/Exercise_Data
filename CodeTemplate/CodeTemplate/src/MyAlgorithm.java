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
        if (node == null) {
            return;
        }
        postorderList.add(node.node);
        postorderTraversal(node.leftChild, postorderList);
        postorderTraversal(node.rightChild, postorderList);
    }

    public List<Integer> dfs(GraphM graph, int startNode) {
        boolean[] isVisited = new boolean[graph.numVertices];
        List<Integer> dfsList = new ArrayList<>();
        return dfsUtil(graph, startNode, isVisited, dfsList);
    }

    private List<Integer> dfsUtil(GraphM graph, int startNode, boolean[] isVisited, List<Integer> dfsList) {
        isVisited[startNode] = true; // เดินผ่าน startNode แล้ว
        dfsList.add(startNode);
        for (int i = 0; i < graph.numVertices; i++) {
            if (graph.matrix[startNode][i] != 0 && !isVisited[i]) {
                dfsUtil(graph, i, isVisited, dfsList);
            }
        }
        return dfsList;
    }

    public List<Integer> bfs(GraphL graph, int startNode) {
        boolean[] isVisited = new boolean[graph.numVertices + 1];
        List<Integer> bfsList = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        q.add(startNode);
        isVisited[startNode] = true;

        while (!q.isEmpty()) {
            int current = q.poll();
            bfsList.add(current);
            List<Pair<Integer, Integer>> neighbors = graph.adjacencyList.get(current);
            for (Pair<Integer, Integer> Pair : neighbors) {
                int i = Pair.first;
                if (isVisited[i] == false) {
                    q.add(i);
                    isVisited[i] = true;                                        
                }
            }
        }
        return bfsList;
    }

    public int bfsDistance(GraphL graph, int startNode, int destNode){

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
        int[] minDist = new int[graph.numVertices + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[startNode] = 0;

        Set<Integer> visitedSet = new HashSet<>();
        PriorityQueue<Pair<Integer, Integer>> walker = new PriorityQueue<>();
        walker.add(new Pair<>(0, startNode));

        while (!walker.isEmpty()) {
            int cumulativeDist = walker.peek().first;
            int nodeNow = walker.poll().second;

            if (visitedSet.contains(nodeNow)) {
                continue;
            }
            visitedSet.add(nodeNow);

            if (nodeNow == destNode) {
                return cumulativeDist;
            }

            for (Pair<Integer, Integer> neighbor : graph.adjacencyList.get(nodeNow)) {
                int nodeNext = neighbor.first;
                int weightNext = neighbor.second;

                if (!visitedSet.contains(nodeNext)
                        && cumulativeDist + weightNext < minDist[nodeNext]) {
                    minDist[nodeNext] = cumulativeDist + weightNext;
                    walker.add(new Pair<>(minDist[nodeNext], nodeNext));
                }
            }
        }

        return -1;
    }

    public int dijkstra1(GraphL graph, int startNode, int destNode) {
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

    public BinNode searchNode(BinNode root, int key) {
        if (root == null || root.node == key) {
            return root;
        }

        if (key < root.node) {
            return searchNode(root.leftChild, key);
        } else {
            return searchNode(root.rightChild, key);
        }
    }

    public int height(BinNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.leftChild), height(root.rightChild)) + 1;
    }
}
