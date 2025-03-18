
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    // 이동 방향: 상, 하, 좌, 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int testCase = 0;
        
        while (true) {
            int N = Integer.parseInt(br.readLine().trim());
            if (N == 0) break;
            
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            int result = dijkstra(map, N);
            testCase++;
            sb.append("Problem " + testCase + ": " + result + "\n");
        }
        System.out.print(sb);
    }
    
    // Dijkstra 알고리즘을 사용하여 (0,0)에서 (N-1, N-1)까지의 최소 소모 금액을 구하는 메소드
    public static int dijkstra(int[][] map, int N) {
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        // 시작 지점의 비용은 첫 번째 칸의 값
        dist[0][0] = map[0][0];
        
        // 우선순위 큐: {x좌표, y좌표, 현재까지 소모한 금액}를 저장
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        
        pq.offer(new int[]{0, 0, map[0][0]});
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int x = curr[0], y = curr[1], cost = curr[2];
            
            // 도착 지점에 도달했다면 최소 비용이므로 반환
            if (x == N - 1 && y == N - 1) {
                return cost;
            }
            // 이미 더 적은 비용으로 방문한 경우 무시
            if (cost > dist[x][y]) continue;
            
            // 상하좌우 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    int newCost = cost + map[nx][ny];
                    if (newCost < dist[nx][ny]) {
                        dist[nx][ny] = newCost;
                        pq.offer(new int[]{nx, ny, newCost});
                    }
                }
            }
        }
        return dist[N - 1][N - 1];
    }
}
