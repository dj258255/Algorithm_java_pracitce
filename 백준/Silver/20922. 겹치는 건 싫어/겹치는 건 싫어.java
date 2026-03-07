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

    static int N,K;
    static int[] a;
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        a = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0 ; i < N; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
    }

    static int answer;
    public static void solve() throws IOException{
        answer = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        int count =0;
        int left = 0;
        for(int i = 0 ; i < N; i++){
            map.put(a[i], map.getOrDefault(a[i],0) +1);
            count++;

            if(map.get(a[i]) > K){
                for(int j = left; j < i; j++){
                    map.put(a[j], map.getOrDefault(a[j], 0)-1);
                    count--;
                    left++;
                    if(a[j] == a[i]) break;
                }
            }

            answer = Math.max(answer,count);

        }

        bw.write(""+answer);

    }

    public static void output() throws IOException{
        bw.flush();
        bw.close();
    }
}