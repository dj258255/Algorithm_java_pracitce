import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        
        int T = Integer.parseInt(st.nextToken());
        for(int Tc = 1; Tc<=T; Tc++) {
        	st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken()); //햄버거 개수
            int L = Integer.parseInt(st.nextToken()); //내 최대 칼로리

            int[] score = new int[N];  //햄버거들 점수 
            int[] calorie = new int[N]; //햄버거 칼로리

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                score[i] = Integer.parseInt(st.nextToken()); //햄버거 맛
                calorie[i] = Integer.parseInt(st.nextToken()); //햄버거 칼로리
            }
            
            int[] dp = new int[L + 1]; //최대칼로리 +1

            for(int i = 0; i < N; i++) {
                for(int j = L; j >= calorie[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - calorie[i]] + score[i]);
                }
            }
            
            System.out.println("#" + Tc + " " + dp[L]);
        }
    }
}