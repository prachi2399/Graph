import java.util.ArrayList;
import java.io.*;
import java.util.*;
public class perfectFriends{

    static class Edge {
        int src;
        int nbr;

        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
            //this.wt = wt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        ArrayList < Edge > [] graph = new ArrayList[n];
        for ( int v = 0; v < n; v++) {
            graph[v] = new ArrayList < > ();
        }
        for (int i = 0; i < k; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
    
            graph[v1].add(new Edge(v1, v2 ));
            graph[v2].add(new Edge(v2, v1));
        }
        ArrayList < ArrayList < Integer >> comps = new ArrayList < > ();
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (vis[i] == false) {
                ArrayList < Integer > comp = new ArrayList < > ();
                drawGeneralComponents(graph, i, comp, vis);
                comps.add(comp);
            }
        }
        int parts = 0;
        for (int i = 0; i < comps.size(); i++) {
            for (int j = i + 1; j < comps.size(); j++) {
                int count = comps.get(i).size() * comps.get(j).size();
                parts += count;
            }
        }
        System.out.println(parts);
    }
    public static void drawGeneralComponents(ArrayList < Edge > [] graph, int src, ArrayList < Integer > comp, boolean[] vis) {
        vis[src] = true;
        comp.add(src);
        for (Edge e: graph[src]) {
            if(!vis[e.nbr])
            drawGeneralComponents(graph, e.nbr, comp, vis);
        }
    }
}