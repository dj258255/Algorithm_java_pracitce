import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int N, M;
    static int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} }; //상하좌우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        Queue<int[]> queue = new ArrayDeque<>();

        //토마토 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) { //익은 토마토면 큐에 추가
                    queue.add(new int[]{i, j});
                }
            }
        }

        //토마토 익히기
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                //범위를 벗어나면 무시
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                //아직 익지 않은 토마토인 경우
                if (map[nx][ny] == 0) {
                    map[nx][ny] = map[x][y] + 1; //익은 날짜를 갱신
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        int days = 0;

        //토마토가 모두 익었는지 확인 -> 최대 날짜 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) { //익지 않은 토마토가 있다면
                    System.out.println(-1);
                    return;
                }
                days = Math.max(days, map[i][j]);
            }
        }

        //초기 익은 토마토가 1로 표시되었으므로, 최종 날짜에서 1을 빼줌
        System.out.println(days - 1);
    }
}
