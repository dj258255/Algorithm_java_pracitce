import java.util.Map;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
 public static void main(String args[]) throws Exception {
	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 
	 int T = Integer.parseInt(br.readLine());
	 
	 for (int tc = 1; tc <= T; tc++) {
		 int answer = 0;
		 int N = Integer.parseInt(br.readLine());
		 Map<Integer, ArrayList<Integer>> bigger = new HashMap<>(); 
		 Map<Integer, ArrayList<Integer>> smaller = new HashMap<>(); 
		 
		 for(int i = 1 ; i <= N; i++) {
			 bigger.put(i, new ArrayList<Integer>());
			 smaller.put(i, new ArrayList<Integer>());
		 }
		 
		 
		 
		 int M = Integer.parseInt(br.readLine());
		 StringTokenizer st;
		 for (int i = 0; i < M; i++) {
			 st = new StringTokenizer(br.readLine());
			 int a = Integer.parseInt(st.nextToken());
			 int b = Integer.parseInt(st.nextToken());
			 bigger.get(a).add(b);
			 smaller.get(b).add(a);
		 }
		 
		 for (int i = 1; i <= N; i++) {
			 int count = 1;
			 boolean[] visited = new boolean[N + 1];
			 Queue<Integer> queue = new ArrayDeque<>();
			 queue.add(i);
			 visited[i] = true;
			 while (!queue.isEmpty()) {
				 int node = queue.poll();
				 for (int neighbor : bigger.get(node)) {
					 if (visited[neighbor]) continue;
					 visited[neighbor] = true;
					 queue.add(neighbor);
					 count++;
				 }
			 }
			 
			 queue.add(i);
			 while (!queue.isEmpty()) {
				 int node = queue.poll();
				 for (int neighbor : smaller.get(node)) {
					 if (visited[neighbor]) continue;
					 visited[neighbor] = true;
					 queue.add(neighbor);
					 count++;
				 }
			 }
			 
			 if (count == N) 
				 answer++;
			 }
		 System.out.println("#" + tc + " " + answer);
	 }
 }
}