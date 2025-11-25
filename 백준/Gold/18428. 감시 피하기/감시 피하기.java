import java.util.*;
import java.io.*;

public class Main{
	static BufferedReader br;
	static BufferedWriter bw; 
	static StringTokenizer st;
	static StringBuilder sb;
	static int N;
	static boolean possible;
	static ArrayList<int[]> teacherPos, emptyPos;
	static char[][] originalMap; 
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
		teacherPos = new ArrayList<>();
		emptyPos = new ArrayList<>();
		originalMap = new char[N][N];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				String input = st.nextToken();
				originalMap[i][j] = input.charAt(0);  // 문자로 저장
				if(originalMap[i][j] == 'T') teacherPos.add(new int[] {i,j});
				if(originalMap[i][j] == 'X') emptyPos.add(new int[] {i,j});
			}
		}
	}

	public static void solve() throws IOException{
		set(0, 0);  //매개변수 추가
	}

	public static boolean simul() throws IOException{
		for (int[] teacher : teacherPos) {
			int r = teacher[0];
			int c = teacher[1];

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				while (nr >= 0 && nc >= 0 && nr < N && nc < N) {
					//장애물이 있으면 break
					if (originalMap[nr][nc] == 'O') break;

					//학생이 있다면 감시 실패
					if (originalMap[nr][nc] == 'S') return false;

					//계속 직진
					nr += dr[d];
					nc += dc[d];
				}
			}
		}
		// 모든 선생님이 학생을 못 봤으면 성공
		return true;
	}

	public static void set(int count, int start) throws IOException{
		if(possible) return;

		if(count == 3) {
			if(simul()) {
				possible = true;
			}
			return; 
		}

		for(int i = start; i < emptyPos.size(); i++) { 
			int[] cell = emptyPos.get(i); 
			originalMap[cell[0]][cell[1]] = 'O';
			set(count+1, i+1);
			originalMap[cell[0]][cell[1]] = 'X';
		}
	}

	public static void output() throws IOException{
		System.out.println(possible ? "YES" : "NO"); 
	}
}