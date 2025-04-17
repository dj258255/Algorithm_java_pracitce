import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution{
	//세로위치 가로위치 미생물 수 이동방향
	static int N,M,K;
	static int[] dr = {-1,1,0,0}; //상 하 좌 우
	static int[] dc = {0,0,-1,1};
	
	public static class Group{
		int r;
		int c;
		int count;
		int dir;
		
		public Group(int r, int c , int count, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //셀 크기
			M = Integer.parseInt(st.nextToken()); //M시간 동안 
			K = Integer.parseInt(st.nextToken()); //곰팡이 개수

			
			Queue<Group> groups = new PriorityQueue<>( (g1,g2) -> g2.count-g1.count );
			
			for(int i = 0 ; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r= Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;//보정
				groups.add(new Group(r,c,count,dir)); 
			}
			
			
			int time = M;
			
			Group[][] board = new Group[N][N];
			
			while(time-->0) { //시간
				
				while(!groups.isEmpty()) { //모든 곰팡이 다 돌기 , count 높은순에서 낮은 순으로
					Group temp = groups.poll();
					int dir = temp.dir; //방향
					int nr = temp.r + dr[dir]; //다음 칸
					int nc = temp.c + dc[dir]; //다음 칸
					int count = temp.count; //곰팡이 개수

					
					
					if(nr == N || nc == N || nr < 0 || nc < 0) continue; //맵 밖 벗어났을 때
					if(nr == N-1 || nc == N-1 || nr == 0 || nc == 0) { //빨간색에 닿았을 때
						count /= 2; //소수점은 버림.
						
						switch(dir) { //방향 반대로
						case 0: dir = 1;
						break;
						case 1: dir = 0;
						break;
						case 2: dir = 3;
						break;
						case 3: dir = 2;
						break;
						}
					}

				    // board 배열에 합치기
				    if (board[nr][nc] == null) {
				        //비어 있으면 새로 놓기
				        board[nr][nc] = new Group(nr, nc, count, dir);
				    } else {
				        //이미 있으면 개수 합치기
				        Group exist = board[nr][nc];
				        exist.count += count;
				        //방향은 더 큰 개수를 가진 그룹의 방향을 유지
				        if (count > exist.count - count) {
				            exist.dir = dir;
				        }
				    }
				} //전부 다 이동 끝


				//합친 결과를 다시 PQ에 담기
				for (int i = 0; i < N; i++) {
				    for (int j = 0; j < N; j++) {
				        if (board[i][j] != null) {
				            groups.add(board[i][j]);
				            board[i][j] = null;  //다음 턴을 위해 초기화
				        }
				    }
				}
			}
			
			
			
			int answer = 0;
			while (!groups.isEmpty()) {
			    answer += groups.poll().count;
			}

			System.out.println("#" + tc + " " + answer);
		}
	}
}