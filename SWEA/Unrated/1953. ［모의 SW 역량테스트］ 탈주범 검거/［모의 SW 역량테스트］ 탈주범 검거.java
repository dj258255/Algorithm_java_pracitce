import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution{
	static int N,M,R,C,L;
	static ArrayList<int[]> list;
	static int[][] map;
	static int[][] move = {{-1,0},{1,0},{0,-1},{0,1}}; //상 하 좌 우
	static int count;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		int Tc = Integer.parseInt(st.nextToken());
		for(int T = 1; T<=Tc; T++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			for(int i = 0 ; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs();
			System.out.println("#" + T + " " + count);
		}
		
	}
	
	public static void bfs() {
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> queue = new ArrayDeque<>();
		//시작 위치를 방문 처리하면서 count에 포함 (시작 셀 포함)
		queue.add(new int[] {R, C, map[R][C], 0});
		visited[R][C] = true;
		count = 1;

		while(!queue.isEmpty()){
		    int[] node = queue.poll();
		    int r = node[0];
		    int c = node[1];
		    int type = node[2];
		    int level = node[3];
		    
		    if(level == L - 1) continue;
		    
		    for(int dir = 0; dir < 4; dir++) {
		        if(possibletype(type, dir)) continue; //현재 파이프가 해당 방향으로 이동 가능한지 체크
		        
		        // 올바른 방향 인덱스 사용
		        int nr = r + move[dir][0];
		        int nc = c + move[dir][1];


		        if(board(nr, nc)) continue; //맵 경계 체크 또는 파이프가 없으면 건너뜀
		        
		        int ntype = map[nr][nc];
		        if(possibleFind(ntype, dir)) continue; //파이프가 연결 가능한지 체크
		        
		        if(!visited[nr][nc]) {
		            visited[nr][nc] = true;
		            queue.add(new int[] {nr, nc, ntype, level + 1});
		            count++;
		        }
		    }
		}

		
		
	}
	
	//이동할 수 있는지 없는지
	public static boolean possibletype(int type, int d) {
		if(d==0) { //위쪽으로 이동가능?
			switch(type) {
			case 3:
			case 5:
			case 6:
				return true; //이넘들은 위로 못감
			}
		} else if(d==1) { //아래쪽으로 이동 가능?
			switch(type){
			case 3:
			case 4:
			case 7:
				return true; // 이넘들은 아래로 못감
			}
		} else if(d==2) {
			switch(type) {
			case 2:
			case 4:
			case 5:
				return true; //이넘들은 왼쪽으로 못감
			}
		} else if(d==3) {
			switch(type) {
			case 2:
			case 6:
			case 7:
				return true; //이넘들은 오른쪽으로 못감
			}
		}
	
		return false;
	}
	
	public static boolean board(int r,int c) {
		if(r < 0 || r >= N || c < 0 || c >= M) return true;
		if(map[r][c] == 0) return true; //파이프없으면
		
		return false;
	}

	
	public static boolean possibleFind(int type , int d) { 
		//방향 0: 상 , 1:하 , 2:좌, 3:우
		if(d==0) {
			switch(type) {
		    case 3:
		    case 4:
		    case 7:
		        return true;
			}
		} else if(d==1) {
			switch(type) {
			case 3:
			case 5:
			case 6:
				return true;
			}
		} else if(d==2) {
			switch(type){
			case 2:
			case 6:
			case 7:
				return true;
			}
		} else if(d==3) {
			switch(type) {
			case 2:
			case 4:
			case 5:
				return true;
			}
		}
		
		return false;
	}
	
}