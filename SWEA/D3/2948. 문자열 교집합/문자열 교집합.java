import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution{
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N,M;
	static HashSet<String> setA;
	static ArrayList<String> setB;
	public static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		setA = new HashSet<>();
		setB = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++) {
			setA.add(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M; i++) {
			setB.add(st.nextToken());
		}
		
	}
	static int answer;
	public static void solve() {
		answer = 0;
		for(int i = 0; i < setB.size(); i++) {
			if(setA.contains(setB.get(i))) {
				answer++;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine().trim());
		sb = new StringBuilder();
		for(int t = 1; t<=tc; t++) {
			//입력
			input();
			
			solve();
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}