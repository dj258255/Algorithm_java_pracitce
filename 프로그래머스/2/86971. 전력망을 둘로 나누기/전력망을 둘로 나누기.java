import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        //모든 전선을 하나씩 끊어보면서 최소 차이 찾기
        for ( int i = 0; i < wires.length; i++){
            //전선 하나를 제외하고 그래프에 추가
            Map<Integer,List<Integer>> graph = new HashMap<>();
            for(int j = 1; j <=n; j++){
                graph.put(j,new ArrayList<>());
            }
            
            //전선 하나를 제외하고 그래프에 추ㅏㄱ
            for(int j = 0 ; j < wires.length; j++){
                if(i==j)
                    continue; //이번에 끊을 전선은 제외
                int v1 = wires[j][0];
                int v2 = wires[j][1];
                graph.get(v1).add(v2);
                graph.get(v2).add(v1);
            }
            
            //BFS로 두 개의 네트워크 크기 구하기
            int count1 = bfs(graph, 1,n);
            int count2 = n - count1; // 전체 개수에서 빼기
            
            int diff = Math.abs(count1 -count2);
            
            answer = Math. min(answer, diff);
        }
        return answer;
    }
    
    private int bfs(Map<Integer, List<Integer>> graph, int start , int n){
        boolean[] visited = new boolean[n+1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        
        int count = 1;
        

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    count++; // 송전탑 개수 증가
                }
            }
        }
        return count;
    }
}