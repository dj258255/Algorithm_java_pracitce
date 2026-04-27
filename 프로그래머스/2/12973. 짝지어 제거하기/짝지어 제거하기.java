import java.util.*;
import java.io.*;

class Solution
{
    public int solution(String s)
    {
        //1. 같은 알파벳 2개 붙어있는 짝을 찾음.
        //2. 그 둘을 제거 -> 앞뒤로 문자열 이어 붙임
        //3. 문자열을 모두 제거하면 짝지어 제거하기 종료
        //모두 제거할 수 있으면 1. 아니면 0
        if(s.length() % 2 == 1){ //사이즈가 홀수면 0반환 애초에 다 못지움
            return 0;
        }
        
        Deque<Character> stack = new ArrayDeque<>();
        
        for(int i = 0 ; i < s.length(); i++){
            //Stack의 peek랑 같을때 pop 아니면 list 배열에 추가?
            
            if(!stack.isEmpty() && stack.peek() == s.charAt(i)){
                stack.pop();
            } else{
                stack.push(s.charAt(i));
            }
        }
        
        

        return stack.isEmpty() ? 1 : 0;
    }
}