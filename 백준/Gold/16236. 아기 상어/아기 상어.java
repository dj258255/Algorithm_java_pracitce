import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int[][] map;
	static BabyShark babyshark;
	static PriorityQueue<int[]> eatFish;
	static int N;
	public static class BabyShark{
		int r;
		int c;
		int size;
		int eatFish;
		public BabyShark(int r, int c, int size , int eatFish) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.eatFish = eatFish;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());

	    N = Integer.parseInt(st.nextToken());
	    map = new int[N][N];

	    eatFish = new PriorityQueue<>((o1,o2) -> {
	        if(o1[2] != o2[2]) return Integer.compare(o1[2], o2[2]);
	        if(o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
	        return Integer.compare(o1[1], o2[1]);
	    });

	    for(int i = 0 ; i < N; i++) {
	        st = new StringTokenizer(br.readLine());
	        for(int j = 0 ; j < N; j++) {
	            map[i][j] = Integer.parseInt(st.nextToken());
	            if(map[i][j] == 9) {
	                babyshark = new BabyShark(i,j,2,0);
	                map[i][j] = 0;
	            }
	        }
	    }

	    int totalTime = 0;
	    while(true){
	        int time = bfs();
	        if(time == 0) break;  //먹을 물고기가 더이상 없다면 종료
	        totalTime += time;    //이동 시간 누적
	    }

	    System.out.println(totalTime);
	}

	
	public static int bfs() {
		
		//크기가 같은건 못먹음
		//아기상어 기준 윗칸 왼쪽칸 먹음
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> queue = new ArrayDeque<>();
		
		queue.add(new int[] {babyshark.r, babyshark.c,0});
		visited[babyshark.r][babyshark.c] = true;
		
		eatFish.clear();
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int dist = cur[2];
			
			if(babyshark.size > map[r][c] && map[r][c] != 0) { //물고기보다 아기상어 가 크거나 같을 
				eatFish.add(new int[] {r,c,dist});
				continue;
			}
			
			for(int d = 0 ; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue; //맵밖 벗어나면 컨티뉴
				
				if(!visited[nr][nc] && map[nr][nc] <= babyshark.size) { //아기상어가 지나갈 수 있을 때
					visited[nr][nc] = true;
					queue.add(new int[] {nr,nc,dist+1});					
				}
			}
		}
		
		//먹을 수 있는 물고기 중 우선순위 가장 높은 
		if(!eatFish.isEmpty()) {
			int[] fish = eatFish.poll();
			int fishR = fish[0];
			int fishC = fish[1];
			int fishDist = fish[2];
			
			//아기상어 업데이트
			map[babyshark.r][babyshark.c] = 0;
			babyshark.r = fishR;
			babyshark.c = fishC;
			babyshark.eatFish++;
			map[fishR][fishC] = 0;
			

			if(babyshark.eatFish == babyshark.size) { //진화조건 만족하
				babyshark.eatFish =0;
				babyshark.size +=1;
			}
			
			return fishDist;

		}
		return 0;
	}
}