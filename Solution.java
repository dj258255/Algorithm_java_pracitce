import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        for (int T = 1; T <= 10; T++) {
            int N = scan.nextInt(); // 찾아야 할 회문의 길이
            char[][] map = new char[8][8];

            for (int i = 0; i < 8; i++) {
                String temp = scan.next();
                for (int j = 0; j < 8; j++) {
                    map[i][j] = temp.charAt(j);
                }
            }

            int answer = 0;

            // 가로 검사
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j <= 8 - N; j++) { // 유효 범위: j + N - 1 < 8
                    StringBuilder str = new StringBuilder();
                    for (int k = 0; k < N; k++) {
                        str.append(map[i][j + k]);
                    }
                    if (str.toString().equals(str.reverse().toString())) {
                        answer++;
                    }
                }
            }

            // 세로 검사
            for (int j = 0; j < 8; j++) {
                for (int i = 0; i <= 8 - N; i++) { // 유효 범위: i + N - 1 < 8
                    StringBuilder str = new StringBuilder();
                    for (int k = 0; k < N; k++) {
                        str.append(map[i + k][j]);
                    }
                    if (str.toString().equals(str.reverse().toString())) {
                        answer++;
                    }
                }
            }
            System.out.println("#" + T + " " + answer);
        }
    }
}