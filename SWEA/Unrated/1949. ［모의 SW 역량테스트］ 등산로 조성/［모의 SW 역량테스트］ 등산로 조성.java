import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int[] dx = {-1,1,0,0}; //좌 우 상 하
	static int[] dy = {0,0,-1,1}; //좌 우 상 하 
	static ArrayList<int[]> startList;
	static int N,K;
	static int answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int test_case = Integer.parseInt(st.nextToken());
		for(int tc=1; tc<=test_case; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			
			int maxHeight =0;
			for(int i = 0 ; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxHeight = Math.max(maxHeight, map[i][j]); //제일 큰 숫자 찾
				}
			}
			
			//제일 높은 봉우리들
			startList = new ArrayList<>();
			for(int i = 0 ; i < N; i++) {
				for(int j = 0 ; j < N; j++) {
					if(map[i][j] == maxHeight) {
						startList.add( new int[] {i,j} );
					}
				}
			}
			
			answer = Integer.MIN_VALUE;
			for(int i = 0 ; i < startList.size(); i++) {
				boolean[][] visited = new boolean[N][N];
				visited[startList.get(i)[0]][startList.get(i)[1]] = true;
				dfs(map, startList.get(i)[1],startList.get(i)[0], 1 ,visited , 0);
			}
			
			
			
			System.out.println("#" + tc + " " + answer);
		}
	}
	
	public static void dfs(int[][] map , int x,int y,int count,boolean[][] visited , int used) {
		for(int d = 0 ; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
			if(visited[ny][nx]) continue;
			
			if(map[ny][nx] < map[y][x]) {
				visited[ny][nx] = true;
				dfs(map,nx,ny,count+1, visited , used);
				visited[ny][nx] = false;
			} else {
				if(used==0) {
					for(int i = 1 ; i <= K; i++) {
						//깎고나서현재보다 낮아지면서 0보다 클
						if( (map[ny][nx] - i < map[y][x]) && (map[ny][nx] -i >=0)) {
							visited[ny][nx] = true;
							map[ny][nx] -= i;
							dfs(map, nx,ny,count+1, visited , used+1);
							map[ny][nx] += i;
							visited[ny][nx] = false;
						}
					}
				}
			}
		}
		
		
		answer = Math.max(answer, count);
		visited[y][x] = false;
	}

}
