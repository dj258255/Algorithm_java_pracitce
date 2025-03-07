import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main{
	static char[][] grid;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1}; //상하좌우
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		grid = new char[N][N];
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			for(int j = 0 ; j < N; j++) {
				grid[i][j] = temp.charAt(j); 
			}
		}
		
		//적록색약 아닌사람
		visited = new boolean[N][N]; //방문초기화
		int count = 0; //구역 카운트
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < N; j++) {
				if(!visited[i][j]) { //현재 미방문한 지역이면
					dfs(i, j); //dfs 돌림
					count++;
				}
			}
		}
		
		int answer1 = count;
		
		//적록색약인 사람
		
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < N; j++) {
				if(grid[i][j] =='R' ) {
					grid[i][j] = 'G'; 
				}
			}
		}
		
		
		
		
		
		
		visited = new boolean[N][N]; //방문초기화
		count = 0; //구역 카운트
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < N; j++) {
				if(!visited[i][j]) { //현재 미방문한 지역이면
					dfs(i, j); //dfs 돌림
					count++;
				}
			}
		}
		int answer2 = count;
		
		System.out.print(answer1 + " " + answer2);
		
	}
	
	public static void dfs(int r, int c) {
		if(possible()) { //미방문한 지역이 없으면
			return;
		}
		
		visited[r][c] = true; //방문처리 
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			//범위 밖을 벗어나면 컨티뉴
			if(nr >= N || nc >= N || nr < 0 || nc < 0) {
				continue;
			}
			
			if(visited[nr][nc]) {
				continue;
			}
			
			if(grid[r][c] != grid[nr][nc]) {
				continue;
			}
			
			dfs(nr, nc);
		}
	}
	
	
	public static boolean possible() {
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(visited[i][j] == false) { //미방문한 지역이 있으면
					return false; //폴스
				}
			}
		}
		return true;
		
	}
	
	
}