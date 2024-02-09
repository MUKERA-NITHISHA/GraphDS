import java.util.ArrayList;
import java.util.Collections;

public class DisjointSet {
    static class Edge implements Comparable<Edge>{
        int src;
        int dest;
        int wght;

        public Edge(int src,int dest,int wght){
            this.src=src;
            this.dest=dest;
            this.wght=wght;
        }

        @Override
        public  int compareTo(Edge e2){
            return this.wght - e2.wght;
        }
    }
    static int n = 4;
    static int par[] = new int[n];
    static int rank[] = new int[n];

    public static void init(){
        for(int i=0;i<n;i++){
            par[i] = i;
        }
    }

    static void createGraph(ArrayList<Edge> edges){
        
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 15));
        edges.add(new Edge(0, 3,30));
        edges.add(new Edge(1,3, 40));
        edges.add(new Edge(2,3, 50));
    }

    public static int find(int x){
        if(x==par[x]){
            return x;
        }

        return par[x]=find(par[x]);
    }

    public static void union(int a,int b){
        int parA = find(a);
        int parB = find(b);

        if(rank[parA]==rank[parB]){
            par[parB] = parA;
            rank[parA]++;
        }
        else if(rank[parA]<rank[parB]){
            par[parA] = parB;
        }
        else{
            par[parB] = parA;
        }

    }

    public static void kruskal(ArrayList<Edge> edges,int V){
        init();
        Collections.sort(edges);
        int ans = 0;
        int count = 0;

        for(int i=0; count<V-1;i++){
            Edge e = edges.get(i);
            if(find(e.src)!=find(e.dest)){
                union(e.src, e.dest);
                ans+=e.wght;
                count++;
            }
        }
        System.out.println(ans);
    }
    public static void main(String args[]){
        /*System.out.println(find(3));
        union(1, 3);
        System.out.println(find(3));
        union(2, 4);
        union(3, 6);
        union(1, 4);
        System.out.println(find(3));
        System.out.println(find(4));
        union(1, 5);*/


        //         KRUSKAL'S ALGORITHM
        int v =4;
        ArrayList<Edge> edges = new ArrayList<>();
        createGraph(edges);
        kruskal(edges, v);




    }
}
