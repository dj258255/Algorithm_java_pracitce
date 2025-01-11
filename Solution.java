import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();

        for (int t = 1; t <= T; t++) {
            int N = scan.nextInt();
            System.out.println("#" + t);

            int[][] pascal = new int[N][N];

            // 파스칼의 삼각형 생성
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i) {
                        pascal[i][j] = 1; // 각 행의 첫 번째와 마지막 숫자는 1
                    } else {
                        pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
                    }
                }
            }

            // 파스칼의 삼각형 출력
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= i; j++) {
                    System.out.print(pascal[i][j]);
                    if (j < i) {
                        System.out.print(" "); // 숫자들 사이에 빈 칸 추가
                    }
                }
                System.out.println(); // 다음 줄로 이동
            }
        }
    }
}
