import java.util.*;
import java.io.*;
public class Main{
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		input();
		solve();
		output();
	}
	static int N,M,answerSum;
	static int[][] map;
	public static void input() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		answerSum = Integer.MIN_VALUE;
		
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j = 0 ; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static void solve() throws IOException{
		boolean[][] visited = new boolean[N][M];
		for(int i =0  ; i < N; i++) {
			for(int j = 0 ; j < M; j++) {
				visited[i][j] = true;
				simul(i,j,1,map[i][j],visited);
				visited[i][j] = false;
				
				checkT(i, j);
			}
		}
	}
	
	public static void simul(int r, int c, int count, int sum, boolean[][] visited) throws IOException {
	    if(count == 4) {
	        answerSum = Math.max(answerSum, sum);
	        return;
	    }
	    
	    for(int d = 0; d < 4; d++) {
	        int nr = r + dr[d];
	        int nc = c + dc[d];
	        if(!possible(nr, nc) || visited[nr][nc]) continue;
	        
	        visited[nr][nc] = true;  // 방문 전에 표시
	        simul(nr, nc, count + 1, sum + map[nr][nc], visited);
	        visited[nr][nc] = false; // 복구
	    }
	}
	
	public static void checkT(int r, int c) {
	    int center = map[r][c];
	    int wingSum = 0;
	    int minWing = Integer.MAX_VALUE;
	    int cnt = 0;

	    for(int d = 0; d < 4; d++) {
	        int nr = r + dr[d];
	        int nc = c + dc[d];

	        if(!possible(nr, nc)) continue;

	        cnt++;
	        wingSum += map[nr][nc];
	        minWing = Math.min(minWing, map[nr][nc]);
	    }

	    // 날개가 3개 이상 있어야 ㅗ 가능
	    if(cnt >= 3) {
	        // 4방향 다 있으면 가장 작은 날개 하나 빼기
	        if(cnt == 4) wingSum -= minWing;
	        answerSum = Math.max(answerSum, center + wingSum);
	    }
	}

	
	
	public static void output() throws IOException{
		bw.write("" + answerSum);
		bw.flush();
		bw.close();
	}

	
	public static boolean possible(int r, int c) {
		if(r >= N || c >= M || r < 0 || c < 0) return false;
		return true;
	}
	
}