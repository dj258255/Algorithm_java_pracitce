import java.util.*;
import java.io.*;

class Solution {
    static char[][] map;

    public int solution(String[] board) {
        map = new char[3][3];
        int oCount = 0, xCount = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map[i][j] = board[i].charAt(j);
                if (map[i][j] == 'O') oCount++;
                if (map[i][j] == 'X') xCount++;
            }
        }

        // 1. O가 선공이므로 O개수 == X개수 또는 O개수 == X개수 + 1
        if (oCount < xCount || oCount > xCount + 1) return 0;

        boolean oWin = isWin('O');
        boolean xWin = isWin('X');

        // 2. 둘 다 이길 수는 없음
        if (oWin && xWin) return 0;

        // 3. O가 이겼으면 O가 마지막에 둔 것이므로 O개수 == X개수 + 1
        if (oWin && oCount != xCount + 1) return 0;

        // 4. X가 이겼으면 X가 마지막에 둔 것이므로 O개수 == X개수
        if (xWin && oCount != xCount) return 0;

        return 1;
    }

    public static boolean isWin(char target) {
        // 가로 3줄
        for (int i = 0; i < 3; i++) {
            if (map[i][0] == target && map[i][1] == target && map[i][2] == target)
                return true;
        }
        // 세로 3줄
        for (int j = 0; j < 3; j++) {
            if (map[0][j] == target && map[1][j] == target && map[2][j] == target)
                return true;
        }
        // 대각선 2줄
        if (map[0][0] == target && map[1][1] == target && map[2][2] == target)
            return true;
        if (map[0][2] == target && map[1][1] == target && map[2][0] == target)
            return true;

        return false;
    }
}