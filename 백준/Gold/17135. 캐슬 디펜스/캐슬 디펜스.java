import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[][] map;
    public static int N, M, D;
    public static int answer = 0;
    public static int[] character; //궁수가 위치할 열 번호 3개

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken()); //궁수의 공격 거리 제한
        map = new int[N][M];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        character = new int[3];
        //모든 궁수 배치를 선택하여 게임 시뮬레이션
        chooseArchers(0, 0);
        System.out.println(answer);
    }

    //궁수 배치
    public static void chooseArchers(int count, int start) {
        if (count == 3) {
            answer = Math.max(answer, StartGame());
            return;
        }

        for (int i = start; i < M; i++) {
            character[count] = i;
            //백트래킹
            chooseArchers(count + 1, i + 1);
        }

    }

    //게임시작
    public static int StartGame() {
        int killCount = 0;
        //임시맵으로 게임 진행
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp[i] = map[i].clone();
        }

        //턴 진행
        for (int turn = 0; turn < N; turn++) {
            //각 궁수가 공격할 적의 좌표를 저장 (중복 제거 후 한 번에 제거)
            List<int[]> targets = new ArrayList<>();
            for (int archer : character) {
                int[] target = findTarget(temp, archer);
                if (target != null) {
                    targets.add(target);
                }
            }

            //중복된 적은 한 번만 제거
            Set<String> removed = new HashSet<>();
            for (int[] t : targets) {
                int r = t[0], c = t[1];
                String key = r + "," + c;
                if (!removed.contains(key)) {
                    if (temp[r][c] == 1) {
                        killCount++;
                        temp[r][c] = 0;
                    }
                    removed.add(key);
                }
            }

            //적 이동
            temp = moveMonster(temp);
        }

        return killCount;
    }

    //궁수가 공격할 적 찾음
    public static int[] findTarget(int[][] board, int archerCol) {
        int minDistance = Integer.MAX_VALUE;
        int targetRow = -1;
        int targetCol = -1;

        //맵 전체 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) {
                    int distance = Math.abs(N - i) + Math.abs(archerCol - j);
                    if (distance <= D) {
                        //거리가 짧은 적 우선, 같다면 왼쪽에 있는 적 선택
                        if (distance < minDistance) {
                            minDistance = distance;
                            targetRow = i;
                            targetCol = j;
                        } else if (distance == minDistance && j < targetCol) {
                            targetRow = i;
                            targetCol = j;
                        }
                    }
                }
            }
        }
        if (targetRow == -1) return null;
        return new int[]{targetRow, targetCol};
    }

    //적이동
    public static int[][] moveMonster(int[][] board) {
        int[][] newBoard = new int[N][M];
        //마지막 행은 제거되므로 N-2행부터 아래로 이동
        for (int i = N - 2; i >= 0; i--) {
            newBoard[i + 1] = board[i].clone();
        }
        //맨 위 행은 빈 칸으로 채움
        Arrays.fill(newBoard[0], 0);
        return newBoard;
    }

}
