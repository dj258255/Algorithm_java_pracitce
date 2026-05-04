import java.util.*;
import java.io.*;

class Solution {
    static final int MOD = 1_000_000_007;
    public int solution(int n, int[] money) {
        
        int[] dp = new int [n+1];
        
        dp[0] = 1;
        
        for(int i = 0; i < money.length; i++){
            int coin = money[i];
            
            for(int j = coin; j<= n; j++){
                dp[j] = (dp[j] + dp[j-coin]) % MOD;
            }
        }
        
        return dp[n] % MOD;
    }
}