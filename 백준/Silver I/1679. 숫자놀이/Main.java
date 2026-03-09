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

    static int N,K;
    static int[] num;
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine().trim());
        num = new int[N];
        for(int i = 0 ; i < N ; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine().trim());
        K = Integer.parseInt(st.nextToken());
    }

    static String answerName;
    static boolean endGame;
    public static void solve() throws IOException{
        boolean isHol = true;
        int goal = 1;
        while(true){
            bfs(isHol ? "holsoon" : "jjaksoon", goal);
            if(endGame){
                bw.write(answerName + " win at " + goal);
                return;
            }
            goal++;
            isHol = !isHol;
        }
    }

    public static void bfs(String name, int goal){
        Queue<int[]> queue = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        set.add(0);
        queue.add(new int[]{0, 0});

        while(!queue.isEmpty()){
            int[] node = queue.poll();
            int curNum = node[0];
            int count  = node[1];

            if(count >= K) continue;

            for(int i = 0; i < num.length; i++){
                int nextNum = curNum + num[i];

                if(nextNum == goal){
                    answerName = name;
                    return;
                }

                if(nextNum > goal) continue;
                if(set.contains(nextNum)) continue;

                set.add(nextNum);
                queue.add(new int[]{nextNum, count + 1});
            }
        }

        answerName = name.equals("holsoon") ? "jjaksoon" : "holsoon";
        endGame = true;
    }

    public static void output() throws IOException{
        bw.flush();
        bw.close();
    }
}