import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();

        for (int t = 1; t <= T; t++) {
            int N = scan.nextInt();
            int M = scan.nextInt();//스프레이 세기

            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = scan.nextInt();
                }
            }

            int max = Math.max(plus(map, N, M), minus(map, N, M));
            System.out.println("#" + t + " " + max);
        }
    }

    //+형태
    private static int plus(int[][] map, int N, int M) {
        int maxFlies = 0; //최대 파리 수

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int flies = map[i][j]; //파리수

                for (int k = 1; k < M; k++) {
                    if (i - k >= 0) flies += map[i - k][j]; // 위쪽
                    if (i + k < N) flies += map[i + k][j]; // 아래쪽
                    if (j - k >= 0) flies += map[i][j - k]; // 왼쪽
                    if (j + k < N) flies += map[i][j + k]; // 오른쪽
                }

                maxFlies = Math.max(maxFlies, flies);
            }
        }

        return maxFlies;
    }

    //X형태
    private static int minus(int[][] map, int N, int M) {
        int maxFlies = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int flies = map[i][j];

                for (int k = 1; k < M; k++) {
                    if (i - k >= 0 && j - k >= 0) flies += map[i - k][j - k]; // 좌상단
                    if (i - k >= 0 && j + k < N) flies += map[i - k][j + k]; // 우상단
                    if (i + k < N && j - k >= 0) flies += map[i + k][j - k]; // 좌하단
                    if (i + k < N && j + k < N) flies += map[i + k][j + k]; // 우하단
                }

                maxFlies = Math.max(maxFlies, flies);
            }
        }

        return maxFlies;
    }
}