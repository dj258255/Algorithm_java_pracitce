class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];

        for (int i = 1; i < n; i++) { //높이
            for (int j = 0; j <= i; j++) { //왼쪽
                if (j == 0) { //맨왼쪽
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                } else if (j == i) { //맨오른쪽
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                } else { //중간값들
                    dp[i][j] = triangle[i][j] + Math.max(dp[i-1][j-1], dp[i-1][j]);
                }
            }
        }

        
        int answer = 0;
        for(int i = 0 ; i < dp[n-1].length; i++){
            answer = Math.max(answer, dp[n-1][i]);
        }
        
        return answer;

    }
}