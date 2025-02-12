import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 정점의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수
        int V = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호 V

        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph.get(i));
        }

        boolean[] visited = new boolean[N + 1];
        ArrayList<Integer> dfsResult = new ArrayList<>();
        dfs(graph, V, visited, dfsResult);

        for (int node : dfsResult) {
            System.out.print(node + " ");
        }
        System.out.println();

        ArrayList<Integer> bfsResult = bfs(graph, N, V);
        for (int node : bfsResult) {
            System.out.print(node + " ");
        }
        System.out.println();
    }

    public static void dfs(Map<Integer, ArrayList<Integer>> graph, int start, boolean[] visited, ArrayList<Integer> list) {
        visited[start] = true;
        list.add(start);

        for (int neighbor : graph.get(start)) {
            if (!visited[neighbor]) {
                dfs(graph, neighbor, visited, list);
            }
        }
    }

    public static ArrayList<Integer> bfs(Map<Integer, ArrayList<Integer>> map, int N, int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            list.add(node);

            for (int neighbor : map.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return list;
    }
}