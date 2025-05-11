import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution{
	
	
	static int N;
	static long M;
	static long[] num;
	public static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //N종류의 사탕
		M = Long.parseLong(st.nextToken()); // 가방안에 정확히 M개의 사탕
		
		num = new long[N];
		right = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++) {
			num[i] = Long.parseLong(st.nextToken());
			right = Math.max(right, num[i]);
		}
		right++;
	}
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int t = Integer.parseInt(br.readLine());
		for(int T=1; T<=t; T++) {
			input();
			solve();
			
			bw.write("#" + T + " " + (left-1));
			bw.newLine();
		}
		
		bw.flush();
		br.close();
	}
	
	
	static long left, right;
	public static void solve() {
	    left = 1;

	    while (left < right) {
	        long mid = (left + right) / 2;  //한 봉지당 사탕 개수
	        long need = 0;
	        for (long x : num) {
	            need += x / mid; //need =봉지 개수
	        }
	        
	        if (need >= M) {
	            left = mid + 1;
	        } else {
	            right = mid;
	        }
	    }
	}
}