import java.util.*;
import java.io.*;

public class Main{
	static BufferedReader br;
	static StringTokenizer st;
	static BufferedWriter bw;
	
	static int V; //약속 장소후보의 수 V
	static int M; // 약속 장소 연결하는 길이 수 M
	static Map<Integer,ArrayList<int[]>> map;
	static int J; //지헌이 위치 
	static int S; //성하 위치 
	static int[] jdist;
	static int[] sdist;
	public static void input() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine().trim());
		V = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new HashMap<>();
		for(int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken()); //길의 시작
			int b = Integer.parseInt(st.nextToken()); // 길의 끝
			int c = Integer.parseInt(st.nextToken()); //걸리는 시간
			map.computeIfAbsent(a, k-> new ArrayList<>()).add(new int[] {b,c});
			map.computeIfAbsent(b,k-> new ArrayList<>()).add(new int[] {a,c});
		}
		st = new StringTokenizer(br.readLine().trim());
		J = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		jdist = new int[V+1];
		sdist = new int[V+1];
	}
	public static void main(String[] args) throws IOException{
		input();
		solve();
		output();
	}
	
	

	public static void solve() throws IOException{
		// 1.지헌이와 성하의 출발위치는 약속장소 X
		// 2. 둘다 걸리는 시간 '합' 최단시간 
		// 3. 1,2둘다 만족하면 J가 S보다 먼저 도착해야한다
		// 4. 1~3 만족하면 J로부터 가장 가까운  곳. 그 장소도 여러개면 번호가 가장 작은 장소 
		
		PriorityQueue<int[]> jpq = new PriorityQueue<>(
				(a,b) -> a[1]-b[1]
				);
		
		jpq.add(new int[] {J,0});
		
		PriorityQueue<int[]> spq = new PriorityQueue<>(
				(a,b) -> a[1]-b[1]
				);
		

		
		boolean[] visited = new boolean[V+1];
		Arrays.fill(jdist,Integer.MAX_VALUE);
		jdist[J] = 0;
		while(!jpq.isEmpty()) {
			int[] node = jpq.poll();
			int pos = node[0];
			int min = node[1];
			
			if(jdist[pos] < min || visited[pos]) continue;
			jdist[pos] = min;
			visited[pos] = true;
			
			for(int[] neighbor : map.getOrDefault(pos, new ArrayList<>())) {
				int nextPos = neighbor[0];
				int nextMin = min + neighbor[1];
				
				jpq.add(new int[] {nextPos,nextMin});
			}
		}
		
		Arrays.fill(sdist,Integer.MAX_VALUE);
		sdist[S] = 0;
		visited = new boolean[V+1];
		spq.add(new int[] {S,0});
		while(!spq.isEmpty()) {
			int[] node = spq.poll();
			int pos = node[0];
			int min = node[1];
			
			if(sdist[pos] < min || visited[pos]) continue;
			sdist[pos] = min;
			visited[pos] = true;
			
			for(int[] neighbor : map.getOrDefault(pos, new ArrayList<>())) {
				int nextPos = neighbor[0];
				int nextMin = min + neighbor[1];
				
				spq.add(new int[] {nextPos,nextMin});
			}
		}
		
		// 1.지헌이와 성하의 출발위치는 약속장소 X
		// 2. 둘다 걸리는 시간 '합' 최단시간 
		// 3. 1,2둘다 만족하면 J가 S보다 먼저 도착해야한다
		// 4. 1~3 만족시 번호가 가장 작은 장소 
		
		// 기존의 선택 로직을 조건 순서대로 적용하도록 수정

		// 1단계: 합의 최솟값 먼저 구하기 (조건 1,2만 적용)
		int minSum = Integer.MAX_VALUE;
		for(int i = 1; i <= V; i++) {
		    if(i == J || i == S) continue;
		    if(jdist[i] == Integer.MAX_VALUE || sdist[i] == Integer.MAX_VALUE) continue;
		    minSum = Math.min(minSum, jdist[i] + sdist[i]);
		}

		// 2단계: 합이 최소인 것들 중에서 조건 3, 4 적용
		int answerJdist = Integer.MAX_VALUE;
		int answerNode = -1;

		for(int i = 1; i <= V; i++) {
		    if(i == J || i == S) continue;
		    if(jdist[i] == Integer.MAX_VALUE || sdist[i] == Integer.MAX_VALUE) continue;
		    if(jdist[i] + sdist[i] != minSum) continue;  // 조건 2
		    if(jdist[i] > sdist[i]) continue;             // 조건 3

		    if(jdist[i] < answerJdist) {                   // 조건 4
		        answerJdist = jdist[i];
		        answerNode = i;
		    } else if(jdist[i] == answerJdist && i < answerNode) {
		        answerNode = i;
		    }
		}

		bw.write(answerNode + "\n");
		
	}

	
	public static void output() throws IOException{
		bw.flush();
		bw.close();
	}
}