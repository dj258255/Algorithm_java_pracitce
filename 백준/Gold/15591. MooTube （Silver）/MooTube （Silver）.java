import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int N, Q;
    static List<int[]>[] adj;  // adj[node] = List of {neighbor, weight}

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    @SuppressWarnings("unchecked")
    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            adj[p].add(new int[]{q, r});
            adj[q].add(new int[]{p, r});
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            bw.write("" + solve(k, v));
            bw.newLine();
        }
    }

    public static int solve(int k, int v) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        queue.add(v);
        visited[v] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int[] edge : adj[node]) {
                int neighbor = edge[0];
                int weight = edge[1];
                if (visited[neighbor]) continue;
                if (weight >= k) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    count++;
                }
            }
        }
        return count;
    }

    public static void output() throws IOException {
        bw.flush();
        bw.close();
    }
}