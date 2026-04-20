import java.sql.Array;
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N;
    static int[] num;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    public static void solve() throws IOException {
        long answer = 0;
        int i = 0 ;
        while(i <N){
            int j = i;
            while(j+1 < N && num[j] < num[j+1]){
                j++;
            }
            long len = j-i+1;
            answer += len*(len+1)/2;
            i = j+1;
        }
        bw.write(""+answer);
    }

    public static void output() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());

        num = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
    }
}