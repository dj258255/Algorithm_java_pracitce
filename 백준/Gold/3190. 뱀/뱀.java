import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {0, 1, 0, -1}; //오른쪽 아래 왼쪽 위
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N+1][N+1];

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[r][c] = 1; //사과는 1로 설정
        }

        int L = Integer.parseInt(br.readLine());
        Map<Integer, Character> turnInfo = new HashMap<>(); //key: 몇 초 후 전환, value: 'L' (왼쪽) 또는 'D' (오른쪽)
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);
            turnInfo.put(time, direction);
        }

        ArrayDeque<int[]> snake = new ArrayDeque<>(); //뱀의 위치 머리는 뒤 꼬리는 앞
        snake.offer(new int[]{1, 1}); //1,1시작
        board[1][1] = 2;  //뱀은 2로 설정
        int time = 0; //시간 (정답)
        int currentDirection = 0; //처음 방향 오른쪽

        while (true) {
            time++; //1초 후
            int[] head = snake.peekLast(); // 뱀의 현재 머리 위치
            int nr = head[0] + dr[currentDirection];
            int nc = head[1] + dc[currentDirection];

            //벽에 부딪히거나 뱀의 몸에 부딪힌 경우 종료
            if (nr < 1 || nr > N || nc < 1 || nc > N || board[nr][nc] == 2) {
                break;
            }

            //이동한 칸에 사과가 있다면 (사과 먹음) 꼬리 이동 없이 머리 추가
            if (board[nr][nc] == 1) {
                board[nr][nc] = 2;
                snake.offer(new int[]{nr, nc});
            } else { //사과가 없다면, 머리를 추가하고 꼬리를 제거 (길이 유지)
                board[nr][nc] = 2;
                snake.offer(new int[]{nr, nc});
                int[] tail = snake.poll();
                board[tail[0]][tail[1]] = 0;
            }

            //이번 초가 끝난 후 방향 전환 정보가 있으면 변경
            if (turnInfo.containsKey(time)) {
                char c = turnInfo.get(time);
                if (c == 'L') {
                    currentDirection = (currentDirection + 3) % 4;// 왼쪽으로 90도 회전: currentDirection - 1 (모듈러 연산)
                } else if (c == 'D') {
                    currentDirection = (currentDirection + 1) % 4;// 오른쪽으로 90도 회전: currentDirection + 1 (모듈러 연산)
                }
            }
        }
        System.out.println(time);
    }
}
