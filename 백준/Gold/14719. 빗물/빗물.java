//  빗물

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static int H,W;
    static int[] heights , water;
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        heights = new int[W];
        water = new int[W];

        for(int i = 0 ; i < W; i++){
            heights[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException{
        input();
        solve();
        output();
    }


    public static void output() throws IOException{
        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int count;
    public static void solve() throws IOException{
        boolean waterPossible;
        count =0;
        for(int i = 0 ; i < W; i++){ //가로
            for(int j = H-1 ; j > 0; j--){ //세로
                waterPossible = possible(i,j);
                if(waterPossible){ // 만약에 닿으면
                    count += j+1 - heights[i]; //N+1 매칭되게
                    break;
                }
            }
        }
    }

    //양 옆 확인
    public static boolean possible(int x , int y){
        // 현재 위치에 블록이 있으면 물을 고일 수 없음
        if(heights[x] > y) return false;

        boolean left = false; //왼쪽 부딪히나?
        boolean right =false; //오른족 부딪히나?

        //왼쪽 확인
        for(int i = x; i >= 0; i--){
            if(i==x) continue; //똑같은 위치는 스킵

            //만약 현재 높이보다 높거나 같다면
            if(heights[i] > y){
                left = true; //부딪힘
                break;
            }
        }

        //오른쪽 확인
        for(int i = x; i < W; i++){
            if(i==x) continue;

            if(heights[i] > y){
                right = true;
                break;
            }
        }


        if(left && right) return true;
        return false;
    }
}