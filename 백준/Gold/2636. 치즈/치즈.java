import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	public static int[][] map;
	public static int[] dx = {-1,1,0,0}; //왼 오 상 하
	static int[] dy = {0,0,-1,1}; // 왼 오 상 하
	public static boolean[][] visited;
	static int N,M;
	static int cheese;
	static int time;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j <M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//1 = 치즈
		//0 = 빈칸
		
		
		
		
		
		while(test()) {
			visited = new boolean[N][M]; //계속 초기화
			
			for(int i = 0 ; i < N; i++) {
				for(int j = 0 ; j <M; j++) {
					visited[i][j] = false; //미방문 처리
				}
			}
			cheese = 0; //치즈 초기화
			dfs(0,0);
			time++;
		}
		
		System.out.println(time);
		System.out.println(cheese);
		
	}
	
	
	
	public static void dfs(int x, int y) {
		
		visited[y][x] = true; //방문처리 갈겨주고
		if(map[y][x] != 0) {
			map[y][x] = 0; //0처리
			cheese +=1; //치즈조각 추가
			return;
		}
		
		for(int d = 0 ; d < 4 ; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0 || ny < 0 || ny >= N || nx >= M) continue;
			
			if(visited[ny][nx]) continue;
			
			dfs(nx,ny);
		}
		
		
		
	}
	
	public static boolean test() {
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < M; j++) {
				if(map[i][j] != 0) {
					return true; //남아있으면 반복
				}
			}
		}
		
		return false;
	}
}