import java.util.*;
import java.io.*;

public class Main{
	static int N,Q;
	static Map<Integer,ArrayList<Integer>> usado;
	static BufferedReader br;
	static StringTokenizer st;
	static BufferedWriter bw;
	static int[][] usadoV;
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
		usadoV = new int[N+1][N+1];
		usado = new HashMap<>();
		
		for(int i = 1; i<= N; i++) {
			usado.put(i, new ArrayList<>());
			
		}
		for(int i = 0 ; i < N-1 ; i ++) {
			st = new StringTokenizer(br.readLine().trim());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			usado.get(p).add(q);
			usado.get(q).add(p);
			usadoV[p][q] = r;
			usadoV[q][p] = r;
		}
		
		for(int i = 0 ; i < Q; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			//유사도가 K라면 동영상 v를 보고 있는 소들에게 몇개의 동영상이 추천될까?
			bw.write("" + solve(k,v));
			bw.newLine();
		}
	}
	
	
	public static int solve(int k, int v) throws IOException{
		boolean[] visited = new boolean[N+1];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(v);
		int count = 0;
		visited[v] = true;
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			for(int neighbor : usado.get(node)) {
				if(visited[neighbor]) continue;
				
				if(usadoV[node][neighbor] >= k) {
					count++;
					visited[neighbor] = true;
					queue.add(neighbor);
				}
				
				
			}
		}
		
		return count;
		
	}
	
	public static void output() throws IOException{
		bw.flush();
	}
}