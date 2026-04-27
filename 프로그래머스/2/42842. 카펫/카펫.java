class Solution {
    public int[] solution(int brown, int yellow) {
        for(int h = 1;  h*h<=yellow; h++){
            if(yellow % h != 0) continue;
            
            int w = yellow / h;
            
            //갈색 조건 체크
            if (2 * (w + h) + 4 == brown) {
                return new int[]{w + 2, h + 2};
            }
        }
        
        return new int[]{};
    }
}