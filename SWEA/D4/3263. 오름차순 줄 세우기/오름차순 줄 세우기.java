import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int Tc = Integer.parseInt(st.nextToken());
		
		for(int t = 1 ; t <= Tc; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[] kids = new int[N];
			int[] dp = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N; i++) {
				kids[i] = Integer.parseInt(st.nextToken());
				dp[kids[i]] = i; //dp[i] 는 dp[kids[i]]의 값
			}
			
			
			int maxLength =1;
			int length = 1;
			for(int i = 2; i <= N; i++) {
				if(dp[i-1] < dp[i] ) {
					length++;
				} else {
					length = 1;
				}
				
				
				maxLength = Math.max(length, maxLength);
			}
			
			System.out.println("#" + t + " " + (N-maxLength));
		}
	}
}