import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {
	static int[][] map;
	static int[][] tempmap;
	static int answer;
	static int N;
	static int[] dx = {-1,1,0,0}; //왼 우 상 하
	static int[] dy = {0,0,-1,1}; //왼 우 상 하
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int Tc = Integer.parseInt(st.nextToken());
		for(int T = 1 ; T <= Tc; T++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			int maxVal=Integer.MIN_VALUE;

			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxVal = Math.max(maxVal, map[i][j]);
				}
			}
			
			
			//덩어리 찾기
			//i는 일 수 
			answer = 0;
			for(int day = 0; day <= maxVal; day++) {
				tempmap = new int[N][N];
				for(int i = 0 ; i <N; i++) {
					tempmap[i] = map[i].clone();
				}
				
				play(day , tempmap); //갉아먹기
				
				int count = 0; //덩어리 초기화
				
				for(int i = 0 ; i < N; i++) {
					for(int j = 0 ; j < N; j++) {
						if(tempmap[i][j] != -1) { //오염되지 않았거나 안갉아먹었으면
							count +=1; //덩어리 한개 추가
							find(j,i); //오염시키기
						}
					}
				}
				answer = Math.max(count, answer);
			}
			
			
			System.out.println("#" + T + " " + answer);
			
			
			
		}
		
	}
	
	//갉아 먹기
	public static void play(int power , int[][] arr) {
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < N; j++) {
				if(arr[i][j] <= power) {
					arr[i][j] = -1; //갉아먹은걸 -1로 표현
				}
			}
		}
	}
	
	
	//덩어리 오염시켜버리기
	public static void find(int x, int y) {	
		if(tempmap[y][x] == -1) { //오염되었으면 반환
			return;
		}
		
		tempmap[y][x] = -1; //오염시키기
		
		for(int d = 0 ; d <4; d++) { //방향 돌면서 -1로 오염시켜버리기
			int nx = x+dx[d];
			int ny = y+dy[d];
			
			
			if(nx <0 || ny < 0 || nx >= N || ny >= N) {
				continue;
			}
			
			find(nx,ny);
		}
		
	}
}
