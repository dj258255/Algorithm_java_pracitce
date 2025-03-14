
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int W, H, K;
    static int[][] hourse = {{-2, -1}, {-1, -2}, {1, 2}, {2, 1}, {-2, 1}, {-1, 2}, {1, -2}, {2, -1}}; //말처럼 움직이는 8방향
    static int[][] walk = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; //상하좌우
    static int[][] map;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken()); 
        
        st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;
        bfs();
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    public static void bfs() {
        //큐에 (x, y, count, hoursecount) 값을 넣고 탐색
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[H][W][K + 1];

        queue.offer(new int[]{0, 0, 0, 0}); //x,y,이동 횟수 count,말처럼 이동 횟수 hoursecount
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], count = current[2], hoursecount = current[3];

            //도착지 도착 
            if (x == W - 1 && y == H - 1) {
                answer = Math.min(answer, count);
                return;
            }

            //상하좌우 이동
            for (int i = 0; i < 4; i++) {
                int nx = x + walk[i][0];
                int ny = y + walk[i][1];

                if (possible(nx, ny) || visited[ny][nx][hoursecount]) continue;

                visited[ny][nx][hoursecount] = true;
                queue.offer(new int[]{nx, ny, count + 1, hoursecount});
            }

            //말처럼 이동
            if (hoursecount < K) {
                for (int j = 0; j < 8; j++) {
                    int nx = x + hourse[j][0];
                    int ny = y + hourse[j][1];

                    if (possible(nx, ny) || visited[ny][nx][hoursecount + 1]) continue;

                    visited[ny][nx][hoursecount + 1] = true;
                    queue.offer(new int[]{nx, ny, count + 1, hoursecount + 1});
                }
            }
        }
    }

    public static boolean possible(int nx, int ny) {
        if (nx < 0 || ny < 0 || nx >= W || ny >= H) {
            return true;
        }
        if (map[ny][nx] == 1) {
            return true;
        }
        return false;
    }
}
