import java.util.*;
import java.io.*;

public class Main{
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int N,Q;
	static Map<Integer,ArrayList<Integer>> map;
	static int[][] usado;
	
	public static void main(String[] args) throws IOException{
		input();
		output();
	}
	
	public static void input() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		map = new HashMap<>();
		usado = new int[N+1][N+1];
		
		for(int i = 1; i<= N; i++) {
			map.put(i, new ArrayList<>());
		}
		for(int i = 0 ; i < N-1; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			map.get(p).add(q);
			map.get(q).add(p);
			usado[p][q] = r;
			usado[q][p] = r;
		}
		
		for(int i = 0 ; i < Q; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			bw.write("" + solve(k,v));
			bw.newLine();
		}
	}
	
	
	public static int solve(int k , int v) throws IOException{
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		queue.add(v);
		visited[v] = true;
		int count = 0;
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			for(int neighbor : map.get(node)) {
				if(visited[neighbor]) continue;
				

				if(usado[neighbor][node] >= k ) {
					queue.add(neighbor);
					visited[neighbor] = true;
					count++;
				}
			}
		}
		return count;
	}
	
	public static void output() throws IOException{
		bw.flush();
		bw.close();
	}
	
}