public class spreadOfInfection{
    static class Edge {
        int src;
        int nbr;

        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
        }
    }
    public static class Pair{
        int v;
        int time;
        Pair(int v, int time){
            this.v=v;
            this.time=time;
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
            graph[v1].add(new Edge(v1, v2));
            graph[v2].add(new Edge(v2, v1));
        }

        int src = Integer.parseInt(br.readLine());
        int t = Integer.parseInt(br.readLine());
         int[] vis=new int[vtces];
         //Arrays.fill(vis,-1);
        int c= bfs(graph, src, t, vis);
        System.out.println(c);
    }
    public static int bfs(ArrayList < Edge > [] graph, int src, int t, int[] vis){
        ArrayDeque<Pair> que=new ArrayDeque<>();
        que.add(new Pair(src, 1));
        int count=0;
        while(que.size()>0){
            Pair rem=que.remove();
            if(vis[rem.v]>0){
                continue;
            }
            
            if(rem.time>t) break;
            vis[rem.v]=rem.time;
            count++;
            for(Edge e:graph[rem.v]){
                if(vis[e.nbr]==0){
                    que.add(new Pair(e.nbr, rem.time+1));
                }
            }
        }
        return count;
    }
}