import java.util.*;
import java.io.*;

public class Main{
	static int T;
	static int n;
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		input();
		output();
	}
	
	public static void input() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		T = Integer.parseInt(br.readLine().trim());
		
		for(int t = 0 ; t < T; t++) {
			st = new StringTokenizer(br.readLine().trim());
			n = Integer.parseInt(st.nextToken());
			dp = new int[2][n+1];
			
			for(int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j = 0 ; j < n; j++) {
					dp[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			solve(dp);
		}

	}
	
	public static void solve(int[][] dp) throws IOException{
	    // dp[0][i] = 윗줄 i번째 스티커 점수
	    // dp[1][i] = 아랫줄 i번째 스티커 점수

	    // 각 열에서 선택할 수 있는 경우는 3가지:
	    // d0 = 이 열에서 아무것도 안 뗌
	    // d1 = 이 열에서 윗줄 스티커를 뗌
	    // d2 = 이 열에서 아랫줄 스티커를 뗌
	    // (같은 열에서 둘 다 떼는 건 상하로 인접하니까 불가능)

	    // 첫 번째 열 초기화
	    long d0 = 0, d1 = dp[0][0], d2 = dp[1][0];

	    for(int i = 1; i < n; i++) {

	        // [규칙 1] 이번 열에서 아무것도 안 뗌
	        // → 이전 열에서 뭘 했든 상관없음
	        long nd0 = Math.max(d0, Math.max(d1, d2));

	        // [규칙 2] 이번 열에서 윗줄을 뗌
	        // → 이전 열에서 윗줄을 뗐으면 안 됨 (좌우로 인접하니까)
	        // → 이전에 안 뗐거나(d0), 아랫줄을 뗐거나(d2)만 가능
	        long nd1 = Math.max(d0, d2) + dp[0][i];

	        // [규칙 3] 이번 열에서 아랫줄을 뗌
	        // → 이전 열에서 아랫줄을 뗐으면 안 됨 (좌우로 인접하니까)
	        // → 이전에 안 뗐거나(d0), 윗줄을 뗐거나(d1)만 가능
	        long nd2 = Math.max(d0, d1) + dp[1][i];

	        d0 = nd0;
	        d1 = nd1;
	        d2 = nd2;
	    }

	    // 마지막 열까지 다 본 후, 세 경우 중 최댓값이 정답
	    bw.write(Math.max(d0, Math.max(d1, d2)) + "\n");
	}
	
	public static void output() throws IOException{
		bw.flush();
		bw.close();
	}
}