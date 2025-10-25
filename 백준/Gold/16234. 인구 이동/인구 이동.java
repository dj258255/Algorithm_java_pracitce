import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    static StringTokenizer st;

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    
    public static void main(String[] args) throws IOException{
        input();
        solve();
        output();
    }

    static int N,L,R;
    static int[][] people;
    
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        people = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                people[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static boolean[][] visited;
    
    public static boolean bfs(int startR, int startC) {
        Queue<int[]> queue = new ArrayDeque<>();
        List<int[]> union = new ArrayList<>();
        
        queue.add(new int[]{startR, startC});
        union.add(new int[]{startR, startC});
        visited[startR][startC] = true;
        int sumPeople = people[startR][startC];

        while(!queue.isEmpty()) {
            int[] node = queue.poll();
            int cr = node[0];
            int cc = node[1];

            for(int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                
                if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if(visited[nr][nc]) continue;

                int diff = Math.abs(people[cr][cc] - people[nr][nc]);
                if(diff >= L && diff <= R) {
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc});
                    union.add(new int[]{nr, nc});
                    sumPeople += people[nr][nc];
                }
            }
        }

        // 연합 크기가 2 이상이어야 인구 이동 발생
        if(union.size() > 1) {
            int newPeople = sumPeople / union.size();
            for(int[] pos : union) {
                people[pos[0]][pos[1]] = newPeople;
            }
            return true;  // 인구 이동 발생
        }
        return false;  // 인구 이동 없음 (혼자)
    }

    static int countDay;
    
    public static void solve() throws IOException{
        countDay = 0;
        
        // 인구 이동이 없을 때까지 반복
        while(true) {
            visited = new boolean[N][N];
            boolean hasMoved = false;  // 오늘 인구 이동이 있었는지
            
            // 모든 칸을 순회하며 연합 찾기
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j]) {
                        if(bfs(i, j)) {
                            hasMoved = true;  // 연합이 있었음
                        }
                    }
                }
            }
            
            // 오늘 인구 이동이 없었으면 종료
            if(!hasMoved) {
                break;
            }
            
            // 인구 이동이 있었으면 하루 증가
            countDay++;
        }
    }

    public static void output() throws IOException {
        sb.append(countDay);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}