import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class CheapestFlights {
    static class Edge{
        int src;
        int dest;
        int wght;

        public Edge(int src,int dest,int wght){
            this.src=src;
            this.dest = dest;
            this.wght =wght;
        }
    }
    public static void createGraph(int flights[][],ArrayList<Edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<flights.length;i++){
            int src = flights[i][0];
            int dest = flights[i][1];
            int wght = flights[i][2];

            graph[src].add(new Edge(src, dest, wght));
        }
    }

    static class Info{
        int node;
        int dist;
        int stops;

        public Info(int node,int dist,int stops){
            this.node=node;
            this.dist = dist;
            this.stops = stops;
        }
    }

    public static int cheapestFlight(int flights[][], int src,int dest,int k){
        ArrayList<Edge> graph[] = new ArrayList[flights.length];
        createGraph(flights, graph);

        int dist[] = new int[graph.length];
        for(int i=0;i<graph.length;i++){
            if(i!=src){
                dist[i] = Integer.MAX_VALUE;
            }
        }

        Queue<Info> q = new LinkedList<>();
        q.add(new Info(src,0,0));
        while(!q.isEmpty()){
            Info curr = q.remove();
            if(curr.stops>k){
                break;
            }

            for(int i=0;i<graph[curr.node].size();i++){
                Edge e = graph[curr.node].get(i);
                int u = e.src;
                int v = e.dest;
                int wght = e.wght;

                if(dist[u]!=Integer.MAX_VALUE && curr.dist+wght<dist[v] && curr.stops<=k){
                    dist[v] = curr.dist+wght;
                    q.add(new Info(v,dist[v],curr.stops+1));
                }
                
            }
        }
        if(dist[dest]==Integer.MAX_VALUE){
            return -1;
        }
        else{
            return dist[dest];
        }

    }
    public static void main(String args[]){
        int flights[][] = {{0,1,100},{1,2,100},{1,3,600},{2,3,200}};
        int src = 0, dst = 3, k=1;

        System.out.println("Cheapest Flights with K stops: "+ cheapestFlight(flights, src, dst, k));

    }
}
