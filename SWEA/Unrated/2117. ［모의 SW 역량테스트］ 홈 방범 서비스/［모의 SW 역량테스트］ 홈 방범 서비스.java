import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    static int N, M, answer;
    static int[][] map;
    static int[][] dir = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            answer = 0;
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    bfs(i, j);
                }
            }
            System.out.println("#" + t + " " + answer);
        }
    }

    static void bfs(int startR, int startC){
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();
        // queue 요소: {r, c, distance}
        visited[startR][startC] = true;
        queue.offer(new int[]{startR, startC, 0});

        int cumulative = (map[startR][startC] == 1 ? 1 : 0);

        int currentLayer = 0;
        int k = 1;
        int cost = k * k + (k - 1) * (k - 1);
        if(cumulative * M >= cost) {
            answer = Math.max(answer, cumulative);
        }
        

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1], dist = cur[2];

            if(dist > currentLayer){
                currentLayer = dist;
                k = currentLayer + 1;
                cost = k * k + (k - 1) * (k - 1);
                if(cumulative * M >= cost) {
                    answer = Math.max(answer, cumulative);
                }
            }

            if(dist < 2 * N){ 
                for (int d = 0; d < 4; d++){
                    int nr = r + dir[d][0], nc = c + dir[d][1];
                    if(outOfBounds(nr, nc)) continue;
                    if(!visited[nr][nc]){
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc, dist + 1});
                        if(map[nr][nc] == 1){
                            cumulative++; 
                        }
                    }
                }
            }
        }
    }

    static boolean outOfBounds(int r, int c){
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}
