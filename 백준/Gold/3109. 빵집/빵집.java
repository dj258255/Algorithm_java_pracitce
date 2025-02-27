import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
    static boolean[][] used; //이미 다른 파이프라인이 지나간 칸 표시
    static int result;
    static int[] dy = {-1, 0, 1}; //오른쪽 위 오른쪾 오른쪽 아래
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        
        used = new boolean[R][C];
        result = 0;
        
        //0번째 열의 모든 행에서 출발해 볼 수 있다.
        for (int r = 0; r < R; r++) {
            //경로를 찾을 수 있으면 개수 증가
            if (dfs(r, 0)) {
                result++;
            }
        }
        
        System.out.println(result);
    }
    
    private static boolean dfs(int r, int c) {
        used[r][c] = true;

        if (c == C - 1) {
            return true;
        }
        //오른쪽위 오른쪽 오른쪽아래
        for (int i = 0; i < 3; i++) {
            int nr = r + dy[i];
            int nc = c + 1;
            
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                continue;
            }
            
            //건물이 있거나 이미 사용된 칸이면 패스
            if (map[nr][nc] == 'x' || used[nr][nc]) {
                continue;
            }
            
            //다음 칸에 경로를 이어갈 수 있으면
            if (dfs(nr, nc)) {
                return true;
            }
        }
        
        //세 방향 모두 실패하면 경로 완성 불가
        return false;
    }
}
