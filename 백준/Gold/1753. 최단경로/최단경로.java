import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V;
    static int E;
    static Map<Integer, ArrayList<int[]>> map; //int[]: {목적지, 가중치}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); //정점의 개수
        E = Integer.parseInt(st.nextToken()); //간선의 개수

        //정점 번호가 1부터 시작하므로 1 ~ V까지 초기화
        map = new HashMap<>();
        for (int i = 1; i <= V; i++) {
            map.put(i, new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());

        //간선 입력: u에서 v로 가는 가중치 w인 간선
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            //int[0]: 목적지, int[1]: 가중치
            map.get(u).add(new int[]{v, w});
        }

        // 다익스트라 알고리즘 수행
        int[] distances = dijkstra(start);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                sb.append("INF").append("\n");
            } else {
                sb.append(distances[i]).append("\n");
            }
        }
        System.out.print(sb);
    }

    public static int[] dijkstra(int start) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // 우선순위 큐에 {정점, 현재까지의 거리}를 저장 (거리 오름차순 정렬)
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });

        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curVertex = cur[0];
            int curDist = cur[1];

            // 이미 더 짧은 경로가 발견되었으면 건너뜀
            if (curDist > dist[curVertex]) continue;

            // 인접 정점에 대해 최단 경로 갱신 시도
            for (int[] edge : map.get(curVertex)) {
                int nextVertex = edge[0];
                int weight = edge[1];
                if (dist[nextVertex] > curDist + weight) {
                    dist[nextVertex] = curDist + weight;
                    pq.offer(new int[]{nextVertex, dist[nextVertex]});
                }
            }
        }

        return dist;
    }
}
