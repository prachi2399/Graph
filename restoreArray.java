//1743. Restore the Array From Adjacent Pairs
public class restoreArray{
    public int[] restoreArray(int[][] adjacentPairs) {
    HashMap<Integer,ArrayList<Integer>> map=new HashMap<>();
    for(int i=0;i<adjacentPairs.length;i++){
        makeGraph(map,adjacentPairs[i][0],adjacentPairs[i][1]);
        makeGraph(map,adjacentPairs[i][1],adjacentPairs[i][0]);
    }
    int head=-1;
    HashSet<Integer> set=new HashSet<>();
    ArrayList<Integer> ans=new ArrayList<>();
    for(int key:map.keySet()){
        if(map.get(key).size==1){
            head=key;
        }
    }
    restoreArray(adjacentPairs,map,set,ans,head);
    int[] res=new int[ans.size()];
        for(int i=0;i<ans.size();i++){
            res[i]=ans.get(i);
        }
        return res;
    } 
    public void makeGraph(HashMap<Integer,ArrayList<Integer>> map, int i, int j){
        if(map.containsKey(i)){
            map.get(i).add(j);
        }
        else{
            List<Integer> ans=new ArrayList<>();
            ans.add(j);
            map.put(i,ans);
        }
    }

    public void restoreArray(HashMap<Integer,ArrayList<Integer>> map, HashSet<Integer> set, ArrayList<Integer> ans, int head){
     ans.add(head);
     set.add(head);
     List<Integer> nbrs=map.get(head);
     for(int ele: nbrs){
         if(!set.add(ele)){
             restoreArray(map,adjacentPairs,set,ans,ele);
         }
     }
    }
}