import java.util.*;
import java.io.*;

public class Main{
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;
	static int N,K;
	static int[][] gameBoard;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		input();
		solve();
		output();
	}
	
	public static void input() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		gameBoard = new int[N][10];
		
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			for(int j = 0 ; j < 10; j++) {
				gameBoard[i][j] = Integer.parseInt(temp.substring(j,j+1));
			}
		}
		
	}
	
	public static void solve() throws IOException{
		//1. K이상 뭉쳐있는게 있는지 확인한다.  (bfs 이용하는 것 같음)
		//1.1. 만약에 뭉쳐있다면 삭제한다. (뭉쳐있다면 -> true)
		//1.2. 뭉쳐있지 않다면 프로세스 종료 (안뭉치 -> false)
		while(check()) {
			//2. 중력 작동
			gravity();
		} //3. 1~2번 과정을 무한 반복한다.
	}
	
	public static boolean check() throws IOException{
	    Queue<int[]> queue = new ArrayDeque<>();
	    Queue<int[]> deleteQueue = new ArrayDeque<>();
	    
	    boolean[][] visited = new boolean[N][10];
	    boolean isTry = false; //뭉쳐있는 부분이 없다.
	    
	    for(int i = 0 ; i < N ; i++) {
	        for(int j = 0 ; j < 10; j++) {
	            //[방문을 안했다 && 0이 아니다] 라면 bfs를 돌려서 K개 이상 뭉쳐있는지 확인, 뭉쳐 있는 부분이 있다면 제거.
	            if(!visited[i][j] && gameBoard[i][j] != 0) {
	                //큐로 스타트 끊기
	                int color = gameBoard[i][j]; //색깔
	                queue.add(new int[] {i,j}); //r,c
	                deleteQueue.add(new int[] {i,j});
	                
	                visited[i][j] = true;
	                int count = 1; //임시 카운트 (시작점 포함)
	                
	                while(!queue.isEmpty()) {
	                    int[] node = queue.poll();
	                    int r = node[0];
	                    int c = node[1];
	                    
	                    for(int d = 0 ; d < 4; d++) {
	                        int nr = r + dr[d];
	                        int nc = c + dc[d];
	                        
	                        if(nr >= N || nc >= 10 || nr < 0 || nc < 0) continue; //게임보드 밖으로 벗어나면
	                        if(visited[nr][nc]) continue; //이미 방문했으면
	                        if(gameBoard[nr][nc] != color) continue; //같은 색상이 아니면
	                        
	                        visited[nr][nc] = true;
	                        queue.add(new int[] {nr, nc}); //다음칸 탐색
	                        deleteQueue.add(new int[] {nr, nc});
	                        count++;
	                    }
	                }
	                
	                //만약 count가 K개 이상이면 방금 둘러본거 다 삭제, isTry는 true로
	                if(count >= K) {
	                    isTry = true;
	                    while(!deleteQueue.isEmpty()) {
	                        int[] node = deleteQueue.poll();
	                        int r = node[0];
	                        int c = node[1];
	                        gameBoard[r][c] = 0;
	                    }
	                }
	                
	                //K개 이하면 청소.
	                deleteQueue.clear();
	            }
	        }
	    }
	    //K이상 뭉쳐 있으면 true 아니면 false
	    return isTry;
	}
	
	public static void gravity() throws IOException{
	    for (int j = 0; j < 10; j++) {
	        int emptyRow = N - 1; //맨 아래칸을 빈칸이라고 가정
	        for (int i = N - 1; i >= 0; i--) {
	            if (gameBoard[i][j] != 0) { // 현재 칸이 블럭이 있다면
	                gameBoard[emptyRow][j] = gameBoard[i][j]; //빈 칸에 채우고
	                if (emptyRow != i) { //같은 칸이 아니라면 원래 자리를 0으로 설정 <- 스왑
	                    gameBoard[i][j] = 0;
	                }
	                emptyRow--; //한 칸 채웠으니 빈칸을 하나 위로 이동
	            }
	        }
	    }
	}

	public static void output() throws IOException{
	    for(int i = 0; i < N; i++) {
	        for(int j = 0; j < 10; j++) {
	            sb.append(gameBoard[i][j]);
	        }
	        sb.append("\n");
	    }
	    bw.write(sb.toString());
	    bw.flush();
	    bw.close();
	    br.close();
	}
}