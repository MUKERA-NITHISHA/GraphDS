import java.util.ArrayList;;
public class DetectCycle {
    static class Edge{
        int src;
        int dest;

        Edge(int src,int dest){
            this.src = src;
            this.dest = dest;
        }
    }

    //        DETECTION OF CYCLE IN UNDIRECTED GRAPH
    public static boolean detectCycle(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];

        for(int i=0;i<graph.length;i++){
            if(!vis[i]){
                if(detectcycleUtil(graph,i,vis,-1)){
                    return true;
                }
            }
        }
        return false;

    }

    public static boolean detectcycleUtil(ArrayList<Edge> graph[], int curr, boolean vis[],int par){
        vis[curr] = true;

        for(int i=0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            // Case - 3
            if(!vis[e.dest]){
                if(detectcycleUtil(graph, e.dest, vis, curr)){
                    return true;
                }
            }
            // case -1
            else if(vis[e.dest] && e.dest!=par){
                return true;
            }
            //case -2 -> do nothing continue
        }
        return false;
    }
    public static void createGraph(ArrayList<Edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,2));

        graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,3));

        graph[2].add(new Edge(2,0));
        graph[2].add(new Edge(2,4));

        graph[3].add(new Edge(3,1));
        //graph[3].add(new Edge(3,4));
        //graph[3].add(new Edge(3,5,1));

        graph[4].add(new Edge(4,2));
        graph[4].add(new Edge(4,3));
        //graph[4].add(new Edge(4,5));  

        //graph[5].add(new Edge(5,3));
        //graph[5].add(new Edge(5,4));
        graph[5].add(new Edge(5,6));

        graph[6].add(new Edge(6,5));
    }

    //               DETECTION OF CYCLE IN DIRECTED GRAPH
    public static boolean isCycle(ArrayList<Edge> graph1[]){
        boolean vis[] = new boolean[graph1.length];
        boolean stack[] = new boolean[graph1.length];

        for(int i=0;i<graph1.length;i++){
            if(!vis[i]){
                if(isCycleUtil(graph1,vis,stack,i)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isCycleUtil(ArrayList<Edge> graph1[],boolean vis[],boolean stack[],int curr){
        vis[curr] = true;
        stack[curr] = true;

        for(int i=0;i<graph1[curr].size();i++){
            Edge e = graph1[curr].get(i);
            if(stack[e.dest]){
                return true;
            }
            if(!vis[e.dest] && isCycleUtil(graph1, vis, stack, e.dest)){
                return true;
            }
        }
        stack[curr] = false;
        return false;
    }
    public static void main(String args[]){
        // Undirected graph
        int v = 7;
        ArrayList<Edge> graph[] = new ArrayList[v];
        createGraph(graph);

        System.out.println("Is Cycle Present? "+detectCycle(graph));

        // Directed Graph
        int v1 =4;
        ArrayList<Edge> graph1[] = new ArrayList[v1];
        for(int i=0;i<graph1.length;i++){
            graph1[i] = new ArrayList<>();
        }

        graph1[0].add(new Edge(0, 2));
        graph1[1].add(new Edge(1, 2));
        graph1[2].add(new Edge(2, 3));
        graph1[3].add(new Edge(3, 0));
        System.out.println("IS Cycle? "+isCycle(graph1));



    }
}
