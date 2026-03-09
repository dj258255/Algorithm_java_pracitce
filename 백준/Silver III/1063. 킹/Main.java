import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int[] dr = {1,-1,0,0,1,-1,1,-1}; //상 하 좌 우 왼상 왼하 우상 우하
    static int[] dc = {0,0,-1,1,-1,-1,1,1};

    public static void main(String[] args) throws IOException{
        input();
        solve();
        output();
    }


    static int N;
    static String[] kingMove;
    static String kingPos;
    static String rockPos;

    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        kingPos = st.nextToken();
        rockPos = st.nextToken();
        N = Integer.parseInt(st.nextToken());

        kingMove = new String[N];
        for(int i =0 ; i < N; i++){
            st = new StringTokenizer(br.readLine().trim());
            kingMove[i] = st.nextToken();
        }
    }


    public static void solve() throws IOException{
        for(int i = 0 ; i < N; i++){
            String move = kingMove[i];
            int cur = match(move);

            int r = Character.valueOf(kingPos.charAt(1)) - '0';
            char c = kingPos.charAt(0);

            int nr = r + dr[cur];
            char nc = (char) (c + dc[cur]);

            int rr = rockPos.charAt(1) -'0';
            char rc = rockPos.charAt(0);
            //킹 다음 위치가 범위 밖 나갔는지 확인
            if(nc > 'H' || nr > 8 || nr < 1 || nc < 'A') continue;
            //킹의 위치랑 돌의 위치가 같을 때
            if(nr == rr && nc == rc){
                int rnr = rr + dr[cur];
                char rnc = (char) (rc + dc[cur]);
                //돌 움직이고 돌이 범위 밖 나갔는지 확인
                if(rnc > 'H' || rnr > 8 || rnc < 'A' || rnr < 1) continue;
                rockPos = Character.toString(rnc) + Integer.toString(rnr);
            }

            kingPos = Character.toString(nc) + Integer.toString(nr);
        }

        bw.write("" + kingPos);
        bw.newLine();
        bw.write(rockPos);
    }


    public static int match(String name) throws IOException{
        if(name.equals("R")){
            return 3;
        } else if(name.equals("L")){
            return 2;
        } else if(name.equals("B")){
            return 1;
        } else if(name.equals("T")){
            return 0;
        } else if(name.equals("RT")){
            return 6;
        } else if(name.equals("LT")){
            return 4;
        } else if(name.equals("RB")){
            return 7;
        } else if(name.equals("LB")){
            return 5;
        }

        return 0;

    }


    
    public static void output() throws IOException{
        bw.flush();
        bw.close();
    }
}