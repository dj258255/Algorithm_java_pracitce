import java.util.*;
import java.io.*;

public class Main{
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int T,A,B;
	static int max;
	public static void main(String[] args) throws IOException{
		input();
		solve();
		output();
	}
	
	public static void solve() throws IOException{
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[T+1][2];
		queue.add(new int[] {0,0}); //포만감 , 물마심 여
		visited[0][0] = true;
		
		max = 0;
		
		while(!queue.isEmpty()) {
			int[] node = queue.poll();
			int full = node[0]; //현재 포만감
			int water = node[1]; //물 마셨니? 0==안마심 
			
			max = Math.max(max,full);
            // 오렌지 먹기
            if (full + A <= T && !visited[full + A][water]) {
                visited[full + A][water] = true;
                queue.add(new int[]{full + A, water});
            }
            
            // 레몬 먹기
            if (full + B <= T && !visited[full + B][water]) {
                visited[full + B][water] = true;
                queue.add(new int[]{full + B, water});
            }
            
            // 물 마시기 (아직 안 마셨을 때만)
            if (water == 0) {
                int half = full / 2;
                if (!visited[half][1]) {
                    visited[half][1] = true;
                    queue.add(new int[]{half, 1});
                }
            }
		}
	}
	
	public static void output() throws IOException{
		bw.write(""+max);
		bw.flush();
		bw.close();
	}
	
	public static void input() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		st = new StringTokenizer(br.readLine().trim());
		T = Integer.parseInt(st.nextToken()); //최대의 풍성
		A = Integer.parseInt(st.nextToken()); // 오렌지 먹으면 포만감 A 증가 
		B = Integer.parseInt(st.nextToken()); //레몬 먹으면 포만감 B 증가
	}
}