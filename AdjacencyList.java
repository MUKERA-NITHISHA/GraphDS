import java.util.ArrayList;
public class AdjacencyList{
    static class Edge{
        int src;
        int dest;
        int edge;

        Edge(int s, int d, int e){
            this.src = s;
            this.dest = d;
            this.edge = e;
        }
    }
    public static void main(String args[]){
        int v =5;
        ArrayList<Edge> graph[] = new ArrayList[5];

        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }

        // 0th node
        graph[0].add(new Edge(0,1,5));

        //1st vertex
        graph[1].add(new Edge(1,0,5));
        graph[1].add(new Edge(1,2,1));
        graph[1].add(new Edge(1,3,3));

        //2nd vertex
        graph[2].add(new Edge(2,1,1));
        graph[2].add(new Edge(2,3,1));
        graph[2].add(new Edge(2,4,4));

        // 3rd vertex
        graph[3].add(new Edge(3,1,3));
        graph[3].add(new Edge(3,2,1));
        graph[3].add(new Edge(3,1,3));

        // 4th vertex
        graph[4].add(new Edge(4,2,4));

        // 2nd neighbours
        for(int i=0;i<graph[2].size();i++){
            Edge e = graph[2].get(i);
            System.out.println(e.dest);
        }




    }
}