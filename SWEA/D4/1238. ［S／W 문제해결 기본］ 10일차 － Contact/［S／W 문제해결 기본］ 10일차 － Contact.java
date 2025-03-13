import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.HashMap;

public class Solution{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int tc=1; tc<=10; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 입력 받는 데이터의 길
			int start = Integer.parseInt(st.nextToken());//데이터의 시작
			Map<Integer, HashSet<Integer>> map = new HashMap<>();

			for(int i = 1; i <= 100; i++) {
				map.put(i, new HashSet<Integer>() );
			}

			st = new StringTokenizer(br.readLine());
			
			for(int i = 0 ; i < N/2; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map.get(a).add(b);
			}
			
			int answer = bfs(map,start);
			
			System.out.println("#" + tc + " " + answer);
		}
	}
	
	public static int bfs(Map<Integer, HashSet<Integer>> map , int start ) {
		boolean[] visited = new boolean[101];
		Queue<Integer> queue = new ArrayDeque<>();
		int[] depth = new int[101];
		
		queue.add(start);
		visited[start] = true;
		depth[start] = 0;

		while(!queue.isEmpty()) {
			int node = queue.poll();
			

			for(int neighbor : map.get(node)) {
				if(!visited[neighbor]) {
					visited[neighbor] = true;
					queue.add(neighbor);

	                //부모 노드 깊이에 1더하기
	                depth[neighbor] = depth[node] + 1;
				}
			}	
		}
		
	    int maxDepth = Integer.MIN_VALUE;
	    int answer = Integer.MIN_VALUE;
	    
	    for (int i = 1; i <= 100; i++) {
	        if (visited[i]) {
	            if (depth[i] > maxDepth) {
	                maxDepth = depth[i];
	                answer = i;
	            } else if (depth[i] == maxDepth && i > answer) {
	                answer = i;
	            }
	        }
	    }
	    
	    return answer;
	}
}