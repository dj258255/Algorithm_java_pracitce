import java.io.*;
import java.util.*;

class Main {
    static class Node {
        int vertex, distance, type; // type: 0=맥도날드경로, 1=스타벅스경로
        
        Node(int vertex, int distance, int type) {
            this.vertex = vertex;
            this.distance = distance;
            this.type = type;
        }
    }
    
    static final int INF = 1000000000;
    static int V, E;
    static ArrayList<ArrayList<int[]>> graph;
    static boolean[] isMcdonalds;
    static boolean[] isStarbucks;
    static int maxMcdist, maxStardist;
    static int[][] dist; // dist[vertex][type] = 해당 타입으로 정점에 도달하는 최단거리
    
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
        
        // 단일 다익스트라 실행
        int result = singleDijkstra();
        System.out.println(result == INF ? -1 : result);
    }
    
    static int singleDijkstra() {
        // dist[vertex][type] - type: 0=맥도날드로부터의 거리, 1=스타벅스로부터의 거리
        dist = new int[V + 1][2];
        
        // 거리 배열 초기화
        for (int i = 0; i <= V; i++) {
            dist[i][0] = INF; // 맥도날드로부터의 거리
            dist[i][1] = INF; // 스타벅스로부터의 거리
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return a.distance - b.distance;
            }
        });
        
        // 모든 맥도날드에서 시작 (type = 0)
        for (int i = 1; i <= V; i++) {
            if (isMcdonalds[i]) {
                dist[i][0] = 0;
                pq.offer(new Node(i, 0, 0));
            }
        }
        
        // 모든 스타벅스에서 시작 (type = 1)
        for (int i = 1; i <= V; i++) {
            if (isStarbucks[i]) {
                dist[i][1] = 0;
                pq.offer(new Node(i, 0, 1));
            }
        }
        
        // 단일 다익스트라 실행
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int vertex = current.vertex;
            int distance = current.distance;
            int type = current.type;
            
            // 이미 더 짧은 거리로 방문한 경우
            if (distance > dist[vertex][type]) {
                continue;
            }
            
            // 인접한 정점들을 탐색
            for (int[] edge : graph.get(vertex)) {
                int nextVertex = edge[0];
                int weight = edge[1];
                int nextDistance = distance + weight;
                
                // 같은 타입으로 더 짧은 거리를 찾은 경우
                if (nextDistance < dist[nextVertex][type]) {
                    dist[nextVertex][type] = nextDistance;
                    pq.offer(new Node(nextVertex, nextDistance, type));
                }
            }
        }
        
        // 조건을 만족하는 집 중 최소값 찾기
        int result = INF;
        for (int i = 1; i <= V; i++) {
            // 집이 있는 곳 (맥도날드도 스타벅스도 아닌 곳)
            if (!isMcdonalds[i] && !isStarbucks[i]) {
                int mcDistance = dist[i][0];   // 가장 가까운 맥도날드까지의 거리
                int starDistance = dist[i][1]; // 가장 가까운 스타벅스까지의 거리
                
                // 맥세권 && 스세권 조건 확인
                if (mcDistance <= maxMcdist && starDistance <= maxStardist) {
                    result = Math.min(result, mcDistance + starDistance);
                }
            }
        }
        
        return result;
    }
}