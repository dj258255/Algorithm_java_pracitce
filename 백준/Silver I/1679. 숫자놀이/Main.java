import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static int N, K;
    static int[] num;

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine().trim());
        num = new int[N];
        for (int i = 0; i < N; i++) num[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine().trim());
        K = Integer.parseInt(st.nextToken());
        endGame = false;
    }

    static boolean endGame;

    public static void solve() throws IOException {
        boolean isHolsoon = true;
        int goal = 1;
        while (true) {
            bfs(isHolsoon ? "holsoon" : "jjaksoon", goal);
            if (endGame) {
                bw.write(winName + " win at " + goal);
                return;
            }
            goal++;
            isHolsoon = !isHolsoon;
        }
    }

    static String winName;

    public static void bfs(String name, int goal) {
        Queue<int[]> queue = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        queue.add(new int[]{0, 0});
        set.add(0);

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int curN  = node[0];
            int count = node[1];

            for (int n : num) {
                int nextNum = curN + n;

                if (nextNum == goal) {
                    winName = name;
                    return;
                }

                if (set.contains(nextNum)) continue;
                if (nextNum > goal) continue;
                if (count + 1 >= K) continue;  // K번 초과 방지

                set.add(nextNum);  // curN 아니라 nextNum
                queue.add(new int[]{nextNum, count + 1});
            }
        }

        winName = name.equals("holsoon") ? "jjaksoon" : "holsoon";
        endGame = true;
    }

    public static void output() throws IOException {
        bw.flush();
        bw.close();
    }
}