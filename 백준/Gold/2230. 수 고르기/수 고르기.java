import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N,M;
    static List<Integer> list;
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine().trim());
            list.add(Integer.parseInt(st.nextToken()));
        }
        list.sort(
                (a,b) -> a-b
        );


    }

    public static void solve() throws IOException{
        //차이가 M 이상이면서 제일 작은 경우
        int minDif = Integer.MAX_VALUE;
        int i = 0, j = 1;

        while (i < N && j < N) {
            int min = list.get(j) - list.get(i);
            //만약 M보다 크거나 같으면 갱신
            if (min >= M) {
                minDif = Math.min(minDif, min); // M보다 크거나 같은데 정답은 작은거니 Math.min
                i++; //i를 늘려서 간격을 좁힘
            } else { //M보다 작으면 간격을 넓힘
                j++;
            }
        }
        bw.write(""+minDif);

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