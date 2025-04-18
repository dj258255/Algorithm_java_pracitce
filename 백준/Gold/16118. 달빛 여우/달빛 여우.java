import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to, w;
        Edge(int to, int w) { this.to = to; this.w = w; }
    }
    
    static class Node {
        int u, t, type, mode;
        // type: 0=fox, 1=wolf
        // wolf의 mode: 0=fast, 1=slow
        Node(int u, int t, int type, int mode) {
            this.u = u; this.t = t; this.type = type; this.mode = mode;
        }
        
        
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        //인접 리스트로 그래프 표현
        List<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()),
                b = Integer.parseInt(st.nextToken()),
                d = Integer.parseInt(st.nextToken()) * 2;  // 거리*2
            graph[a].add(new Edge(b, d));
            graph[b].add(new Edge(a, d));
        }

        int[] distF = new int[N + 1]; //fox
        int[][] distW = new int[N + 1][2]; //wolf, 2 modes

        Arrays.fill(distF, Integer.MAX_VALUE);
        for (int i = 1; i <= N; i++)
            Arrays.fill(distW[i], Integer.MAX_VALUE);

        //하나의 PQ에 fox(1,0,0,0)와 wolf(1,0,1,0) 상태 모두 푸시
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)-> o1.t - o2.t); //오름차순
        
        distF[1] = 0;
        pq.add(new Node(1, 0, 0, 0));   // 여우 시작
        distW[1][0] = 0;
        pq.add(new Node(1, 0, 1, 0));   // 늑대 시작, mode=0 (fast)

        //멀티 상태 Dijkstra
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.u, t = cur.t, tp = cur.type, m = cur.mode;

            if (tp == 0) {
                if (t > distF[u]) continue;
                for (Edge e : graph[u]) {
                    int nt = t + e.w;
                    if (nt < distF[e.to]) {
                        distF[e.to] = nt;
                        pq.add(new Node(e.to, nt, 0, 0));
                    }
                }
            } else {
                if (t > distW[u][m]) continue;
                for (Edge e : graph[u]) {
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
