class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(int i = 0 ; i < skill_trees.length; i++){
            if(possible(skill_trees[i] , skill)) answer++;
        }
        
        
        return answer;
    }
    
    public boolean possible(String tree , String skill){
        StringBuilder sb = new StringBuilder();
        for(char ch : tree.toCharArray()){
            String temp = Character.toString(ch);
            
            if(skill.contains(temp)){
                sb.append(temp);
            }
            
        }
        /**
        System.out.println(sb.toString());
        if(skill.contains(sb.toString())){
            return true;
        } else{
            return false;
        }
        **/
        
        for(int i = 0 ; i < sb.toString().length(); i++){
            if(!skill.substring(i,i+1).equals(sb.toString().substring(i,i+1))) return false;
        }
        return true;
    }
}