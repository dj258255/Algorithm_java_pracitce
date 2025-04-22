import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int Tc = Integer.parseInt(br.readLine().trim());
		for(int t = 1; t<= Tc; t++) {
			//입력
			init();
			//풀이
			solve();
			//출력
			System.out.println("#" + t + " " + left);
		}
	}
	
	static int left,right;
	static int oddDay,evenDay;
	public static void solve() {
		oddDay = 0;
		evenDay =0;
		for(int i = 0 ; i < N; i++) {
			int diffTree = highTree-trees[i];
			oddDay += diffTree%2;
			evenDay += diffTree/2;
		}
		
		
		
		
		left = 0;
		right = highTree*N;
		
		while(left <= right) {
			int mid = (left+right) / 2;
			int midOdd = (mid+1)/2;
			int midEven = (mid)/2;
			
			int oddNeed = 2 * Math.max(0, evenDay - midEven);
			
			if(oddNeed+oddDay > midOdd) {
				left = mid+1;
			} else {
				right = mid-1;
			}
			
		}
		
	}
	
	static int N,highTree;
	static int[] trees;
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		trees = new int[N];
		
		highTree = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			highTree = Math.max(highTree, trees[i]);
		}
		
	}
}