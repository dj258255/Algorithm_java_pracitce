import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    static char[][] map;
    static char[][] GameBoard;
    static int N;
    static int answer;
    static int count;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        //지뢰: '*'
        //지뢰 없는 칸: '.'
        //클릭한 안전 칸: 숫자(0~8)
        //최소 클릭 횟수: 0번 인접 셀 영역은 한 번에 드러나고, 나머지 안전 칸은 각각 클릭
        
        int Tc = Integer.parseInt(st.nextToken());
        
        for (int t = 1; t <= Tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new char[N][N];
            
            for (int i = 0; i < N; i++) {
                String temp = br.readLine().trim();
                for (int j = 0; j < N; j++) {
                    map[i][j] = temp.charAt(j);
                }
            }
            
            //게임판 초기화 및 클릭 횟수 초기화
            createGame();
            count = 0;
            
            //근처 지뢰가 없는 칸 우선적으로 열어버리기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (GameBoard[i][j] == '.' && find(j, i) == 0) {
                        dfs(j, i);
                        count++; // 한 영역당 클릭 1회 추가
                    }
                }
            }
            
            //아직 클릭되지 않은 셀들 각각 클릭 처리
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (GameBoard[i][j] == '.') {
                        count++;
                    }
                }
            }
            
            answer = count;
            System.out.println("#" + t + " " + answer);
        }
    }
    
    //클릭 , 0이면 확장
    public static void dfs(int x, int y) {
        int number = find(x, y); //주변 지뢰 개수 확인
        GameBoard[y][x] = Character.forDigit(number, 10);
        
        if (number == 0) {
            for (int d = 0; d < 8; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (GameBoard[ny][nx] == '.') {
                    dfs(nx, ny);
                }
            }
        }
    }
    
    //주변 지뢰 탐색
    public static int find(int x, int y) {
        int cnt = 0;
        for (int d = 0; d < 8; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

            if (map[ny][nx] == '*') {
                cnt++;
            }
        }
        return cnt;
    }
    

    public static void createGame() {
        GameBoard = new char[N][N];
        for (int i = 0; i < N; i++) {
            GameBoard[i] = map[i].clone();
        }
    }
}
