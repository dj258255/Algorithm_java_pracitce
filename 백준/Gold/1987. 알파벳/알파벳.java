import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{
    static int R;
    static int C;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] board;
    static int count;
    static int answer;
    static ArrayList<Character> list = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        board = new char[R][C];
        
        for(int i = 0 ; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();
            for(int j = 0 ; j < C; j++) {
                board[i][j] = temp.charAt(j);
            }
        }
        
        answer = Integer.MIN_VALUE;
        count = 0;
        dfs(0,0);
        
        
        System.out.println(answer);
    }
    
    public static void dfs(int x, int y) {
        list.add(board[y][x]); // 방문 처리
        count++;

        answer = Math.max(answer, count);

        for(int d = 0 ; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || ny < 0 || ny >= R || nx >= C) continue; // 범위 체크
            if(list.contains(board[ny][nx])) continue; // 중복 방문 체크

            dfs(nx, ny);
        }
        
        // 백트래킹: 현재 칸 방문 해제
        list.remove(list.size() - 1);
        count--;
    }

    
//    public static void dfs(int x, int y) {
//        list.add(board[y][x]); //방문처리
//        count++;
//        
//        for(int d = 0 ; d < 4; d++) {
//            int nx = x + dx[d];
//            int ny = y + dy[d];
//            
//            if(nx < 0 || ny < 0 || ny >= R || nx >= C) continue; //범위 밖으로 가면 컨티뉴
//            if(list.contains(board[ny][nx])) continue; //중복이면 컨티뉴
//            
//            dfs(nx,ny);
//            
//        }
//        
//        
//        answer = Math.max(answer, count);
//        return;
//    }
}