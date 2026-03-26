import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N;
    static int[] home;
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        home = new int[N];

        st = new StringTokenizer(br.readLine().trim());
        for(int i= 0 ; i < N; i++){
            home[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void solve() throws IOException {
        Arrays.sort(home);
        bw.write("" + home[(N - 1) / 2]);
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