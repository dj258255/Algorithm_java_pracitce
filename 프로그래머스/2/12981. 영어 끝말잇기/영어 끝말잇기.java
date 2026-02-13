import java.util.*;
import java.io.*;

class Solution {
    public static ArrayList<String> list;
    public int[] solution(int n, String[] words) {
        list = new ArrayList<>();
        int[] answer = new int[2];
        answer[0] = 0;
        answer[1] = 0;
        //한글자 인정x        
        for(int i = 0 ; i < words.length; i++){
            String word = words[i];
            if(list.contains(word) || possible(i,word)){
                answer[0] = (i+1)%n == 0 ? n : (i+1)%n;
                answer[1] = (i+n)/n;
                break;
            }
            list.add(word);
        }
        
        //가장 먼저 탈락하는 사람의 번호
        //그 사람이 자신의 몇 번째 차례에 탈락하는지

        return answer;
    }
    
    public static boolean possible(int idx, String word){
        if(idx==0) return false;
        String pre = list.get(idx-1);
        if(pre.charAt(pre.length()-1) == word.charAt(0)) return false;
        return true;
    }
}