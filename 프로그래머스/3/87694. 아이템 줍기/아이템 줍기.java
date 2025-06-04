import java.util.*;

class Solution {
    
    static int answer;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    
    static int[][] map;
    static int N;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        N = 51 * 2;
        map = new int[N][N];
        
        //모든 직사각형 좌표로 변환해서 내부 1로 채움
        //좌측하단 x , 좌측하단 y,우측 상단 x,우측 상단 y
        for(int[] temp : rectangle){
            int x1= temp[0] * 2;
            int y1= temp[1] * 2;
            int x2= temp[2] * 2;
            int y2= temp[3] * 2;
            for(int i = x1; i <= x2; i++){
                for(int j = y1; j <= y2; j++){
                    map[i][j] =1;
                }
            }
        }

        //내부 경계제외 0
        for (int[] temp : rectangle) {
            int x1 = temp[0] * 2;
            int y1 = temp[1] * 2;
            int x2 = temp[2] * 2;
            int y2 = temp[3] * 2;
            for (int i = x1 + 1; i < x2; i++) {
                for (int j = y1 + 1; j < y2; j++) {
                    map[i][j] = 0;
                }
            }
        }

        bfs(characterX * 2,characterY * 2, itemX * 2, itemY * 2);
        
        return answer;
    }
    
    
    public static void bfs(int startX, int startY , int endX , int endY){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startX,startY,0}); //현재 x , 현재 y ,거리
        boolean[][] visited = new boolean[N][N];
        visited[startX][startY] = true;
        

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int x = node[0]; 
            int y = node[1];
            int dist = node[2];
            
            if (x == endX && y == endY) {
                answer = dist/2;
                return;
            }
            
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(map[nx][ny] != 1) continue;
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, dist+1});
                }
            }
        }
    }
}