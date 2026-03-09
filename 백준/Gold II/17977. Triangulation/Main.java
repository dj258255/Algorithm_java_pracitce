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
    
    public static void solve() throws IOException{
        
    }
    
    public static void output() throws IOException{
        bw.flush();
        bw.close();
    }
    
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
    }
}