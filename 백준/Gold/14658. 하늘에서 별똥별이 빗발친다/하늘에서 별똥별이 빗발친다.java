//  하늘에서 별똥별이 빗발친다

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;
    static int N,M,L,K;
    static int[][] stars;
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken()); //별똥별 가로
        M = Integer.parseInt(st.nextToken()); //별똥별 세로
        L = Integer.parseInt(st.nextToken()); //트램펄린 한 변의 길이
        K = Integer.parseInt(st.nextToken()); //별똥별의 수

        stars = new int[K][2];
        for(int i = 0 ; i < K; i++){
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Integer.parseInt(st.nextToken());
            stars[i][1] = Integer.parseInt(st.nextToken());
        }

    }

    public static void solve() throws IOException{
        int maxCaught = 0;
        //트램펄린의 왼쪽 아래 모서리를 각 별의 x, y 좌표에 맞춰서 시도
        for(int i = 0; i < K; i++){
            for(int j = 0; j < K; j++){
                int x = stars[i][0]; //트램펄린 왼쪽 x 좌표
                int y = stars[j][1]; //트램펄린 아래 y 좌표

                int caught = countStars(x, y);
                maxCaught = Math.max(maxCaught, caught);
            }
        }
        //최소로 떨어뜨려야 할 별똥별 개수 = 전체 - 최대로 튕겨낸 개수
        sb = new StringBuilder();
        sb.append(K - maxCaught);
    }

    // (x, y)를 왼쪽 아래 모서리로 하는 트램펄린으로 튕겨낼 수 있는 별의 개수
    public static int countStars(int x, int y){
        int count = 0;
        for(int i = 0; i < K; i++){
            int starX = stars[i][0];
            int starY = stars[i][1];

            // 트램펄린 범위: [x, x+L] × [y, y+L]
            if(starX >= x && starX <= x + L && starY >= y && starY <= y + L){
                count++;
            }
        }
        return count;
    }
    public static void output() throws IOException{
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException{
        input();
        solve();
        output();
    }
}