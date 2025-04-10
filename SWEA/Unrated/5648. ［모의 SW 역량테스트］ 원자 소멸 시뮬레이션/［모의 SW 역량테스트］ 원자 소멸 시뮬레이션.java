import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
    static int[] dx = {0, 0, -1, 1}; // 상하좌우
    static int[] dy = {1, -1, 0, 0};
    static int[][] check = new int[4001][4001];
    static int sum;

    static class Core {
        int x, y, dir, k;

        Core(int x, int y, int dir, int k) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.k = k;
        }

        void move() {
            this.x += dx[dir];
            this.y += dy[dir];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            LinkedList<Core> cores = new LinkedList<>();
            sum = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                // 좌표 확장 (정수 충돌 방지 및 소수좌표 고려)
                cores.add(new Core(2 * (x + 1000), 2 * (y + 1000), dir, k));
            }

            solve(cores);
            System.out.println("#" + t + " " + sum);
        }
    }

    static void solve(LinkedList<Core> cores) {
        while (!cores.isEmpty()) {
            Iterator<Core> itr = cores.iterator();
            while (itr.hasNext()) {
                Core c = itr.next();
                c.move();
                if (c.x < 0 || c.y < 0 || c.x > 4000 || c.y > 4000) {
                    itr.remove(); // 범위 밖이면 삭제
                }
            }

            collision(cores);
        }
    }

    static void collision(LinkedList<Core> cores) {
        for (Core c : cores) {
            check[c.x][c.y] = 0;
        }
        for (Core c : cores) {
            check[c.x][c.y] += c.k;
        }

        Iterator<Core> itr = cores.iterator();
        while (itr.hasNext()) {
            Core c = itr.next();
            if (check[c.x][c.y] != c.k) {
                sum += c.k;
                itr.remove();
            }
        }
    }
}
