import java.util.*;

class Solution {
    Set<Set<Integer>> resultSet = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        dfs(user_id, banned_id, new boolean[user_id.length], 0);
        return resultSet.size();
    }
    
    public void dfs(String[] user_id, String[] banned_id, boolean[] checked, int depth) {
        if (depth == banned_id.length) {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < checked.length; i++) {
                if (checked[i]) set.add(i);
            }
            resultSet.add(set);
            return;
        }
        
        for (int j = 0; j < user_id.length; j++) {
            if (!checked[j] && match(user_id[j], banned_id[depth])) {
                checked[j] = true;
                dfs(user_id, banned_id, checked, depth + 1);
                checked[j] = false;
            }
        }
    }
    
    public boolean match(String userId, String banId) {
        if(userId.length() != banId.length()) return false;
        for(int i = 0 ; i < userId.length(); i++){
            if(banId.charAt(i) == '*') continue;
            if(banId.charAt(i) != userId.charAt(i)) return false;
        }
        return true;
    }
}