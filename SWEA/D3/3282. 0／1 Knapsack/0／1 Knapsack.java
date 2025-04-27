import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N;
	static int K;
	static int[] V,C;
	static int[]dp;
	public static void input() throws IOException{
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());//물건
		
		K = Integer.parseInt(st.nextToken());//부피
		
		V = new int[N];
		C = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			V[i] = Integer.parseInt(st.nextToken());//부피
			C[i] = Integer.parseInt(st.nextToken());//가치
		}
	}
	
	public static void dp_solve() {
		dp = new int[K+1];
		
		for(int i = 0 ; i < N; i++) {
			for(int j = K; j >= V[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j- V[i]] + C[i]);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int Tc = Integer.parseInt(br.readLine().trim());
		for(int t = 1 ; t<=Tc; t++) {
			//입력
			input();
			//계산
			dp_solve();
			//결과
			sb.append("#").append(t).append(" ").append(dp[K]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
