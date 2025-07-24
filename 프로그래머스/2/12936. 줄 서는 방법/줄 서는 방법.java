import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        int[] answer = new int[n];
        k--;

        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }

        for (int i = 0; i < n; i++) {
            fact /= (n - i);
            int index = (int)(k / fact);
            answer[i] = nums.get(index);
            nums.remove(index);
            k %= fact;
        }

        return answer;
    }
}
