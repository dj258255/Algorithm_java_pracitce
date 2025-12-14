import java.util.*;
import java.io.*;

public class Main{
    static StringBuilder sb;
    static StringTokenizer st;
    static BufferedReader br;
    static BufferedWriter bw;
    static int N, M, H;
    static ArrayList<Integer>[] student;

    static final int MOD = 10007;
    static int count;

    public static void main(String[] args) throws IOException{
        input();
        solve();
        output();
    }

    public static void solve() {
        int[] dp = new int[H + 1];
        dp[0] = 1;

        for (int i = 0; i < N; i++) {
            int[] next = Arrays.copyOf(dp, dp.length);

            for (int block : student[i]) {
                for (int h = H; h >= block; h--) {
                    next[h] = (next[h] + dp[h - block]) % MOD;
                }
            }

            dp = next;
        }

        count = dp[H];
    }

    public static void output() throws IOException {
        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
    }

    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        student = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            student[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                student[i].add(Integer.parseInt(st.nextToken()));
            }
        }
    }
}
