import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Solution {
    static int T, N;
    static int[][] grid;
    static ArrayList<int[]> cores; //내부 코어들의 좌표 (y, x)
    static int maxConnected; //연결된 코어의 최대 개수
    static int minWireLength; //최대 연결 시 최소 전선 길이 합
    static int[] dy = {-1, 1, 0, 0}; //상,하,좌,우 방향 (y, x)
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            grid = new int[N][N];
            cores = new ArrayList<>();

            for (int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++){
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    if (grid[i][j] == 1) { //만약에 코어면
                        if (i == 0 || i == N - 1 || j == 0 || j == N - 1) continue; //만약에 가장자리에 저장 되었다면 스킵
                        cores.add(new int[]{i, j}); //코어들 저장
                    }
                }
            }

            maxConnected = 0; //최대 연결
            minWireLength = Integer.MAX_VALUE;
            dfs(0, 0, 0);

            System.out.println("#" + tc + " " + minWireLength);
        }
    }


    public static void dfs(int idx, int connected, int wireLength) {
        if (idx == cores.size()) { //모든 코어를 다 처리했을 때
            if (connected > maxConnected) { //현재 연결된 코어가 더 클경우
                maxConnected = connected; //갱신
                minWireLength = wireLength; //갱신
            } else if (connected == maxConnected) { //만약 똑같을 경우
                minWireLength = Math.min(minWireLength, wireLength); //갱신
            }
            return;
        }

        // 가지치기: 남은 코어 모두를 연결해도 최대보다 못하면 탐색 중단
        if (cores.size() - idx + connected < maxConnected) return;

        int y = cores.get(idx)[0];
        int x = cores.get(idx)[1];

        for (int d = 0; d < 4; d++) {
            int len = canConnect(y, x, d);
            if (len != -1) {  //-1이 아닐 때 -> 연결 가능한 경우
                markPath(y, x, d, 2); //전선을 설치 (경로를 2로 표시)
                dfs(idx + 1, connected + 1, wireLength + len);
                markPath(y, x, d, 0); //백트래킹: 설치했던 전선 제거
            }
        }

        dfs(idx + 1, connected, wireLength); //해당 코어를 연결하지 않는 경우도 고려 (연결 불가능하거나 다른 선택을 위해)
    }

    public static int canConnect(int y, int x, int d) { //연결 가능한지 확인. 가능하면 전선 길이를 반환, 불가능하면 -1 반환
        int ny = y, nx = x;
        int count = 0;
        while (true) {
            ny += dy[d];
            nx += dx[d];

            if (ny < 0 || ny >= N || nx < 0 || nx >= N) return count; //전원 흐르는 가장자리 도착하면 성공
            if (grid[ny][nx] != 0) return -1; //만약 0이 아니면 불가능
            count++; //전선 길이 반환
        }
    }


    public static void markPath(int y, int x, int d, int value) {
        int ny = y, nx = x; //전선 설치 value가 2 = 설치 , 0 = 노설치
        while (true) {
            ny += dy[d];
            nx += dx[d];
            if (ny < 0 || ny >= N || nx < 0 || nx >= N) break; //가장자리 도착하면 성공
            grid[ny][nx] = value;
        }
    }
}
