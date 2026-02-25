import java.util.*;
import java.io.*;

public class Main{
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int N;
	static int[] dp;

	public static void main(String[] args) throws IOException{
		input();
		output();
	}

	public static void input() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine().trim());
		dp = new int[N];
		List<Integer> emptySlots = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			dp[i] = Integer.parseInt(st.nextToken());
			emptySlots.add(i);
		}

		int[] answer = new int[N];
		for (int i = 0; i < N; i++) {
			int pos = emptySlots.remove(dp[i]);
			answer[pos] = i + 1;
		}

		for(int i = 0; i < N; i++) {
			bw.write(answer[i] + " ");
		}
	}

	public static void output() throws IOException{
		bw.flush();
		bw.close();
	}
}