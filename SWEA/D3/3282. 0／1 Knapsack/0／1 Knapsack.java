import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int Tc = Integer.parseInt(st.nextToken());
		
		for(int t= 1; t <= Tc; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[] V = new int[N];
			int[] C = new int[N];
			for(int i = 0 ; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				V[i] = Integer.parseInt(st.nextToken());
				C[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] dp = new int[K+1];
			
			for(int i = 0 ; i < N; i++) {
				for(int j = K; j >= V[i]; j--) {
					dp[j] = Math.max(dp[j], dp[j - V[i]] + C[i] );
				}
			}
			
			System.out.println("#" + t + " " + dp[K]);
		}
	}
}