import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = scan.nextInt();
            int[][] arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = scan.nextInt();
                }
            }

            int[][] rotated90 = rotate90(arr, N);
            int[][] rotated180 = rotate90(rotated90, N);
            int[][] rotated270 = rotate90(rotated180, N);

            System.out.println("#" + t);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(rotated90[i][j]);
                }
                System.out.print(" ");
                for (int j = 0; j < N; j++) {
                    System.out.print(rotated180[i][j]);
                }
                System.out.print(" ");
                for (int j = 0; j < N; j++) {
                    System.out.print(rotated270[i][j]);
                }
                System.out.println();
            }
        }
    }

    private static int[][] rotate90(int[][] arr, int N) {
        int[][] rotated = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rotated[j][N - 1 - i] = arr[i][j];
            }
        }
        return rotated;
    }
}
