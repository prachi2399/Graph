public class basics{
    public class Edge{
        int v;
        int w;
        Edge(int v, int w){
            this.v=v;
            this.w=w;
        }
    }
    int N=7;
    ArrayList<Edge>[] graph=new ArrayList[N];

    public  void addEdge(int u, int v, int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }

    public void display(){
     for(int i=0;i<N;i++){
         System.out.print(i+"->");
         for(Edge e:graph[i]){
          System.out.print("("+e.v+","+e.w+")");
         }
         System.out.println();
     }
    }

    public int searchIdx(int u, int v){
        int idx=-1;
        for(int i=0;i<graph[u].size();i++){
            for(Edge e:graph[u].get(i)){
                if(e.v==v){
                    return i;
                }
            }
        }
        return -1;
    }

    public void removeEdge(int u, int v){
        int idx=searchIdx(u,v);
        graph[u].remove(v);
        int idx2=searchIdx(v,u);
        graph[v].remove(u);
    }

    public void removeVtx(int v, int u){
        for(int i=graph[u].size()-1;i>=0;i--){
            Edge e:graph[u].get(i){
                  removeVtx(u,e.v);
            }
        }
    }

    public static boolean hasPath(int src, int dest, boolean[] vis, ArrayList < Edge > [] graph) {
        if (src == dest) return true;
        vis[src] = true;
        for (Edge e: graph[src]) {
            if (!vis[e.nbr]) {
                boolean res = hasPath(e.nbr, dest, vis, graph);
                if(res) return true;
            }
        }
        return false;
    }

    public static void allPath(ArrayList < Edge > [] graph,int src, int dest, boolean[] vis,String psf) {
        if (src == dest) 
        {
            System.out.println(psf);
            return ;}
            
        vis[src] = true;
        for (Edge e: graph[src]) {
            if (!vis[e.nbr]) {
                 allPath(graph, e.nbr, dest, vis, psf+e.nbr);
            }
        }
        vis[src]=false;
    }
    /// shortestPathBinaryMatrix
    public int shortestPathBinaryMatrix(int[][] grid) {
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
    //
    public boolean isBipartite_(int[][] graph, int[] vis, int src){
        LinkedList<Integer> que=new LinkedList<>();
        que.addLast(src);
        
        int color=0;
        while(que.size()>0){
            int size=que.size();
            while(size-->0){
                int rem=que.getFirst();
                if(vis[rem]!=-1){
                    if(vis[rem]!=color) return false;
                    continue;
                }
                vis[rem]=color;
                for(int e:graph[rem]){
                    if(vis[e]==-1){
                    que.addLast(e);
                    } 
                }
            }
           color=(color+1)%2;
        }
        return true;
    }
    public boolean isBipartite(int[][] graph) {
     int N=graph.length;
     boolean res=true;
     int[] vis=new int[N];
     Arrays.fill(vis,-1);
     for(int i=0;i<N;i++){
       if(vis[i] == -1 && !isBipartite_(graph,vis,i)){
             return false;
       }
     }
    return true;
    }
}