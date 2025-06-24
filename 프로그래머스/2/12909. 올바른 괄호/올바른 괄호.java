import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    boolean solution(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(c);
            } else { // c == ')'
                if (stack.isEmpty()) {
                    return false;  // 닫는 괄호가 더 많음
                }
                stack.pop();
            }
        }

        // 모든 괄호가 짝이 맞아야 하므로 스택이 비어야 함
        return stack.isEmpty();
    }
}
