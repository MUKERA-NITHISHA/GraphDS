public class FloodFillAlgorithm {

    public static void helper(int image[][],int sr, int sc, int color,boolean vis[][],int origColor){
        if(sr<0 || sc<0 || sr>=image.length || sc>=image[0].length || vis[sr][sc] || image[sr][sc]!=origColor){
            return;
        }
        if(image[sr][sc]==origColor){
            image[sr][sc]=color;
        }

        //left
        helper(image, sr, sc-1, color,vis, origColor);

        // right
        helper(image, sr, sc+1, color, vis, origColor);

        //up
        helper(image, sr+1, sc, color, vis, origColor);

        //down
        helper(image, sr-1, sc, color, vis, origColor);
    }

    public static void floodfill(int image[][],int sr,int sc,int color){
        boolean vis[][] = new boolean[image.length][image[0].length];
        helper(image,sr,sc,color,vis,image[sr][sc]);

    }
    public static void main(String args[]){
        int image[][] = {{1,1,1},{1,1,0},{1,0,1}};
        floodfill(image, 1, 1, 2);
        for(int i=0;i<image.length;i++){
            for(int j=0;j<image[0].length;j++){
                System.out.print(image[i][j]+" ");
            }
            System.out.println();
        }

    }
}
