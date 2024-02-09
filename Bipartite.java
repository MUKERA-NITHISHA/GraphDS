import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Bipartite {
    static class Edge{
        int src;
        int dest;

        Edge(int src,int dest){
            this.src = src;
            this.dest = dest;
        }
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
        graph[3].add(new Edge(3,4));
        //graph[3].add(new Edge(3,5,1));

        graph[4].add(new Edge(4,2));
        graph[4].add(new Edge(4,3));
        //graph[4].add(new Edge(4,5));

        //graph[5].add(new Edge(5,3));
        //graph[5].add(new Edge(5,4));
        graph[5].add(new Edge(5,6));

        graph[6].add(new Edge(6,5));

        //graph[7].add(new Edge(7,5));
        //graph[7].add(new Edge(7,6));
    }

    public static boolean bfs(ArrayList<Edge> graph[]){
        int color[] = new int[graph.length];

        for(int i=0;i<graph.length;i++){
            color[i] = -1;
        }

        for(int i=0;i<graph.length;i++){
            if(color[i]==-1){
                if(!isBipartite(graph,color,i)){ // Different components in graph
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isBipartite(ArrayList<Edge> graph[], int color[],int src){
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        color[src] = 0;
        while(!q.isEmpty()){
            int curr = q.remove();
            for(int i=0;i<graph[curr].size();i++){
                Edge e = graph[curr].get(i);
                if(color[e.dest]==-1){
                    int nextcolor = color[curr]==0 ? 1 :0;
                    color[e.dest] = nextcolor;
                    q.add(e.dest);
                }
                else if(color[e.dest]==color[curr]){
                    return false;
                }
            }
        }
        return true;

    }
    public static void main(String args[]){
        int v =8;
        ArrayList<Edge> graph[] = new ArrayList[v];
        createGraph(graph);

        System.out.println("Bipartite Graph");
        if(bfs(graph)){
            System.out.println("Is Bipartite? "+"Yes");
        }
        else{
            System.out.println("Is Bipartite? "+"NO");
        }

    }
}
