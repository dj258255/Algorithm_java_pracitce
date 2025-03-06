import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.Queue;


class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        Map<Integer,ArrayList<Integer>> map = new HashMap<>();
        
        for(int i = 1; i <= n; i++){
            map.put(i, new ArrayList<>());
        }
        
        for(int i = 0; i< wires.length; i++){
            int a = wires[i][0];
            int b = wires[i][1];
            map.get(a).add(b);
            map.get(b).add(a);
        }
        
        //전선을 하나씩 끊어보자
        for(int i = 0; i < wires.length; i++){
            int a = wires[i][0];
            int b = wires[i][1];
            
            map.get(a).remove(Integer.valueOf(b));
            map.get(b).remove(Integer.valueOf(a));
            
            int groupA = bfs(a,n,map); //그룹 A의 송전탑 개수
            int groupB = n-groupA; //그룹 B의 송전탑 개수
            int diff = Math.abs(groupA- groupB);
            answer = Math.min(answer, diff);
            
            //다시 붙여주기
            map.get(a).add(b);
            map.get(b).add(a);
            
        }
        
        
        return answer;
    }
    
    
    public static int bfs(int start, int n , Map<Integer,ArrayList<Integer>> map){
        boolean[] visited = new boolean[n+1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        int count = 1;
        
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int neighbor : map.get(node)){
                if(!visited[neighbor]){
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    count++; //송전탑 개수
                }
            }
        }
        
        return count;
    }
}