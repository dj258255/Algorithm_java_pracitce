import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int n, p,answer;
    static int[] days;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
        	input();

        	answer = solve();
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
    
    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        days = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            days[i] = Integer.parseInt(st.nextToken());
        }
    }
    public static int solve() {
        int maxLen = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            while (days[right] - days[left] + 1 - (right - left + 1) > p) {
                left++;
            }
            int covered = days[right] - days[left] + 1;
            int used = covered - (right - left + 1);
            int leftover = p - used;
            int currentLen = covered + leftover;
            maxLen = Math.max(maxLen, currentLen);
        }
        return maxLen;
    }
}
