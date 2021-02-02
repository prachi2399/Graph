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
}