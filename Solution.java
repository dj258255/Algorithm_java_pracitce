import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = scan.nextInt();
            int M = scan.nextInt();
            int[][] map = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = scan.nextInt();
                }
            }

            int maxFlies = Integer.MIN_VALUE;

            // M x M 크기의 파리채로 파리를 잡는 최대값 계산
            for (int i = 0; i <= N - M; i++) {
                for (int j = 0; j <= N - M; j++) {
                    int currentFlies = 0;

                    // M x M 영역 내 파리 수 합산
                    for (int k = 0; k < M; k++) {
                        for (int l = 0; l < M; l++) {
                            currentFlies += map[i + k][j + l];
                        }
                    }

                    maxFlies = Math.max(maxFlies, currentFlies);
                }
            }

            System.out.println("#" + t + " " + maxFlies);
        }
        scan.close();
    }
}
