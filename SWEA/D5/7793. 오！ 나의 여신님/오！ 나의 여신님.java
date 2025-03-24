import java.io.*;
import java.util.*;

public class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N, M;
    static char[][] map;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int test_case = Integer.parseInt(st.nextToken());
        
        for (int tc = 1; tc <= test_case; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            map = new char[N][M];
            int[] start = new int[2];
            int[] destination = new int[2];
            Queue<int[]> demonQueue = new ArrayDeque<>();
            
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String temp = st.nextToken();
                for (int j = 0; j < M; j++) {
                    map[i][j] = temp.charAt(j);
                    if (map[i][j] == 'S') {
                        start[0] = i;
                        start[1] = j;
                    }
                    if (map[i][j] == 'D') {
                        destination[0] = i;
                        destination[1] = j;
                    }
                    if (map[i][j] == '*') {
                        demonQueue.add(new int[]{i, j});
                    }
                }
            }
            
            int result = bfs(start, destination, demonQueue);
            System.out.println("#" + tc + " " + (result == -1 ? "GAME OVER" : result));
        }
    }
    
    public static int bfs(int[] start, int[] destination, Queue<int[]> demonQueue) {
        Queue<int[]> sQueue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        sQueue.add(start);
        visited[start[0]][start[1]] = true;
        
        int steps = 0;
        while (!sQueue.isEmpty()) {
            int demonSize = demonQueue.size();
            for (int i = 0; i < demonSize; i++) {
                int[] pos = demonQueue.poll();
                int r = pos[0], c = pos[1];
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                    if (map[nr][nc] == 'X' || map[nr][nc] == 'D') continue;
                    if (map[nr][nc] != '*') {
                        map[nr][nc] = '*';
                        demonQueue.add(new int[]{nr, nc});
                    }
                }
            }
            
            int sSize = sQueue.size();
            for (int i = 0; i < sSize; i++) {
                int[] pos = sQueue.poll();
                int r = pos[0], c = pos[1];
                if (r == destination[0] && c == destination[1]) {
                    return steps;
                }
                
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                    if (visited[nr][nc]) continue;
                    if (map[nr][nc] == 'X' || map[nr][nc] == '*') continue;
                    
                    visited[nr][nc] = true;
                    sQueue.add(new int[]{nr, nc});
                }
            }
            steps++;
        }
        
        return -1;
    }
}
