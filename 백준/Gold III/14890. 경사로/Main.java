import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N, L;
    static int[][] map;

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void solve() throws IOException {
        int sum = 0;

        //아래로
        for (int i = 0; i < N; i++) {
            sum += cal(0, i, 0);
        }

        //오른쪽으로
        for (int i = 0; i < N; i++) {
            sum += cal(i, 0, 1);
        }

        bw.write(sum + "\n");
    }

    //r,c좌표 d방향
    //d==0 아래쪽
    //d==1 오른쪽
    public static int cal(int r, int c, int d) throws IOException {
        boolean[] used = new boolean[N]; // 경사로 사용 여부

        for (int i = 0; i < N - 1; i++) {
            int cur, next;

            if (d == 0) { //아래로
                cur = map[r+i][c];
                next = map[r+i+1][c];
            } else { //오른쪽으로
                cur = map[r][c+i];
                next = map[r][c+i+1];
            }

            int diff = next - cur;

            //높이가 같으면 그냥 진행
            if (diff == 0) continue;
            //높이 차가 2 이상이면 불가능
            if (Math.abs(diff) > 1) return 0;
            //오르막 -> 다음 칸이 1 더 높음
            if (diff == 1) {
                for (int j = i; j > i - L; j--) {
                    if (j < 0) return 0;
                    if (used[j]) return 0;

                    int height;
                    if (d == 0) height = map[r + j][c];
                    else height = map[r][c + j];

                    if (height != cur) return 0;
                    used[j] = true;
                }
            }

            //내리막 -> 다음 칸이 1 더 낮음
            else if (diff == -1) {
                for (int j = i + 1; j <= i + L; j++) {
                    if (j >= N) return 0;
                    if (used[j]) return 0;

                    int height;
                    if (d == 0) height = map[r + j][c];
                    else height = map[r][c + j];

                    if (height != next) return 0;
                    used[j] = true;
                }
            }
        }

        return 1;
    }

    public static void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }
}