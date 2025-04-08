import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] map;
    static ArrayList<int[]>[] wormholes;
    static int maxScore;
    static int[] dr = {-1, 1, 0, 0}; //상하좌우
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());
        for (int t = 1; t <= testCase; t++) {
        	st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            maxScore = 0;
            
            wormholes = new ArrayList[11];
            for (int i = 0; i < wormholes.length; i++) {
                wormholes[i] = new ArrayList<>();
            }
            
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] >= 6 && map[i][j] <= 10) {//웜홀이면
                        wormholes[ map[i][j] ].add(new int[] {i, j});
                    }
                }
            }
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0) {
                        for (int d = 0; d < 4; d++) {
                            maxScore = Math.max(maxScore, simulate(i, j, d));
                        }
                    }
                }
            }
            System.out.println("#" + t + " " + maxScore);
        }
    }
    
    public static int simulate(int startR, int startC, int initDir) {
        int score = 0;
        int r = startR, c = startC;
        int dir = initDir;
        while (true) {
            r += dr[dir];
            c += dc[dir];

            if (r < 0 || r >= N || c < 0 || c >= N) {
                score++;
                dir = reverse(dir);
                continue;
            }
            
            if ((r == startR && c == startC) || map[r][c] == -1) {
                return score;
            }
            
            int cell = map[r][c];
            if (cell >= 1 && cell <= 5) {
                score++;
                dir = blockReflect(dir, cell);
                continue;
            }
            
            if (cell >= 6 && cell <= 10) {
                int[] pos1 = wormholes[cell].get(0);
                int[] pos2 = wormholes[cell].get(1);
                if (r == pos1[0] && c == pos1[1]) {
                    r = pos2[0];
                    c = pos2[1];
                } else {
                    r = pos1[0];
                    c = pos1[1];
                }
                continue;
            }
        }
    }

    
    static int reverse(int d) {
        if (d == 0) return 1;
        if (d == 1) return 0;
        if (d == 2) return 3;
        if (d == 3) return 2;
        return d;
    }
    
    static int blockReflect(int d, int block) {
        if (block == 1) {
            if (d == 0) return 1; //위 -> 아래
            if (d == 1) return 3; //아래 -> 오른쪽
            if (d == 2) return 0; //왼쪽 -> 위
            if (d == 3) return 2; //오른쪽 -> 왼쪽
        }
        else if (block == 2) {
            if (d == 0) return 3; //위 -> 오른쪽
            if (d == 1) return 0; //아래 -> 위
            if (d == 2) return 1; //왼쪽 -> 아래
            if (d == 3) return 2; //오른쪽 -> 왼쪽
        }
        else if (block == 3) {
            if (d == 0) return 2; //위 -> 왼쪽
            if (d == 1) return 0; //아래 -> 위
            if (d == 2) return 3; //왼쪽 -> 오른쪽
            if (d == 3) return 1; // 오른쪽 -> 아래
        }
        else if (block == 4) {
            if (d == 0) return 1; //위 -> 아래
            if (d == 1) return 2; // 아래 -> 왼쪽
            if (d == 2) return 3; //왼쪽 -> 오른쪽
            if (d == 3) return 0; //오른쪽 -> 위
        }
        else if (block == 5) {
            return reverse(d);
        }
        return d;
    }
}
