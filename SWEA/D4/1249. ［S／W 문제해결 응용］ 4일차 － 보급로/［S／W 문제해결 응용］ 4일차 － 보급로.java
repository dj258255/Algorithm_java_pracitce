import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N;
    static int[][] map;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Tc = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= Tc; t++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String line = br.readLine().trim();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j) - '0';
                }
            }
            System.out.println("#" + t + " " + dijkstra());
        }
    }
    
    public static int dijkstra() {
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[0][0] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[] {0, 0, 0});
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int y = cur[0];
            int x = cur[1];
            int cost = cur[2];
            
            if (cost > dist[y][x]) continue;
            if (y == N - 1 && x == N - 1) return cost;
            
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
                    int newCost = cost + map[ny][nx];
                    if (newCost < dist[ny][nx]) {
                        dist[ny][nx] = newCost;
                        pq.add(new int[] {ny, nx, newCost});
                    }
                }
            }
        }
        return dist[N - 1][N - 1];
    }
}
