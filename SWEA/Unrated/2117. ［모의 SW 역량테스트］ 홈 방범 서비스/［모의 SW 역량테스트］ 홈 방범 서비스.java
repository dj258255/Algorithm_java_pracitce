import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
    static int N, M;
    static int[][] map;
    static int[][] dir = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Tc = Integer.parseInt(st.nextToken());
        for (int t = 1; t <= Tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            answer = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    find(i, j, 1);
                }
            }
            System.out.println("#" + t + " " + answer);
        }
    }
    
    public static void find(int startR, int startC, int k) {
        // 서비스 영역의 k값이 최대 범위를 넘으면 종료
        if (k > 2 * N) return;
        
        // 운영 비용 계산: cost = k^2 + (k-1)^2
        int cost = k * k + (k - 1) * (k - 1);
        

        int maxPossible = 0;
        for (int i = -(k - 1); i <= (k - 1); i++) {
            for (int j = -(k - 1); j <= (k - 1); j++) {
                if (Math.abs(i) + Math.abs(j) < k) {
                    int nr = startR + i;
                    int nc = startC + j;
                    if (!outOfBounds(nr, nc) && map[nr][nc] == 1) {
                        maxPossible++;
                    }
                }
            }
        }
        if (M * maxPossible < cost) {
            find(startR, startC, k + 1);
            return;
        }
        
        // BFS를 이용하여 실제 서비스 영역 내 집 개수 계산
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { startR, startC, 0 });
        visited[startR][startC] = true;
        int homeCount = (map[startR][startC] == 1) ? 1 : 0;
        
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int r = node[0], c = node[1], depth = node[2];
            
            if (depth == k - 1) continue;
            for (int d = 0; d < 4; d++) {
                int nr = r + dir[d][0], nc = c + dir[d][1];
                if (outOfBounds(nr, nc)) continue;
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    if (map[nr][nc] == 1) homeCount++;
                    queue.add(new int[] { nr, nc, depth + 1 });
                }
            }
        }
        
        if (M * homeCount >= cost) {
            answer = Math.max(answer, homeCount);
        }
        find(startR, startC, k + 1);
    }

    public static boolean outOfBounds(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}
