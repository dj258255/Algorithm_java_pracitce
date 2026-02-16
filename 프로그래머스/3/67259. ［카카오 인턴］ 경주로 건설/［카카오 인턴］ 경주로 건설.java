import java.util.*;
import java.io.*;
class Solution {
    static int[][] Map;
    static int mapR;
    static int mapC;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    
    public int solution(int[][] board) {
        Map = board;
        mapR = board.length;
        mapC = board[0].length;
        int answer = bfs();
        return answer;
    }
    
    public static int bfs(){
        PriorityQueue<int[]> queue = new PriorityQueue<>(
        (a,b) -> a[3] - b[3]
        );
        int[][][] visited = new int[mapR][mapC][4];
        
        for(int[][] a : visited) {
            for(int[] b : a){
                Arrays.fill(b, Integer.MAX_VALUE);
            }
        }
        
        for(int d = 0; d < 4; d++) visited[0][0][d] = 0;
        queue.add(new int[]{0,0,-1,0});
        
        while(!queue.isEmpty()){
            int[] node = queue.poll();
            int r = node[0];
            int c = node[1];
            int dir = node[2];
            int money = node[3];
            
            if(r == mapR-1 && c == mapC-1) return money;
            
            for(int d = 0 ; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(possible(nr,nc)) continue;
                if(Map[nr][nc] == 1) continue;
                
                int nmoney;
                if(dir == -1){
                    nmoney = money + 100;
                } else if(dir == d){
                    nmoney = money + 100;
                } else {
                    nmoney = money + 600;
                }
                
                if(nmoney >= visited[nr][nc][d]) continue;
                visited[nr][nc][d] = nmoney;
                queue.add(new int[]{nr,nc,d,nmoney});
            }
        }
        
        return -1;
    }
    
    public static boolean possible(int r, int c){
        if(r >= mapR || r < 0 || c>= mapC || c < 0) return true;
        return false;
    }
}