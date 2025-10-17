//  뱀과 사다리 게임

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int N,M,answer;
    static Map<Integer,Integer> moveMap;
    static boolean[] visited;
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        visited = new boolean[101];
        moveMap = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //사다리 수
        M = Integer.parseInt(st.nextToken()); //뱀의 수

        //사다리
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            moveMap.put(a,b);
        }
        //뱀
        for(int i = 0 ; i <  M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            moveMap.put(a,b);
        }

    }
    public static void main(String[] args) throws IOException{
        input();
        solve();

        bw.write(answer + "");
        bw.flush();
        bw.close();
    }

    //BFS
    public static void solve(){
        //주사위 6
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{1,0}); //현재 위치 , 주사위 굴린 횟수
        visited[1] = true; // [1] = true;

        while(!queue.isEmpty()){
            int[] node = queue.poll();
            int pos = node[0];
            int count = node[1];

            if(pos == 100) {
                answer = count;
                return;
            }

            for(int i = 1; i <= 6; i++){
                int next = pos + i;

                if(next > 100) continue;

                if(moveMap.containsKey(next)){
                    next = moveMap.get(next);
                }
                if(!visited[next]){
                    queue.add(new int[]{next,count+1});
                    visited[next] = true;
                }
            }
        }


        return;
    }
}