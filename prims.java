public class prims{
    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }
    
    static class Pair implements Comparable<Pair> {
        int v;
        int wsf;
        int av;
        Pair(int v, int wsf, int av){
            this.v=v;
            this.wsf=wsf;
            this.av=av;
        }
        public int compareTo(Pair o){
            return this.wsf-o.wsf;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int vtces = Integer.parseInt(br.readLine());
        ArrayList < Edge > [] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList < > ();
        }

        int edges = Integer.parseInt(br.readLine());
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            int wt = Integer.parseInt(parts[2]);
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }
        boolean[] vis=new boolean[vtces];
        PriorityQueue<Pair> pq=new PriorityQueue<>();
        pq.add(new Pair(0,0,-1));
        while(pq.size()>0){
        Pair rem=pq.remove();
        if(vis[rem.v]){
            continue;
        }
        vis[rem.v]=true;
        if(rem.av!=-1){
            System.out.println("["+rem.v+"-"+rem.av+"@"+rem.wsf+"]");
        }
        for(Edge e:graph[rem.v]){
            if(!vis[e.nbr]){
                pq.add(new Pair(e.nbr,e.wt,rem.v));
            }
        }
        }
    }
    
    
    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    public static void display(int N, ArrayList<Edge>[] graph) {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    public class primsPair {
        int vtx = 0;
        int par = 0;
        int wt = 0;

        public primsPair(int vtx, int par, int wt) {
            this.vtx = vtx;
            this.par = par;
            this.wt = wt;
        }
    }

    public static void primsAlgo_02(int src, int N, boolean[] vis, ArrayList<Edge>[] graph){
        ArrayList<Edge>[] MST=new ArrayList<>();
        for(int i=0;i<N;i++){
            graph[i]=new ArrayList<>();
        }

        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->{
            return a.wt-b.wt;
        });

        pq.add(new primsPair(src,-1,0));
        int NumberOfEdges=0;
        // while (que.size() != 0) { // for disconnected graph and more generic way
        // while (que.size() != 0 && NumberOfEdges < N - 1) { // for disconnected graph
            while (NumberOfEdges < N - 1) { // when you know graph is connected.
                primsPair p = que.remove();

                if(vis[p.vtx]) continue;

                if(p.par!=-1){
                    addEdge(MST,p.vtx,p.par,p.wt);
                    NumberOfEdges++;
                }

                vis[p.vtx]=true;
                for(Edge e:graph[p.vtx]){
                    if(!vis[e.vtx]){
                        pq.add(new primsPair(e.vtx,p.vtx,e.wt));
                    }
                }
            }
        }

    
        public static void primsAlgo_02(int src, int N, boolean[] vis, int[] dis, int[] par, ArrayList<Edge>[] graph){
            ArrayList<Edge>[] MST=new ArrayList<>();
            for(int i=0;i<N;i++){
                graph[i]=new ArrayList<>();
            }
    
            PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->{
                return a.wt-b.wt;
            });
    
            pq.add(new primsPair(src,-1,0));
            int NumberOfEdges=0;
            // while (que.size() != 0) { // for disconnected graph and more generic way
            // while (que.size() != 0 && NumberOfEdges < N - 1) { // for disconnected graph
                while (NumberOfEdges < N - 1) { // when you know graph is connected.
                    primsPair p = que.remove();
    
                    if(vis[p.vtx]) continue;
    
                    if(p.par!=-1){
                        addEdge(MST,p.vtx,p.par,p.wt);
                        NumberOfEdges++;
                    }
    
                    vis[p.vtx]=true;
                    for(Edge e:graph[p.vtx]){
                        if(!vis[e.vtx]&&e.w<dis[e.vtx]){
                            dis[e.vtx]=e.w;
                            par[e.vtx]=p.vtx;
                            pq.add(new primsPair(e.vtx,p.vtx,e.wt));
                        }
                    }
                }

                public static void prims(){
                    int[] dis=new int[N];
                    int[] par=new int[N];
                    boolean[] vis=new boolean[N];
                    Arrays.fill(dis,(int)1e8);
                    Arrays.fill(par,-1);

                    for(int i=0;i<N;i++){
                        if(!vis[i]){
                            primsAlgo_02(i,N,graph);
                        }
                    }
                }
            }    
}