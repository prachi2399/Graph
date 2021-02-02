public class Hamitonian{
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

        int src = Integer.parseInt(br.readLine());

        // write all your codes here
        
        HashSet<Integer> vis=new HashSet<>();
        hamiltonianPath(graph, src, vis, src+"",src);
    }
    public static void hamiltonianPath( ArrayList < Edge > [] graph, int src, HashSet<Integer> vis, String psf, int osrc){
        if(vis.size()==graph.length-1){
            System.out.print(psf);
            boolean closingEdge=false;
            for(Edge e:graph[src]){
                if(e.nbr==osrc){
                    closingEdge=true;
                    break;
                }
            }
            if(closingEdge){
                System.out.println("*");
            }
            else System.out.println(".");
            return;
        }
            
        vis.add(src);
        for(Edge e:graph[src]){
            if(!vis.contains(e.v)){
                hamiltonianPath(graph, e.v, vis, psf+e.v,osrc);
            }
            vis.remove(src);
        }
    }
}