import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;



public class Main{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt(); //컴퓨터의 수
        int c = scan.nextInt(); //컴퓨터 쌍의 수
        int[][] cou = new int[c][2];

        for(int i =0  ; i < cou.length; i++) {
            for(int j = 0 ; j < 2; j++) {
                cou[i][j] = scan.nextInt();
            }
        }
        Map<Integer,ArrayList<Integer>> map = new HashMap<>();
        for(int i = 1 ; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }
        for(int i = 0; i < c; i++) {
            int a = cou[i][0];
            int b = cou[i][1];
            map.get(a).add(b);
            map.get(b).add(a);  // <-- 양방향(반대 방향) 연결 추가!
        }

        /*
        for(int i = 0 ; i < c; i++) {
            map.get(cou[i][0]).add(cou[i][1]);
        }
        */
        int answer = bfs(map,1,N);
        System.out.println(answer);
    }

    public static int bfs(Map<Integer,ArrayList<Integer>> map , int start , int N) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        boolean[] visited = new boolean[N+1];
        visited[start] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : map.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    count++; // 컴퓨터 개수 증가
                }
            }
        }
        return count;
    }
}