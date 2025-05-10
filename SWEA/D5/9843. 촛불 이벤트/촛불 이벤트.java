
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static long N;
    static long answer;

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
    }

    public static void solve() {
        if (N == 0) {
            answer = -1;
            return;
        }
        long x = 1 + 8 * N;
        long d = (long) Math.sqrt(x);
        if (d * d == x && (d - 1) % 2 == 0) {
            answer = (d - 1) / 2;
        } else {
            answer = -1;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int T = 1; T <= t; T++) {
            input();
            solve();
            bw.write("#" + T + " " + answer);
            bw.newLine();
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
