import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static BufferedWriter bw;
	
	static int[] pi;
	static int answer;
	static String word;
	static String all;
	public static void input() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		all = st.nextToken();
		st = new StringTokenizer(br.readLine().trim());
		word = st.nextToken();
		
		pi = new int[word.length()];
		answer = 0;
	}
	
	public static void makePi() {
		int j = 0;
		for(int i = 1 ; i < word.length(); i++) {
			while(j > 0 && word.charAt(i) != word.charAt(j)) {
				j = pi[j-1];
			}
            
            // 현재 문자가 일치하는 경우
            if(word.charAt(i) == word.charAt(j)){
                pi[i] = ++j; // 일치하는 길이를 저장하고 j 증가
            }
		}
	}
	

	public static void solve() {
		int j = 0;
		for(int i = 0; i< all.length(); i++) {
			while(j > 0 && all.charAt(i) != word.charAt(j) ) {
				j = pi[j-1];
			}
			
			if(all.charAt(i) == word.charAt(j)) {
				if(j== word.length()-1) {
					answer++;
					j = pi[j];
				} else {
					j++;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=tc; t++) {
			
			input();
			makePi();
			solve();
			bw.write("#" + t + " " + answer);
			bw.newLine();
		}
		bw.flush();
	}

}
