//  줄세우기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int N,maxLen;
    static int[] child;
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        child = new int[N];
        for(int i = 0 ; i < N; i++){
            child[i] = Integer.parseInt(br.readLine());
        }

    }


    public static void solve(){
        maxLen = Integer.MIN_VALUE;
        int[] dp = new int[N+1];
        for(int i = 0 ; i < N; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(child[i] > child[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        for(int i = 0 ; i <N; i++){
            maxLen = Math.max(maxLen,dp[i]);
        }
    }

    public static void main(String[] args) throws IOException{
        input();

        solve();


        bw.write(N-maxLen + "");
        bw.flush();
        bw.close();
    }
}