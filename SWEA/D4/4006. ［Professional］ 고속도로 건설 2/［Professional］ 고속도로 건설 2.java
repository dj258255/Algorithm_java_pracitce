import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {

	static BufferedWriter bw;
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N,M;
	static ArrayList<Edge> edges;
	static int[] parent;
	
	public static class Edge{
		int start;
		int end;
		int cost;
		public Edge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}
	
	public static void input() throws IOException{
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		
		//초기화
		parent = new int[N+1];
		edges = new ArrayList<>();
				
		for(int i=1; i<= N; i++) {
			parent[i] = i;
		}
		
		
		for(int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			//s와 e는 도로 후보가 잇는 각 도시의 번호 
			int c = Integer.parseInt(st.nextToken());
			//c는 그 도로를 건설하는데 드는 비용
			
			edges.add(new Edge(s,e,c));
		}
	}
	
	//유니온파인드 find
	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	//유니온
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB) return false;
		
		parent[rootB] = rootA;
		return true;
	}
	
	public static void mst() throws IOException{
		Collections.sort(edges,
				(a,b) -> a.cost - b.cost
				);
		
		long sumCost = 0;
		int edgeCount = 0;
		
		for(Edge edge : edges) {
			if(union(edge.start, edge.end)) {
				sumCost += edge.cost;
				edgeCount++;
				
				//N-1
				if(edgeCount == N-1) break;
			}
		}
		
		bw.write(String.valueOf(sumCost));
	}
	
	
	
	
	public static void main(String[] args)  throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			bw.write("#" + t + " ");
			input();
			mst();
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
