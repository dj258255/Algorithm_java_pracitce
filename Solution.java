import java.util.Scanner;

public class Solution {
    static int answer;
    static int B; //선반 높이
    static int[] H; //직원들의 키

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int tc = scan.nextInt();

        for (int t = 1; t <= tc; t++) {
            int N = scan.nextInt(); // 직원 수
            B = scan.nextInt(); // 선반 높이
            H = new int[N]; // 직원들의 키

            for (int i = 0; i < N; i++) {
                H[i] = scan.nextInt();
            }

            answer = Integer.MAX_VALUE;
            find(0, 0);

            System.out.println("#" + t + " " + (answer - B));
        }
    }

    //백트래킹으로 부분 집합 탐색
    static void find(int index, int sum) {
        if (sum >= B) {
            answer = Math.min(answer, sum);
            return;
        }

        if (index == H.length) { //배열의 끝까지 탐색한 경우
            return;
        }

        // 현재 index 포함하지 않는 경우
        find(index + 1, sum);

        // 현재 index 포함하는 경우
        find(index + 1, sum + H[index]);
    }
}

