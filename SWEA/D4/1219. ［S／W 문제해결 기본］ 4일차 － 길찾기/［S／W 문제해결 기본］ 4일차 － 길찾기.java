import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Solution {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int t = 1; t<=10; t++) {
			st = new StringTokenizer(br.readLine());
			int test_case = Integer.parseInt(st.nextToken());
			
			int N = Integer.parseInt(st.nextToken());
			
			Map<Integer,ArrayList<Integer>> map = new HashMap<>();
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < 100; i++) {
				map.put(i, new ArrayList<>());
			}
			
			for(int i = 0 ; i < N; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map.get(a).add(b);
			}
			
			int answer = 0;
			
			Queue<Integer> queue = new ArrayDeque<>();
			queue.add(0);
			boolean[] visited = new boolean[100];
			visited[0] = true;
			while(!queue.isEmpty()) {
				int node = queue.poll();
				for(int neighbor : map.get(node)) {
					if(!visited[neighbor]) {
						visited[neighbor] = true;
						queue.add(neighbor);
						if(neighbor == 99) {
							answer = 1;
						}
					}
				}
			}
			
			System.out.println("#" + test_case + " " + answer);
		}

	}

}
