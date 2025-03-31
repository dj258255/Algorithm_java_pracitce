import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            
            Map<Integer, List<int[]>> graph = new HashMap<>();
            for (int i = 1; i <= V; i++) {
                graph.put(i, new ArrayList<>());
            }
            
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                graph.get(a).add(new int[]{b, cost});
                graph.get(b).add(new int[]{a, cost});
            }
            
            boolean[] visited = new boolean[V + 1];
            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            });
            long result = 0;
            int count = 0;
            

            pq.offer(new int[]{1, 0});
            
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int vertex = cur[0];
                int cost = cur[1];
                

                if (visited[vertex]) continue;
                visited[vertex] = true;
                result += cost;
                count++;
                
                if (count == V) break;
                
                for (int[] next : graph.get(vertex)) {
                    if (!visited[next[0]]) {
                        pq.offer(new int[]{next[0], next[1]});
                    }
                }
            }
            
            System.out.println("#" + t + " " + result);
        }
    }
}
