import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	static int K;
	static String S;
	static TreeSet<String> substrings;

	public static void input() throws IOException {
		K = Integer.parseInt(br.readLine().trim());
		S = br.readLine().trim();
	}

	public static String solve() {
		substrings = new TreeSet<>();

		int len = S.length();
		for (int i = 0; i < len; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = i; j < len; j++) {
				sb.append(S.charAt(j));
				substrings.add(sb.toString());
			}
		}

		// TreeSet을 순회하며 K번째 원소 찾기
		int idx = 1;
		for (String sub : substrings) {
			if (idx == K) return sub;
			idx++;
		}
		return "none";
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) {
			input();
			String ans = solve();
			bw.write("#" + t + " " + ans);
			bw.newLine();
		}
		bw.flush();
	}
}
	