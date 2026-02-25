import java.util.*;
import java.io.*;

public class Main{
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int N,M,X;
	static Map<Integer,ArrayList<int[]>> map;
	static Map<Integer,ArrayList<int[]>> reverseMap;
	public static void main(String[] args) throws IOException{
		input();
		solve();
		output();
	}
	
	public static void input() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken()); //N명의 학생 
		M = Integer.parseInt(st.nextToken()); //단방향 도로 
		X =  Integer.parseInt(st.nextToken()); //X번 마을에서 파티
		
		map = new HashMap<>();
		reverseMap = new HashMap<>();

		for(int i = 0 ; i < M; i++) { //i번째 길을 지나는데 T의 시간을 소비
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());//도로의 시작점 
			int end = Integer.parseInt(st.nextToken()); //도로의 끝점
			int num = Integer.parseInt(st.nextToken());//소요시간 
			map.computeIfAbsent(start, k -> new ArrayList<>()).add(new int[] {end,num});
			reverseMap.computeIfAbsent(end, k-> new ArrayList<>()).add(new int[] {start,num});
		}
		
		toX = new int[N+1];
		fromX = new int[N+1];
		for(int i = 1; i<=N; i++) {
			fromX[i] = Integer.MAX_VALUE;
			toX[i] = Integer.MAX_VALUE;
		}
		answer = Integer.MAX_VALUE;
	}
	
	static PriorityQueue<int[]> pq;
	static int[] toX,fromX;
	static int answer;
	public static void solve() throws IOException {
	    fromX = dijkstra(map, X);        // X → 각 노드 (귀가)
	    toX = dijkstra(reverseMap, X);   // 각 노드 → X (역방향으로 뒤집어서)

	    int answer = 0;
	    for (int i = 1; i <= N; i++) {
	        answer = Math.max(answer, toX[i] + fromX[i]);
	    }
	    bw.write(answer + "");
	}

	public static int[] dijkstra(Map<Integer, ArrayList<int[]>> graph, int start) {
	    int[] dist = new int[N + 1];
	    Arrays.fill(dist, Integer.MAX_VALUE);
	    boolean[] visited = new boolean[N + 1];
	    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

	    dist[start] = 0;
	    pq.add(new int[]{start, 0});

	    while (!pq.isEmpty()) {
	        int[] node = pq.poll();
	        int cur = node[0];
	        int cost = node[1];

	        if (visited[cur]) continue;
	        visited[cur] = true;

	        for (int[] neighbor : graph.getOrDefault(cur, new ArrayList<>())) {
	            int next = neighbor[0];
	            int weight = neighbor[1];
	            if (cost + weight < dist[next]) {
	                dist[next] = cost + weight;
	                pq.add(new int[]{next, dist[next]});
	            }
	        }
	    }
	    return dist;
	}
	
	
	public static void output() throws IOException{
		bw.flush();
		bw.close();
	}
}