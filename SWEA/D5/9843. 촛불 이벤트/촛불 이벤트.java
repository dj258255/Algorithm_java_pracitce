import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution{
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
     
    static long N;
    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
    }
     
    static long left,right;
    public static void solve() {
        //K단에는 1개의 양초를 배치해서 K단에는  (K(K+1))/2개의 양초가 필요
        //1단에는 K개 2단에는 K-1개가 필요
             
            left = 0;
            right = Integer.MAX_VALUE;
             
            while(left < right) {
                long mid = (left+right)/2;
                //mid 는 단으로 해야함
                 
                long need = (mid*(mid+1))/2;
                 
                if(need < N) {
                    left = mid+1;
                }else{
                    right = mid;
                } 
            }
             
            if (left * (left + 1) / 2 != N) {
                left = -1;
            }
         
    }
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        for(int T = 1; T<=t; T++) {
            input();
             
            solve();
            sb.append("#").append(T).append(" ").append(left).append("\n");
        }
        System.out.print(sb.toString());
    }
}