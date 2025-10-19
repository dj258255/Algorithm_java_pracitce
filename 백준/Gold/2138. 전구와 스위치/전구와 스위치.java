//  전구와 스위치

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static BufferedWriter bw;
    static int N;
    static int[] before;
    static int[] after;
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        before = new int[N];
        after = new int[N];

        String number = br.readLine();
        for(int i = 0 ; i < N; i++){
            before[i] = Integer.parseInt(number.substring(i,i+1));
        }

        number = br.readLine();
        for(int i = 0 ; i < N; i++){
            after[i] = Integer.parseInt(number.substring(i,i+1));
        }
    }

    public static void main(String[] args) throws IOException{
        input();
        int result = solve();
        bw.write(result + "");
        bw.flush();
        bw.close();
    }

    public static int solve(){
        //1번 스위치를 누르지 않는 경우
        int case1 = simulate(false);
        //1번 스위치를 누르는 경우
        int case2 = simulate(true);

        //둘 중 최솟값 (불가능하면 -1)
        if(case1 == -1 && case2 == -1) return -1;
        if(case1 == -1) return case2;
        if(case2 == -1) return case1;
        return Math.min(case1, case2);
    }

    public static int simulate(boolean pressFirst){
        int[] current = before.clone();
        int count = 0;

        if(pressFirst){
            current[0] = 1 - current[0];
            current[1] = 1 - current[1];
            count++;
        }

        for(int i = 1; i < N; i++){
            if(current[i-1] != after[i-1]){
                current[i-1] = 1 - current[i-1];
                current[i] = 1 - current[i];
                if(i + 1 < N){
                    current[i+1] = 1 - current[i+1];
                }
                count++;
            }
        }

        // 모든 전구가 목표 상태와 같은지 확인
        for(int i = 0; i < N; i++){
            if(current[i] != after[i]) return -1;
        }
        return count;
    }
}