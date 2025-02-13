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
            int N = Integer.parseInt(st.nextToken()); // 한변의 크기
            int X = Integer.parseInt(st.nextToken()); // 경사로의 길이
            int[][] map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;

            // 가로 검사
            for (int i = 0; i < N; i++) {
                if (check(map[i], X)) {
                    answer++;
                }
            }

            // 세로 검사
            for (int i = 0; i < N; i++) {
                int[] col = new int[N];
                for (int j = 0; j < N; j++) {
                    col[j] = map[j][i];
                }
                if (check(col, X)) {
                    answer++;
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    private static boolean check(int[] line, int X) {
        int N = line.length;
        boolean[] installed = new boolean[N]; // 경사로 설치 여부 체크

        for (int i = 0; i < N - 1; i++) {
            if (line[i] == line[i + 1]) continue; // 높이가 같으면 패스

            // 높이 차이가 1보다 크면 불가능
            if (Math.abs(line[i] - line[i + 1]) > 1) return false;

            if (line[i] > line[i + 1]) { // 내리막 경사로 설치
                for (int j = i + 1; j <= i + X; j++) {
                    if (j >= N || line[j] != line[i + 1] || installed[j]) return false;
                    installed[j] = true;

                }
            } else { // 오르막 경사로 설치
                for (int j = i; j > i - X; j--) {
                    if (j < 0 || line[j] != line[i] || installed[j]) return false;
                    installed[j] = true;
                }
            }
        }
        return true;
    }
}
