import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        input();
        solve();
        output();
    }

    static int N,d,k,c;
    static int[] box;
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        box = new int[N];
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine().trim());
            box[i] = Integer.parseInt(st.nextToken());
        }
    }

    static int answer;
    public static void solve() throws IOException{
        answer = Integer.MIN_VALUE;

        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < k; i++){
            map.merge(box[i],1,(a,b) -> a+b);
        }

        for(int i = 0 ; i < N; i++){
            int count = map.size();
            if(!map.containsKey(c)) count++;
            answer = Math.max(answer,count);

            int left = box[i%N];
            if(map.get(left) == 1) map.remove(left);
            else map.merge(left,-1,(a,b) -> a+b);

            map.merge(box[(i+k) % N],1,(a,b) -> a+b);
        }

        bw.write("" + answer);


    }
    public static void output() throws IOException{
        bw.flush();
        bw.close();
    }
}
