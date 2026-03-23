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
        find = "";
        for(int i = 0 ; i < (N*2)+1; i++){
            if(i%2==0) find += "I";
            else find += "O";
        }

        st = new StringTokenizer(br.readLine().trim());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine().trim());
        S = st.nextToken();
    }

    public static void solve() throws IOException{

        boolean isCon;
        int count =0;
        while(true){
            isCon = true;

            if(S.contains(find)){
                int index = S.indexOf(find);
                count++;
                S = S.substring(index+1);
                isCon = false;
            }

            if(isCon) {
                bw.write("" + count);
                break;
            }
        }
    }


    public static void output() throws IOException{
        bw.flush();
        bw.close();
    }

}