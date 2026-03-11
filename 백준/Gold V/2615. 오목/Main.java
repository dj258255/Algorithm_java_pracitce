import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static final int N = 19;
    static int[][] map;
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        map = new int[N][N];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine().trim());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void solve() throws IOException{
        boolean find = false;

        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < N; j++){
                if(map[i][j] != 0){
                    if(check(i,j)){
                        bw.write("" + map[i][j]);
                        bw.newLine();
                        int answerR = i+1;
                        int answerC = j+1;
                        bw.write(answerR + " " + answerC);
                        find = true;
                        return;
                    }
                }
            }
        }

        if(!find){
            bw.write("" + "0");
        }
    }

    public static boolean check(int r, int c) throws IOException{

        // 아래
        boolean possible = false;
        for(int i = 0 ; i < 5; i++){
            if(i==0){
                if(r-1 < N && r-1 >= 0){
                    if(map[r-1][c] == map[r][c]) break;
                }
            }
            int cr = r + i;
            if(cr >= N || cr < 0) break;
            if(map[cr][c] != map[r][c]) break;
            if(i==4){
                possible = true;
                if(cr+1 < N && cr+1 >= 0){
                    if(map[cr+1][c] == map[r][c]) possible = false;
                }
            }
        }
        if(possible) return true;

        // 오른 아래
        possible = false;
        for(int i = 0 ; i < 5; i++){
            if(i==0){
                if(r-1 < N && r-1 >= 0 && c-1 < N && c-1 >=0){
                    if(map[r-1][c-1] == map[r][c]) break;
                }
            }
            int cr = r + i;
            int cc = c + i;
            if(cr >= N || cr < 0 || cc >=N || cc <0) break;
            if(map[cr][cc] != map[r][c]) break;
            if(i==4){
                possible = true;
                if(cr+1 < N && cr+1 >= 0 && cc+1 < N && cc+1 >=0){
                    if(map[cr+1][cc+1] == map[r][c]) possible = false;
                }
            }
        }
        if(possible) return true;

        // 오른위
        possible = false;
        for(int i = 0 ; i < 5; i++){
            if(i==0){
                if(r+1 < N && r+1 >= 0 && c-1 < N && c-1 >=0){
                    if(map[r+1][c-1] == map[r][c]) break;
                }
            }
            int cr = r - i;
            int cc = c + i;
            if(cr >= N || cr < 0 || cc >=N || cc <0) break;
            if(map[cr][cc] != map[r][c]) break;
            if(i==4){
                possible = true;
                if(cr-1 < N && cr-1 >= 0 && cc+1 < N && cc+1 >=0){
                    if(map[cr-1][cc+1] == map[r][c]) possible = false;
                }
            }
        }
        if(possible) return true;

        // 오른
        possible = false;
        for(int i = 0 ; i < 5; i++){
            if(i==0){
                if(c-1 < N && c-1 >=0){
                    if(map[r][c-1] == map[r][c]) break;
                }
            }
            int cc = c + i;
            if(cc >= N || cc < 0) break;
            if(map[r][cc] != map[r][c]) break;
            if(i==4){
                possible = true;
                if(cc+1 < N && cc+1 >= 0){
                    if(map[r][cc+1] == map[r][c]) possible = false;
                }
            }
        }

        return possible;
    }



    public static void output() throws IOException{
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) throws IOException{
        input();
        solve();
        output();
    }
}