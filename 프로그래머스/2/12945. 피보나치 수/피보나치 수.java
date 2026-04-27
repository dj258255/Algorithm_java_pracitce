import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n) {
        //f0은 1 f2 는 1 f3은 2 f4는 3 f5는 5
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        
        for(int i = 2 ; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
            dp[i] = dp[i] % 1234567;
        }
        return dp[n];
    }
}