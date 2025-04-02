import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
    static int N, M, R, C, L;
    static int[][] map;
    static int[] pipe = new int[8];
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] opposite = {1, 0, 3, 2};
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pipe[1] = (1 << 0) | (1 << 1) | (1 << 2) | (1 << 3); // 타입 1: 상,하,좌,우
        pipe[2] = (1 << 0) | (1 << 1);                         // 타입 2: 상,하
        pipe[3] = (1 << 2) | (1 << 3);                         // 타입 3: 좌,우
        pipe[4] = (1 << 0) | (1 << 3);                         // 타입 4: 상,우
        pipe[5] = (1 << 1) | (1 << 3);                         // 타입 5: 하,우
        pipe[6] = (1 << 1) | (1 << 2);                         // 타입 6: 하,좌
        pipe[7] = (1 << 0) | (1 << 2);                         // 타입 7: 상,좌

        int Tc = Integer.parseInt(br.readLine().trim());
        for (int T = 1; T <= Tc; T++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs();
            System.out.println("#" + T + " " + count);
        }
    }

    public static void bfs() {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { R, C });
        visited[R][C] = true;
        count = 1;

        int steps = 1;
        while (!queue.isEmpty() && steps < L) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int r = cur[0], c = cur[1];
                int curType = map[r][c];
                int possible = pipe[curType];
                for (int d = 0; d < 4; d++) {
                    if ((possible & (1 << d)) == 0) continue;
                    int nr = r + dr[d], nc = c + dc[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 0 || visited[nr][nc])
                        continue;
                    int nextType = map[nr][nc];
                    if ((pipe[nextType] & (1 << opposite[d])) == 0) continue;
                    visited[nr][nc] = true;
                    queue.add(new int[] { nr, nc });
                    count++;
                }
            }
            steps++;
        }
    }
}
