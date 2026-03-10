import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N,M;
    static boolean[][] friends;
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        friends = new boolean[N+1][N+1];

        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine().trim());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            friends[A][B] = true;
            friends[B][A] = true;
        }
    }
    
    public static void solve() throws IOException{
        int day = 0;

        List<Integer> dayList = new ArrayList<>();
        while(true){
            boolean[][] newFriends = new boolean[N+1][N+1];
            int count =0;
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    //본인이 아니고 친구가 아닐 때
                    if(i != j && !friends[i][j]){
                        for(int k = 1; k<=N; k++){
                            //A와 B가 공통친구가 있으면 오늘부터 친구가 됨
                            if(friends[i][k] && friends[k][j]){
                                newFriends[i][j] = true;
                                break;
                            }
                        }
                    }
                }
            }

            // 새 친구 관계 반영
            for(int i = 1; i <=N; i++){
                for(int j = i+1; j<=N; j++){
                    if(newFriends[i][j]){
                        friends[i][j] = true;
                        friends[j][i] = true;
                        count++;
                    }
                }
            }
            if(count == 0) break;
            day++;
            dayList.add(count);
        }
        bw.write(String.valueOf(day));
        bw.newLine();
        for (int d : dayList) {
            bw.write(String.valueOf(d));
            bw.newLine();
        }
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