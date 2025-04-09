import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int R,C;
	static char[][] map;
	static int answer;
	static int goalR,goalC;
	static int[][] count;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		count = new int[R][C];	
		
		for(int i = 0 ; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			for(int j = 0 ; j < C; j++) {
				map[i][j] = temp.charAt(j);
				if(map[i][j] == 'D') {
					goalR = i;
					goalC = j;
				}
			}
		}
		
		answer = 0;
		for(int i = 0 ; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == 'S') {
					bfs(i,j);
				}
			}
		}
		
		System.out.println(answer == 0 ? "KAKTUS" : answer);
	}
	
	
	public static void bfs(int startR, int startC) {
	    Queue<int[]> queue = new ArrayDeque<>();
	    queue.add(new int[]{startR, startC});
	    boolean[][] visited = new boolean[R][C];
	    visited[startR][startC] = true;

	    while (!queue.isEmpty()) {
	        waterMap();

	        Queue<int[]> nextQueue = new ArrayDeque<>();

	        while (!queue.isEmpty()) {
	            int[] node = queue.poll();
	            int r = node[0];
	            int c = node[1];

	            for (int d = 0; d < 4; d++) {
	                int nr = r + dr[d];
	                int nc = c + dc[d];

	                if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
	                if (map[nr][nc] == '*' || map[nr][nc] == 'X') continue;
	                if (!visited[nr][nc]) {
	                    visited[nr][nc] = true;
	                    count[nr][nc] = count[r][c] + 1;
	                    if (nr == goalR && nc == goalC) {
	                        answer = count[nr][nc];
	                        return;
	                    }
	                    nextQueue.add(new int[]{nr, nc});
	                }
	            }
	        }

	        queue = nextQueue;
	    }
	}

	
	
	public static void waterMap() {
		Queue<int[]> queue = new ArrayDeque<>();
		
		for(int i = 0 ; i < R; i++) {
			for(int j = 0 ; j <C;j++) {
				if(map[i][j] == '*') {
					queue.add(new int[] {i,j});
				}
			}
		}

		while(!queue.isEmpty()) {
			int[] node = queue.poll();
			int r = node[0];
			int c = node[1];
			for(int d = 0; d< 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr < 0 || nc < 0 || nr >= R || nc >= C) continue; //맵밖으로 넘어가면
				if(map[nr][nc] == '*' || map[nr][nc] == 'X' || map[nr][nc] == 'D') continue; //물이거나 벽이면
				
				map[nr][nc] = '*';
			}
		}
		
		
		
		
	}
	
	
}