import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    public int solution(int n, int[][] computers) {
        
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        for(int i = 0 ; i < n; i++){
            map.put(i , new ArrayList<>());
        }
        
        for(int i = 0 ; i < computers.length; i++){
            for(int j = 0 ; j < computers[i].length; j++){
                if(i==j) continue;
                if(computers[i][j] != 1) continue;
                map.get(i).add(j);
                map.get(j).add(i);
            }
        }
        
        
        int answer = bfs(map,n);
        
        
        return answer;
    }
    
    
    public static int bfs(Map<Integer, ArrayList<Integer>> map , int N){
        int count = 0;
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new ArrayDeque<>();
        
        for(int i = 0 ; i < N; i++){
            if(!visited[i]){
                queue.add(i);
                count++;
                while(!queue.isEmpty()){
                    int node = queue.poll();
                    for(int neighbor : map.get(node)){
                        if(!visited[neighbor]){ //방문안했으면
                            visited[neighbor] = true; //방문처리
                            queue.add(neighbor); //큐에 추가
                        }
                    }
                }
            }
        }
        
        return count;
    }
}