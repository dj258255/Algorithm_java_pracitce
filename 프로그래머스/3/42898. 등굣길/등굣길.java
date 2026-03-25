import java.util.*;
import java.io.*;

class Solution {
    public int solution(int m, int n, int[][] puddles){
        int[][] dp = new int[n][m];
        
        for(int i = 0; i < puddles.length; i++){
            int x = puddles[i][0];
            int y = puddles[i][1];
            
            dp[y-1][x-1] = -1;
        }
        
        dp[0][0] = 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(dp[i][j] == -1) continue; //현재가 물웅덩이면 넘겨
                if(i==0 && j==0) continue;
                //만약 위가 물웅덩이거나 현재가 맨 위면
                if(i == 0 || dp[i-1][j] == -1){
                    if(j == 0) continue;
                    dp[i][j] = dp[i][j-1] % 1000000007;
                } else if(j == 0 || dp[i][j-1] ==-1 ) { //만약 왼쪽이 물웅덩이거나 없으면
                    dp[i][j] = dp[i-1][j] % 1000000007;
                } else{  //그것도 아니면 
                    dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000007;
                }
                
            }
        }
        
        return dp[n-1][m-1] % 1000000007;
    }
}