import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = bfs(begin.length(),begin,target,words);
        return answer;
    }
    
    
    public static int bfs(int N,String begin, String target, String[] words){
        boolean found = false;
        for (String word : words) {
            if (word.equals(target)) {
                found = true;
                break;
            }
        }
        if (!found) return 0;

        
        int count = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];
        
        for (int i = 0; i < words.length; i++) {
            if (diff(begin, words[i])) {
                queue.add(new int[]{i, 1});  // 1은 begin → 첫 단어로 바꾸는 1단계
                visited[i] = true;
            }
        }

        
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();  // [index, depth]
            String currentWord = words[now[0]];

            if (currentWord.equals(target)) return now[1];

            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && diff(currentWord, words[i])) {
                    queue.add(new int[]{i, now[1] + 1});
                    visited[i] = true;
                }
            }
        }

        
        return count;
    }
    
    //정확히 하나만 달라?
    
    public static boolean diff(String a, String b){
        int diffcount = 0;
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) != b.charAt(i)) diffcount++;
            if(diffcount > 1) return false;
        }
        return diffcount==1;
    }
}