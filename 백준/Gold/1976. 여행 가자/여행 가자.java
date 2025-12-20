import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> graph;
    static ArrayList<Integer> plan;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        M = Integer.parseInt(br.readLine().trim());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    graph.get(i).add(j);
                }
            }
        }

        st = new StringTokenizer(br.readLine().trim());
        plan = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            plan.add(Integer.parseInt(st.nextToken()));
        }

        System.out.println(solve() ? "YES" : "NO");
    }

    static boolean solve() {
        int start = plan.get(0);
        boolean[] visited = bfs(start);

        for (int city : plan) {
            if (!visited[city]) {
                return false;
            }
        }
        return true;
    }

    static boolean[] bfs(int start) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        return visited;
    }
}