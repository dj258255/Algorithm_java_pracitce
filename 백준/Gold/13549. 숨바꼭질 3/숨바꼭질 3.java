import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt(); // 수빈이의 위치
        int K = scan.nextInt(); // 동생의 위치

        System.out.println(bfs(N, K));
    }

    public static int bfs(int N, int K) {
        int MAX = 100001; //위치 범위
        int[] time = new int[MAX]; // 각 위치까지 도달하는 최소 시간
        Arrays.fill(time, -1); // 방문하지 않은 위치는 -1로 초기화

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(N);
        time[N] = 0; // 시작 위치는 0초

        while (!deque.isEmpty()) {
            int current = deque.poll();

            // 동생의 위치에 도달하면 종료
            if (current == K) {
                return time[current];
            }

            // 순간이동 (0초 소요)
            if (current * 2 < MAX && time[current * 2] == -1) {
                time[current * 2] = time[current];
                deque.offerFirst(current * 2); // 덱 앞쪽에 추가
            }

            // 걷기 (1초 소요)
            if (current - 1 >= 0 && time[current - 1] == -1) {
                time[current - 1] = time[current] + 1;
                deque.offerLast(current - 1);
            }

            if (current + 1 < MAX && time[current + 1] == -1) {
                time[current + 1] = time[current] + 1;
                deque.offerLast(current + 1);
            }
        }

        return -1;
    }
}
