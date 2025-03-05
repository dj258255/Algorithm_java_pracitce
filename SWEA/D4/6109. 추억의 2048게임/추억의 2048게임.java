import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int map[][];
    static int dir[][] = { {1,0},{-1,0},{0,1},{0,-1} };
    static int N;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            sb.append('#').append(tc).append(' ').append('\n');
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            String c = st.nextToken();
            map = new int[N][N];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if(c.equals("up")) {
                for(int i=0; i<N;i++) move(0, i, 0);
            } else if(c.equals("down")) {
                for(int i=0; i<N;i++) move(N-1, i, 1);
            } else if(c.equals("left")) {
                for(int i=0; i<N; i++) move(i, 0, 2);
            } else if(c.equals("right")) {
                for(int i=0; i<N; i++) move(i, N-1, 3);
            }

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    sb.append(map[i][j]).append(' ');
                }
                sb.append('\n');
            }

            System.out.print(sb);
            sb.delete(0, sb.length());
        }

    }

    private static void move(int mi, int mj, int d) {
        int cui = mi;
        int cuj = mj;
        int ni = mi;
        int nj = mj;
        for(int i=0; i<N-1; i++) {
            ni = ni + dir[d][0];
            nj = nj + dir[d][1];

            if(map[cui][cuj]!=0) {
                if(map[ni][nj]!=0) {
                    if(map[cui][cuj]==map[ni][nj]) {
                        map[cui][cuj] *= 2;
                        map[ni][nj] = 0;
                        if(ni != cui || nj != cuj) {
                            cui += dir[d][0];
                            cuj += dir[d][1];
                        }
                    } else {
                        cui += dir[d][0];
                        cuj += dir[d][1];
                        if(ni != cui || nj != cuj) {
                            map[cui][cuj] = map[ni][nj];
                            map[ni][nj] = 0;
                        }
                    }
                }
            } else {
                if(map[ni][nj]!=0) {
                    map[cui][cuj]=map[ni][nj];
                    map[ni][nj]=0;
                }
            }
        }
    }

}