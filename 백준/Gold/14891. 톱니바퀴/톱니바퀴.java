import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static ArrayDeque<Integer>[] gear;
    static int K;

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        gear = new ArrayDeque[4];
        for (int i = 0; i < 4; i++) {
            gear[i] = new ArrayDeque<>();
            String temp = br.readLine().trim();
            for (int j = 0; j < 8; j++) {
                gear[i].addLast(temp.charAt(j) - '0');
            }
        }

        K = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken());
            solve(a, b);
        }
    }

    public static void solve(int num, int rotateNum) {
        int[] dir = new int[4];
        dir[num] = rotateNum;

        // 회전 전에 맞닿은 극 먼저 확인 (배열로 변환)
        Integer[][] gearArr = new Integer[4][];
        for (int i = 0; i < 4; i++) {
            gearArr[i] = gear[i].toArray(new Integer[0]);
        }

        // 왼쪽으로
        for (int i = num - 1; i >= 0; i--) {
            if (gearArr[i][2] != gearArr[i + 1][6]) {
                dir[i] = -dir[i + 1];
            } else {
                break;
            }
        }

        // 오른쪽으로
        for (int i = num + 1; i < 4; i++) {
            if (gearArr[i - 1][2] != gearArr[i][6]) {
                dir[i] = -dir[i - 1];
            } else {
                break;
            }
        }

        // 모든 톱니바퀴 회전
        for (int i = 0; i < 4; i++) {
            if (dir[i] != 0) {
                rotate(i, dir[i]);
            }
        }
    }

    public static void rotate(int gearNum, int rotateNum) {
        if (rotateNum == 1) { // 시계 방향
            gear[gearNum].addFirst(gear[gearNum].pollLast());
        } else if (rotateNum == -1) { // 반시계 방향
            gear[gearNum].addLast(gear[gearNum].pollFirst());
        }
    }

    public static void output() throws IOException {
        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (gear[i].peekFirst() == 1) { // 12시 방향이 S극이면
                score += (1 << i); // 1, 2, 4, 8
            }
        }
        bw.write(score + "\n");
        bw.flush();
        bw.close();
    }
}