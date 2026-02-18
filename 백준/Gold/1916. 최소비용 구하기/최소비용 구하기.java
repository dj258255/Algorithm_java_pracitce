import java.util.*;
import java.io.*;

public class Main{
	
	static int N;
	static int M;
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static Map<Integer,ArrayList<int[]>> map;
	static PriorityQueue<int[]> pq;
	static int startNum,goalNum;
	
	public static void main(String[] args) throws IOException{
		input();
		solve();
		output();
	}
	
	public static void input() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		map = new HashMap<>();
		
		pq = new PriorityQueue<>(
				(a,b) -> a[1]-b[1]
				);
		for(int i = 0 ; i < M; i++) {
		    st = new StringTokenizer(br.readLine().trim());
		    int a = Integer.parseInt(st.nextToken());
		    int b = Integer.parseInt(st.nextToken());
		    int c = Integer.parseInt(st.nextToken());
		    map.computeIfAbsent(a, k -> new ArrayList<>()).add(new int[]{b, c});
		}
		
		//구간 출발점 도시번호 도착점 도시번호
		st = new StringTokenizer(br.readLine().trim());
		startNum = Integer.parseInt(st.nextToken());
		goalNum = Integer.parseInt(st.nextToken());
	}
	
	public static void solve() throws IOException{
		pq.add(new int[] {startNum,0});
		boolean[] visited = new boolean[N+1];
		
		while(!pq.isEmpty()) {
			int[] node = pq.poll();
			int cur = node[0];
			int money = node[1];
			
	        if (cur == goalNum) {
	            bw.write("" + money);
	            return;
	        }
	        if (visited[cur]) continue;
	        visited[cur] = true;
	        if (!map.containsKey(cur)) continue;
	        
			for(int[] neighbor : map.get(cur)) {
				int nextCity = neighbor[0];
				int nextMoney = neighbor[1] + money;
				
	            if (!visited[nextCity]) {
	                pq.add(new int[]{nextCity, nextMoney});
	            }
			}
			
		}
	}
	
	public static void output() throws IOException{
		bw.flush();
		bw.close();
	}
}