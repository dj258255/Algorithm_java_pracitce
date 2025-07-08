import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static BufferedWriter bw;
	
	static int N,M;
	static int r,c,d;
	
	static int[][] map;
	
	//북동남서 -> 시계방향이라는 뜻
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	static Robot robot;
	
	public static class Robot{
		int r;
		int c;
		int d;
		
		public Robot(int r,int c,int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	
	public static void input() throws IOException{
		//입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine().trim());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		//북동남서
		d = Integer.parseInt(st.nextToken());
		
		//초기화 함수 실행
		initialize();

		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M; j++) {
				//0은 청소 X 1은 벽
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static void initialize() {
		map = new int[N][M];
		robot = new Robot(r,c,d);
		answer = 0;
	}
	
	
	//청소
	static int answer;
	public static void clean() {
	    while (true) {
	        if (map[robot.r][robot.c] == 0) {
	            map[robot.r][robot.c] = -1;
	            answer++;
	        }

	        if (!emptyRoom()) {
	            //후진
	            int backDir = (robot.d + 2) % 4;
	            int backR = robot.r + dr[backDir];
	            int backC = robot.c + dc[backDir];

	            if (backR < 0 || backR >= N || backC < 0 || backC >= M || map[backR][backC] == 1) {
	                return; // 벽이거나 맵 밖이면 작동 종료
	            }
	            robot.r = backR;
	            robot.c = backC;
	        } else {
	            // 반시계 방향 회전
	            robot.d = (robot.d + 3) % 4;
	            int nr = robot.r + dr[robot.d];
	            int nc = robot.c + dc[robot.d];
	            if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
	                robot.r = nr;
	                robot.c = nc;
	            }
	            //아니면 계속 회전만 한다 (다음 while 반복에서 다시 처리)
	        }
	    }
	}

	
	//앞으로 
	public static boolean frontEnd() {
	    int nr = robot.r + dr[robot.d];
	    int nc = robot.c + dc[robot.d];
	    return map[nr][nc] == 0;
	}

	
	public static boolean emptyRoom() {
		if(map[robot.r+1][robot.c] == 0 ||
				map[robot.r-1][robot.c] == 0 ||
				map[robot.r][robot.c-1] == 0 ||
				map[robot.r][robot.c+1]== 0) {
			return true; //청소되지 않은 빈칸이 있으니 true
		}
		
		return false; // 청소 끝! false
	}
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		input();
		clean();
		bw.write(String.valueOf(answer));
		
		bw.flush();
	}

}
