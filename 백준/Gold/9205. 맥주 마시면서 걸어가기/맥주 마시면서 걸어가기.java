import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	public static int[][] convenienceStore;
	public static int[] goalPos;
	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		//맥주 한 박스에는 맥주가 20개 들어있다. 
		//목이 마르면 안되기 때문에 50미터에 한 병씩 마시려고 한다. 
		//즉, 50미터를 가려면 그 직전에 맥주 한 병을 마셔야 한다.
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); //편의점 개수
			
			
			convenienceStore = new int[n+2][2];
			
			Queue<int[]> queue = new ArrayDeque<>();
			
			for(int i = 0 ; i < n+2; i++) {
				st = new StringTokenizer(br.readLine());
				convenienceStore[i][0] = Integer.parseInt(st.nextToken());
				convenienceStore[i][1] = Integer.parseInt(st.nextToken());
			}

			System.out.println( (bfs(convenienceStore[0][0],convenienceStore[0][1]) ? "happy" : "sad" ));

		}
	}
	
	
	
	public static boolean bfs(int startR, int startC) {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[n+2]; //인덱스 방문
		queue.add(new int[] {startR,startC,0}); //인덱스 0 시작.
		visited[0] = true;

		
		while(!queue.isEmpty()) {
			//50미터당 1개
			//현재 위치 꺼냄
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1]; 
			int index = cur[2];
			
			int brew = 20; //맥주 한박스 최대 20병
			
			for(int i = 0 ; i < n + 2; i++) {
				int nr = convenienceStore[i][0];
				int nc = convenienceStore[i][1];
				int distance = Math.abs(nr-r) + Math.abs(nc-c);
				
				if(!visited[i] && brew >= (distance/50.0)) {
					visited[i] = true;
//					System.out.println(distance);
//					System.out.println(distance/50);
					
				
					queue.add(new int[] {nr,nc,i});
					
				}
				
				
			}
			
			if(index == n + 1) {
				return true;
			}
			
		}
		
		return false;
	}
}