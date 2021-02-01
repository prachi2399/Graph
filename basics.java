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

}