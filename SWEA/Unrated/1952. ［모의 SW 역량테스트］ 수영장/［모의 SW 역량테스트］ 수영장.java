import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int Tc = Integer.parseInt(st.nextToken());
        
        for (int tc = 1; tc <= Tc; tc++) {
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int threeMonth = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            int[] years = new int[13];
            for (int i = 1; i <= 12; i++) {
                years[i] = Integer.parseInt(st.nextToken());
            }
            
            int[] dp = new int[13];
            dp[0] = 0;
            
            for (int i = 1; i <= 12; i++) {
                dp[i] = dp[i-1] + (years[i] * day);
                dp[i] = Math.min(dp[i], dp[i-1] + month);
                
                if (i >= 3) {
                    dp[i] = Math.min(dp[i], dp[i-3] + threeMonth);
                } else {
                    dp[i] = Math.min(dp[i], threeMonth);
                }
            }
            
            int result = Math.min(dp[12], year);
            System.out.println("#" + tc + " " + result);
        }
    }
}
