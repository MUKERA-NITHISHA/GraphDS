import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
public class TopologicalSort{
    static class Edge{
        int src;
        int dest;

        Edge(int src,int dest){
            this.src=src;
            this.dest = dest;
        }
    }
    static void createGraph(ArrayList<Edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,3));
        graph[2].add(new Edge(2,3));

        graph[3].add(new Edge(3, 1));

        graph[4].add(new Edge(4, 0));
        graph[4].add(new Edge(4, 1));

        graph[5].add(new Edge(5, 0));
        graph[5].add(new Edge(5,2));
    }

    public static void topSort(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        Stack<Integer> st = new Stack<>();

        for(int i=0;i<graph.length;i++){
            if(!vis[i]){
                topsortUtil(graph,vis,st,i);
            }
        }

        System.out.println("Topological Sort");
        while(!st.isEmpty()){
            System.out.print(st.pop()+" ");
        }
        System.out.println();
    }

    public static void topsortUtil(ArrayList<Edge> graph[],boolean vis[], Stack<Integer> st,int curr){
        vis[curr] = true;

        for(int i=0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                topsortUtil(graph, vis, st, e.dest);;
            }
        }
        st.push(curr);
    }

    //                     KHAN'S ALGORITHM 

    public static void Calcindegree(ArrayList<Edge> graph[],int indegree[]){
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[i].size();j++){
                Edge e = graph[i].get(j);
                indegree[e.dest]++;
            }
        }
    }

    public static void KhansSort(ArrayList<Edge> graph[]){
        int indegree[] = new int[graph.length];
        Calcindegree(graph, indegree);

        Queue<Integer> q = new LinkedList<>();

        for(int i=0;i<indegree.length;i++){
            if(indegree[i]==0){
                q.add(i);
            }
        }
        System.out.println("Topological Sort Using Khan's Algorithm");

        while(!q.isEmpty()){
            int curr = q.remove();
            System.out.print(curr+" ");

            for(int i=0;i<graph[curr].size();i++){
                Edge e = graph[curr].get(i);
                indegree[e.dest]--;
                if(indegree[e.dest]==0){
                    q.add(e.dest);
                }
            }
        }
        System.out.println();
    }

    //            ALL PATHS FROM SOURCE TO TARGET
    public static void allPaths(ArrayList<Edge> graph[], int src,int dest,String path){
        if(src==dest){
            System.out.println(path+" "+dest);
            return;
        }

        for(int i=0;i<graph[src].size();i++){
            Edge e = graph[src].get(i);
            allPaths(graph, e.dest, dest, path+" "+e.src);
        }

    }
    public static void main(String args[]){
        int v =6;
        ArrayList<Edge> graph[] = new ArrayList[v];
        createGraph(graph);
        topSort(graph);

        //  Topological Sort Using Khan's Algorithm
        KhansSort(graph);

        // ALL Paths From Source To Target
        System.out.println("All Paths from source to target");
        allPaths(graph, 5, 1, "");




    }
}