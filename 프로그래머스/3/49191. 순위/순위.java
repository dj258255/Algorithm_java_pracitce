import java.util.ArrayDeque;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int solution(int n, int[][] results) {
        ArrayList<Integer>[] groupA = new ArrayList[n+1]; //이김
        ArrayList<Integer>[] groupB = new ArrayList[n+1]; //짐
        for(int i = 0 ; i < n+1; i++){
            groupA[i] = new ArrayList<>();
            groupB[i] = new ArrayList<>();
        }
        
        for(int i = 0 ; i < results.length; i++){
            int a = results[i][0];
            int b = results[i][1];
            //a가 b를 이김
            groupA[a].add(b);
            groupB[b].add(a);
        }
        
        int answer = 0;
        
        for (int i = 1; i <= n; i++) {
            int winCount = bfs(groupA, i, n);
            int loseCount = bfs(groupB, i, n);
            if (winCount + loseCount == n - 1) answer++;
        }

        
        return answer;
    }
    
    public static int bfs(ArrayList<Integer>[] list , int start , int n){
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        queue.add(start);
        visited[start] = true;
        
        int count = 0;
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int neighbor : list[node]){
                if(!visited[neighbor]){
                    visited[neighbor] = true;
                    count++;
                    queue.add(neighbor);
                }
            }
        }
        
        return count;
        
    }
}