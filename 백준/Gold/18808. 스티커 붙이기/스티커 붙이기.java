import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N;
    static int M;
    static int K;

    static int[][] notebook;

    public static boolean canAttach(int[][] sticker, int startR, int startC) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (sticker[i][j] == 1) {
                    int nr = startR + i;
                    int nc = startC + j;

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) return false;
                    if (notebook[nr][nc] == 1) return false;
                }
            }
        }
        return true;
    }

    public static void attach(int[][] sticker, int startR, int startC) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (sticker[i][j] == 1) {
                    notebook[startR + i][startC + j] = 1;
                }
            }
        }
    }

    public static int[][] rotate90(int[][] sticker) {
        int r = sticker.length;
        int c = sticker[0].length;
        int[][] rotated = new int[c][r];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                rotated[j][r - 1 - i] = sticker[i][j];
            }
        }
        return rotated;
    }

    public static void solve(int[][] sticker) {
        // 4번 회전 시도 (0도, 90도, 180도, 270도)
        for (int rot = 0; rot < 4; rot++) {
            // 모든 위치 완탐
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (canAttach(sticker, i, j)) {
                        attach(sticker, i, j);
                        return;
                    }
                }
            }
            sticker = rotate90(sticker);
        }
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        notebook = new int[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[r][c];

            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int k = 0; k < c; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            solve(sticker);
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (notebook[i][j] == 1) count++;
            }
        }
        System.out.println(count);
    }
}