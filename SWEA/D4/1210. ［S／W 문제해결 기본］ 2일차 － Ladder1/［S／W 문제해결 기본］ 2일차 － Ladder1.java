import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for (int t = 1; t <= 10; t++) {
            int T = scan.nextInt();

            int[][] map = new int[100][100];

            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    map[i][j] = scan.nextInt();
                }
            }

            int answer = 0; //시작점
            boolean found = false;

            while (answer < 100 && !found) {
                if (map[0][answer] == 0) {
                    answer++;
                    continue;
                }

                // 현재 위치 초기화
                int i = 0;
                int j = answer;

                // 아래로 이동하면서 체크
                while (i < 100) {
                    // 오른쪽으로 이동
                    if (j < 99 && map[i][j + 1] == 1) {
                        while (j < 99 && map[i][j + 1] == 1) {
                            j++;
                        }
                        i++;  // 가로 이동이 끝난 후 한 칸 아래로
                    }
                    // 왼쪽으로 이동
                    else if (j > 0 && map[i][j - 1] == 1) {
                        while (j > 0 && map[i][j - 1] == 1) {
                            j--;
                        }
                        i++;  // 가로 이동이 끝난 후 한 칸 아래로
                    }
                    // 가로선이 없으면 아래로 이동
                    else {
                        i++;
                    }

                    // 마지막 줄 도착
                    if (i == 100) {
                        if (map[99][j] == 2) {
                            found = true;
                        }
                        break;
                    }
                }

                if (!found) {
                    answer++;
                }
            }

            System.out.println("#" + T + " " + answer);
        }
    }
}