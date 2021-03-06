public class ques{
    // rotting oranges
    public int orangesRotting(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return -1;
       int n=grid.length;
       int m=grid[0].length;
       int fo=0;
       LinkedList<Integer> que=new LinkedList<>();
       for(int i=0;i<n;i++){
           for(int j=0;j<m;j++){
               if(grid[i][j]==2){
                   que.addLast(i*m+j);
               }
               else if(grid[i][j]==1) fo++;
           }
       }
       if(fo==0) return 0;
       int[][] dir={{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
       
       int time=0;
       while(que.size()!=0){
           int size=que.size();
           while(size-->0){
               int idx=que.removeFirst();
               int r=idx/m;
               int c=idx%m;
               for(int d=0;d<4;d++){
                   int x=r+dir[d][0];
                   int y=c+dir[d][1];
                   if(x>=0&&x<n&&y>=0&&y<m&&grid[x][y]==1){
                       fo--;
                       grid[x][y]=2;
                       que.addLast(x*m+y);
                       if(fo==0) return time+1;
                   }
               }
           }
           time++;
       }
       return -1;
   }

   //walls and gate
   public void wallsGate(int[][] grid) { //-1: obstacle //0: gate // 2147483647: empty space
    if (grid.length == 0 || grid[0].length == 0) return;
   int n=grid.length;
   int m=grid[0].length;
   int emptySpace=0;
   LinkedList<Integer> que=new LinkedList<>();
   for(int i=0;i<n;i++){
       for(int j=0;j<m;j++){
           if(grid[i][j]==0){
               que.addLast(i*m+j);
           }
           else if(grid[i][j]==2147483647) fo++;
       }
   }
   if(fo==0) return;
   int[][] dir={{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
   
   int distance=0;
   while(que.size()!=0){
       int size=que.size();
       while(size-->0){
           int idx=que.removeFirst();
           int r=idx/m;
           int c=idx%m;
           for(int d=0;d<4;d++){
               int x=r+dir[d][0];
               int y=c+dir[d][1];
               if(x>=0&&x<n&&y>=0&&y<m&&grid[x][y]==1){
                   fo--;
                   grid[x][y]=distance+1;
                   que.addLast(x*m+y);
                   if(fo==0) return time+1;
               }
           }
       }
       display++;
   }
}

int[][] dir={{1,0},{-1,0},{0,1},{0,-1}};
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length==0||matrix[0].length==0) return 0;
        int n=matrix.length;
        int m=matrix[0].length;
        int[][] indegree=new int[n][m];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                for(int d=0;d<4;d++){
                    int r=i+dir[d][0];
                    int c=j+dir[d][1];
                    if(r>=0&&r<n&&c>=0&&c<m&&matrix[r][c]>matrix[i][j]){
                           indegree[r][c]++;
                    }
                }
            }
        }
        LinkedList<Integer> que=new LinkedList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(indegree[i][j]==0){
                    que.addLast(i*m+j);
                }
            }
        }
        int level=0;
        while(que.size()>0){
            int size=que.size();
            while(size-->0){
                int idx=que.removeFirst();
                int r=idx/m;
                int c=idx%m;
                for(int d=0;d<4;d++){
                    int x=r+dir[d][0];
                    int y=c+dir[d][1];
                    if(x>=0&&y>=0&&x<n&&y<m&&matrix[x][y]>matrix[r][c]&&--indegree[x][y]==0){
                            que.addLast(x*m+y);
                    }
                }
            }
            level++;
        }
        
        return level;
    }

}