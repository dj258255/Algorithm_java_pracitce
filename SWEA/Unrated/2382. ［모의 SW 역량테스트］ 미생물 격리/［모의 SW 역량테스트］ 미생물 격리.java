import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    // 세로위치 가로위치 미생물 수 이동방향
    static int N, M, K;
    static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
    static int[] dc = { 0, 0, -1, 1 };

    public static class Group {
        int r, c, count, dir;

        public Group(int r, int c, int count, int dir) {
            this.r = r;
            this.c = c;
            this.count = count;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            sb.setLength(0);
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 셀 크기
            M = Integer.parseInt(st.nextToken()); // M시간 동안
            K = Integer.parseInt(st.nextToken()); // 곰팡이 개수

            // PQ를 K 크기로 미리 생성
            Queue<Group> groups = new PriorityQueue<>(K, (g1, g2) -> g2.count - g1.count);

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1; // 보정
                groups.add(new Group(r, c, count, dir));
            }

            Group[][] board = new Group[N][N];
            // 이번 턴에 실제로 곰팡이가 들어온 셀만 기록할 리스트
            List<int[]> touched = new ArrayList<>();

            int time = M;
            while (time-- > 0) {
                touched.clear();  // 이전 턴 기록 초기화

                // 1) 모든 그룹을 꺼내서 이동시키며 board에 합치기
                while (!groups.isEmpty()) {
                    Group temp = groups.poll();
                    int dir = temp.dir;
                    int nr = temp.r + dr[dir];
                    int nc = temp.c + dc[dir];
                    int count = temp.count;

                    // 맵을 벗어나면 스킵
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                        continue;
                    // 빨간 구역에 닿으면 절반으로, 방향 반전
                    if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
                        count /= 2;
                        switch (dir) {
                            case 0: dir = 1; break;
                            case 1: dir = 0; break;
                            case 2: dir = 3; break;
                            case 3: dir = 2; break;
                        }
                    }

                    // board 셀에 합치기
                    if (board[nr][nc] == null) {
                        board[nr][nc] = new Group(nr, nc, count, dir);
                        touched.add(new int[] { nr, nc });  // 첫 방문 기록
                    } else {
                        Group exist = board[nr][nc];
                        // 기존 개수와 합치기
                        exist.count += count;
                        // 더 큰 개수에 따라 방향 결정
                        if (count > exist.count - count) {
                            exist.dir = dir;
                        }
                    }
                }

                // 2) 실제로 곰팡이가 들어온 셀만 다시 PQ에 담고 board 초기화
                for (int[] pos : touched) {
                    Group g = board[pos[0]][pos[1]];
                    groups.add(g);
                    board[pos[0]][pos[1]] = null;
                }
            }

            // 최종 남은 미생물 수 합산
            int answer = 0;
            while (!groups.isEmpty()) {
                answer += groups.poll().count;
            }

            System.out.println(sb.append("#").append(tc).append(" ").append(answer));
        }
    }
}
