class Solution {
    public int solution(String A, String B) {
        if(A.equals(B)) {
            return 0;
        }
        
        int len = A.length();
        for(int i = 1; i <= len; i++) {
            // 오른쪽으로 한 칸 밀기
            A = A.charAt(len - 1) + A.substring(0, len - 1);
            if(A.equals(B)) {
                return i;
            }
        }
        
        return -1;  // 어떤 경우에도 B와 같아질 수 없는 경우
    }
}
