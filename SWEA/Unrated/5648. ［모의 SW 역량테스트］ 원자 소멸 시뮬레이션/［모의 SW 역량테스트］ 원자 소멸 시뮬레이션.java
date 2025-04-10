import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static final int SIZE = 4001;
    static final int OFFSET = 1000;
    static final int SCALE = 2;

    static int[] dx = {0, 0, -1, 1}; //상하좌우
    static int[] dy = {1, -1, 0, 0};

    static class Core {
        int x, y, dir, k;

        Core(int x, int y, int dir, int k) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.k = k;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Tc = Integer.parseInt(br.readLine());

        for (int T = 1; T <= Tc; T++) {
            int N = Integer.parseInt(br.readLine());
            List<Core> cores = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = (Integer.parseInt(st.nextToken()) + OFFSET) * SCALE;
                int y = (Integer.parseInt(st.nextToken()) + OFFSET) * SCALE;
                int dir = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                cores.add(new Core(x, y, dir, k));
            }

            int sum = simulate(cores);
            System.out.println("#" + T + " " + sum);
        }
    }

    static int simulate(List<Core> cores) {
        int totalEnergy = 0;
        int[][] map = new int[SIZE][SIZE];

        while (!cores.isEmpty()) {
            //원자 이동
            for (Core c : cores) {
                c.x += dx[c.dir];
                c.y += dy[c.dir];
            }

            for (Core c : cores) {
                if (c.x >= 0 && c.x < SIZE && c.y >= 0 && c.y < SIZE) {
                    map[c.x][c.y] += c.k;
                }
            }

            List<Core> next = new ArrayList<>();
            for (Core c : cores) {
                if (c.x < 0 || c.x >= SIZE || c.y < 0 || c.y >= SIZE) continue;

                if (map[c.x][c.y] == c.k) {
                    next.add(c);
                } else {
                    totalEnergy += c.k;
                }
            }

            for (Core c : cores) {
                if (c.x >= 0 && c.x < SIZE && c.y >= 0 && c.y < SIZE) {
                    map[c.x][c.y] = 0;
                }
            }

            cores = next;
        }

        return totalEnergy;
    }
}
