import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

public class Solution{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int tc = Integer.parseInt(st.nextToken());
		
		for(int t= 1; t<=tc; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[] number = new int[N+1];
			
			st = new StringTokenizer(br.readLine());
			
			int highTree =0;
			for(int i = 0 ; i < N; i++) {
				number[i] = Integer.parseInt(st.nextToken()); 
				highTree = Math.max(highTree, number[i]);
			}
			
			int oddDay=0;
			int evenday=0;
			
			for(int i = 0 ; i < N; i++) {
				int temp = highTree-number[i];
				oddDay += temp%2;
				evenday += temp/2;
			}
			
			int left = 0 ;
			int right = N*highTree;
			while(left <= right) {
				int mid = (left+right)/2;
				int midOdd = (mid+1)/2;
				int midEven = mid/2;
				
				int oddNeed = 2 * Math.max(0, evenday -midEven );
				
				if(oddNeed+oddDay <= midOdd) {
					right = mid-1;
				} else {
					left = mid+1;
				}
			}
			
			System.out.println("#" + t + " " + left);
			
		}
	}
}