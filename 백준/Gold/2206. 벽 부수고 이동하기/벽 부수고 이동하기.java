import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	static int N,M;
	static int[][] map;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		input();
		bw.write(solve(0,0) + "");
		bw.flush();
		bw.close();
	}
	
	public static int solve(int r, int c) {
		//bfs
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][M][2];
		visited[r][c][0] = true;
		queue.add(new int[] {r,c,1,0}); //r,c,거리 , 벽 부숨 여부
		
		while(!queue.isEmpty()) {
			int[] node = queue.poll();
			int cR = node[0];
			int cC = node[1];
			int cCount = node[2];
			int cIsAvail = node[3];
			//도착시
			if(cR == N-1 && cC == M-1 ) {
				return cCount;
			}
			
			for(int d = 0 ; d < 4; d++) {
				int nr = cR + dr[d];
				int nc = cC + dc[d];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}
	            // 벽인 경우
	            if (map[nr][nc] == 1 && cIsAvail == 0 && !visited[nr][nc][1]) {
	                visited[nr][nc][1] = true;
	                queue.add(new int[]{nr, nc, cCount + 1, 1});
	            }
	            // 빈칸인 경우
	            else if (map[nr][nc] == 0 && !visited[nr][nc][cIsAvail]) {
	                visited[nr][nc][cIsAvail] = true;
	                queue.add(new int[]{nr, nc, cCount + 1, cIsAvail});
	            }
			}
		}
		
		
		return -1;
	}
	
	
	
	public static void input() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
		    String line = br.readLine().trim();
		    for (int j = 0; j < M; j++) {
		        map[i][j] = line.charAt(j) - '0';
		    }
		}
	}
}