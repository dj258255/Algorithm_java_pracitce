import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int SIZE = 4001; //좌표 확장을 위해 2배 + 1000

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
                int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
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
        int[][] map = new int[SIZE][SIZE]; //위치에 존재하는 에너지 총합

        while (!cores.isEmpty()) {
            //원자 이동
            for (Core c : cores) {
                c.x += dx[c.dir];
                c.y += dy[c.dir];
            }

            //map 초기화
            for (Core c : cores) {
                if (c.x >= 0 && c.x < SIZE && c.y >= 0 && c.y < SIZE) {
                    map[c.x][c.y] += c.k;
                }
            }

            //충돌 확인 및 에너지 합산
            List<Core> next = new ArrayList<>();
            for (Core c : cores) {
                if (c.x < 0 || c.x >= SIZE || c.y < 0 || c.y >= SIZE) continue;

                if (map[c.x][c.y] == c.k) {
                    //충돌 안함
                    next.add(c);
                } else {
                    //충돌:이 에너지는 여기서 더해지고, 제거됨
                    totalEnergy += c.k;
                }
            }

            //map초기화 (다음 시간 대비)
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
