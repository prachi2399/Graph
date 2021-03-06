public class topological_sort{
    static class Edge {
        int src;
        int nbr;

        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
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
        }

        topological_sort(graph);
    }
    // 
    public static void topological_sort(ArrayList < Edge > [] graph){
        boolean[] vis=new boolean[N];
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=0;i<N;i++){
          dfs(graph,i,vis,st);
        }
        Collections.reverse(ans);
    }
    public static void dfs(ArrayList < Edge > [] graph, int src, boolean[] vis, ArrayList<Integer> ans){
        vis[src]=true;
        for(Edge e:graph[src]){
            if(!vis[e.nbr]){
                dfs(edges,e.nbr,vis,ans);
            }
        }
        ans.add(src);
    }
    // kahan Algorithm
    public static void kahanAlgo(ArrayList < Edge > [] graph){
        int[] inorder=new int[N];
        for(int i=0;i<n;i++){
            for(Edge e:graph[i]){
                inorder[e.v]++;
            }
        }

        LinkedList<Integer> que=new LinkedList<>();
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=0;i<N;i++){
            if(inorder[i]==0){
                que.addLast(i);
            }
        }
        int level=0;
        while(que.size()>0){
            int size=que.size();
            while(size-->0){
             int rem=que.removeFirst();
             ans.add(rem);
             for(Edge e:graph[rem]){
              if(--inorder[e.v]==0){
                  que.addLast(e.v);
              }
             }
            }
            level++;
        }
        if(ans.size()!=N) System.out.println("Cyclic Graph");
        return ans;
    } 

    // courses schedule

    public static boolean kahanAlgo(int N, int[][] prerequisites){
        List<Integer>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            graph[i] = new ArrayList();
        }
        
        int[] inorder = new int[N];
        for(int[] e: prerequisites) {
            graph[e[0]].add(e[1]);
            inorder[e[1]]++;
        }

        LinkedList<Integer> que=new LinkedList<>();
        int count=0;
        for(int i=0;i<N;i++){
            if(inorder[i]==0){
                que.addLast(i);
            }
        }
        while(que.size()>0){
            int size=que.size();
            while(size-->0){
             int rem=que.removeFirst();
             count++;
             for(int e:graph[rem]){
              if(--inorder[e]==0){
                  que.addLast(e);
              }
             }
            }
        }
        return count==N;
    } 
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return kahanAlgo(numCourses,prerequisites);
    }
     
    // course sechedule
    public static List kahanAlgo(int N, int[][] prerequisites){
        List<Integer>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            graph[i] = new ArrayList();
        }
        
        int[] inorder = new int[N];
        for(int[] e: prerequisites) {
            graph[e[0]].add(e[1]);
            inorder[e[1]]++;
        }
        List<Integer> ans=new ArrayList<>();
        LinkedList<Integer> que=new LinkedList<>();
        int count=0;
        for(int i=0;i<N;i++){
            if(inorder[i]==0){
                que.addLast(i);
            }
        }
        int i=0;
        while(que.size()>0){
            int size=que.size();
            while(size-->0){
             int rem=que.removeFirst();
             ans.add(rem);
             for(int e:graph[rem]){
              if(--inorder[e]==0){
                  que.addLast(e);
              }
             }
            }
        }
         Collections.reverse(ans);
        return ans;
    } 
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>  ans= kahanAlgo(numCourses, prerequisites);
        if(ans.size()!=numCourses) return new int[]{};
        int[] res=new int[numCourses];
        for(int i=0;i<numCourses;i++){
          res[i]=ans.get(i);
        }
        return res;
    }
}