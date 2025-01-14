import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt(); // 테스트 케이스 수

        for (int T = 1; T <= t; T++) {
            long N = scan.nextInt(); // 타일 수
            long A = scan.nextInt(); // 가중치 A
            long B = scan.nextInt(); // 가중치 B

            long minCost = Integer.MAX_VALUE; // 최소 비용 초기화

            // 가능한 R, C 조합 탐색
            for (int R = 1; R <= N; R++) {
                for (int C = 1; C <= N / R; C++) { // R * C <= N을 만족해야 함
                    long cost = A * Math.abs(R - C) + B * (N - R * C);
                    minCost = Math.min(minCost, cost); // 최소 비용 갱신
                }
            }

            // 결과 출력
            System.out.println("#" + T + " " + minCost);
        }
    }
}
