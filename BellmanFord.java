import java.util.ArrayList;
public class BellmanFord {
    static class Edge{
        int src;
        int dest;
        int wght;

        public Edge(int src,int dest,int wght){
            this.src = src;
            this.dest = dest;
            this.wght = wght;
        }
    }
    public static void createGraph(ArrayList<Edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,1,2));
        graph[0].add(new Edge(0,2,4));

        graph[1].add(new Edge(1,2,-4));

        graph[2].add(new Edge(2,3,2));

        graph[3].add(new Edge(3, 4, 4));
        graph[4].add(new Edge(4,1,-1));

    }
    public static void bellman(ArrayList<Edge> graph[],int src){
        int dist[] = new int[graph.length];
        for(int i=0;i<graph.length;i++){
            if(i!=src){
                dist[i] = Integer.MAX_VALUE;
            }
        }

        for(int i=0;i<graph.length-1;i++){
            for(int j=0;j<graph.length;j++){
                for(int k=0;k<graph[j].size();k++){
                    Edge e = graph[j].get(k);
                    int u = e.src;
                    int v = e.dest;
                    int w = e.wght;
                    if(dist[u]!=Integer.MAX_VALUE && dist[u]+w<dist[v]){
                        dist[v] = dist[u] +w;
                    }
                }
            }
        }
        System.out.println("Ddistances from Source to All Vertices");
        for(int i=0;i<dist.length;i++){
            System.out.println(src+"->"+i +"="+dist[i]);
        }

    }
    public static void main(String args[]){
        int v = 5;
        ArrayList<Edge> graph[] = new ArrayList[v];
        createGraph(graph);
        bellman(graph, 0);


    }
}
