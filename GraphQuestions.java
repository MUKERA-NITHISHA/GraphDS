import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
public class GraphQuestions {
    static class Edge{
        int src;
        int dest;
        int wght;

        public Edge(int src,int dest,int wght){
            this.src=src;
            this.dest = dest;
            this.wght = wght;
        }
    }

    static class Node{
        int data;
        Node left ;
        Node right;

        Node(int data){
            this.data=data;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,1,1));
        graph[0].add(new Edge(0,2,1));

        graph[1].add(new Edge(1,0,1));
        graph[1].add(new Edge(1,3,1));

        graph[2].add(new Edge(2,0,1));
        graph[2].add(new Edge(2,4,1));

        graph[3].add(new Edge(3,1,1));
        graph[3].add(new Edge(3, 5, 1));
        graph[3].add(new Edge(3, 6, 1));


        //graph[3].add(new Edge(3,4,1));
        //graph[3].add(new Edge(3,5,1));

        graph[4].add(new Edge(4,2,1));
        graph[4].add(new Edge(4,3,1));
        //graph[4].add(new Edge(4,5,1));

        //graph[5].add(new Edge(5,3,1));
        //graph[5].add(new Edge(5,4,1));
        graph[5].add(new Edge(5,6,1));
        graph[5].add(new Edge(5, 3, 1));

        graph[6].add(new Edge(6,5,1));
        graph[6].add(new Edge(6, 3, 1));

    }

    //       DETECTION OF CYCLE IN UNDIRECTED GRAPH (USING BFS)
    public static boolean detectCycle(ArrayList<Edge> graph[]){
        boolean vis[] = new boolean[graph.length];
        int par[] = new int[graph.length];
        
        for(int i=0;i<graph.length;i++){
            if(!vis[i]){
                if(detectCycleUtil(graph,vis,i,par)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean detectCycleUtil(ArrayList<Edge> graph[],boolean vis[],int src,int par[]){
        Queue<Integer> q = new LinkedList<>();
        par[src]= -1;
        

        q.add(src);
        while(!q.isEmpty()){
            int curr = q.remove();
            if(!vis[curr]){
                vis[curr] = true;

                for(int i=0;i<graph[curr].size();i++){
                    Edge e = graph[curr].get(i);
                    if(!vis[e.dest]){
                        q.add(e.dest);
                        par[e.dest] = curr;
                    }
                    else if(par[e.dest]!=curr){
                        return true;
                    }
                }
            }

        }
        return false;
        
    }

    //                      MINIMUM DEPTH IN BINARY TREE
    public static int min = Integer.MAX_VALUE;
    public static int minimumDepth(Node root){
        if(root==null){
            return 0;
        }

        int lefthgt = minimumDepth(root.left);
        int righthgt = minimumDepth(root.right);
        min = Math.min(lefthgt,righthgt);
        return Math.max(lefthgt,righthgt)+1;
    }

    //                      ROTTEN ORANGES
    static int r = 3;
    static int c = 5;
    static class Ele{
        int x;
        int y;

        public Ele(int x,int y){
            this.x=x;
            this.y=y;
        }
    }

    public static boolean isValid(int x, int y){
        return (x>=0 && y>=0 && x<r && y<c );
    }

    public static boolean isDelim(Ele temp){
        return temp.x==-1 && temp.y==-1; 
    }

    public static boolean checkAll(int oranges[][]){
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(oranges[i][j]==1){
                    return true;
                }
            }
        }
        return false;
    }

    public static int convert(int oranges[][]){
        Queue<Ele> q = new LinkedList<>();
        int ans=0;

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(oranges[i][j]==2){
                    q.add(new Ele(i, j));
                }
            }
        }
        q.add(new Ele(-1, -1));

        while(!q.isEmpty()){

            boolean flag=false;

            while(!isDelim(q.peek())){
                Ele temp = q.peek();

                // left
                if(isValid(temp.x, temp.y-1) && oranges[temp.x][temp.y-1]==1){
                    if(!flag){
                        flag=true;
                        ans++;
                    }
                    oranges[temp.x][temp.y-1]=2;
                    temp.y--;
                    q.add(new Ele(temp.x, temp.y));
                    temp.y++;
                }
                // right
                if(isValid(temp.x, temp.y+1) && oranges[temp.x][temp.y+1]==1){
                    if(!flag){
                        flag=true;
                        ans++;
                    }
                    oranges[temp.x][temp.y+1]=2;
                    temp.y++;
                    q.add(new Ele(temp.x, temp.y));
                    temp.y--;
                }
                // up
                if(isValid(temp.x+1, temp.y) && oranges[temp.x+1][temp.y]==1){
                    if(!flag){
                        flag=true;
                        ans++;
                    }
                    oranges[temp.x+1][temp.y]=2;
                    temp.x++;
                    q.add(new Ele(temp.x, temp.y));
                    temp.x--;
                }

                // down
                if(isValid(temp.x-1, temp.y) && oranges[temp.x-1][temp.y]==1){
                    if(!flag){
                        flag=true;
                        ans++;
                    }
                    oranges[temp.x-1][temp.y]=2;
                    temp.x--;
                    q.add(new Ele(temp.x, temp.y));
                }
                q.remove();
                
            }
            q.remove();

            if(!q.isEmpty()){
                q.add(new Ele(-1, -1));
            }

        }
        return (checkAll(oranges)) ? -1 :ans;
    }

    //                    LARGEST REGION IN BOOLEAN MATRIX
    static int row = 4;
    static int col = 5;
    static int count;
    public static int dfs(int matrix[][]){
        boolean vis[][] = new boolean[matrix.length][matrix[0].length];
        int result =0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(matrix[i][j]==1 && !vis[i][j]){
                    count = 1;
                    dfsUtil(matrix,vis,i,j);
                    result = Math.max(result,count);
                }
            }
        }
        return result;
    }

    public static boolean isSafe(int r,int c, boolean vis[][],int matrix[][]){
        return (r>=0 && c>=0 && r<row && c<col && matrix[r][c]==1 && !vis[r][c]);
    }

    public static void dfsUtil(int matrix[][],boolean vis[][],int r,int c){
        int rowNo[] = {1,-1,1,-1,0,0,1,-1};
        int colNo[] = {-1,-1,1,1,-1,1,0,0};

        vis[r][c] = true;

        for(int i=0;i<8;i++){
            if(isSafe(r+rowNo[i], c+colNo[i], vis, matrix)){
                count++;
                dfsUtil(matrix, vis, r+rowNo[i],c+colNo[i]);
            }
        }
    }

    //                  WORD LADDER
    public static int wordChain(Set<String> dict, String start,String target){
        if(start==target){
            return 0;
        }

        if(!dict.contains(target)){
            return 0;
        }
        else{
            int level = 0;
            Queue<String> q = new LinkedList<>();
            q.add(start);
            while(!q.isEmpty()){

                String curr = q.remove();
                char ch[] = curr.toCharArray();
                level++;
                for(int i=0;i<curr.length();i++){
                    char origpos = ch[i];

                    for(char c='A';c<='Z';c++){
                        ch[i] = c;

                        if(String.valueOf(ch).equals(target)){
                            return level+1;
                        }

                        if(dict.contains(String.valueOf(ch))){
                            dict.remove(String.valueOf(ch));
                            q.add(String.valueOf(ch));
                        }
                    }
                    ch[i] = origpos;
                }
            }
        }
        return 0;
    }
    public static void main(String args[]){

        // Detect Cycle in Undirected Graph(BFS);
        int v = 7;
        ArrayList<Edge> graph[] = new ArrayList[v];
        createGraph(graph);
        System.out.println("Cycle Present: "+ detectCycle(graph));

        // Minimum Depth In Binary Tree
        Node root = new Node(1);
        root.left = new Node(8);
        root.right = new Node(2);
        root.left.left = new Node(6);
        root.left.right = new Node(5);
        //root.right.left = new Node(9);
        root.left.left.left = new Node(12);
        minimumDepth(root);
        System.out.println("Minimum height of tree: "+min);

        // Minimum time required to rot all oranges
        int oranges[][] = {{2,1,0,2,1},{1,0,1,2,1},{1,0,0,2,1}};
        System.out.println("Time Required for all oranges to get rotten: "+convert(oranges));
        
        // Find the size of the largest region in the Boolean Matrix
        int matrix[][] = {{0,0,1,1,0},{0,0,1,1,0},{0,0,0,0,0},{0,0,0,0,1}};
        System.out.println("Largest Area of boolean Matrix: "+dfs(matrix));

        // Word Ladder
        Set<String> dict = new HashSet<>();
        dict.add("POON");
        dict.add("PLEE");
        dict.add("SAME");
        dict.add("POIE");
        dict.add("PLIE");
        dict.add("PLEA");
        dict.add("POIN");
        String start = "TOON";
        String target = "PLEA";
        System.out.println("Smallest Chain length: "+wordChain(dict, start, target));






    }
}
