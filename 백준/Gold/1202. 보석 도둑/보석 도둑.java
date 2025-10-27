//  보석 도둑

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException{
        input();
        solve();
        output();
    }

    static int N,K;
    static PriorityQueue<int[]> jewelry;  // 무게 기준 정렬 [무게,가격] 보석정보
    static PriorityQueue<Integer> candidates;  //[현재 담을 수 있는 보석들] 가격만 저장, 가격 기준 정렬
    static int[] bags;

    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewelry = new PriorityQueue<>((a,b) -> a[0] - b[0]);  //무게 오름차순
        candidates = new PriorityQueue<>((a,b) -> b - a); //가격 내림차순
        bags = new int[K];

        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); //무게
            int b = Integer.parseInt(st.nextToken()); //가격
            jewelry.add(new int[]{a,b});
        }

        for(int i = 0 ; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            bags[i] = Integer.parseInt(st.nextToken());
        }
    }

    static long answerSum;
    public static void output() throws IOException{
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void solve() throws IOException{
        answerSum = 0;
        Arrays.sort(bags);

        for(int i = 0 ; i < K; i++){
            while(!jewelry.isEmpty()){
                int[] node = jewelry.peek();
                //무게 기준 필터링
                if(node[0] <= bags[i]) {
                    jewelry.poll(); //가방에 담음!
                    candidates.add(node[1]);
                } else{
                    break;
                }
            }

            //가격 기준 필터링
            if(!candidates.isEmpty()){
                answerSum += candidates.poll();
            }
        }

        sb.append(answerSum);
    }
}