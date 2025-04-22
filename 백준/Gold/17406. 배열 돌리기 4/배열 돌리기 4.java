import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static BufferedReader br;
	static StringTokenizer st;
	static int answer;
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		
		
		init();
		
		//초기화
		visited = new boolean[K];
		per = new int[K];
		answer = Integer.MAX_VALUE;
		
		dfs(0);
		
		//출력
		System.out.println(answer);
	}
	
	static boolean[] visited;
	static int[] per;
	//순열
	public static void dfs(int index) {
		if(index == K) {
			rotate(per);
			
			cal();
			
			return;
		}
		
		
		for(int i = 0 ; i < K; i++) {
			if(!visited[i]) {
				visited[i] = true;
				per[index] = i;
				dfs(index+1);
				visited[i] = false; 
			}
		}
	}
	
	
	public static void cal() {
		int tempMin = Integer.MAX_VALUE;

		for(int i = 1 ; i <= N; i++) {
			int sum =0;
			for(int j = 1 ; j <= M; j++) {
				sum += newMap[i][j];
			}
			
			tempMin = Math.min(tempMin, sum);
		}
		
		answer = Math.min(tempMin, answer);
	}
	
	
	
	static int[][] newMap;
    public static void rotate(int[] per) {
        createMap(); // 원본 map → newMap 복사

        for (int k = 0; k < K; k++) {
            int idx = per[k];
            //layer별로 안쪽까지 반복
            for (int layer = 1; layer <= s[idx]; layer++) {
                int r0 = r[idx] - layer;
                int c0 = c[idx] - layer;
                int r1 = r[idx] + layer;
                int c1 = c[idx] + layer;
                int prev = newMap[r0][c0];

                //top row -> 오른쪽
                for (int j = c0 + 1; j <= c1; j++) {
                    int tmp = newMap[r0][j];
                    newMap[r0][j] = prev;
                    prev = tmp;
                }
                //right col -> 아래
                for (int i = r0 + 1; i <= r1; i++) {
                    int tmp = newMap[i][c1];
                    newMap[i][c1] = prev;
                    prev = tmp;
                }
                //bottom row -> 왼쪽
                for (int j = c1 - 1; j >= c0; j--) {
                    int tmp = newMap[r1][j];
                    newMap[r1][j] = prev;
                    prev = tmp;
                }
                //left col -> 위
                for (int i = r1 - 1; i >= r0; i--) {
                    int tmp = newMap[i][c0];
                    newMap[i][c0] = prev;
                    prev = tmp;
                }
            }
        }
    }

	
	//새로운 게임판 만들기
	public static void createMap() {
		for(int i = 1 ; i <= N; i++) {
			for(int j = 1; j<=M; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}
	
	
	static int[][] map;
	static int N,M,K; // 배열 크기 NM 회전 연산개수 K
	static int[] r,c,s;
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); //회전 연산 개수
		
		map = new int[N+1][M+1];
		for(int i = 1 ; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		r = new int[K];
		c = new int[K];
		s = new int[K];
		for(int i = 0 ; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			r[i] = Integer.parseInt(st.nextToken()); //r,c,s
			c[i] = Integer.parseInt(st.nextToken());
			s[i] = Integer.parseInt(st.nextToken()); 
		}
		
		newMap = new int[N+1][M+1];
	}
}