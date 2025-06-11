import java.util.PriorityQueue;



class Solution {
    
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    
    static int N;
    static int M;
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        int answer = bfs(maps);
        return answer;
    }
    
    public static int bfs(int[][] maps){
        PriorityQueue<int[]> pq = new PriorityQueue<>(
        (a,b) -> a[2]-b[2]
        );
        pq.add(new int[]{0,0,1});
        
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        
        while(!pq.isEmpty()){
            int[] node = pq.poll();
            int r = node[0];
            int c = node[1];
            int e = node[2];
            
            for(int d = 0 ; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if(maps[nr][nc] == 0) continue;
                if(visited[nr][nc]) continue;
                
                if(nr == N-1 && nc == M-1){
                    return e+maps[nr][nc];
                }
                
                visited[nr][nc] = true;
                pq.add(new int[]{nr,nc,e+maps[nr][nc]});
            }
            
            
            
        }
        
        
        
        return -1;
    }
}