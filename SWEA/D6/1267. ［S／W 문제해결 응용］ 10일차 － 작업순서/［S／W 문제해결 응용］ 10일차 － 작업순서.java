import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    public static int V, E;
    public static Map<Integer, ArrayList<Integer>> graph;
    public static int[] inDegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            
            graph = new HashMap<>();
            inDegree = new int[V + 1];
            
            for (int i = 1; i <= V; i++) {
                graph.put(i, new ArrayList<>());
            }
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
                inDegree[v]++;
            }
            
            //위상 정렬 실행
            ArrayList<Integer> answer = topologicalSort();
            
            System.out.print("#" + tc + " ");
            for (int num : answer) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
    
    public static ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        
        //진입 차수가 0인 모든 노드를 큐에 추가
        for (int i = 1; i <= V; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            
            //현재 노드의 모든 자식 노드에 대해 진입 차수를 감소시키고, 0이 되면 큐에 추가
            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return result;
    }
}
