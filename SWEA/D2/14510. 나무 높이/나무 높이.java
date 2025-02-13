import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int maxHeight = Integer.MIN_VALUE; //최고 나무
            int[] trees = new int[N];
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, trees[i]);
            }

            long oddday = 0;
            long evenday = 0;
            for (int i = 0; i < N; i++) {
                int temp = maxHeight - trees[i]; //최고 나무와 현재 나무의 차이
                oddday += temp % 2; //최소 홀수일
                evenday += temp / 2;  //최소 짝수일
            }

            //이분 탐색
            int left = 0, right = Integer.MAX_VALUE;
            while (left < right) {
                int mid = (left + right) / 2;
                int leftAvail = (mid + 1) / 2; //홀수 가능한날
                int rightAvail = mid / 2; //짝수 가능한 날

                long extraOddNeeded = 2 * Math.max(0, evenday - rightAvail); //짝수 추가가 부족해서 1로 대체해야 하는 개수

                //이 조건을 만족하면 right = mid, 아니면 left = mid + 1로 이동하여 최소 날짜를 찾는 것이다.
                if (oddday + extraOddNeeded <= leftAvail) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            System.out.println("#" + t + " " + left);
        }
    }
}
