import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static int N, answer;
    static int[][] map;
    static int[] dr = {1, 1, -1, -1}; //오른쪽 아래 , 왼쪽 아래 , 왼쪽 위 , 오른쪽 위
    static int[] dc = {1, -1, -1, 1};
    static int startR, startC;
    static boolean[] dessertVisited; //디저트 방문 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            answer = -1;
            

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dessertVisited = new boolean[101];
                    startR = i;
                    startC = j;
                    dessertVisited[map[i][j]] = true;
                    dfs(i, j, 0, 1);
                }
            }
            
            System.out.println("#" + tc + " " + answer);
        }
    }
    
    static void dfs(int r, int c, int dir, int count) {
        for (int i = dir; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr == startR && nc == startC && count >= 4) {
                answer = Math.max(answer, count);
                return;
            }
            
            //범위 내이고 아직 해당 디저트를 먹지 않은 경우
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && !dessertVisited[map[nr][nc]]) {
                dessertVisited[map[nr][nc]] = true;
                dfs(nr, nc, i, count + 1);
                dessertVisited[map[nr][nc]] = false;
            }
        }
    }
}
