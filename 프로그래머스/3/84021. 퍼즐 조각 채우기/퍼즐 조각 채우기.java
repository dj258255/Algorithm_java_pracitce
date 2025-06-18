import java.util.*;

class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int tN;
    static boolean[][] visited;

    public int solution(int[][] game_board, int[][] table) {
        tN = table.length;
        visited = new boolean[tN][tN];

        // 1. 퍼즐 조각 추출
        List<List<int[]>> tablePieces = new ArrayList<>();
        for (int i = 0; i < tN; i++) {
            for (int j = 0; j < tN; j++) {
                if (table[i][j] == 1 && !visited[i][j]) {
                    tablePieces.add(normalize(count(i, j, table, 1)));
                }
            }
        }

        // 2. 게임 보드 빈 공간 추출
        visited = new boolean[tN][tN];
        int answer = 0;

        for (int i = 0; i < tN; i++) {
            for (int j = 0; j < tN; j++) {
                if (game_board[i][j] == 0 && !visited[i][j]) {
                    List<int[]> space = normalize(count(i, j, game_board, 0));

                    boolean matched = false;
                    for (int k = 0; k < tablePieces.size(); k++) {
                        List<int[]> piece = tablePieces.get(k);

                        for (int r = 0; r < 4; r++) {
                            if (compare(space, piece)) {
                                answer += space.size();
                                tablePieces.remove(k);
                                matched = true;
                                break;
                            }
                            piece = rotate(piece);
                        }

                        if (matched) break;
                    }
                }
            }
        }

        return answer;
    }

    // 덩어리 추출
    public static List<int[]> count(int startR, int startC, int[][] board, int target) {
        List<int[]> result = new ArrayList<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startR, startC});
        visited[startR][startC] = true;

        result.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int r = now[0];
            int c = now[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (!possible(nr, nc)) continue;
                if (visited[nr][nc] || board[nr][nc] != target) continue;

                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc});
                result.add(new int[]{nr - startR, nc - startC});
            }
        }

        return result;
    }

    // 회전
    public static List<int[]> rotate(List<int[]> piece) {
        List<int[]> result = new ArrayList<>();
        for (int[] p : piece) {
            result.add(new int[]{p[1], -p[0]}); // 90도 회전
        }
        return normalize(result);
    }

    //정규화 (기준 좌표로 정렬하고 가장 작은 위치 기준으로 이동)
    public static List<int[]> normalize(List<int[]> shape) {
        shape.sort((a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        int baseR = shape.get(0)[0];
        int baseC = shape.get(0)[1];

        List<int[]> normalized = new ArrayList<>();
        for (int[] s : shape) {
            normalized.add(new int[]{s[0] - baseR, s[1] - baseC});
        }

        return normalized;
    }

    // 모양 비교
    public static boolean compare(List<int[]> a, List<int[]> b) {
        if (a.size() != b.size()) return false;

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] != b.get(i)[0] || a.get(i)[1] != b.get(i)[1]) {
                return false;
            }
        }

        return true;
    }

    public static boolean possible(int r, int c) {
        return r >= 0 && c >= 0 && r < tN && c < tN;
    }
}
