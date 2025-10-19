import java.util.*;
import java.io.*;

public class Main{
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        
        String text = st.nextToken();
        
        Map<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i < text.length(); i++) {
            String ch = text.substring(i, i+1).toUpperCase();
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        String maxChar = "";
        int maxCount = 0;
        int maxCountOccurrences = 0;
        
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxChar = entry.getKey();
                maxCountOccurrences = 1; // 새로운 최대값이므로 1로 리셋
            } else if(entry.getValue() == maxCount) {
                maxCountOccurrences++; // 같은 빈도수를 가진 문자 발견
            }
        }
        
        // 최대 빈도수를 가진 문자가 여러 개면 ? 출력
        if(maxCountOccurrences > 1) {
            bw.write("?");
        } else {
            bw.write(maxChar);
        }
        
        bw.flush();
        bw.close();
    }
}