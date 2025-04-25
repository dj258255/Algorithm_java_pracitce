import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    //입력
    static int N;
    static char[][] map;

    //1- 누적합 맵
    static int[][] aMap;
    //누적합 맵
    static int[][] psum;
    //지뢰 카운트맵
    static int[][] countmap;
    //방문 체크
    static boolean[][] visited;
    //클릭 수
    static int count;

    //8방향
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1,  0,  1, -1, 1, -1, 0, 1};

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();
            for (int j = 0; j < N; j++) {
                map[i][j] = temp.charAt(j);
            }
        }
    }

    //지뢰있으면 1 아니면 0
    public static void isFail() {
        aMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                aMap[i][j] = (map[i][j] == '*') ? 1 : 0;
            }
        }
    }

    //누적합생성
    public static void prefix() {
        psum = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                psum[i][j] = psum[i - 1][j]
                           + psum[i][j - 1]
                           - psum[i - 1][j - 1]
                           + aMap[i - 1][j - 1];
            }
        }
    }

    //지뢰 카운트맵 생성
    public static void count() {
        countmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int r1 = Math.max(0, i - 1), c1 = Math.max(0, j - 1);
                int r2 = Math.min(N - 1, i + 1), c2 = Math.min(N - 1, j + 1);
                countmap[i][j] = psum[r2 + 1][c2 + 1]
                               - psum[r1][c2 + 1]
                               - psum[r2 + 1][c1]
                               + psum[r1][c1];
            }
        }
    }

    //BFS 클릭탐색 form
    public static void bfs_form() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '.' && countmap[i][j] == 0 && !visited[i][j]) {
                    count++;
                    bfs(i, j);
                }
            }
        }
    }

    //진짜 확장
    static void bfs(int sr, int sc) {
        Queue<int[]> q = new LinkedList<>();
        visited[sr][sc] = true;
        q.offer(new int[]{sr, sc});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            if (countmap[r][c] == 0) {
                for (int d = 0; d < dr.length; d++) {
                    int nr = r + dr[d], nc = c + dc[d];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N
                     && map[nr][nc] == '.' && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        if (countmap[nr][nc] == 0) {
                            q.offer(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
    }

    //빈 칸 직접 클릭
    public static void direct() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '.' && !visited[i][j]) {
                    count++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int Tc = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= Tc; t++) {
            //1.입력
            input();

            //2.지뢰 변경
            isFail();

            //3. 누적합 & 카운트맵
            prefix();
            count();

            //4. 변수 초기화
            visited = new boolean[N][N];
            count = 0;

            //5. BFS 탐색
            bfs_form();
            //6. 남은 칸 클
            direct();

            sb.append('#').append(t).append(' ').append(count).append('\n');
        }

        System.out.print(sb.toString());
    }
}
