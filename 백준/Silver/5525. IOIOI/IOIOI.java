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

    static int N; //N+1개의 I와 N개의 o
    static int M;
    static String S;
    static String find;

    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine().trim());
        S = st.nextToken();
    }

    public static void solve() throws IOException {
        int count = 0;
        int result = 0;

        for (int i = 0; i < M - 2; i++) {
            if (S.charAt(i) == 'I' && S.charAt(i+1) == 'O' && S.charAt(i+2) == 'I') {
                count++;
                if (count == N) {
                    result++;
                    count--;
                }
                i++;
            } else {
                count = 0;
            }
        }

        bw.write("" + result);
    }


    public static void output() throws IOException{
        bw.flush();
        bw.close();
    }

}