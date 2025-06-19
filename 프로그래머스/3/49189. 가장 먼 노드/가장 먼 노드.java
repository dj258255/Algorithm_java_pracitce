import java.util.Map;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.ArrayList;

class Solution {
    public int solution(int n, int[][] edge) {
        Map<Integer,ArrayList<Integer>> map = new HashMap<>();
        for(int i = 1; i<=n; i++){
            map.put(i, new ArrayList<>());
        }
        
        for(int i = 0; i < edge.length; i++){
            int a = edge[i][0];
            int b = edge[i][1];
            map.get(a).add(b);
            map.get(b).add(a);
        }
        
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        boolean[] visited = new boolean[n+1];
        visited[1] = true;
        
        int[] list = new int[n+1];
        
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int neighbor : map.get(node)){
                if(!visited[neighbor]){
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    list[neighbor] = list[node]+1;
                }
            }
        }
        
        int max = Integer.MIN_VALUE;
        
        for(int i = 0 ; i < list.length; i++){
            max = Math.max(max,list[i]);
        }
        
        int answer = 0;
        
        for(int i = 0 ; i < list.length; i++){
            if(list[i] == max) answer++;
        }
        
        
        return answer;
    }
}