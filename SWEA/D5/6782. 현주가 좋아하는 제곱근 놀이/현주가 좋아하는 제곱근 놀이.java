import java.io.*;
import java.util.*;

class Solution {

    static long answer;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 1; tc <= t; tc ++) {
            sb.append("#").append(tc).append(" ");
            long n = Long.parseLong(br.readLine());
            answer = Integer.MAX_VALUE;

            replace(n, 0);
            sb.append(answer).append("\n");
        }
        System.out.println(sb);

    }

    public static void replace(long n, long cnt) {

        if(n == 2) {
            answer = Math.min(cnt, answer);
            return;
        }

        if((long)Math.sqrt(n) *(long) Math.sqrt(n) == n) {
            replace((long) Math.sqrt(n), cnt + 1);
        } else {
            long nextSqrt = (long)Math.sqrt(n) + 1;
            long next = nextSqrt * nextSqrt;
            long diff = next - n;
            replace(next, cnt + diff);
        }

    }

}
