import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution{
	static BufferedReader br;
	static StringTokenizer st;
	static BufferedWriter bw;
	
	static int N;
	static int M;
	public static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	}
	
	static String answer;
	public static void solve() {
        // 하위 N비트를 모두 1로 세팅한 마스크 생성
        int mask = (1 << N) - 1;  
        // M 과 mask 를 AND 했을 때 mask 와 같으면 "ON", 아니면 "OFF"
        answer = ((M & mask) == mask) ? "ON" : "OFF";
		
	}
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int t= 1; t<= T; t++) {
			input();
			
			solve();
			
			bw.write("#" + t + " " + answer);
			bw.newLine();
		}
		bw.flush();
	}
}