import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int N;
	static int[][] map;
	static int[] dx = {-1,1,0,0}; //좌 우 상 하 
	static int[] dy = {0,0,-1,1}; //좌 우 상 하
	static int maxValue;
	static int answer = 0;
	static boolean[][] visited;
	static int count;
	
	public static void main(String[] args) throws IOException{
		//--------------솔루션 코드를 작성하세요.--------------------------------
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N; j++) {
				map[i][j]= Integer.parseInt(st.nextToken()); 
				maxValue = Math.max(map[i][j], maxValue); // 퍼트릴 수 있는 최대 꽃가루 수치
			}
		}
		
		for(int i = 0; i < maxValue; i++) {
			visited = new boolean[N][N];
			count = 0;
			find(i);
		}
		
		System.out.println(answer);
		
	}
	
	public static void find(int power) {
		//안전지대 아닌곳 분홍색만들기
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < N; j++) {
				if(power >= map[i][j]) { //꽃가루 농도가 안전지대보다 크면
					visited[i][j] = false; 
				} else if(map[i][j] > power ) {
					visited[i][j] = true; 
				}
			}
		}
		//여기까진 정상

		
		
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < N; j++) {
				if(visited[i][j] == false) { //방문했거나 꽃가루 알레르기면 넘기기
					continue;
				} else if(visited[i][j] == true) {
					spread(i, j); //전부다 트루로 만들기
					count++; // 그리고 카운트도 증가
					
//					for(int ii = 0 ; ii < N; ii++) {
//						for(int jj = 0 ; jj < N; jj++) {
//							System.out.print(visited[ii][jj] + " ");
//						}
//						System.out.println();
//					}
//					
//					System.out.println("count :" + count);	
				}
				
			}
			
		}
		
		
//		for(int i = 0 ; i < N; i++) {
//			for(int j = 0 ; j < N; j++) {
//				System.out.print(visited[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		
		answer = Math.max(count, answer);

	}
	
	public static void spread(int i,int j){ //
		visited[i][j] = false; //비 안전지대로 만들기
		
		for(int k = 0 ; k < 4; k++) {
			int nx = j+dx[k];
			int ny = i+dy[k];
			
			if(!(nx >=0 && ny >= 0 && nx < N && ny < N)) //범위안에 없으면 컨티뉴
				continue;
			
			if(!visited[ny][nx]) continue; // 비안전지대면 컨티뉴
			
			if(visited[ny][nx]) { //다음칸이 안전지대면
				visited[ny][nx] = false; //비안전지대로 만들기
				spread(ny,nx); //다음 비 안전지대도 계속 진행
			}
			
		}
	}

}
