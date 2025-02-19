import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            
            int[][] map = new int[n][n];
            boolean[][] visited = new boolean[n][n];
            
            int[] dx = {0, 1, 0, -1};
            int[] dy = {1, 0, -1, 0};
            
            int x = 0, y = 0, d = 0;
            
            for (int i = 1; i <= n * n; i++) {
                map[x][y] = i;
                visited[x][y] = true;
                
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) {
                    d = (d + 1) % 4;
                    nx = x + dx[d];
                    ny = y + dy[d];
                }
                
                x = nx;
                y = ny;
            }
            
            System.out.println("#" + tc);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
