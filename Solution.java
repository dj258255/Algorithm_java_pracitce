import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for(int t = 1; t <= T; t++) {

            int N = scan.nextInt();
            int M = scan.nextInt();

            int[] A = new int[N];
            int[] B = new int[M];

            for(int i = 0; i < N; i++) {
                A[i] = scan.nextInt();
            }
            for(int i = 0; i < M; i++) {
                B[i] = scan.nextInt();
            }
            int[] longer = N >=M ? A : B;
            int[] shorter = N < M ? A : B;

            int max = Integer.MIN_VALUE;

            for(int i = 0; i <= longer.length - shorter.length; i++){
                int answer =0;
                for(int j = 0; j < shorter.length; j++) {
                    answer += shorter[j] * longer[i+j];
                }
                max = Integer.max(answer, max);
            }

            System.out.println("#" + t + " " + max);

        }

    }
}