import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	static int K;
	static String S;
	public static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		S = st.nextToken();
	}
	
	public static void solve() {
		
		answer = new String[S.length()];
		for(int i = 0 ; i < S.length(); i++) {
			answer[i] = S.substring(i);
		}
		
		Arrays.sort(answer);
	}
	
	static String[] answer;
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine().trim());
		for(int t=1; t<=T; t++) {
			input();
			
			solve();
			
			bw.write("#" + t + " " + answer[K-1]);
			bw.newLine();
		}
		bw.flush();
	}

}
