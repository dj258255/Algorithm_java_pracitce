import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
        //--------------솔루션 코드를 작성하세요.--------------------------------
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<Integer>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.get(a).add(b);
            map.get(b).add(a);
        }
        
        int count = bfs(map, 1);
        System.out.println(count);
    }
    
    public static int bfs(Map<Integer, ArrayList<Integer>> map, int start) {
        int answer = 0;
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        int[] distance = new int[N + 1];
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : map.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    distance[neighbor] = distance[node] + 1;
                }
            }
        }
        
        for (int i = 2; i <= N; i++) {
            if (distance[i] >= 1 && distance[i] <= 2) {
                answer++;
            }
        }
        
        return answer;
    }
}
