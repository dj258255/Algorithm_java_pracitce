import java.io.*;
import java.util.*;

public class Solution {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0}; 
    static int[] dy = {0, 0, -1, 1}; 
    static int maxHome;
    
    public static class Point {
        int x;
        int y; 
        Point(int x, int y) {
            this.x = x; 
            this.y = y; 
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            maxHome = 0; 
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    bfs(i, j);
                }
            }
            
            sb.append("#").append(tc).append(" ").append(maxHome).append("\n");
        }
        
        System.out.print(sb.toString());
    }
    
    public static void bfs(int x, int y) {
        visited = new boolean[N][N];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;
        
        int houseCount = 0;
        if(board[x][y] == 1) {
            houseCount++;
        }
        
        int k = 1;
        int cost = k * k + (k - 1) * (k - 1);
        if(houseCount * M >= cost) {
            maxHome = Math.max(maxHome, houseCount);
        }
        
        while(!queue.isEmpty()){
            int size = queue.size();
            k++;
            if(k > 2 * N) break;
            for(int s = 0; s < size; s++){
                Point cur = queue.poll();
                for(int i = 0; i < 4; i++){
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if(visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    if(board[nx][ny] == 1) {
                        houseCount++;
                    }
                    queue.add(new Point(nx, ny));
                }
            }
            cost = k * k + (k - 1) * (k - 1);
            if(houseCount * M >= cost) {
                maxHome = Math.max(maxHome, houseCount);
            }
        }
    }
}