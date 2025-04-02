import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
  
public class Solution {
    static int map[][];
    static int dx[] = { -1, 1, 0, 0 }; // 상 하 좌 우
    static int dy[] = { 0, 0, -1, 1 };
  
    static class Pair {
        int x;
        int y;
        int v;
  
        public Pair(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }
  
    static int bfs(int x, int y, int N, int M, int L) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y, map[x][y]));
        boolean check[][] = new boolean[N][M];
        check[x][y] = true;
        int q_size = 0;
        while (!q.isEmpty() && L > 1) {
            L--;
            q_size = q.size();
            for (int qSize = 0; qSize < q_size; qSize++) {
                Pair p = q.poll();
                if (p.v == 1) {
                    // 상하좌우
                    for (int i = 0; i < 4; i++) {
                        int nx = p.x + dx[i];
                        int ny = p.y + dy[i];
                        if (0 <= nx && nx < N && 0 <= ny && ny < M && check[nx][ny] == false) {
                            int mapV = map[nx][ny];
                            // 상
                            if (i == 0 && (mapV == 1 || mapV == 2 || mapV == 5 || mapV == 6)) {
                                q.add(new Pair(nx, ny, map[nx][ny]));
                                check[nx][ny] = true;
                            }
                            // 하
                            else if (i == 1 && (mapV == 1 || mapV == 2 || mapV == 4 || mapV == 7)) {
                                q.add(new Pair(nx, ny, map[nx][ny]));
                                check[nx][ny] = true;
                            }
                            // 좌
                            else if (i == 2 && (mapV == 1 || mapV == 3 || mapV == 4 || mapV == 5)) {
                                q.add(new Pair(nx, ny, map[nx][ny]));
                                check[nx][ny] = true;
                            }
                            // 우
                            else if (i == 3 && (mapV == 1 || mapV == 3 || mapV == 6 || mapV == 7)) {
                                q.add(new Pair(nx, ny, map[nx][ny]));
                                check[nx][ny] = true;
                            }
                        }
  
                    }
                } else if (p.v == 2) {
                    // 상하
                    for (int i = 0; i < 2; i++) {
                        int nx = p.x + dx[i];
                        int ny = p.y + dy[i];
                        if (0 <= nx && nx < N && 0 <= ny && ny < M && check[nx][ny] == false) {
                            int mapV = map[nx][ny];
                            // 상
                            if (i == 0 && (mapV == 1 || mapV == 2 || mapV == 5 || mapV == 6)) {
                                q.add(new Pair(nx, ny, map[nx][ny]));
                                check[nx][ny] = true;
                            }
                            // 하
                            else if (i == 1 && (mapV == 1 || mapV == 2 || mapV == 4 || mapV == 7)) {
                                q.add(new Pair(nx, ny, map[nx][ny]));
                                check[nx][ny] = true;
                            }
                        }
                    }
                } else if (p.v == 3) {
                    // 좌우
                    for (int i = 2; i < 4; i++) {
                        int nx = p.x + dx[i];
                        int ny = p.y + dy[i];
                        if (0 <= nx && nx < N && 0 <= ny && ny < M && check[nx][ny] == false) {
                            int mapV = map[nx][ny];
                            // 좌
                            if (i == 2 && (mapV == 1 || mapV == 3 || mapV == 4 || mapV == 5)) {
                                q.add(new Pair(nx, ny, map[nx][ny]));
                                check[nx][ny] = true;
                            }
                            // 우
                            else if (i == 3 && (mapV == 1 || mapV == 3 || mapV == 6 || mapV == 7)) {
                                q.add(new Pair(nx, ny, map[nx][ny]));
                                check[nx][ny] = true;
                            }
                        }
                    }
                } else if (p.v == 4) {
                    // 상우
                    for (int i = 0; i < 4; i++) {
                        int nx = p.x + dx[i];
                        int ny = p.y + dy[i];
                        if (0 <= nx && nx < N && 0 <= ny && ny < M && check[nx][ny] == false) {
                            int mapV = map[nx][ny];
                            // 상
                            if (i == 0 && (mapV == 1 || mapV == 2 || mapV == 5 || mapV == 6)) {
                                q.add(new Pair(nx, ny, map[nx][ny]));
                                check[nx][ny] = true;
                            }
                            // 우
                            else if (i == 3 && (mapV == 1 || mapV == 3 || mapV == 6 || mapV == 7)) {
                                q.add(new Pair(nx, ny, map[nx][ny]));
                                check[nx][ny] = true;
                            }
                        }
                    }
                } else if (p.v == 5) {
                    // 하우
                    for (int i = 0; i < 4; i++) {
                        int nx = p.x + dx[i];
                        int ny = p.y + dy[i];
                        if (0 <= nx && nx < N && 0 <= ny && ny < M && check[nx][ny] == false) {
                            int mapV = map[nx][ny];
                            // 하
                            if (i == 1 && (mapV == 1 || mapV == 2 || mapV == 4 || mapV == 7)) {
                                q.add(new Pair(nx, ny, map[nx][ny]));
                                check[nx][ny] = true;
                            }
                            // 우
                            else if (i == 3 && (mapV == 1 || mapV == 3 || mapV == 6 || mapV == 7)) {
                                q.add(new Pair(nx, ny, map[nx][ny]));
                                check[nx][ny] = true;
                            }
                        }
                    }
                } else if (p.v == 6) {
                    // 하좌
                    for (int i = 0; i < 4; i++) {
                        int nx = p.x + dx[i];
                        int ny = p.y + dy[i];
                        if (0 <= nx && nx < N && 0 <= ny && ny < M && check[nx][ny] == false) {
                            int mapV = map[nx][ny];
                            // 하
                            if (i == 1 && (mapV == 1 || mapV == 2 || mapV == 4 || mapV == 7)) {
                                q.add(new Pair(nx, ny, map[nx][ny]));
                                check[nx][ny] = true;
                            }
                            // 좌
                            else if (i == 2 && (mapV == 1 || mapV == 3 || mapV == 4 || mapV == 5)) {
                                q.add(new Pair(nx, ny, map[nx][ny]));
                                check[nx][ny] = true;
                            }
                        }
                    }
                } else if (p.v == 7) {
                    // 상좌
                    for (int i = 0; i < 4; i++) {
                        int nx = p.x + dx[i];
                        int ny = p.y + dy[i];
                        if (0 <= nx && nx < N && 0 <= ny && ny < M && check[nx][ny] == false) {
                            int mapV = map[nx][ny];
                            // 상
                            if (i == 0 && (mapV == 1 || mapV == 2 || mapV == 5 || mapV == 6)) {
                                q.add(new Pair(nx, ny, map[nx][ny]));
                                check[nx][ny] = true;
                            }
                            // 좌
                            else if (i == 2 && (mapV == 1 || mapV == 3 || mapV == 4 || mapV == 5)) {
                                q.add(new Pair(nx, ny, map[nx][ny]));
                                check[nx][ny] = true;
                            }
                        }
                    }
                }
            }
        }
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (check[i][j] == true) {
                    result++;
                }
            }
        }
        return result;
    }
  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());// 맵 세로
            int M = Integer.parseInt(st.nextToken());// 맵 가로
            int R = Integer.parseInt(st.nextToken());// 맨홀 세로
            int C = Integer.parseInt(st.nextToken());// 맨홀 가로
            int L = Integer.parseInt(st.nextToken());// 탈출 후 소요된 시간
            map = new int[N][M];
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    map[n][m] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println("#" + (t + 1) + " " + bfs(R, C, N, M, L));
        }
    }
}