import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static BufferedWriter bw;
    static int H, W, N;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static char[][] Map;
    static int[] start;
    static ArrayList<int[]> cheese;

    public static int bfs(int sr, int sc, int tr, int tc) {
        boolean[][] visited = new boolean[H][W];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sr, sc, 0});
        visited[sr][sc] = true;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int r = node[0], c = node[1], dist = node[2];

            if (r == tr && c == tc) {
                return dist;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                if (Map[nr][nc] == 'X') continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc, dist + 1});
            }
        }
        return -1;
    }

    public static void solve() throws IOException {
        int r = start[0], c = start[1];
        int total = 0;

        for (int i = 0; i < N; i++) {
            int[] target = cheese.get(i);
            total += bfs(r, c, target[0], target[1]);
            r = target[0];
            c = target[1];
        }
        bw.write("" + total);
    }

    public static void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        start = new int[2];
        Map = new char[H][W];
        int[][] cheesePos = new int[N + 1][2];

        for (int i = 0; i < H; i++) {
            String temp = br.readLine().trim();
            for (int j = 0; j < W; j++) {
                Map[i][j] = temp.charAt(j);
                if (Map[i][j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                } else if (Map[i][j] >= '1' && Map[i][j] <= '9') {
                    int num = Map[i][j] - '0';
                    cheesePos[num][0] = i;
                    cheesePos[num][1] = j;
                }
            }
        }

        cheese = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            cheese.add(new int[]{cheesePos[i][0], cheesePos[i][1]});
        }
    }
}