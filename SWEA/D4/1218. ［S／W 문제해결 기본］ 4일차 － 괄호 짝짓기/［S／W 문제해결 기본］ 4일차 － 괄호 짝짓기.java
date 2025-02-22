import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.io.IOException;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            String line = br.readLine();

            Stack<Character> stack = new Stack<>();
            boolean answer = true;

            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                if (ch == '(' || ch == '[' || ch == '{' || ch == '<') {
                    stack.push(ch);
                }
                //닫는 괄호이면 스택의 top과 비교
                else {
                    if (stack.isEmpty()) { //매칭되는 여는 괄호가 없는 경우
                        answer = false;
                        break;
                    }
                    char top = stack.pop();
                    if ((ch == ')' && top != '(') ||
                            (ch == ']' && top != '[') ||
                            (ch == '}' && top != '{') ||
                            (ch == '>' && top != '<')) {
                        answer = false;
                        break;
                    }
                }
            }

            //처리 후 스택이 비어있지 않으면 균형이 맞지 않음
            if (!stack.isEmpty()) {
                answer = false;
            }

            System.out.println("#" + tc + " " + (answer ? "1" : "0"));
        }
    }
}
