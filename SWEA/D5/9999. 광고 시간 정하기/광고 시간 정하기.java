import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    
    static int L,N;
    static int[][] ads;
    public static void input() throws IOException {
    	st = new StringTokenizer(br.readLine());
    	L = Integer.parseInt(st.nextToken()); //광고길이
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken()); //구간 개수
    	
        ads = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ads[i][0] = Integer.parseInt(st.nextToken()); //시작
            ads[i][1] = Integer.parseInt(st.nextToken()); //끝
        }
        
    }
    
    
    static long best;
    public static void solve() {
        long[] pref = new long[N + 1];
        for (int i = 0; i < N; i++) {
            pref[i + 1] = pref[i] + (ads[i][1] - ads[i][0]); //구간합
        }

        best = 0;
        int r = 0;  //윈도우의 끝 바로 다음 인덱스

        for (int l = 0; l < N; l++) {
            int windowStart = ads[l][0];
            int windowEnd   = windowStart + L;

            //완전히 포함되는 구간만
            while (r < N && ads[r][1] <= windowEnd) {
                r++;
            }

            //완전 포함된 구간 합
            long total = pref[r] - pref[l];

            //ads[r]가 윈도우 일부를 덮는 경우
            if (r < N) {
                int overlap = Math.max(0, Math.min(ads[r][1], windowEnd) - ads[r][0]);
                total += overlap;
            }

            best = Math.max(best, total);
        }
    }
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
        	input();
        	
        	solve();
        	
        	bw.write("#" + tc + " " + best);
        	bw.newLine();
        }
        
        bw.flush();
        br.close();
    }
}
