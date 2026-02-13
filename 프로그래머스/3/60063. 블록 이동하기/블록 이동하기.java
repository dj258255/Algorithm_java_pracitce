import java.util.*;

class Solution {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int N;

    public int solution(int[][] board) {
        N = board.length;
        return bfs(board);
    }

    public static int bfs(int[][] board) {
        Set<ArrayList<Integer>> visited = new HashSet<>();
        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{0, 0, 0, 1, 0}); // r1,c1,r2,c2,time
        visited.add(new ArrayList<>(Arrays.asList(0, 0, 0, 1)));

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int r1 = node[0], c1 = node[1];
            int r2 = node[2], c2 = node[3];
            int time = node[4];

            // 종료 체크
            if ((r1 == N - 1 && c1 == N - 1) || (r2 == N - 1 && c2 == N - 1))
                return time;

            // 상하좌우 이동
            for (int d = 0; d < 4; d++) {
                int nr1 = r1 + dr[d], nc1 = c1 + dc[d];
                int nr2 = r2 + dr[d], nc2 = c2 + dc[d];

                if (!possible(nr1, nc1) || !possible(nr2, nc2)) continue;
                if (board[nr1][nc1] == 1 || board[nr2][nc2] == 1) continue;

                ArrayList<Integer> state = new ArrayList<>(Arrays.asList(nr1, nc1, nr2, nc2));
                if (visited.contains(state)) continue;
                visited.add(state);
                queue.add(new int[]{nr1, nc1, nr2, nc2, time + 1});
            }

            // 회전
            if (r1 == r2) { // 가로 상태
                // 위로 회전
                if (possible(r1 - 1, c1) && possible(r2 - 1, c2)
                        && board[r1 - 1][c1] == 0 && board[r2 - 1][c2] == 0) {
                    addIfNew(queue, visited, r1 - 1, c1, r1, c1, time + 1);
                    addIfNew(queue, visited, r2 - 1, c2, r2, c2, time + 1);
                }
                // 아래로 회전
                if (possible(r1 + 1, c1) && possible(r2 + 1, c2)
                        && board[r1 + 1][c1] == 0 && board[r2 + 1][c2] == 0) {
                    addIfNew(queue, visited, r1, c1, r1 + 1, c1, time + 1);
                    addIfNew(queue, visited, r2, c2, r2 + 1, c2, time + 1);
                }
            } else if (c1 == c2) { // 세로 상태
                // 왼쪽 회전
                if (possible(r1, c1 - 1) && possible(r2, c2 - 1)
                        && board[r1][c1 - 1] == 0 && board[r2][c2 - 1] == 0) {
                    addIfNew(queue, visited, r1, c1 - 1, r1, c1, time + 1);
                    addIfNew(queue, visited, r2, c2 - 1, r2, c2, time + 1);
                }
                // 오른쪽 회전
                if (possible(r1, c1 + 1) && possible(r2, c2 + 1)
                        && board[r1][c1 + 1] == 0 && board[r2][c2 + 1] == 0) {
                    addIfNew(queue, visited, r1, c1, r1, c1 + 1, time + 1);
                    addIfNew(queue, visited, r2, c2, r2, c2 + 1, time + 1);
                }
            }
        }
        return 0;
    }

    static void addIfNew(Queue<int[]> queue, Set<ArrayList<Integer>> visited,
                         int r1, int c1, int r2, int c2, int time) {
        ArrayList<Integer> state = new ArrayList<>(Arrays.asList(r1, c1, r2, c2));
        if (!visited.contains(state)) {
            visited.add(state);
            queue.add(new int[]{r1, c1, r2, c2, time});
        }
    }

    public static boolean possible(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}