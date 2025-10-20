//  좋다

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N,answer;
    static int[] A;
    public static void input() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        answer = 0;
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
    }

    public static void main(String[] args) throws IOException{
        input();
        solve();
        output();
    }

    public static void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public static void solve() throws IOException {
        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N - 1;
            while (left < right) {
                if (left == i) {
                    left++;
                    continue;
                }
                if (right == i) {
                    right--;
                    continue;
                }
                int sum = A[left] + A[right];
                if (sum == A[i]) {
                    answer++;
                    break;
                } else if (sum < A[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        sb.append(answer);
        bw.write(sb.toString());
    }
}