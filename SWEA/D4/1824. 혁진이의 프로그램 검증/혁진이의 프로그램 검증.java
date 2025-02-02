import java.util.ArrayDeque;
import java.util.Scanner;

public class Solution {
    static int R, C;
    static char[][] matrix;
    static boolean[][][][] visited; // [x][y][dir][number]
    static int[] dx = {-1, 0, 1, 0}; // 좌, 상, 우, 하
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();

        for (int t = 1; t <= T; t++) {
            R = scan.nextInt();
            C = scan.nextInt();
            matrix = new char[R][C];
            visited = new boolean[C][R][4][16];

            for (int i = 0; i < R; i++) {
                String temp = scan.next();
                for (int j = 0; j < C; j++) {
                    matrix[i][j] = temp.charAt(j);
                }
            }

            boolean result = simulate();
            System.out.println("#" + t + " " + (result ? "YES" : "NO"));
        }
    }

    public static boolean simulate() {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 2, 0}); //처음 상태. 오른쪽 방향.

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curX = current[0];
            int curY = current[1];
            int dir = current[2];
            int number = current[3];

            if (visited[curX][curY][dir][number]) continue; //반뭉했으면 스킵
            visited[curX][curY][dir][number] = true;

            char currentChar = matrix[curY][curX];
            if (currentChar == '@') return true;

            if (currentChar == '?') {
                for (int newDir = 0; newDir < 4; newDir++) {
                    int nextX = (curX + dx[newDir] + C) % C;
                    int nextY = (curY + dy[newDir] + R) % R;
                    queue.add(new int[]{nextX, nextY, newDir, number});
                }
                continue;
            }

            int nextDir = dir;
            int nextNumber = number;

            switch (currentChar) {
                case '<': nextDir = 0; break;
                case '>': nextDir = 2; break;
                case '^': nextDir = 1; break;
                case 'v': nextDir = 3; break;
                case '_': nextDir = (number == 0) ? 2 : 0; break;
                case '|': nextDir = (number == 0) ? 3 : 1; break;
                case '+': nextNumber = (number == 15) ? 0 : number + 1; break;
                case '-': nextNumber = (number == 0) ? 15 : number - 1; break;
                default:
                    if (currentChar >= '0' && currentChar <= '9') {
                        nextNumber = currentChar - '0';
                    }
            }

            int nextX = (curX + dx[nextDir] + C) % C;
            int nextY = (curY + dy[nextDir] + R) % R;
            queue.add(new int[]{nextX, nextY, nextDir, nextNumber});
        }

        return false;
    }
}