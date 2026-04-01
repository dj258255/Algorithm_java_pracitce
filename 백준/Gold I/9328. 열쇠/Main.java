import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        input();
        output();
    }

    static void input() throws IOException {
        sb = new StringBuilder();
        int test_cases = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < test_cases; T++) {
            st = new StringTokenizer(br.readLine().trim());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            char[][] map = new char[h + 2][w + 2];
            for (int i = 0; i < h + 2; i++) {
                Arrays.fill(map[i], '.');
            }

            //실제 입력은 1행 ~ h행, 1열 ~ w열에 넣음
            for (int i = 1; i <= h; i++) {
                String temp = br.readLine().trim();
                for (int j = 1; j <= w; j++) {
                    map[i][j] = temp.charAt(j - 1);
                }
            }

            boolean[] key = new boolean[26];
            String keyTemp = br.readLine().trim();
            if (!keyTemp.equals("0")) {
                for (int i = 0; i < keyTemp.length(); i++) {
                    key[keyTemp.charAt(i) - 'a'] = true;
                }
            }

            sb.append(bfs(map, h + 2, w + 2, key)).append("\n");
        }

        bw.write(sb.toString());
    }

    static void output() throws IOException {
        bw.flush();
        bw.close();
    }

    static int bfs(char[][] map, int h, int w, boolean[] key) {
        boolean[][] visited = new boolean[h][w];
        Queue<int[]> queue = new ArrayDeque<>();

        List<int[]>[] doors = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            doors[i] = new ArrayList<>();
        }

        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        int count = 0;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int r = node[0];
            int c = node[1];

            for(int d = 0; d< 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(nr < 0 || nc < 0 || nr >=h || nc >=w) continue; //맵밖 조건
                if(visited[nr][nc]) continue;
                if(map[nr][nc] == '*') continue;

                char cur = map[nr][nc];

                //문이면
                if(cur >= 'A' && cur <='Z'){
                    int index = cur - 'A';
                    if(key[index]){
                        visited[nr][nc] = true;
                        queue.add(new int[]{nr,nc});
                    } else{
                        doors[index].add(new int[]{nr,nc});
                    }
                }
                //열쇠면
                if(cur >= 'a' && cur <='z'){
                    int index = cur - 'a';
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr,nc});

                    //키가 없다면
                    if(!key[index]){
                        key[index] = true;
                        //남겨뒀던 문들 돌아보기
                        for(int[] doorPos : doors[index]){
                            int rr = doorPos[0];
                            int cc = doorPos[1];

                            //방문 안했다면
                            if(!visited[rr][cc]){
                                visited[rr][cc] = true;
                                queue.add(new int[]{rr,cc});
                            }
                        }
                    }
                }

                //빈간이면
                if(cur =='.'){
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr,nc});
                }
                //문서면
                if(cur =='$'){
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr,nc});
                    count++;
                }

            }
        }

        return count;
    }
}