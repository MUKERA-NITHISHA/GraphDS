import java.util.ArrayList;
public class TarjansAlgo {
    static class Edge{
        int src;
        int dest;

        public Edge(int s,int d){
            this.src = s;
            this.dest = d;
        }
    }
    public static void creategraph(ArrayList<Edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 2));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 1));

        graph[3].add(new Edge(3, 0));
        graph[3].add(new Edge(3, 4));
        graph[3].add(new Edge(3, 5));

        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));

        graph[5].add(new Edge(5,3));
        graph[5].add(new Edge(5,4));

    }

    public static void dfs(ArrayList<Edge> grap[],int dt[],int lt[],int par,int time,int curr,boolean vis[]){
        vis[curr] = true;
        dt[curr] = lt[curr] = time++;

        for(int i=0;i<grap[curr].size();i++){
            Edge e = grap[curr].get(i);
            int neigh = e.dest;
            if(neigh==par){
                continue;
            }
            if(!vis[neigh]){
                dfs(grap, dt, lt, curr, time, neigh, vis);
                lt[curr] = Math.min(lt[curr],lt[neigh]);
                if(dt[curr]<lt[neigh]){
                    System.out.println("Bridge : "+ curr + " ----- " + neigh);
                }
            }
            else{
                lt[curr] = Math.min(lt[curr],dt[neigh]);
            }

        }

    }

    public static void tarjanBridge(ArrayList<Edge> graph[], int v){
        int dt[] = new int[v];
        int lt[] = new int[v];
        int time =0;
        boolean vis[] = new boolean[v];

        for(int i=0;i<v;i++){
            if(!vis[i]){
                dfs(graph, dt, lt, -1, time, i, vis);
            }
        }
    }

    //                   ARTICULATION POINT
    public static void dfsAP(ArrayList<Edge> graph[],int curr,int par,int dt[], int lt[], int time,boolean vis[],boolean ap[]){
        vis[curr] = true;
        dt[curr] = lt[curr] = time++;
        int children = 0;

        for(int i=0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            int neigh = e.dest;

            if(neigh==par){
                continue;
            }

            if(vis[neigh]){
                lt[curr] = Math.min(lt[curr], dt[neigh]);
            }

            if(!vis[neigh]){
                dfsAP(graph,neigh,curr,dt,lt,time,vis,ap);
                lt[curr] = Math.min(lt[curr], lt[neigh]);
                if(par!=-1 && dt[curr]<=lt[neigh]){
                    ap[curr] = true;
                }
                children++;
            }
        }
        if(par==-1 && children>1){
            ap[curr] = true;
        }
    }

    public static void getAP(ArrayList<Edge> graph[],int v){
        int dt[] = new int[v];
        int lt[] = new int[v];
        int time =0;
        boolean vis[] = new boolean[v];
        boolean ap[] = new boolean[v];

        for(int i=0;i<v;i++){
            if(!vis[i]){
                dfsAP(graph, i, -1, dt, lt, time, vis,ap);
            }
        }
        

        for(int i=0;i<v;i++){
            if(ap[i]){
                System.out.println("AP: "+i);
            }
        }

    }
    public static void main(String args[]){
        int v = 6;
        ArrayList<Edge> graph[] = new ArrayList[v];
        creategraph(graph);
        tarjanBridge(graph, v);

        System.out.println("Articulation Point");
        getAP(graph, v);

    }
}
