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
            int winCount = dfs(groupA, i, new boolean[n + 1]);
            int loseCount = dfs(groupB, i, new boolean[n + 1]);
            if (winCount + loseCount == n - 1) answer++;
        }

        
        return answer;
    }
    
    public static int dfs(ArrayList<Integer>[] group , int seq , boolean[] visited){
        int count = 0 ;
        visited[seq] = true;
        
        for(int next : group[seq]){
            if(!visited[next]){
                count += 1 + dfs(group,next,visited);
            }
        }
        
        return count;
    }
}