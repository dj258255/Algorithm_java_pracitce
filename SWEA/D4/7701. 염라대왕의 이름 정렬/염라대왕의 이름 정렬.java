import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.TreeSet;

public class Solution {
    static BufferedReader br;
    static StringBuilder sb;
    static int N;
    static TreeSet<String> ts;
    
    static Comparator<String> comp = Comparator
            .comparingInt(String::length)
            .thenComparing(Comparator.naturalOrder());

    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        ts = new TreeSet<>(comp);
        for (int i = 0; i < N; i++) {
            ts.add(br.readLine().trim());
        }
    }

    public static void solve(int tc) {
        sb.append("#").append(tc).append("\n");
        for (String name : ts) {
            sb.append(name).append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            input();
            solve(tc);
        }

        System.out.print(sb.toString());
    }
}
