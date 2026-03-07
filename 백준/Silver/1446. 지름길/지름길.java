import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        input();
        solve();
        output();
    }

    static int answer;
    public static void solve() throws IOException{
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a,b) -> a[1]-b[1]
        );

        pq.add(new int[]{0,0});
        int[] len = new int[D+1];
        boolean[] visited = new boolean[D+1];
        Arrays.fill(len,Integer.MAX_VALUE);
        while(!pq.isEmpty()){
            int[] node = pq.poll();
            int cus = node[0];
            int sum = node[1];
            if(cus == D){
                answer = sum;
                break;
            }
            if(visited[cus]) continue;
            visited[cus] = true;

            for(int[] neighbor : road.get(cus)){
                int nextCus = neighbor[0];
                int nextSum = sum+neighbor[1];

                if(D < nextCus) continue;
                if(len[nextCus] < nextSum) continue;

                pq.add(new int[]{nextCus,nextSum});
            }

        }
        bw.write("" + answer);
    }

    public static void output() throws IOException{
        bw.flush();
        bw.close();
    }

    static int N,D;

    static Map<Integer,ArrayList<int[]>> road;
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken()); //지름길 개수
        D = Integer.parseInt(st.nextToken()); //고속도로 길이
        road = new HashMap<>();
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine().trim());
            int startPos = Integer.parseInt(st.nextToken()); //시작위치
            int goalPos = Integer.parseInt(st.nextToken()); //도착위치
            int len = Integer.parseInt(st.nextToken()); //길이

            road.computeIfAbsent(startPos, k-> new ArrayList<>()).add(new int[]{goalPos,len});

        }

        //일반도로
        for(int i = 0; i < D; i++){
            road.computeIfAbsent(i, k -> new ArrayList<>()).add(new int[]{i+1, 1});
        }
    }
}