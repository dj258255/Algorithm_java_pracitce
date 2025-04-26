import java.util.Arrays;
import java.util.HashMap;
import java.util.Collection;
import java.util.Map;
import java.util.Collections;
import java.util.ArrayList;

class Solution {

    static Map<String,Integer> map; 
    //메뉴구성,조합 sb,조합을 idx, 코스요리 개수에 따른 종료조건 cnt와 n)
    public static void dfs(String str,StringBuilder sb,int idx, int cnt, int n){
       if(cnt == n) { //코스요리 개수랑 같을 때
           //map에삽입. 이미 있으면 카운트
           map.put(sb.toString(),map.getOrDefault(sb.toString(),0)+1);
           return;
        }
        
        //조합.
        for(int i = idx ; i<str.length();i++){
            sb.append(str.charAt(i)); //sb에 붙임
            dfs(str,sb,i+1,cnt+1,n); //조합
            sb.delete(cnt,cnt+1); //다른거 붙이기
        }
    }
    
    public ArrayList<String> solution(String[] orders, int[] course) {
        ArrayList<String> answer = new ArrayList<>();
        
        //손님 번호당 주문한 단품메뉴들
        for(int i =0;i<orders.length;i++){
            char[] charArr = orders[i].toCharArray();
            Arrays.sort(charArr);
            orders[i] = String.valueOf(charArr);
        }
        
        //코스내 음식 개수
        for(int i =0;i<course.length;i++){
            map = new HashMap<>();
            int max = Integer.MIN_VALUE;   
            for(int j =0;j<orders.length;j++){
                StringBuilder sb = new StringBuilder(); 
                if(course[i]<=orders[j].length()) //만약 요리 숫자가 orders[j[보다 크면 조건 만족하니 바로 조합]
                    dfs(orders[j],sb,0,0,course[i]);                   
            }
            
            //가장많이 주문된 횟수 저장
            for(Map.Entry<String,Integer> entry : map.entrySet()){
                    max = Math.max(max,entry.getValue());
                   
            }
            //최소 2번 이상 주문된 것들 담기
            for(Map.Entry<String,Integer> entry : map.entrySet()){
                    if(max >=2 && entry.getValue() == max)
                        answer.add(entry.getKey());
            }
        }
        
        
        //조합들 오름차순 정렬
        Collections.sort(answer);
        
        return answer;
    }
}