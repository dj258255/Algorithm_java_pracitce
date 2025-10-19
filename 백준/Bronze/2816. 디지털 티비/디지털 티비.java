import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int N;
    static String[] channel;
    
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        channel = new String[N];
        for(int i = 0 ; i < N; i++) {
            channel[i] = br.readLine();
        }
    }
    
    public static void main(String[] args) throws IOException{
        input();
        solve();
        bw.flush();
        bw.close();
    }

    public static void solve() throws IOException {
        int pos1 = -1, pos2 = -1;
        
        for (int i = 0; i < N; i++) {
            if (channel[i].equals("KBS1")) {
                pos1 = i;
            }
            if (channel[i].equals("KBS2")) {
                pos2 = i;
            }
        }
        
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < pos1; i++) {
            result.append('1');
        }
        for (int i = 0; i < pos1; i++) {
            result.append('4');
        }
        if (pos2 < pos1) {
            pos2++;
        }
        for (int i = 0; i < pos2; i++) {
            result.append('1');
        }
        for (int i = 0; i < pos2 - 1; i++) {
            result.append('4');
        }
        
        bw.write(result.toString());
    }
}