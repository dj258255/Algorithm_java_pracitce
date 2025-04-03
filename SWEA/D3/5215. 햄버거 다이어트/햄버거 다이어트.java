import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			int[][] hamburger = new int[N][2];
			for(int i = 0 ; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				hamburger[i][0] = Integer.parseInt(st.nextToken()); //맛
				hamburger[i][1] = Integer.parseInt(st.nextToken()); //칼로리
			}
			
			int[] dp = new int[L+1];
			
			for(int i = 0; i < N; i++) {
				for(int j = L; j > hamburger[i][1]; j--) {
					dp[j] = Math.max(dp[j], dp[ j- hamburger[i][1] ] + hamburger[i][0]);
				}
			}
			
			System.out.println("#" + tc + " " + dp[L]);
			
		}
	}
}