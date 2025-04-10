import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static int N, W, H, answer;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map, simulMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //구슬 쏘는 횟수
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
            dfs(0, map);

            System.out.println("#" + tc + " " + answer);
        }
    }

    public static void dfs(int count, int[][] gameMap) {
        if (count == N) {
            int remain = countBricks(gameMap);
            answer = Math.min(answer, remain);
            return;
        }

        for (int i = 0; i < W; i++) {
            int[][] nextMap = bombMap(gameMap, i);
            dfs(count + 1, nextMap);
        }
    }

    //구슬을 떨어뜨리고 터트린 뒤, 정리된 맵 반환
    public static int[][] bombMap(int[][] gameMap, int index) {
        simulMap = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                simulMap[i][j] = gameMap[i][j];
            }
        }

        for (int i = 0; i < H; i++) {
            if (simulMap[i][index] > 0) {
                bomb(i, index);
                break;
            }
        }

        //중력 처리
        for (int i = 0; i < W; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = H - 1; j >= 0; j--) {
                if (simulMap[j][i] > 0) {
                    list.add(simulMap[j][i]);
                    simulMap[j][i] = 0;
                }
            }

            for (int j = 0; j < list.size(); j++) {
                simulMap[H - 1 - j][i] = list.get(j);
            }
        }

        return simulMap;
    }

    public static void bomb(int r, int c) {
        if (simulMap[r][c] == 0) return;

        int range = simulMap[r][c];
        simulMap[r][c] = 0;

        for (int d = 0; d < 4; d++) {
            for (int i = 1; i < range; i++) {
                int nr = r + dr[d] * i;
                int nc = c + dc[d] * i;
                if (nr < 0 || nc < 0 || nr >= H || nc >= W) continue;

                if (simulMap[nr][nc] > 0) {
                    bomb(nr, nc);
                }
            }
        }
    }

    public static int countBricks(int[][] tempmap) {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (tempmap[i][j] > 0) cnt++;
            }
        }
        return cnt;
    }
}
