import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_DIST = 20 * 50;   // 한 박스(20병)로 최대 이동 거리
    static final int CELL_SIZE = MAX_DIST; // 그리드 셀 크기

    public static void main(String[] args) throws Exception {
        FastReader in = new FastReader();
        StringBuilder sb = new StringBuilder();
        int T = in.nextInt();

        while (T-- > 0) {
            int storeCount = in.nextInt();
            int total = storeCount + 2;

            // 좌표 분리 저장
            int[] x = new int[total], y = new int[total];
            for (int i = 0; i < total; i++) {
                x[i] = in.nextInt();
                y[i] = in.nextInt();
            }

            // 공간 해싱: (cellX,cellY) → 인덱스 리스트
            Map<Long, List<Integer>> grid = new HashMap<>();
            for (int i = 0; i < total; i++) {
                long key = keyOf(x[i], y[i]);
                grid.computeIfAbsent(key, k -> new ArrayList<>()).add(i);
            }

            // BFS 준비
            boolean[] visited = new boolean[total];
            int[] queue = new int[total];
            int qh = 0, qt = 0;

            // 집(0)에서 출발
            visited[0] = true;
            queue[qt++] = 0;

            boolean canReach = false;
            while (qh < qt) {
                int u = queue[qh++];
                if (u == total - 1) {  // 페스티벌 도달!
                    canReach = true;
                    break;
                }

                int cx = x[u], cy = y[u];
                int cellX = cx / CELL_SIZE;
                int cellY = cy / CELL_SIZE;

                // 주변 3×3 셀만 살핀다
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        long neighKey = (((long)(cellX + dx)) << 32)
                                      | ((cellY + dy) & 0xffffffffL);
                        List<Integer> bucket = grid.get(neighKey);
                        if (bucket == null) continue;

                        // 방문 가능한 편의점만 골라서 큐에 추가하고 버킷에서 제거
                        Iterator<Integer> it = bucket.iterator();
                        while (it.hasNext()) {
                            int v = it.next();
                            if (!visited[v]
                                && Math.abs(cx - x[v]) + Math.abs(cy - y[v]) <= MAX_DIST) {
                                visited[v] = true;
                                queue[qt++] = v;
                                it.remove();
                            }
                        }
                    }
                }
            }

            sb.append(canReach ? "happy\n" : "sad\n");
        }

        System.out.print(sb);
    }

    // 그리드 셀 키 계산
    static long keyOf(int xi, int yi) {
        int cx = xi / CELL_SIZE;
        int cy = yi / CELL_SIZE;
        return (((long)cx) << 32) | (cy & 0xffffffffL);
    }

    // 빠른 입력 파서
    static class FastReader {
        static final int BS = 1<<16;
        final char[] buf = new char[BS];
        int idx = 0, size = 0;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int nextInt() throws IOException {
            int c, neg = 0;
            while ((c = read()) <= ' ') {
                if (c == -1) return -1;
            }
            if (c == '-') {
                neg = 1;
                c = read();
            }
            int x = c - '0';
            while ((c = read()) >= '0' && c <= '9') {
                x = x * 10 + (c - '0');
            }
            return neg == 1 ? -x : x;
        }

        private int read() throws IOException {
            if (idx >= size) {
                size = in.read(buf);
                idx = 0;
                if (size == -1) return -1;
            }
            return buf[idx++];
        }
    }
}
