import java.io.*;
import java.util.*;

class Main {
    static class Node {
        int vertex, distance;
        
        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
    
    static final int INF = 1000000000;
    static int V, E;
    static ArrayList<ArrayList<int[]>> graph;
    static boolean[] isMcdonalds;
    static boolean[] isStarbucks;
    static int maxMcdist, maxStardist;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        // 그래프 초기화
        graph = new ArrayList<ArrayList<int[]>>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<int[]>());
        }
        
        // 간선 정보 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }
        
        // 맥도날드 정보
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        maxMcdist = Integer.parseInt(st.nextToken());
        
        isMcdonalds = new boolean[V + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            isMcdonalds[Integer.parseInt(st.nextToken())] = true;
        }
        
        // 스타벅스 정보
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        maxStardist = Integer.parseInt(st.nextToken());
        
        isStarbucks = new boolean[V + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            isStarbucks[Integer.parseInt(st.nextToken())] = true;
        }
        
        // 각 정점에서 가장 가까운 맥도날드까지의 거리
        int[] mcDist = dijkstraFromMultiple(isMcdonalds);
        
        // 각 정점에서 가장 가까운 스타벅스까지의 거리
        int[] starDist = dijkstraFromMultiple(isStarbucks);
        
        // 조건을 만족하는 집 중 최소값 찾기
        int result = INF;
        for (int i = 1; i <= V; i++) {
            // 집이 있는 곳 (맥도날드도 스타벅스도 아닌 곳)
            if (!isMcdonalds[i] && !isStarbucks[i]) {
                // 맥세권 && 스세권 조건 확인
                if (mcDist[i] <= maxMcdist && starDist[i] <= maxStardist) {
                    result = Math.min(result, mcDist[i] + starDist[i]);
                }
            }
        }
        
        System.out.println(result == INF ? -1 : result);
    }
    
    // 여러 시작점에서 모든 정점까지의 최단거리를 구하는 다익스트라
    static int[] dijkstraFromMultiple(boolean[] isSource) {
        int[] dist = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            dist[i] = INF;
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return a.distance - b.distance;
            }
        });
        
        // 모든 소스(맥도날드 또는 스타벅스)에서 시작
        for (int i = 1; i <= V; i++) {
            if (isSource[i]) {
                dist[i] = 0;
                pq.offer(new Node(i, 0));
            }
        }
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int vertex = current.vertex;
            int distance = current.distance;
            
            if (distance > dist[vertex]) {
                continue;
            }
            
            for (int[] edge : graph.get(vertex)) {
                int nextVertex = edge[0];
                int weight = edge[1];
                int nextDistance = distance + weight;
                
                if (nextDistance < dist[nextVertex]) {
                    dist[nextVertex] = nextDistance;
                    pq.offer(new Node(nextVertex, nextDistance));
                }
            }
        }
        
        return dist;
    }
}