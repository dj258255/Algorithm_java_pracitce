import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map; //1은 벽
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        //가로 방향으로 시작
        find(1, 0, 0);
        System.out.println(count);
    }
    
    //0가로,1대각선,2세로
    public static void find(int x, int y, int d) {
        if (x == N - 1 && y == N - 1) {
            count++;
            return;
        }
        
        
        //가로방향일 때
        if (d == 0) {
            //가로이동
            if (canMove(x, y, 0)) {
                find(x + 1, y, 0);
            }
            //대각선 이동
            if (canMove(x, y, 1)) {
                find(x + 1, y + 1, 1);
            }
        }
        //대각선방향 일 때
        else if (d == 1) {
            //가로이동
            if (canMove(x, y, 0)) {
                find(x + 1, y, 0);
            }
            //대각선이동
            if (canMove(x, y, 1)) {
                find(x + 1, y + 1, 1);
            }
            //세로이동
            if (canMove(x, y, 2)) {
                find(x, y + 1, 2);
            }
        }
        //세로방향일 때
        else if (d == 2) {
            //세로이동
            if (canMove(x, y, 2)) {
                find(x, y + 1, 2);
            }
            //대각선 이동
            if (canMove(x, y, 1)) {
                find(x + 1, y + 1, 1);
            }
        }
    }
    
    public static boolean canMove(int x, int y, int moveType) {
        //가로 이동인 경우 오른쪽 칸 체크
        if (moveType == 0) {
            int nx = x + 1;
            if (nx >= N || map[y][nx] == 1)
                return false;
        }
        //세로 이동인 경우 아래 칸 체크
        else if (moveType == 2) {
            int ny = y + 1;
            if (ny >= N || map[ny][x] == 1)
                return false;
        }
        //대각선 이동인 경우 오른쪽, 아래, 오른쪽 아래 칸 모두 체크
        else if (moveType == 1) {
            int nx = x + 1;
            int ny = y + 1;
            if (nx >= N || ny >= N || map[y][nx] == 1 || map[ny][x] == 1 || map[ny][nx] == 1)
                return false;
        }
        return true;
    }
}