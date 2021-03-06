public class numberIsland{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[m][n];

        for (int i = 0; i < arr.length; i++) {
            String parts = br.readLine();
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = Integer.parseInt(parts.split(" ")[j]);
            }
        }
        int count=0;
         boolean[][] vis=new boolean[m][n];
         for(int i=0;i<m;i++){
             for(int j=0;j<n;j++){
                 if(vis[i][j]==false&&arr[i][j]==0){
                     dfs(arr,i,j,vis);
                     count++;
                 }
             }
         }
         System.out.println(count);
        // write your code here
    }
    static int[][] dir={{1,0},{0,1},{0,-1},{-1,0}};
     public static void dfs(int[][] arr, int i, int j, boolean[][] vis){
         vis[i][j]=true;
         for(int d=0;d<4;d++){
             int r=i+dir[d][0];
             int c=j+dir[d][1];
             if(r>=0&&c>=0&&r<arr.length&&c<arr[0].length&&!vis[r][c]&&arr[r][c]!=1){
                 dfs(arr,r,c,vis);
             }
         }
     }

     //leetcode perimeter question
     public int islandPerimeter(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
        return 0;

        int[][] dir={{1,0},{0,1},{0,-1},{-1,0}};
        int n=grid.length;
        int m=grid[0].length;
        int count=0,nbr=0;
         for(int i=0;i<n;i++){
             for(int j=0;j<m;j++){
              if(grid[i][j]==1){
                  count++;
               for(int d=0;d<4;d++)
               {   
                 int r=i+dir[d][0];
                 int c=j+dir[d][1];
                 if(r>=0&&c>=0&&r<grid.length&&c<grid[0].length&&grid[r][c]==1){
                 nbr++;
               }
            }
           }
         }
        }    
          return  count*4-nbr;
    }
    
}