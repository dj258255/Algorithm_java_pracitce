import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static String a;
	static String b;
	static int aLen,bLen;
	public static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		a = st.nextToken();
		b = st.nextToken();

		
	}
	
	public static void solve() {
		aLen = a.length();
		bLen = b.length();
		
		dp = new int[aLen + 1][bLen + 1];

		for (int i = 1; i <= aLen; i++) {
			for (int j = 1; j <= bLen; j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
				else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

	}
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		for (int t = 1; t <= TC; t++) {
			input();
			
			solve();
			
			sb.append("#").append(t).append(" ").append(dp[aLen][bLen]).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}