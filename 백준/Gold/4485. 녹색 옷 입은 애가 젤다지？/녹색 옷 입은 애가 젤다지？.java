import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main{
	static int answer;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int N;
	static int[][] moneyMap;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = 0;
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N==0) break;
			
			T++;
			int[][] map = new int[N][N];
			moneyMap = new int[N][N];
			
			for(int i = 0 ; i < N; i++) {
				Arrays.fill(moneyMap[i], Integer.MAX_VALUE);
			}


			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs(map);
			
			System.out.println("Problem " + T + ": " + moneyMap[N-1][N-1]);
		}
	}
	
	
	public static void bfs(int[][] map) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]); //r,c,지금까지 뺏긴 루피
		pq.add(new int[] {0,0,map[0][0]});
		
		boolean[][] visited = new boolean[N][N];
		visited[0][0] = true;
		moneyMap[0][0] = map[0][0]; //moneyMap == 그 지역 지금까지 잃은 돈의 합
		
		while(!pq.isEmpty()) {
			int[] node = pq.poll();
			int r = node[0];
			int c = node[1];
			int money = node[2];
			
			for(int d = 0 ; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue; //맵밖 벗어나면 컨티뉴
				int nmoney = money + map[nr][nc];
				
				if(!visited[nr][nc]) { //방문한 적 없는지
					pq.add(new int[] {nr,nc,nmoney});
					visited[nr][nc] = true;
					moneyMap[nr][nc] = Math.min(moneyMap[nr][nc], nmoney);
				}
			}
			
		}
		
	}
}