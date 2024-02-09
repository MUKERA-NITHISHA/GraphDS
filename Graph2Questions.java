import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.management.AttributeChangeNotificationFilter;

public class Graph2Questions {
    static class Edge {
        int src;
        int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    // MOTHER VERTEX
    public static int motherVertex(ArrayList<Edge> graph[], int v) {
        boolean vis[] = new boolean[v];
        int ver = -1;
        for (int i = 0; i < v; i++) {
            if (!vis[i]) {
                dfsUtil(graph, vis, i);
                ver = i;
            }
        }

        boolean check[] = new boolean[v];
        dfsUtil(graph, check, ver);
        for (boolean c : check) {
            if (!c) {
                return -1;
            }
        }
        return ver;
    }

    public static void dfsUtil(ArrayList<Edge> graph[], boolean vis[], int curr) {
        vis[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.dest]) {
                dfsUtil(graph, vis, e.dest);
            }
        }
    }

    // UNION-FIND ALGORITHM
    static int n = 4;
    static int par[] = new int[n];
    static int rank[] = new int[n];

    public static void init() {
        for (int i = 0; i < n; i++) {
            par[i] = i;
        }
    }

    static void createGraph(ArrayList<Edge> edges) {

        edges.add(new Edge(0, 1));
        edges.add(new Edge(0, 2));
        edges.add(new Edge(0, 3));
        // edges.add(new Edge(1,3));
        // edges.add(new Edge(2,3));
    }

    public static int find(int x) {
        if (x == par[x]) {
            return x;
        }

        return par[x] = find(par[x]);
    }

    public static void union(int a, int b) {
        int parA = find(a);
        int parB = find(b);

        if (rank[parA] == rank[parB]) {
            par[parB] = parA;
            rank[parA]++;
        } else if (rank[parA] < rank[parB]) {
            par[parA] = parB;
        } else {
            par[parB] = parA;
        }

    }

    public static boolean detectCycle(ArrayList<Edge> edges, int n) {
        init();

        for (Edge e : edges) {
            if (find(e.src) != find(e.dest)) {
                union(e.src, e.dest);
            } else if (find(e.src) == find(e.dest)) {
                return true;
            }
        }
        return false;
    }

    // COMPLETE TASK
    public static void findDegree(ArrayList<Edge> list[], int indegree[]) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].size(); j++) {
                Edge e = list[i].get(j);
                indegree[e.dest]++;
            }
        }
    }

    public static boolean completeTask(ArrayList<Edge> list[], int tasks) {
        // indegree
        int indegree[] = new int[tasks];
        findDegree(list, indegree);

        Queue<Integer> q = new LinkedList<>();
        int flag = 0;
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                flag = 1;
            }
        }
        if (flag == 0) {
            return false;
        }

        while (!q.isEmpty()) {
            int curr = q.remove();
            for (int i = 0; i < list[curr].size(); i++) {
                Edge e = list[curr].get(i);
                indegree[e.dest]--;
                if (indegree[e.dest] == 0) {
                    q.add(e.dest);
                }
            }
        }

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] > 0) {
                return false;
            }
        }
        return true;
    }

    // ALIEN DICTIONARY

    public static boolean isCycle(ArrayList<Edge> graph[], boolean vis[], boolean stack[], int curr) {
        vis[curr] = true;
        stack[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (stack[e.dest]) {
                return true;
            }
            if (!vis[curr] && isCycle(graph, vis, stack, e.dest)) {
                return true;
            }
        }
        stack[curr] = false;
        return false;
    }

    public static void topSort(ArrayList<Edge> graph[], Stack<Integer> st, boolean check[], int curr) {
        check[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!check[e.dest]) {
                topSort(graph, st, check, e.dest);
            }
        }
        st.push(curr);
    }

    public static int printOrder(String dict[], int K) {
        // Create graph
        ArrayList<Edge> graph[] = new ArrayList[K];
        // String dict[] = {"baa","abcd","cab","cad"};
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < dict.length - 1; i++) {
            char word1[] = dict[i].toCharArray();
            char word2[] = dict[i + 1].toCharArray();
            int w = 0;
            while (w < word1.length && w < word2.length) {
                if (word1[w] != word2[w]) {
                    graph[word1[w] - 'a'].add(new Edge(word1[w] - 'a', word2[w] - 'a'));
                    break;
                }
                w++;
            }
        }

        // Cycle Detection
        boolean vis[] = new boolean[graph.length];
        boolean stack[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!vis[i]) {
                if (isCycle(graph, vis, stack, i)) {
                    return 0;
                }
            }
        }

        // topological Sorting
        boolean check[] = new boolean[K];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < graph.length; i++) {
            if (!check[i]) {
                topSort(graph, st, check, i);
            }
        }

        System.out.println("Order of Characters in dictionary");
        while (!st.isEmpty()) {
            int ch = 'a' + st.pop();
            System.out.print((char) ch + " ");
        }
        System.out.println();
        return 1;
    }

    // FIND ISLANDS
    static int isRow = 5;
    static int isCol = 8;

    public static boolean isCornerCell(int r, int c) {
        return (r == 0 || c == 0 || r == isRow - 1 || c == isCol - 1);
    }

    public static boolean countIslandsUtil(int islands[][], boolean vis[][], int r, int c) {
        vis[r][c] = true;
        int row[] = { 1, -1, 0, 0 };
        int col[] = { 0, 0, -1, 1 };

        for (int i = 0; i < 4; i++) {
            if (isCornerCell(r + row[i], c + col[i]) && islands[r+row[i]][c+col[i]]==1) {
                return false;
            } 
            else if (islands[r + row[i]][c + col[i]] == 1 && !vis[r + row[i]][c + col[i]]) {
                if (countIslandsUtil(islands, vis, r + row[i], c + col[i])) {
                    return true;
                } 
            }
        }
        return true;
    }

    public static void countIslands(int islands[][]) {
        int count = 0;
        boolean vis[][] = new boolean[islands.length][islands[0].length];

        for (int i = 0; i < islands.length; i++) {
            for (int j = 0; j < islands[0].length; j++) {
                if (islands[i][j] == 1 && !vis[i][j] && !isCornerCell(i, j)) {
                    if (countIslandsUtil(islands, vis, i, j)) {
                        System.out.println(i + " " + j);
                        count++;
                    }
                }
            }
        }
        System.out.println("Total Islands are: " + count);
    }

    public static void main(String args[]) {
        // MOTHER VERTEX IN GRAPH
        int v = 5;
        ArrayList<Edge> graph[] = new ArrayList[v];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 2));

        graph[2].add(new Edge(2, 1));

        graph[3].add(new Edge(3, 4));
        System.out.println("Mother of the Graph: " + motherVertex(graph, v));

        // UNION FIND ALGORITHM FOR DETECTION OF CYCLE IN UNDIRECTED GRAPH
        ArrayList<Edge> edges = new ArrayList<>();
        createGraph(edges);
        System.out.println("Cycle Present? " + detectCycle(edges, v));

        // Find whether it is possible to finish all tasks or not
        // int task[][] = {{1,0},{2,1},{3,2}};
        // int tasks = 4;
        int task[][] = { { 1, 0 }, { 0, 1 } };
        int tasks = 2;
        ArrayList<Edge> list[] = new ArrayList[tasks];
        for (int i = 0; i < tasks; i++) {
            list[i] = new ArrayList<>();
        }

        /*
         * list[0].add(new Edge(0,1));
         * list[1].add(new Edge(1,2));
         * list[2].add(new Edge(2,3));
         */
        list[0].add(new Edge(0, 1));
        list[1].add(new Edge(1, 0));
        System.out.println("Task Completed: " + completeTask(list, tasks));

        // ALIEN DICTIONARY
        int N = 3;
        int K = 3; // no of alphabets in dictionary
        // String dict[] = {"baa","abcd","cab","cad"};
        String dict[] = { "caa", "aaa", "aab" };
        if (printOrder(dict, K) == 0) {
            System.out.println("Order Doesn't Exist");

        }

        // Find number of closed islands
        int islands[][] = { { 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 1, 1, 1, 1, 0, 0, 1 },
                { 0, 1, 0, 1, 0, 0, 0, 1 },
                { 0, 1, 1, 1, 1, 0, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1 } };
        countIslands(islands);
    }
}
