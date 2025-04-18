import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int tc = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t<=tc; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int[] number = new int[N];
			int[] dp = new int[N+1];
			for(int i = 0 ; i < N; i++) {
				number[i] = Integer.parseInt(st.nextToken()); 
				dp[number[i]] = i;
			}
			
			int maxLength = 1;
			int curLength = 1;
			for(int i = 2 ; i <= N; i++) {
				if(dp[i-1] < dp[i] ) {
					curLength++;
				} else {
					 curLength = 1;
				}
				
				maxLength = Math.max(maxLength, curLength);
               
			}
			
			System.out.println("#" + t + " " + (N-maxLength));
		}
	}
}

