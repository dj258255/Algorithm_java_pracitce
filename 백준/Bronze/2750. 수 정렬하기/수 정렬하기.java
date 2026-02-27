import java.util.*;
import java.io.*;

public class Main{
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
		input();
		output();
	}
	
	public static void input() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine().trim());
		int n = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			pq.add(Integer.parseInt(st.nextToken()));
		}
		
		while(!pq.isEmpty()) {
			bw.write("" + pq.poll());
			bw.newLine();
		}
		
	}
	
	public static void output() throws IOException{
		bw.flush();
		bw.close();
	}
}