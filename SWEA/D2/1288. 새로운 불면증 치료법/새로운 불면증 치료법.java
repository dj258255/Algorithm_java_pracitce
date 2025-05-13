import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution{
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			input();
			
			solve();
			
			bw.write("#"+t+" "+tempN);
			bw.newLine();
		}
		
		bw.flush();
		br.close();
	}
	
	
	static int tempN;
	public static void solve() {
		HashSet<Integer> set = new HashSet<>();
		int  index = 2;
		tempN = N;
		while(true) {
			String temp = Integer.toString(tempN);
			for(int i = 0 ; i < temp.length(); i++) {
				int tempInteger = Character.getNumericValue(temp.charAt(i));
				if(!set.contains(tempInteger)) {
					set.add(tempInteger);
				}
			}

			if(set.size() == 10) return;
			
			tempN = N*index;
			index++;
		}
		
		
		
	}
	
	static int N;
	public static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
	}
}