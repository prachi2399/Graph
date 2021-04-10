import java.util.ArrayList;
public class kosaraju{
    /// use to find number of strongly connected components in a graph
    static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w=w;
        }
    }

    static int N=11;
    static ArrayList<Edge>[] graph=new ArrayList[11];
    public static void addEdge(int u, int v, int w)
    {
        graph[u].add(new Edge(v, w));
    }

    public static void constructGraph(){
        addEdge(5, 0, 10);
        addEdge(4, 0, 10);
        addEdge(5, 1, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 10);
        addEdge(0, 6, 10);
        addEdge(6, 7, 10);
        addEdge(7, 3, 10);
        addEdge(4, 8, 10);
        addEdge(8, 9, 10);
        addEdge(9, 10, 10);
        addEdge(10, 3, 10);
    }


    public static void main(String[] args) throws Exception {
        constructGraph();
        System.out.println(kosaraju());
    }
    public static void dfs_Scc(int src, ArrayList<Edge>[] graph, boolean[] vis, ArrayList<Integer> res){
        vis[src]=true;
        for(Edge e: graph[src] ){
            if(!vis[e.v]){
                dfs_Scc(e.v, graph, vis, res);
            }
            res.add(src);
        }
    }

    public static int kosaraju(){
        boolean[] vis=new boolean[N];
        ArrayList<Integer> res=new ArrayList<>();
        for(int i=0;i<N;i++){
            if(!vis[i]){
                dfs_Scc(i,graph,vis,res);
            }
        }

        ArrayList<Edge>[] ngraph=new ArrayList[N];

        for(int i=0;i<N;i++){
            for(Edge e: graph[i]){
                ngraph[e.v].add(new Edge(i,1));
            }
        }

        vis=new boolean[N];

        int count=0;
        for(int i=res.size()-1;i>=0;i--){
            int ele=res.get(i);
            if(!vis[ele]){
                ArrayList<Integer> scc=new ArrayList<>();
                count++;
                dfs_Scc(ele,graph,vis,scc);

                for(int e:scc)
                   System.out.print(e+" ");
                System.out.println();   
            }
        }
        return count;
    }
}