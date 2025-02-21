import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        
        while (!(line = br.readLine()).equals(".")) {
            System.out.println(isBalanced(line) ? "yes" : "no");
        }
    }
    
    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(ch == '(' || ch == '[') {
                stack.push(ch);
                
            }
            else if(ch == ')') {
                if(stack.isEmpty() || stack.peek() != '(') {
                    return false;
                }
                stack.pop();
            }
            else if(ch == ']') {
                if(stack.isEmpty() || stack.peek() != '[') {
                    return false;
                }
                stack.pop();
            }
        }
        
        return stack.isEmpty();
    }
}
