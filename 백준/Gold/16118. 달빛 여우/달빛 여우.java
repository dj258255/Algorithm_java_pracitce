import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to, w;
        Edge(int to, int w) { this.to = to; this.w = w; }
    }
    
    static class Node implements Comparable<Node> {
        int u, t, type, mode;
        // type: 0=fox, 1=wolf
        // wolf의 mode: 0=fast, 1=slow
        Node(int u, int t, int type, int mode) {
            this.u = u; this.t = t; this.type = type; this.mode = mode;
        }
        @Override
        public int compareTo(Node o) {
            return this.t - o.t;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        // Map으로 그래프 표현
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()),
                b = Integer.parseInt(st.nextToken()),
                d = Integer.parseInt(st.nextToken()) * 2;
            graph.get(a).add(new Edge(b, d));
            graph.get(b).add(new Edge(a, d));
        }

        int[] distF = new int[N + 1];       
        int[][] distW = new int[N + 1][2];  

        Arrays.fill(distF, Integer.MAX_VALUE);
        for (int i = 1; i <= N; i++) {
            distW[i][0] = distW[i][1] = Integer.MAX_VALUE;
        }

        // 여우와 늑대를 한 PQ로 처리
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distF[1] = 0;
        pq.add(new Node(1, 0, 0, 0));  // fox start
        distW[1][0] = 0;
        pq.add(new Node(1, 0, 1, 0));  // wolf start, mode=0

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.u, t = cur.t, tp = cur.type, m = cur.mode;

            if (tp == 0) {
                if (t > distF[u]) continue;
                for (Edge e : graph.get(u)) {
                    int nt = t + e.w;
                    if (nt < distF[e.to]) {
                        distF[e.to] = nt;
                        pq.add(new Node(e.to, nt, 0, 0));
                    }
                }
            } else {
                if (t > distW[u][m]) continue;
                for (Edge e : graph.get(u)) {
                    int nm = 1 - m;
                    int cost = (m == 0 ? e.w / 2 : e.w * 2);
                    int nt = t + cost;
                    if (nt < distW[e.to][nm]) {
                        distW[e.to][nm] = nt;
                        pq.add(new Node(e.to, nt, 1, nm));
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int wolfBest = Math.min(distW[i][0], distW[i][1]);
            if (distF[i] < wolfBest) answer++;
        }
        System.out.println(answer);
    }
}
