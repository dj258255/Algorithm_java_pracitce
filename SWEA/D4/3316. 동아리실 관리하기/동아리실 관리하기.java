
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
    
    static String a;
    static int answer;

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        a = st.nextToken();
    }
    
    public static void solve() {
        int days = a.length();
        int[][] dp = new int[days][16];
        int MOD = 1000000007;
        
        // 1일차 초기화 -> A(비트0)와 첫째날 책임자 비트 포함
        int firstAdmin = 1 << (a.charAt(0) - 'A');
        for (int mask = 1; mask < 16; mask++) {
            if ((mask & 1) != 0 && (mask & firstAdmin) != 0) {
                dp[0][mask] = 1;
            }
        }
        
        // 2일차 이후 DP 전이
        for (int day = 1; day < days; day++) {
            int adminBit = 1 << (a.charAt(day) - 'A');
            for (int mask = 1; mask < 16; mask++) {
                if ((mask & adminBit) == 0) continue;  // 책임자 미참여 불가

                long ways = 0;
                for (int prev = 1; prev < 16; prev++) {
                    if ((mask & prev) != 0) {
                        ways += dp[day - 1][prev];
                    }
                }
                dp[day][mask] = (int)(ways % MOD);
            }
        }

        // 마지막 날 결과를 클래스 필드에 저장
        answer = 0;
        for (int mask = 1; mask < 16; mask++) {
            answer = (int)(((long)answer + dp[days - 1][mask]) % MOD);
        }
    }
    
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int Tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= Tc; t++) {
            input();
            solve();  
            bw.write("#" + t + " " + answer);
            bw.newLine();
        }
        
        bw.flush();
        br.close();
    }
}