import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[] number = new int[N];
			int highTree =0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N; i++) {
				number[i] = Integer.parseInt(st.nextToken()); 
				highTree = Math.max(highTree, number[i]);
			}
			
			int oddDay =0;
			int evenDay =0;
			for(int i = 0 ; i < N; i++) {
				oddDay += (highTree-number[i])%2;
				evenDay += (highTree-number[i])/2;
			}
			
			
			
			int left=0;
			int right=highTree*N;
			
			while(left <= right) {
				int mid = (left+right)/2;
				
				int midOdd = (mid+1)/2;
				int midEven = (mid)/2;
				
				int needOdd = 2 * Math.max(0, evenDay-midEven );
				
				if(needOdd+oddDay > midOdd) {
					left = mid + 1;
				} else {
					right = mid-1;
					
				}
				
			}
			
			System.out.println("#" + tc + " " +left);
		}
	}
}