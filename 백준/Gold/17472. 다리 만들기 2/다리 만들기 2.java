import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static boolean[][] visited; //섬 번호매길 때만 사용
    static int islandCount,M,N;
    static int[][] map;
    static Map<Integer, ArrayList<Edge>> graph;
    
    
    public static class Edge{
        int to; //어디
        int w; //가중치
        
        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
    
    public static class Node{
        int u,d; //다음 노드 , 총거리
        
        Node(int u,int d){
            this.u = u;
            this.d = d;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        visited = new boolean[N][M];
        
        for(int i = 0 ; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        
        
        //섬번호 지정
        
        islandCount = 1;
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M; j++) {
                if(map[i][j]==1) {
                    number(i,j);

                }
            }
        }
        
        //번호 지정 과정에서 islandCount가 실제 개수+1이 되므로 보정
        islandCount--;

        /*
        
        //섬 번호 출력
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        
        */
        //섬 연결해주기
        graph = new HashMap<>();
        
        
        //graph초기화
        for (int i = 1; i <= islandCount; i++) {
            graph.put(i,new ArrayList<>());
        }

        
        //섬 연결코드
        for(int i = 0 ; i < N; i++) {
    		for(int j = 0 ; j < M; j++) {
    			if(map[i][j] > 0) distance(i,j,map[i][j]);
    		}
    	}
        
        //섬 연결 출력
        /*
        for (int i = 1; i < graph.size(); i++) {
            System.out.print("from " + i + " -> ");
            if (graph.isEmpty()) {
                System.out.println("no edges");
            } else {
                for (Edge e : graph.get(i)) {
                    System.out.print("(" + e.to + ", w=" + e.w + ") ");
                }
                System.out.println();
            }
        }
        */
        
        //이제 완벽해. 이제 모든 섬을 연결하는 다리 길이의 최소값을 구하자
        mst1();
        
        
        
    }
    
    
    //이제 mst해보 하자 젭라~!@$~$
    public static void mst() {
        PriorityQueue<Node> pq = new PriorityQueue<>( (o1,o2) -> o1.d - o2.d); //거리 적은순부터
        pq.add(new Node(1, 0)); //스타트
        boolean[] islandVisited = new boolean[islandCount];
        islandVisited[1] = true;
        
        while(!pq.isEmpty()) {
            Node temp = pq.poll();
            int u = temp.u; //현재 노드
            int d = temp.d; // 가중치
            
        }
    }

    //prim 알고리즘으로 MST 구하기
    public static void mst1() {
        boolean[] used = new boolean[islandCount + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.d - b.d);

        // 1번 섬부터 시작
        used[1] = true;
        for (Edge e : graph.get(1)) {
            pq.add(new Node(e.to, e.w));
        }

        int cnt = 0, result = 0;
        //연결된 섬 수가 islandCount-1이 될 때까지
        while (!pq.isEmpty() && cnt < islandCount - 1) {
            Node cur = pq.poll();
            if (used[cur.u]) continue;

            used[cur.u] = true;
            result += cur.d;
            cnt++;

            //새로 연결된 섬의 간선을 모두 PQ에 추가
            for (Edge e : graph.get(cur.u)) {
                if (!used[e.to]) {
                    pq.add(new Node(e.to, e.w));
                }
            }
        }

        // MST 완성 여부 확인
        if (cnt != islandCount - 1) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }
    
    //섬 연결코드
    public static void distance(int r, int c, int islandId) {
        for (int d = 0; d < 4; d++) {
            int len = 0;
            int nr = r, nc = c;

            while (true) {
                nr += dr[d];
                nc += dc[d];
                //범위 벗어나면 종료
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) break;
                //같은 섬 만나면 종료
                if (map[nr][nc] == islandId) break;
                //바다면 길이 증가
                if (map[nr][nc] == 0) {
                    len++;
                    continue;
                }
                //다른 섬 만남
                if (len >= 2) { //다리 최소길이가 2 이상일 때
                    int otherId = map[nr][nc];
                    //중복 검사+최소값 갱신
                    boolean found = false;
                    for (Edge e : graph.get(islandId)) {
                        if (e.to == otherId) {
                            found = true;
                            if (e.w > len) {
                                e.w = len;
                            }
                            break;
                        }
                    }
                    if (!found) {
                        graph.get(islandId).add(new Edge(otherId, len));
                        //graph.get(otherId).add(new Edge(islandId, len));  // 양방향 추가
                    }
                }
                break;
            }
        }
    }
    

    
    //섬에 숫자 부여
    public static void number(int startR , int startC) {
        if(visited[startR][startC] == false) {
            visited[startR][startC] = true;
            map[startR][startC] = islandCount;
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[] {startR,startC});
            
            while(!queue.isEmpty()) {    
                int[] node = queue.poll();
                int r = node[0];
                int c = node[1];
                
                for(int d = 0 ; d < 4 ; d ++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    
                    if(nr >= N || nc >= M || nr < 0 || nc < 0) continue;
                    if(map[nr][nc] == 0 ) continue;
                    
                    if(!visited[nr][nc]) {
                        visited[nr][nc] = true;
                        queue.add(new int[] {nr,nc});
                        map[nr][nc] = islandCount;
                    }
                    
                }
                
            }
            islandCount++;
        }

    }
    
    

}