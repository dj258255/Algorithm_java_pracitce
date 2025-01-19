import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for (int T = 1; T <= 10; T++) {
            int t = scan.nextInt();
            char[][] map = new char[100][100];

            // 2차원 배열 입력
            for (int i = 0; i < 100; i++) {
                String temp = scan.next();
                for (int j = 0; j < 100; j++) {
                    map[i][j] = temp.charAt(j);
                }
            }

            int max = 0;

            // 행 방향 회문 확인
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    for (int k = 1; j + k <= 100; k++) {
                        StringBuilder str = new StringBuilder();
                        for (int l = 0; l < k; l++) {
                            str.append(map[i][j + l]);
                        }
                        if (str.toString().equals(str.reverse().toString())) {
                            max = Math.max(max, k);
                        }
                    }
                }
            }

            // 열 방향 회문 확인
            for (int j = 0; j < 100; j++) {
                for (int i = 0; i < 100; i++) {
                    for (int k = 1; i + k <= 100; k++) {
                        StringBuilder str = new StringBuilder();
                        for (int l = 0; l < k; l++) {
                            str.append(map[i + l][j]);
                        }
                        if (str.toString().equals(str.reverse().toString())) {
                            max = Math.max(max, k);
                        }
                    }
                }
            }

            System.out.println("#" + t + " " + max);
        }
    }
}
