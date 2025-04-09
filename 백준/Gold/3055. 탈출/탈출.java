import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
    static int[][] count;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int goalR, goalC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        count = new int[R][C];
        Queue<int[]> waterQueue = new ArrayDeque<>();
        Queue<int[]> moveQueue = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = row.charAt(j);
                if (map[i][j] == 'D') {
                    goalR = i;
                    goalC = j;
                } else if (map[i][j] == 'S') {
                    moveQueue.add(new int[]{i, j});
                } else if (map[i][j] == '*') {
                    waterQueue.add(new int[]{i, j});
                }
            }
        }

        int result = bfs(moveQueue, waterQueue);
        System.out.println(result == -1 ? "KAKTUS" : result);
    }

    public static int bfs(Queue<int[]> moveQueue, Queue<int[]> waterQueue) {
        boolean[][] visited = new boolean[R][C];
        for (int[] start : moveQueue) {
            visited[start[0]][start[1]] = true;
        }

        while (!moveQueue.isEmpty()) {
            // 물 먼저 퍼뜨리기
            waterQueue = spreadWater(waterQueue);

            int size = moveQueue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = moveQueue.poll();
                int r = cur[0];
                int c = cur[1];

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (!inBounds(nr, nc) || visited[nr][nc]) continue;
                    if (map[nr][nc] == '*' || map[nr][nc] == 'X') continue;

                    count[nr][nc] = count[r][c] + 1;
                    if (map[nr][nc] == 'D') {
                        return count[nr][nc];
                    }

                    visited[nr][nc] = true;
                    moveQueue.add(new int[]{nr, nc});
                }
            }
        }

        return -1;
    }

    public static Queue<int[]> spreadWater(Queue<int[]> waterQueue) {
        int size = waterQueue.size();
        Queue<int[]> newWater = new ArrayDeque<>();

        for (int i = 0; i < size; i++) {
            int[] cur = waterQueue.poll();
            int r = cur[0];
            int c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (!inBounds(nr, nc)) continue;
                if (map[nr][nc] == '.' || map[nr][nc] == 'S') {
                    map[nr][nc] = '*';
                    newWater.add(new int[]{nr, nc});
                }
            }
        }

        return newWater;
    }

    public static boolean inBounds(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
