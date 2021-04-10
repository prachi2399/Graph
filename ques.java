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
                   if(fo==0) return distance+1;
               }
           }
       }
       distance++;
   }
}
// longest incerasing path in matrix
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

    // shortest path in binary matrix
    public int shortestPathBinaryMatrix(int[][] grid){
        int n=grid.length;
        int m=grid[0].length;
        
        if(grid[0][0]==1||grid[n-1][m-1]==1) return -1;
        int[][] dir={{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};

        LinkedList<Integer> que=new LinkedList<>();
        que.addLast(0);//0*m+n;
        grid[0][0]=1;
        int level=1;
        while(que.size()>0){
            int size=que.size();
            while(size-->0){
                int idx=que.removeFirst();
                int r=idx/m;
                int c=idx%m;
                if(r==m-1&&c==n-1) return level;
                for(int d=0;d<8;d++){
                    int x=r+dir[d][0];
                    int y=c+dir[d][1];
                    if(x>=0&&y>=0&&x<n&&y<m&&grid[x][y]==0){
                        grid[x][y]=1;
                        que.addLast(x*m+y);//0*m+n;
                    }
                }
            }
            level++;
        }
        return -1;
    }
    /// knights tour
    static class Pair { 
        int x, y; 
        int dis; 
  
        public Pair(int x, int y, int dis) 
        { 
            this.x = x; 
            this.y = y; 
            this.dis = dis; 
        } 
    } 
    public int minStepToReachTarget(int KnightPos[], int TargetPos[], int N)
    {
        int dx[] = { -2, -1, 1, 2, -2, -1, 1, 2 }; 
        int dy[] = { -1, -2, -2, -1, 1, 2, 2, 1 }; 
        
        LinkedList<Pair> que=new LinkedList<>();
        que.addLast(new Pair(KnightPos[0], KnightPos[1], 0 ));
        
        boolean visit[][] = new boolean[N + 1][N + 1]; 
        visit[KnightPos[0]][KnightPos[1]]=true; 
         
        while(que.size()>0){
            Pair rem=que.removeFirst();
            
            if(rem.x==TargetPos[0]&&rem.y==TargetPos[1])
                 return rem.dis;
                 
            for(int d=0;d<8;d++){
                int r=rem.x+dx[d];
                int c=rem.y+dy[d];
                if (r >= 1 && r <= N && c >= 1 && c <= N&&!visit[r][c]){
                    visit[r][c]=true;
                    que.addLast(new Pair(r,c,rem.dis+1));
                } 
            }     
        }
        return Integer.MAX_VALUE; 
    }

    // Number of Operations to Make Network Connected
    public class Edge{
        int v;
        Edge(int v){
            this.v=v;
        }
    }
     public void dfs(ArrayList<Edge>[] graph, int src, boolean[] vis){
         vis[src]=true;
         for(Edge e : graph[src])
            { if(!vis[e.v]) dfs(graph, e.v, vis);      
            }
     }
    public int makeConnected(int n, int[][] connections) {
          if(n>connections.length+1) return -1;
        boolean[] vis=new boolean[n];
        ArrayList<Edge>[] graph=new ArrayList[n];
        for(int i=0;i<n;i++){
            graph[i]=new ArrayList<>();
        }
        int comp=0;
        for(int[] edg:connections){
            graph[edg[0]].add(new Edge(edg[1]));
            graph[edg[1]].add(new Edge(edg[0]));
            
        }
        for(int i=0;i<n;i++){
            if(!vis[i]){
                dfs(graph,i,vis);
                comp++;
            }
        }
        return comp-1;
    }

    //couples holding hands
    HashMap<Integer, Integer> map=new HashMap<>();
    public int minSwapsCouples(int[] row) {
        
        for(int i=0;i<row.length;i++){
            map.put(row[i],i);
        }
        
        int swaps=0;
        for(int i=0;i<row.length;i=i+2){
            int first=row[i];
            int second=first^1;
            
            if(row[i+1]!=second){
                swaps++;
                swap(row,i+1,map.get(second));
            }
        }
        return swaps;
    }
    
    public void swap(int[] row, int i, int j){
        int temp=row[i];
        row[i]=row[j];
        row[j]=temp;
        
        map.put(row[i],i);
        map.put(row[j],j);
    }
}