import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Solution{
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int N,M,K;
	
	static class Cell{
		
		public Cell(int r, int c, int health, int time, int state) {
			super();
			this.r = r;
			this.c = c;
			this.health = health;
			this.time = time;
			this.state = state;
		}
		
		int r;
		int c;
		int health; //각 세포가 가진생명력
		int time;
		int state; // 상태
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1 ; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //세로크기
			M = Integer.parseInt(st.nextToken()); // 가로크기
			K = Integer.parseInt(st.nextToken()); //K시간 후 결과
			
			ArrayList<Cell> cells = new ArrayList<>();
			int[][] map = new int[N][M];
			for(int i = 0 ; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > 0) {
						cells.add(new Cell(i, j, map[i][j], map[i][j], 2) );
					}
				}
			}
			
			
			int answer = bfs(cells);
			
			System.out.println("#" + tc + " " + answer);
			
			
		}
	}
	
	public static int bfs(ArrayList<Cell> cells ) {
        HashSet<String> used = new HashSet<>();
        for (Cell cell : cells) {
            used.add(cell.r + "_" + cell.c);
        }
        
        
		//우선순위 큐
		PriorityQueue<Cell> queue = new PriorityQueue<>((o1, o2) -> o2.health - o1.health); //큐
		
		int time = 0;
		while(time++ < K) {
			for(int i = 0 ; i < cells.size(); i++) {
				if(cells.get(i).state != 0) { //죽어있지 않다면
					queue.add(cells.get(i));
				}
			}

			while(!queue.isEmpty()) { // 살아있는것들 모두 돌려버려이이이이이이이이이이이이ㅣ이이이이이이이이이ㅣ이이이이이이이
				Cell cell = queue.poll(); //자 셀을 꺼내보자잉

				//현재 셀 상태변화
				if(cell.state == 1) { //확장되어야 하는 상태면
					for(int d = 0 ; d< 4; d++) {
						int nr = cell.r + dr[d];
						int nc = cell.c + dc[d];

						
                        if (possible(nr, nc, used)) { //이미 있는지 그 좌표에
                            continue;
                        }
                        used.add(nr + "_" + nc);
                        
						cells.add(new Cell(nr, nc, cell.health, cell.health, 2)); //모든 셀이 다 들어있는 ArrayList에 추가
					}
				} 
				
				cell.time -=1; //1초 깎음

				if(cell.time == 0) { //시간이 0이되면 , 어차피 state 0인건 다시 안넣을거기 때문
					cell.state -=1;
					cell.time = cell.health; // 다시 회복
				}

				
			}
			
			
		}

		
		int count =0;
		for(int i = 0 ; i < cells.size(); i++) {
			if(cells.get(i).state != 0) {
				count++;
			}
		}
		
		return count;
	}
    public static boolean possible(int r, int c, HashSet<String> used) {
        return used.contains(r + "_" + c);
    }
    
	

}